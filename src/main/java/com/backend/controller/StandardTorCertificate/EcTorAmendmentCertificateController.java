package com.backend.controller.StandardTorCertificate;

import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.EcTorAmendmentCertificate;
import com.backend.response.ResponseHandler;
import com.backend.security.CurrentUser;
import com.backend.service.StandardTORCertificate.EcTorAmendmentCertificateService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/amendmentCertificate")
public class EcTorAmendmentCertificateController {

    @Autowired
    EcTorAmendmentCertificateService ecTorAmendmentCertificateService;

    @PostMapping("/saveAmendmentCertificate")
    public ResponseEntity<Object> saveAmendmentCertificate(@CurrentUser UserPrincipal principal, @RequestBody EcTorAmendmentCertificate ecTorAmendmentCertificate,
                                                           HttpServletRequest request) throws PariveshException, DocumentException, IOException {

        return ResponseHandler.generateResponse("Save EcTorAmendmentCertificate ", HttpStatus.OK, "",
                ecTorAmendmentCertificateService.saveAmendmentCertificate(principal,ecTorAmendmentCertificate, request));
    }

    @GetMapping("/getEcTorAmendmentCertByPropId")
    public ResponseEntity<Object> getAmendmentCertByPropId(@RequestParam int propId,
                                                                     @RequestParam("proposal_no") String proposalNo) {
        return ResponseHandler.generateResponse("get EC Detail for EcTorAmendmentCertificate ", HttpStatus.OK, "",
                ecTorAmendmentCertificateService.getStandTorDetailByPropId(propId, proposalNo));
    }
}
