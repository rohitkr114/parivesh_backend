package com.backend.NSWS.controller;

import com.backend.NSWS.requestbody.GenerateTokenRequestBody;
import com.backend.NSWS.requestbody.PingRequestBody;
import com.backend.NSWS.requestbody.PushLicenseBody;
import com.backend.NSWS.service.PariveshSWSService;
import com.backend.exceptions.PariveshException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/PariveshSWS")
public class PariveshSWSController {

    @Autowired
    private PariveshSWSService pariveshSWSService;

    @PostMapping("/generateToken")
    public ResponseEntity<Object> generateToken(@RequestBody GenerateTokenRequestBody tokenRequestBody) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        return pariveshSWSService.generateToken(tokenRequestBody);
    }

    @PostMapping("/ping")
    public ResponseEntity<Object> pariveshPing(@RequestBody PingRequestBody pingBody, HttpServletRequest request) throws PariveshException, JSONException {

        return pariveshSWSService.pariveshPing(pingBody, request);
    }

    @PostMapping("/pushLicenseApi")
    public ResponseEntity<Object> pushLicenseApi(@RequestBody List<PushLicenseBody> pushLicenseBody) throws PariveshException {

        return pariveshSWSService.pushLicenseApi(pushLicenseBody);
    }


}
