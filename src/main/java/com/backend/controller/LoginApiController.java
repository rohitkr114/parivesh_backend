package com.backend.controller;

import com.backend.dto.GuestTokenBodyDto;
import com.backend.resttemplate.SuperSetCall;
import com.backend.service.LoginApiService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loginApi")
public class LoginApiController {

    @Autowired
    private LoginApiService loginApiService;

    @PostMapping("login")
    public ResponseEntity<Object> login(){
        return loginApiService.login();
    }

    @PostMapping("/guestToken")
    public ResponseEntity<String> guestToken(@RequestBody GuestTokenBodyDto guestTokenBodyDto) throws JSONException {
        return loginApiService.getGuestToken(guestTokenBodyDto);
    }
}
