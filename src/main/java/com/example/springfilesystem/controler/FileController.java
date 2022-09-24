package com.example.springfilesystem.controler;

import com.example.springfilesystem.model.response.GetFilesResponse;
import com.example.springfilesystem.model.response.UploadFileResponse;
import com.example.springfilesystem.service.FileService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("files")
public class FileController {
    FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return new UploadFileResponse(fileService.upload(file));
    }

    @GetMapping
    public GetFilesResponse getFiles(){
        return fileService.getFiles();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FileSystemResource> downloadFile(@PathVariable Long id) {
        FileSystemResource content = fileService.download(id);
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.valueOf(MediaType.APPLICATION_OCTET_STREAM_VALUE));
        return new ResponseEntity<>(content, respHeaders, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteFile(@PathVariable Long id){
        fileService.deleteFile(id);
    }
}
