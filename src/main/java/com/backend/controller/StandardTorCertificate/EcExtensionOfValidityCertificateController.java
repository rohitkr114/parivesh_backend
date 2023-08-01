package com.backend.controller.StandardTorCertificate;


import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.EcExtensionOfValidityCertificate;
import com.backend.model.StandardTORCertificate.EcFreshLetterTemplateCertificate;
import com.backend.response.ResponseHandler;
import com.backend.security.CurrentUser;
import com.backend.service.StandardTORCertificate.EcExtensionOfValidityCertificateService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/ecExtensionOfValidityCertificate")
public class EcExtensionOfValidityCertificateController {

    @Autowired
    EcExtensionOfValidityCertificateService ecExtensionOfValidityCertificateService;

    @PostMapping("/saveEcExtensionOfValidityCertificate")
    public ResponseEntity<Object> saveEcExtensionOfValidityCertificate(@CurrentUser UserPrincipal principal, @RequestBody EcExtensionOfValidityCertificate ecExtensionOfValidityCertificate,
                                                                       HttpServletRequest request) throws PariveshException, DocumentException, IOException {

        return ResponseHandler.generateResponse("Save Specific TOR Certificate ", HttpStatus.OK, "",
                ecExtensionOfValidityCertificateService.saveEcExtensionOfValidityCertificate(principal,ecExtensionOfValidityCertificate, request));
    }

    @GetMapping("/getEcExtensionOfValidityCertByPropId")
    public ResponseEntity<Object> getEcExtensionOfValidityCertByPropId(@RequestParam int propId,
                                                                     @RequestParam("proposal_no") String proposalNo) {
        return ResponseHandler.generateResponse("get EC Detail for EcExtensionOfValidity certificate", HttpStatus.OK, "",
                ecExtensionOfValidityCertificateService.getStandTorDetailByPropId(propId, proposalNo));
    }
    
    @GetMapping("/getIdentificationNo")
    public ResponseEntity<Object> getIdentificationNoByPropId (@RequestParam int propId) {
        return ResponseHandler.generateResponse("get EC Detail for specific certificate", HttpStatus.OK, "",
        		ecExtensionOfValidityCertificateService.getIdentificationNo(propId));
    }

}
