package com.backend.service;

import com.backend.client.LoginApi;
import com.backend.dto.GuestTokenBodyDto;
import com.backend.dto.LoginDto;
import com.backend.exceptions.PariveshException;
import com.backend.response.ResponseHandler;
import com.backend.resttemplate.RestTemplateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginApiService {

    @Autowired
    private LoginApi loginApi;

    @Autowired
    private RestTemplateService restTemplateService;

    public ResponseEntity<Object> login(){
        try {
        LoginDto loginDto= new LoginDto("P@rivesh@321#","db",false,"admin");

        return ResponseHandler.generateResponse("getting login", HttpStatus.OK,"",loginApi.login(loginDto));
    } catch (Exception e){
            log.info("encountered exception",e);
            throw new PariveshException("error in consuming api",e);
        }
    }

//    public ResponseEntity<Object> guestToken(GuestTokenBodyDto guestTokenBodyDto){
//
//        try{
//            ResourcesDto temp=new ResourcesDto("dashboard", guestTokenBodyDto.getEmbed_id());
//            List<ResourcesDto> resources= new ArrayList<ResourcesDto>();
//            resources.add(temp);
//            List<Object> rls= new ArrayList<Object>();
//            LoginApiUserDto userDto=new LoginApiUserDto("admin","Superset","Admin");
//
//
//            GuestTokenDto guestTokenDto= new GuestTokenDto(userDto,resources,rls);
//             return ResponseHandler.generateResponse("getting guest token",HttpStatus.OK,"",loginApi.guestToken(guestTokenDto));
//        } catch (Exception e){
//            log.info("encountered exception",e);
//            throw new PariveshException("error in consuming api",e);
//        }
//    }


    public ResponseEntity<String> getGuestToken(GuestTokenBodyDto guestTokenBodyDto){
        String url="https://stgapm.parivesh.nic.in/api/v1/security/guest_token/";
//        String body="{\"user\": {\"username\": \"admin\",\"first_name\":\"Superset\",\"last_name\":\"Admin\"},\"resources\": [{\"type\": \"dashboard\",\"id\": \"81b15fb8-a576-49d7-b9a3-f7828d2efac2\"}],\"rls\": []}";
          String body="{\"user\": {\"username\": \"admin\",\"first_name\":\"Superset\",\"last_name\":\"Admin\"},\"resources\": [{\"type\": \"dashboard\",\"id\": \""+guestTokenBodyDto.getEmbed_id()+"\"}],\"rls\": []}";

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setBearerAuth(guestTokenBodyDto.getToken());

        HttpEntity<String> request = new HttpEntity<String>(body, headers);

        ResponseEntity<String> stringResponse = restTemplateService.sendRequest(url, HttpMethod.POST, request, String.class, null);

        return stringResponse;
    }


    public static String toJsonString(Object data) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json;
        try {
            json = ow.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            json = null;
        }
        return json;
    }
}
