package com.backend.resttemplate;

import com.backend.NSWS.requestbody.PushLicenseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TestCall {

    static RestTemplateService restTemplateService = new RestTemplateService();

    public static void main(String[] args) {
        String url = "https://uat-nsws-mnstrportal.investindia.gov.in/nsws_license/pushLincenceDetails";

        PushLicenseBody body = new PushLicenseBody();
        List<PushLicenseBody> body1 = new ArrayList<>();
        body.setLicenseId("M001_D001_A002");
        body.setLicenseVer(1);
        body.setLicenseReqDate("1612426700677");
        body.setSwsId("SW1436841810");
        body.setInvestorReqId(70419);
        body.setMinisteryId("M001");
        body.setDepartmentId("M001_D001");
        body1.add(body);
        log.info("requestBody", body.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("access-id", "MIN_TEST_0");
        headers.set("access-secret", "MintesT@1234");
        headers.set("api-key", "Min1@PLD11");

        HttpEntity<Object> request = new HttpEntity<Object>(body1, headers);
        ResponseEntity<String> stringResponse = restTemplateService.sendRequest(url, HttpMethod.POST, request,
                String.class, null);
        log.info("Response : {}", stringResponse);
    }

}