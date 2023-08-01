package com.backend.model.upload;

import lombok.Data;

@Data
public class FileUploadResponse {
    private String fileName;
    private String downloadUrl;
    private long size;
}
