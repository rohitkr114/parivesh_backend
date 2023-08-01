package com.backend.controller.StandardTorCertificate;

import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.EcTransferTemplateCertificate;
import com.backend.response.ResponseHandler;
import com.backend.security.CurrentUser;
import com.backend.service.StandardTORCertificate.EcTransferTemplateCertificateService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/ecTransferTemplateCertificateController")
public class EcTransferTemplateCertificateController {

    @Autowired
    EcTransferTemplateCertificateService ecTransferTemplateCertificateService;

    @PostMapping("/saveEcTransferTemplateCertificate")
    public ResponseEntity<Object> saveEcTransferTemplateCertificate(@CurrentUser UserPrincipal principal, @RequestBody EcTransferTemplateCertificate ecTransferTemplateCertificate,
                                                                    HttpServletRequest request) throws PariveshException, DocumentException, IOException {

        return ResponseHandler.generateResponse("Ec Transfer Template Certificate ", HttpStatus.OK, "",
                ecTransferTemplateCertificateService.saveEcTransferTemplateCertificate(principal,ecTransferTemplateCertificate, request));
    }

    @GetMapping("/getEcTransferTemplateCertByPropId")
    public ResponseEntity<Object> getEcTransferTemplateCertByPropId(@RequestParam int propId,
                                                                     @RequestParam("proposal_no") String proposalNo) {
        return ResponseHandler.generateResponse("get EC Detail for EcTransferTemplate certificate", HttpStatus.OK, "",
                ecTransferTemplateCertificateService.getStandTorDetailByPropId(propId, proposalNo));
    }

}
