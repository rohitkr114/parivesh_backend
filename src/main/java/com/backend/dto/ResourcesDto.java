package com.backend.dto;

import lombok.Data;

@Data
public class ResourcesDto {

    String type;

    String id;

    public ResourcesDto(String type, String id) {
        this.type = type;
        this.id = id;
    }
}
