package com.backend.controller.StandardTorCertificate;

import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.EcExtensionOfValidityCertificate;
import com.backend.model.StandardTORCertificate.EcMiningAmendmentCertificate;
import com.backend.response.ResponseHandler;
import com.backend.security.CurrentUser;
import com.backend.service.StandardTORCertificate.EcExtensionOfValidityCertificateService;
import com.backend.service.StandardTORCertificate.EcMiningAmendmentCertificateService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/ecMiningAmendmentCertificate")
public class EcMiningAmendmentCertificateController {


    @Autowired
    EcMiningAmendmentCertificateService ecMiningAmendmentCertificateService;

    @PostMapping("/saveEcMiningAmendmentCertificate")
    public ResponseEntity<Object> saveEcMiningAmendmentCertificate(@CurrentUser UserPrincipal principal, @RequestBody EcMiningAmendmentCertificate ecMiningAmendmentCertificate,
                                                                   HttpServletRequest request) throws PariveshException, DocumentException, IOException {

        return ResponseHandler.generateResponse("Save EcMining Amendment Certificate  ", HttpStatus.OK, "",
                ecMiningAmendmentCertificateService.saveEcMiningAmendmentCertificate(principal,ecMiningAmendmentCertificate, request));
    }

    @GetMapping("/getEcMiningAmendmentCertByPropId")
    public ResponseEntity<Object> getEcMiningAmendmentCertByPropId(@RequestParam("propId") int propId,
                                                                 @RequestParam("proposal_no") String proposalNo) {
        return ResponseHandler.generateResponse("get Detail for getEcMiningAmendmentCertificate", HttpStatus.OK, "",
                ecMiningAmendmentCertificateService.getEcAmendmentDetailByPropId(propId, proposalNo));
    }

    @GetMapping("/getIdentificationNo")
    public ResponseEntity<Object> getIdentificationNoByPropId (@RequestParam int propId) {
        return ResponseHandler.generateResponse("get EC Detail for specific certificate", HttpStatus.OK, "",
                ecMiningAmendmentCertificateService.getIdentificationNo(propId));
    }
}
