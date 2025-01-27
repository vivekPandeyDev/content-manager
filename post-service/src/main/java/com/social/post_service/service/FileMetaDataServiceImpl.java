package com.social.post_service.service;

import com.social.post_service.entity.FileMetaData;
import com.social.post_service.repository.MetaDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class FileMetaDataServiceImpl implements FileMetaDataService{

    @Autowired
    private MetaDataRepository metaDataRepository;

    public void saveFileMetaData(MultipartFile file, String userName){
        FileMetaData fileMetaData = new FileMetaData();
        fileMetaData.setUsername(userName);
        fileMetaData.setFileName(file.getOriginalFilename());
        fileMetaData.setFileSize(file.getSize());
        fileMetaData.setContentType(file.getContentType());

        metaDataRepository.save(fileMetaData);
    }


}
