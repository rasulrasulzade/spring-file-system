package com.example.springfilesystem.repository;

import com.example.springfilesystem.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileEntity, Long> {
}
