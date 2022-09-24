package com.example.springfilesystem.service;

import com.example.springfilesystem.entity.FileEntity;
import com.example.springfilesystem.model.FileItem;
import com.example.springfilesystem.model.response.GetFilesResponse;
import com.example.springfilesystem.repository.FileSystemRepository;
import com.example.springfilesystem.repository.FileDBRepository;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {
    FileSystemRepository fileSystemRepository;
    FileDBRepository fileDBRepository;

    public FileService(FileSystemRepository fileSystemRepository, FileDBRepository fileDBRepository) {
        this.fileSystemRepository = fileSystemRepository;
        this.fileDBRepository = fileDBRepository;
    }

    public Long upload(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String fileName = file.getOriginalFilename();
        String location = null;
        try {
            location = fileSystemRepository.save(bytes, fileName);
        } catch (IOException e) {
            throw new RuntimeException();
        }
        FileEntity entity = new FileEntity();
        entity.setName(fileName);
        entity.setLocation(location);
        entity.setSize(file.getSize());
        return fileDBRepository.save(entity).getId();
    }


    public GetFilesResponse getFiles() {
        List<FileItem> fileItems = fileDBRepository.findAll().stream()
                .map(e -> new FileItem(e.getId(), e.getName(), e.getSize()))
                .collect(Collectors.toList());
        return new GetFilesResponse(fileItems);
    }

    public FileSystemResource download(Long id) {
        FileEntity file = fileDBRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return fileSystemRepository.find(file.getLocation());
    }

    public void deleteFile(Long id) {
        String location = fileDBRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getLocation();
        fileDBRepository.deleteById(id);
        try {
            fileSystemRepository.delete(location);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
