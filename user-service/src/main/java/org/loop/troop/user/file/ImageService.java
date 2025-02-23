package org.loop.troop.user.file;

import jakarta.validation.constraints.NotNull;
import org.loop.troop.user.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
public class ImageService implements FileService {

    @Override
    public String save(String uploadDir, MultipartFile file, String fileName) throws IOException {
        Path path = getFolderPath(uploadDir);
        String imageName = getFilenameWithExtension(fileName, file);
        Files.copy(file.getInputStream(), path.resolve(imageName), StandardCopyOption.REPLACE_EXISTING);
        return getFilenameWithExtension(imageName);
    }

    @Override
    public Path getFolderPath(String location)  {
        String currentDir = System.getProperty("user.dir");
        //creating dir if not exists
        File newDir = new File(currentDir, location);
        if (!newDir.exists()) {
            boolean isCreated = newDir.mkdir();
            if (isCreated) {
                log.info("Directory created: " + newDir.getAbsolutePath());
            } else {
                log.info("Failed to create directory: " + newDir.getAbsolutePath());
                throw new ServiceException("Cannot create file dir: {}",currentDir);
            }
        }

        return newDir.toPath();
    }

    @Override
    public byte[] getResource(Path path,String fileName) throws IOException {
        return Files.readAllBytes(path.resolve(fileName));
    }

    private String getFilenameWithExtension(String fileName){
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be null or empty");
        }
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            return fileName;
        } else {
            return fileName.substring(0, dotIndex);
        }
    }

    private String getFilenameWithExtension(String newName, MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        assert originalFilename != null;
        int dotIndex = originalFilename.lastIndexOf('.');
        if (dotIndex >= 0) {
            extension = originalFilename.substring(dotIndex);
        }
        return newName + extension;
    }

}
