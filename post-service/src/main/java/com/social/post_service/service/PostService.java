package com.social.post_service.service;

import com.social.post_service.entity.PostComment;
import com.social.post_service.entity.dto.CommentDto;
import com.social.post_service.entity.dto.PostDto;
import com.social.post_service.entity.dto.PostResponse;
import com.social.post_service.entity.dto.ReplyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    void uploadPost(List<MultipartFile> files, String userName) throws IOException;
    ResponseEntity<String> upload(List<MultipartFile> files, String userName) throws IOException;
    List<PostResponse> getAllPostByUserName(String userName, int pageSize, int content);

    boolean savePost(PostDto postDto);

//    void addCommentInPost(CommentDto commentDto);
//
//    List<PostComment> getPostCommentByPostId(CommentDto commentDto, int pageSize, int size);
//
//    void addReply(ReplyDto replyDto);
//
//    List<ReplyDto> replyDtoList(ReplyDto replyDto, int pageSize, int size);
}
