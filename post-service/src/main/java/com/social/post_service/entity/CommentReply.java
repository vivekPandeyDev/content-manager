package com.social.post_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "comments_reply")
@Getter
@Setter
@NoArgsConstructor
public class CommentReply {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Lob
    @Column(nullable = false, name = "comment_value")
    private String commentValue;

    @Column
    private Integer likeCount;

    @ManyToOne
    @JsonBackReference
    private PostComment postComment;

    private String userName;
}
