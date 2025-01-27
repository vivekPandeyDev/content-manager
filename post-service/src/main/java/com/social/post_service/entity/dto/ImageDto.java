package com.social.post_service.entity.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ImageDto {
    String fileName;
    String description;
    String category;
    List<MultipartFile> fileList;
}
