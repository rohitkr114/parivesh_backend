package com.backend.NSWS.responsebody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneratetokenResponseBody {

    private String username;

    private String jwt;
}
