package com.backend.controller.StandardTorCertificate;

import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.EcAmendmentCertificate;
import com.backend.response.ResponseHandler;
import com.backend.security.CurrentUser;
import com.backend.service.StandardTORCertificate.EcAmendmentCertificateService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/ecAmendmentCertificate")
public class EcAmendmentCertificateController {

    @Autowired
    EcAmendmentCertificateService ecAmendmentCertificateService;

    @PostMapping("/saveEcAmendmentCertificate")
    public ResponseEntity<Object> saveEcAmendmentCertificate(@CurrentUser UserPrincipal principal, @RequestBody EcAmendmentCertificate ecAmendmentCertificate,
                                                             HttpServletRequest request) throws PariveshException, DocumentException, IOException {

        return ResponseHandler.generateResponse("Ec Expansion NIPL Certificate ", HttpStatus.OK, "",
                ecAmendmentCertificateService.saveEcAmendmentCertificate(principal,ecAmendmentCertificate, request));
    }

    @GetMapping("/getEcAmendmentCertByPropId")
    public ResponseEntity<Object> getEcAmendmentCertByPropId(@RequestParam int propId,
                                                                            @RequestParam("proposal_no") String proposalNo) {
        return ResponseHandler.generateResponse("get EC Detail for EcAmendment certificate", HttpStatus.OK, "",
                ecAmendmentCertificateService.getEcAmendmentDetailByPropId(propId, proposalNo));
    }
    
    @GetMapping("/getIdentificationNo")
    public ResponseEntity<Object> getIdentificationNoByPropId (@RequestParam int propId) {
        return ResponseHandler.generateResponse("get EC Detail for specific certificate", HttpStatus.OK, "",
        		ecAmendmentCertificateService.getIdentificationNo(propId));
    }
}
