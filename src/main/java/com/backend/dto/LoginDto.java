package com.backend.dto;

import lombok.Data;
import org.apache.xpath.operations.Bool;

@Data
public class LoginDto {

    String password;

    String provider;

    Boolean refresh;

    String username;

    public LoginDto(String password, String provider, Boolean refresh, String userName) {
        this.password = password;
        this.provider = provider;
        this.refresh = refresh;
        this.username = userName;
    }
}
