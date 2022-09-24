package com.example.springfilesystem.controler;

import com.example.springfilesystem.service.FileService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("files")
public class ImageController {
    FileService fileService;

    public ImageController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public Long uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return fileService.upload(file.getBytes(), file.getOriginalFilename());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FileSystemResource> downloadFile(@PathVariable Long id) {
        FileSystemResource content = fileService.download(id);
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.valueOf(MediaType.APPLICATION_OCTET_STREAM_VALUE));
        return new ResponseEntity<>(content, respHeaders, HttpStatus.OK);
    }
}
