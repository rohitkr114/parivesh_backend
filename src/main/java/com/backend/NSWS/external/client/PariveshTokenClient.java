package com.backend.NSWS.external.client;

import com.backend.NSWS.external.request.AuthenticationRequest;
import com.backend.NSWS.external.response.AuthenticationResponse;
import com.backend.util.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "uaDev", url = "${feign.ua.url}", configuration = FeignConfig.class)
public interface PariveshTokenClient {

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest);

}
