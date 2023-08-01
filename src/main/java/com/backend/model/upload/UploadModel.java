package com.backend.model.upload;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class UploadModel {
    public String fileName;
    public String path;
    public Map<String, String> folderParam = new HashMap<>();
}
