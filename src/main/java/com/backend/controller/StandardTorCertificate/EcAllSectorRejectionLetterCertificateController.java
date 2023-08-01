package com.backend.controller.StandardTorCertificate;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.backend.dto.UserPrincipal;
import com.backend.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.EcAllSectorRejectionLetterCertificate;
import com.backend.response.ResponseHandler;
import com.backend.service.StandardTORCertificate.EcAllSectorRejectionLetterCertificateService;
import com.itextpdf.text.DocumentException;

@RestController
@RequestMapping("/ecAllSectorRejectionLetterCertificate")
public class EcAllSectorRejectionLetterCertificateController {

    @Autowired
    EcAllSectorRejectionLetterCertificateService ecAllSectorRejectionLetterCertificateService;

    @PostMapping("/saveEcAllSectorRejectionLetterCertificate")
    public ResponseEntity<Object> saveEcAllSectorRejectionLetterCertificate(@CurrentUser UserPrincipal principal, @RequestBody EcAllSectorRejectionLetterCertificate ecAllSectorRejectionLetterCertificate,
                                                                            HttpServletRequest request) throws PariveshException, DocumentException, IOException {

        return ResponseHandler.generateResponse("Ec Expansion NIPL Certificate ", HttpStatus.OK, "",
                ecAllSectorRejectionLetterCertificateService.saveEcAllSectorRejectionLetterCertificate(principal,ecAllSectorRejectionLetterCertificate, request));
    }

    @GetMapping("/getEcAllSectorRejectionLetterCertByPropId")
    public ResponseEntity<Object> getEcAllSectorRejectionLetterCertByPropId(@RequestParam int propId,
                                                                     @RequestParam("proposal_no") String proposalNo) {
        return ResponseHandler.generateResponse("get EC Detail for getEcAllSectorRejection certificate", HttpStatus.OK, "",
                ecAllSectorRejectionLetterCertificateService.getStandTorDetailByPropId(propId, proposalNo));
    }
}
