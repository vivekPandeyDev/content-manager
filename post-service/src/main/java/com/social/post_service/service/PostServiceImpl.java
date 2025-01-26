package com.social.post_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostServiceImpl {

//    @Value("${file.upload.location}")
//    private String postServerLocation;
//    private final FileService fileService;
//
//    private final PostRepository postRepository;
//
//    private final CommentRepository commentRepository;
//
//    private final ReplyRepository replyRepository;
//
//    private final ModelMapper modelMapper;
//
//    private final JobLauncher jobLauncher;
//    private final Job multiFileJob;
//
//
//    public void uploadPost(List<MultipartFile> files, String userName) throws IOException {
//        String path = postServerLocation + "/" + userName + "/" + System.currentTimeMillis();
//        log.info("userName and path {}, {}", userName, path);
//        log.info("files {}", files);
//        List<String> uploadedFiles = files.stream().map(file -> {
//            try {
//                return fileService.save(path, file, file.getName());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }).toList();
//
//        log.info(uploadedFiles);
//        if (uploadedFiles.size() == files.size()) {
//            log.error("All files uploaded successfully");
//        } else {
//            log.error("Some files failed to upload. Successfully uploaded files: " + uploadedFiles);
//        }
//    }
//
//    public void uploadPost(List<MultipartFile> files, PostDto postDto) throws IOException {
//        String path = postServerLocation + "/" + postDto.getUserName() + "/" + System.currentTimeMillis();
//        log.info("userName and path {}, {}", postDto.getUserName(), path);
//        log.info("files {}", files);
//        List<String> uploadedFiles = files.stream().map(file -> {
//            try {
//                return fileService.save(path, file, file.getName());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }).toList();
//
//        log.info(uploadedFiles);
//        if (uploadedFiles.size() == files.size()) {
//            log.error("All files uploaded successfully");
//        } else {
//            log.error("Some files failed to upload. Successfully uploaded files: " + uploadedFiles);
//        }
//
//        savePost(postDto, path);
//    }
//
//    @Override
//    public ResponseEntity<String> upload(List<MultipartFile> files, String userName) throws IOException {
//        for (MultipartFile file : files) {
//            try {
//                JobParameters jobParameters = new JobParametersBuilder()
//                        .toJobParameters();
//
//                jobLauncher.run(multiFileJob, jobParameters);
//            } catch (Exception e) {
//                return ResponseEntity.status(500).body("Failed to process file: " + file.getOriginalFilename());
//            }
//        }
//        return ResponseEntity.ok("Files uploaded and processing started successfully");
//    }
//
//    public boolean savePost(PostDto postDto) {
//        Post post = new Post();
//        modelMapper.map(postDto, post);
//
//        postRepository.save(post);
//        return true;
//    }
//
//    public boolean savePost(PostDto postDto, String urlPath) {
//        Post post = new Post();
//        modelMapper.map(postDto, post);
//        post.setPostUrl(urlPath);
//
//        return true;
//    }
//
//    @Override
//    public List<PostResponse> getAllPostByUserName(String userName, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        List<Post> postList = postRepository.findByUserName(userName, pageable);
//        List<PostResponse> postResponses = new ArrayList<>();
//
//        for (Post post : postList) {
//            PostResponse postResponse = new PostResponse();
//            postResponse.setPostUrls(fileService.getPostUrls(post.getPostUrl()));
//            modelMapper.map(post, postResponse);
//
//            postResponses.add(postResponse);
//        }
//
//        return postResponses;
//    }
//
//    public void addCommentInPost(CommentDto commentDto) {
//        Post post = postRepository.findById(commentDto.getPostId()).orElseThrow(() -> new RuntimeException("Invalid Post Id"));
//
//        PostComment postComment = new PostComment();
//        modelMapper.map(commentDto, postComment);
//        postComment.setCommentValue(commentDto.getCommentText());
//        postComment.setPost(post);
//
//        commentRepository.save(postComment);
//    }
//
//    public List<PostComment> getPostCommentByPostId(CommentDto commentDto, int pageSize, int size) {
//        Pageable pageable = PageRequest.of(pageSize, size);
//        List<PostComment> comments = commentRepository.findByPost_Id(commentDto.getPostId(), pageable);
//
//        for (PostComment postComment : comments) {
//            Pageable replyPageable = PageRequest.of(pageSize, size);
//            postComment.setReplies(replyRepository.findByPostComment_Id(postComment.getId(), replyPageable));
//        }
//
//        return comments;
//    }
//
//    public void addReply(ReplyDto replyDto) {
//        PostComment postComment = commentRepository.findById(replyDto.getCommentId()).orElseThrow(() -> new RuntimeException(""));
//        CommentReply reply = new CommentReply();
//        reply.setPostComment(postComment);
//        reply.setCommentValue(replyDto.getCommentText());
//        reply.setLikeCount(0);
//
//        replyRepository.save(reply);
//    }
//
//    public List<ReplyDto> replyDtoList(ReplyDto replyDto, int pageSize, int size) {
//        Pageable pageable = PageRequest.of(pageSize, size);
//        List<CommentReply> replies = replyRepository.findByPostComment_Id(replyDto.getCommentId(), pageable);
//
//        List<ReplyDto> replyDtos = new ArrayList<>();
//        for (CommentReply reply : replies) {
//            ReplyDto replyDto1 = new ReplyDto();
//            modelMapper.map(reply, replyDto1);
//
//            replyDtos.add(replyDto1);
//        }
//
//        return replyDtos;
//    }
}
