package com.example.springfilesystem.repository;

import com.example.springfilesystem.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Repository
@RequiredArgsConstructor
public class FileSystemRepository {
    private final FileUtil fileUtil;
    private static final String SOURCE_DIR = "./uploads/";


    public String save(byte[] content, String fileName) throws IOException {
        String[] fileParts = fileUtil.getFileParts(fileName);
        Path newFile = Path.of(SOURCE_DIR + fileParts[0] + "-" + new Date().getTime() + "." + fileParts[1]);
        Files.createDirectories(newFile.getParent());
        Files.write(newFile, content);
        return newFile.toAbsolutePath().toString();
    }

    public FileSystemResource find(String location) {
        return new FileSystemResource(Paths.get(location));
    }
}
