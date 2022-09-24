package com.example.springfilesystem.repository;

import com.example.springfilesystem.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDBRepository  extends JpaRepository<Image, Long> {
}
