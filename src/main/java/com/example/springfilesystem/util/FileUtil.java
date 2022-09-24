package com.example.springfilesystem.util;

public class FileUtil {
    public String[] getFileParts(String file) {
        String[] arr = {"", ""};
        if (!file.contains(".")) return arr;
        int lastPintIndex = file.lastIndexOf(".");
        arr[0] = file.substring(0, lastPintIndex);
        arr[1] = file.substring(lastPintIndex + 1);
        return arr;
    }

}
