package com.social.post_service.entity.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CommentDto {
    private String commentText;
    private Integer likeCount;
    private UUID postId;
    private String userName;
}
