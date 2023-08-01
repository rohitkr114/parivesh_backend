package com.backend.NSWS.requestbody;

import lombok.Data;

@Data
public class GenerateTokenRequestBody {

    private String username;

    private String password;

}
