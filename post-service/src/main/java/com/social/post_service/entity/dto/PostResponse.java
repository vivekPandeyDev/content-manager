package com.social.post_service.entity.dto;

import lombok.Data;

import javax.xml.stream.events.Comment;
import java.util.List;

@Data
public class PostResponse {
    private String description;
    private Integer likeCount;
    private List<String> postUrls;
    private String listOfTags;
    private List<Comment> comments;
}
