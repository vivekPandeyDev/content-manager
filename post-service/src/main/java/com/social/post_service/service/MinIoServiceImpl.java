package com.social.post_service.service;

import com.social.post_service.repository.MetaDataRepository;
import com.social.post_service.response.ApiError;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Log4j2
public class MinIoServiceImpl implements MinIoService {
    private MinioClient minioClient;
    private MinioAsyncClient minioAsyncClient;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Autowired
    private MetaDataRepository metaDataRepository;

    @PostConstruct
    public void init() {
        minioClient = MinioClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();
        minioAsyncClient = MinioAsyncClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();
    }

    @Async
    @SneakyThrows
    public void uploadFile(List<MultipartFile> files, String userName) {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(this.bucketName).build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(this.bucketName).build());
        }

        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String contentType = file.getContentType();
            InputStream inputStream = file.getInputStream();
            try {
                minioClient.putObject(PutObjectArgs.builder().bucket(bucketName)
                        .object(userName + "/" + fileName)
                        .stream(inputStream, inputStream.available(), -1)
                        .contentType(contentType)
                        .build());

                minioClient.putObject(PutObjectArgs.builder().bucket(bucketName)
                        .object(userName + "/" + fileName)
                        .stream(inputStream, inputStream.available(), 5 * 1024 * 1024)
                        .contentType(contentType)
                        .build());

                minioAsyncClient.putObject(PutObjectArgs.builder().bucket(bucketName)
                                .object(userName + "/" + fileName)
                                .stream(inputStream, inputStream.available(), -1)
                                .contentType(contentType)
                                .build())
                        .whenComplete((success, exception) -> {
                            if (exception != null) {
                                System.out.println("Error during upload: " + exception);
                            } else {
                                System.out.println("Upload completed successfully!");
                            }
                        });

            } catch (Exception e) {
                throw new RuntimeException("Error occurred: " + e.getMessage());
            }
        }
    }

    public List<Item> getAllObjectsByPrefix(String prefix, boolean recursive) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        List<Item> list = new ArrayList<>();
        Iterable<Result<Item>> objectsIterator = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName)
                .prefix(prefix)
                .recursive(recursive)
                .build());

        if (objectsIterator != null) {
            for (Result<Item> o : objectsIterator) {
                Item item = o.get();
                list.add(item);
            }
        }
        return list;
    }

    public String getObject(String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .expiry(2, TimeUnit.MINUTES)
                .method(Method.GET)
                .build());
    }

    public List<String> getObjectsURL(List<MultipartFile> files, String userName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
//        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(this.bucketName).build());
//        if (!found) {
//            throw new NoBucketExistsException;
//        }

        List<String> filesList = new LinkedList<>();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String contentType = file.getContentType();

            String url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(this.bucketName)
                    .object(userName + " / " + fileName)
//                    .expiry(24 * 60 * 60, TimeUnit.DAYS)
                    .build());

            filesList.add(url);
        }

        return filesList;
    }

    public InputStream getObject(String objectName, long offset, long length) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).offset(offset).length(length).build());
    }

    public void removeFile(String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

    public void removeFiles(List<String> keys) {
        List<DeleteObject> objects = new LinkedList<>();
        keys.forEach(s -> {
            objects.add(new DeleteObject(s));
            try {
                removeFile(s);
            } catch (Exception e) {
                log.error("[MinioUtil]>>>>batch delete file，Exception：", e);
            }
        });
    }
}
