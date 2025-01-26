package com.social.post_service.entity.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ReplyDto {
    private String commentText;
    private Integer likeCount;
    private UUID commentId;
    private String userName;
}
