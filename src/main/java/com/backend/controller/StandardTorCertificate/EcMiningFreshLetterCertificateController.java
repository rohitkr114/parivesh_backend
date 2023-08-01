package com.backend.controller.StandardTorCertificate;

import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.EcMiningFreshLetterCertificate;
import com.backend.response.ResponseHandler;
import com.backend.security.CurrentUser;
import com.backend.service.StandardTORCertificate.EcMiningFreshLetterCertificateService;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/ecMiningFreshLetterCertificate")
public class EcMiningFreshLetterCertificateController {

    @Autowired
    EcMiningFreshLetterCertificateService ecMiningFreshLetterCertificateService;

    @PostMapping("/saveEcMiningFreshLetterCertificate")
    public ResponseEntity<Object> saveEcMiningFreshLetterCertificate(@CurrentUser UserPrincipal principal, @RequestBody EcMiningFreshLetterCertificate ecMiningFreshLetterCertificate,
                                                                     HttpServletRequest request) throws PariveshException, DocumentException, IOException {

        return ResponseHandler.generateResponse("Save EcMiningFreshLetterCertificate ", HttpStatus.OK, "",
                ecMiningFreshLetterCertificateService.saveEcMiningFreshLetterCertificate(principal,ecMiningFreshLetterCertificate, request));

    }

    @GetMapping("/getEcMiningFreshLetterCertByPropId")
    public ResponseEntity<Object> getEcMiningFreshLetterCertificateDetails(@RequestParam int propId,
                                                         @RequestParam("proposal_no") String proposalNo) throws PariveshException {
        log.info("get: /ecMiningFreshLetterCertificate/getEcMiningFreshLetterCertByPropId?propId="+propId+"&proposalNo="+proposalNo);
        return ResponseHandler.generateResponse("get EcMiningFreshLetterCertificate Detail", HttpStatus.OK, "",
                ecMiningFreshLetterCertificateService.getStandTorDetailByPropId(propId, proposalNo ));
    }

    @GetMapping("/getIdentificationNo")
    public ResponseEntity<Object> getIdentificationNoByPropId (@RequestParam int propId) {
        return ResponseHandler.generateResponse("get EcMiningFreshLetterCertificate", HttpStatus.OK, "",
                ecMiningFreshLetterCertificateService.getIdentificationNo(propId));
    }
}
