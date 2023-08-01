package com.backend.NSWS.external.client;

import com.backend.NSWS.external.request.RedirectUrlRequest;
import com.backend.NSWS.model.NSWSUserDetails;
import com.backend.model.Applications;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class NswsRedirectionClient {

    @Value("${nsws.redirectionUrl}")
    private String nswsRedirectionUrl;

    @Value("${parivesh.redirectionUrl}")
    private String pariveshRedirectionUrl;


    public ResponseEntity<String> nswsRedirectUrl(String token, NSWSUserDetails pingBody, Applications applications) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        RedirectUrlRequest requestBody = new RedirectUrlRequest();

        String pariveshUrl = pariveshRedirectionUrl.replace("investorId", pingBody.getInvestorReqId().toString()).replace("swsId", pingBody.getInvestorSWSId()).replace("licenceId", pingBody.getApprovalId());

        requestBody.setDepartmentId(applications.getDepartmentId());
        requestBody.setLicenseId(pingBody.getApprovalId());
        requestBody.setRedirectionUrl(pariveshUrl);
        requestBody.setStateId(pingBody.getState_ut());
        requestBody.setSwsId(pingBody.getInvestorSWSId());
        requestBody.setMinistryId(applications.getMinistryId());
        requestBody.setProjectId(pingBody.getProjectId());
        requestBody.setInvestorReqId(pingBody.getInvestorReqId());

        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);

        log.info("NSWSREDIRECTIONCLIENT::NSWSREDIRECTURL::REQUESTBODY {} HEADERS {}", requestBody, headers.toString());
        ResponseEntity<String> responseEntity = restTemplate.exchange(nswsRedirectionUrl, HttpMethod.POST, requestEntity, String.class);

        return responseEntity;

    }
}
