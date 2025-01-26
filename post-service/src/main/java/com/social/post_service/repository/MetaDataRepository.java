package com.social.post_service.repository;

import com.social.post_service.entity.FileMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MetaDataRepository extends JpaRepository<FileMetaData, UUID> {

}
