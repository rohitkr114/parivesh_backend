package com.backend.NSWS.external.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class NswsSsoTokenClient {

    @Value("${nsws.grant_type}")
    private String grant_type;

    @Value("${nsws.client_id}")
    private String client_id;

    @Value("${nsws.username}")
    private String username;

    @Value("${nsws.password}")
    private String password;

    @Value("${nsws.client_secret}")
    private String client_secret;

    @Value("${nsws.tokenUrl}")
    private String nswsTokenUrl;

    public ResponseEntity<String> createSsoToken() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", grant_type);
        params.add("client_id", client_id);
        params.add("username", username);
        params.add("password", password);
        params.add("client_secret", client_secret);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(nswsTokenUrl, HttpMethod.POST, requestEntity, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String response = responseEntity.getBody();
            log.info("NSWSSSOTOKENRESTTEMPLATE::RESPONSE{}", response);
        } else {
            System.out.println("Request failed with status code: " + responseEntity.getStatusCodeValue());
        }

        return responseEntity;
    }
}
