package com.example.springfilesystem.model.response;

import com.example.springfilesystem.model.FileItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetFilesResponse {
    private List<FileItem> files;
}
