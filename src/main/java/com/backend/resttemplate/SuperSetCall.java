package com.backend.resttemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class SuperSetCall
{

    static RestTemplateService restTemplateService=new RestTemplateService();
    
    public static ResponseEntity<String> callSuperSet(String token) {

        String url="https://stgapm.parivesh.nic.in/api/v1/security/guest_token/";

        String body="{\"user\": {\"username\": \"admin\",\"first_name\":\"Superset\",\"last_name\":\"Admin\"},\"resources\": [{\"type\": \"dashboard\",\"id\": \"81b15fb8-a576-49d7-b9a3-f7828d2efac2\"}],\"rls\": [{\"clause\": \"publisher = 'Admin'\"}]}";

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setBearerAuth(token);



        HttpEntity<String> request = new HttpEntity<String>(body, headers);

        ResponseEntity<String> stringResponse = restTemplateService.sendRequest(url, HttpMethod.POST, request, String.class, null);

        System.out.println("token:" + stringResponse);
        
        return stringResponse;

    }
}
