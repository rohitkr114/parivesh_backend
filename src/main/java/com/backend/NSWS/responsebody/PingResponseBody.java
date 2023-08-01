package com.backend.NSWS.responsebody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PingResponseBody {
    private String email;
    private String InvestorSWSId;
}
