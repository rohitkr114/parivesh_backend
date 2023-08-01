package com.backend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.backend.parivesh.nic.UserRegistration;
import com.backend.util.FeignConfig;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@FeignClient(value = "registration", url = "${feign.userregistration.url}", configuration = FeignConfig.class)
public interface UserRegistrationAPI {

	@RequestMapping(method = RequestMethod.POST, value = "registration",consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> saveUserRegistration(@RequestBody UserRegistration userRegistration);
}
