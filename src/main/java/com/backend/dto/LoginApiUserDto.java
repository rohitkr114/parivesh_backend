package com.backend.dto;

import lombok.Data;

@Data
public class LoginApiUserDto {

    String username;

    String first_name;

    String last_name;

    public LoginApiUserDto(String username, String first_name, String last_name) {
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
    }
}
