package com.backend.client;

import com.backend.dto.GuestTokenDto;
import com.backend.dto.LoginDto;
import com.backend.util.FeignConfig;
import feign.Headers;
import feign.Param;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "login",url = "${feign.security.login.url}",configuration = FeignConfig.class)
public interface LoginApi {

    @RequestMapping(method = RequestMethod.POST,value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> login(@RequestBody LoginDto loginDto);

    @RequestMapping(method = RequestMethod.POST,value = "/guest_token",consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> guestToken(@RequestHeader(value = "Authorization") String token, @RequestBody GuestTokenDto guestTokenDto);
}
