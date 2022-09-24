package com.example.springfilesystem.service;

import com.example.springfilesystem.entity.Image;
import com.example.springfilesystem.repository.FileSystemRepository;
import com.example.springfilesystem.repository.ImageDBRepository;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
public class FileService {
    FileSystemRepository fileSystemRepository;
    ImageDBRepository imageDBRepository;

    public FileService(FileSystemRepository fileSystemRepository, ImageDBRepository imageDBRepository) {
        this.fileSystemRepository = fileSystemRepository;
        this.imageDBRepository = imageDBRepository;
    }

    public Long upload(byte[] bytes, String fileName) {
        String location = null;
        try {
            location = fileSystemRepository.save(bytes, fileName);
        } catch (IOException e) {
            throw  new RuntimeException();
        }
        return imageDBRepository.save(new Image(fileName, location)).getId();
    }

    public FileSystemResource download(Long id) {
        Image image = imageDBRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return fileSystemRepository.find(image.getLocation());
    }
}
