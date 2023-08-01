package com.backend.NSWS.external.client;

import com.backend.NSWS.requestbody.PushLicenseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
public class NswsPushLicenseClient {

    @Value("${nsws.pushLicenseUrl}")
    private String pushLicenseUrl;

    @Value("${nsws.access_id}")
    private String access_id;

    @Value("${nsws.access_secret}")
    private String access_secret;

    @Value("${nsws.api_key}")
    private String api_key;

    public ResponseEntity<String> nswsPushLicense(List<PushLicenseBody> pushLicenseBody) {

        String url = pushLicenseUrl;
//        String accessToken = "";

        HttpHeaders headers = new HttpHeaders();
        headers.set("access-id", access_id);
        headers.set("access-secret", access_secret);
        headers.set("api-key", api_key);
//        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> requestEntity = new HttpEntity<>(pushLicenseBody, headers);

        RestTemplate restTemplate = new RestTemplate();

        log.info("NSWSPUSHLICENSECLIENT::NSWSPUSHLICENSE::REQUESTBODY {} HEADERS {}", pushLicenseBody, headers.toString());
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        return responseEntity;

    }
}
