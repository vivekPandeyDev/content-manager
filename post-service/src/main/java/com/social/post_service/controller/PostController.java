package com.social.post_service.controller;

import com.social.post_service.entity.dto.ImageDto;
import com.social.post_service.entity.dto.PostDto;
import com.social.post_service.response.ApiResponse;
import com.social.post_service.service.MinIoService;
import io.minio.errors.*;
import io.minio.messages.Item;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
@CrossOrigin(origins = "*", originPatterns = {"*"}, allowedHeaders = {"*"})
public class PostController {

    private final MinIoService minIoService;

    @PostMapping("hello")
    public String get() {
        System.out.println("hello");
        return "hello";
    }

    @PostMapping(value = "/uploadFiles", consumes = "multipart/form-data")
    public ApiResponse<PostDto> uploadPost(HttpServletRequest servletRequest, @RequestPart List<MultipartFile> files, @RequestPart ImageDto imageDto) throws IOException, MinioException {
        imageDto.setFileList(files);
        minIoService.uploadFile(imageDto.getFileList(), servletRequest.getHeader("userName"));
        return new ApiResponse<PostDto>(true, null, "Post Created Successfully");
    }

    @GetMapping("/getAllFilesFromUserName")
    public ApiResponse<List<Item>> getAllFiles(HttpServletRequest httpServletRequest) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        List<Item> list = minIoService.getAllObjectsByPrefix(httpServletRequest.getHeader("userName"), true);
        Map<String, List<Item>> listMap = new HashMap<>();
        listMap.put("files", list);
        return new ApiResponse<>(true, listMap, "All Files");
    }

    @GetMapping("/getFile")
    public ApiResponse<String> getFile(HttpServletRequest httpServletRequest, @RequestParam("fileName") String fileName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Map<String, String> listMap = new HashMap<>();
        listMap.put("file", minIoService.getObject(fileName));
        return new ApiResponse<>(true, listMap, "All Files");
    }

//    @GetMapping
//    public ApiResponse<> viewPosts(HttpServletRequest httpServletRequest){
//
//    }
//
//    @GetMapping("/getAllPostByUserName")
//    public ApiResponse<List<PostResponse>> getAllPostByUsername(@RequestParam("username") String userName, @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
//        return new ApiResponse<>(true, Map.of("allPost", postService.getAllPostByUserName(userName, page, pageSize)), "All Post of User");
//    }

//    @PostMapping("/addComment")
//    public ApiResponse<String> addCommentInPost(@RequestBody CommentDto commentDto){
//        postService.addCommentInPost(commentDto);
//        return new ApiResponse<String>(true, Map.of("postResponse", "comment added"), "Post Created Successfully");
//    }
//
//    @GetMapping("/getCommentPost")
//    public ApiResponse<List<PostComment>> getCommentPost(@RequestBody CommentDto commentDto, @RequestParam("pageSize") int pageSize, @RequestParam("size") int size){
//        List<PostComment> postComments = postService.getPostCommentByPostId(commentDto,pageSize,size);
//        return new ApiResponse<>(true, Map.of("response", postComments), "AlL Comments");
//    }
//
//    @PostMapping("/addReply")
//    public ApiResponse<String> addReplies(@RequestBody ReplyDto commentDto){
//        postService.addReply(commentDto);
//        return new ApiResponse<>(true, Map.of("replyResponse", ""), "replies");
//    }
//
//    @GetMapping("/getReplies")
//    public ApiResponse<List<ReplyDto>> getReplies(@RequestBody ReplyDto replyDto, @RequestParam("page") int pageSize, @RequestParam("size") int size){
//        List<ReplyDto> replyDtos = postService.replyDtoList(replyDto, pageSize, size);
//        return new ApiResponse<>(true, Map.of("replies", replyDtos), "reply");
//    }
}
