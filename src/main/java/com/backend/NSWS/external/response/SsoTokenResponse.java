package com.backend.NSWS.external.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SsoTokenResponse {

    private String access_token;
    private String expires_in;
    private String refresh_expires_in;
    private String refresh_token;
    private String token_type;
    private String not_before_policy;
    private String session_state;
    private String scope;

}
