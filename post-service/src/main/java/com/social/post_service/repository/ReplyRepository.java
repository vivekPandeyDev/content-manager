package com.social.post_service.repository;

import com.social.post_service.entity.CommentReply;
import jakarta.validation.ReportAsSingleViolation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReplyRepository extends JpaRepository<CommentReply, UUID> {
    List<CommentReply> findByPostComment_Id(UUID id, Pageable pageable);

}
