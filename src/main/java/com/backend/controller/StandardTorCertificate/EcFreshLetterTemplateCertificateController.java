package com.backend.controller.StandardTorCertificate;

import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.EcFreshLetterTemplateCertificate;
import com.backend.model.StandardTORCertificate.RejectionTorCertificate;
import com.backend.response.ResponseHandler;
import com.backend.security.CurrentUser;
import com.backend.service.StandardTORCertificate.EcFreshLetterTemplateCertificateService;
import com.backend.service.StandardTORCertificate.StandardTORCertificateService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/freshLetterCertificate")
public class EcFreshLetterTemplateCertificateController {

    @Autowired
    EcFreshLetterTemplateCertificateService ecFreshLetterTemplateCertificateService;

    @PostMapping("/saveFreshLetterCertificate")
    public ResponseEntity<Object> saveFreshLetterCertificate(@CurrentUser UserPrincipal userPrincipal, @RequestBody EcFreshLetterTemplateCertificate ecFreshLetterTemplateCertificate,
                                                             HttpServletRequest request) throws PariveshException, DocumentException, IOException {

        return ResponseHandler.generateResponse("Save Specific TOR Certificate ", HttpStatus.OK, "",
                ecFreshLetterTemplateCertificateService.saveFreshLetterCertificate(userPrincipal,ecFreshLetterTemplateCertificate, request));
    }

    @GetMapping("/getFreshLetterTemplateCertByPropId")
    public ResponseEntity<Object> getFreshLetterTemplateCertByPropId(@RequestParam int propId,
                                                              @RequestParam("proposal_no") String proposalNo) {
        return ResponseHandler.generateResponse("get EC Detail for specific certificate", HttpStatus.OK, "",
                ecFreshLetterTemplateCertificateService.getStandTorDetailByPropId(propId, proposalNo));
    }

    @GetMapping("/getIdentificationNo")
    public ResponseEntity<Object> getIdentificationNoByPropId (@RequestParam int propId) {
        return ResponseHandler.generateResponse("get EC Detail for specific certificate", HttpStatus.OK, "",
                ecFreshLetterTemplateCertificateService.getIdentificationNo(propId));
    }
}
