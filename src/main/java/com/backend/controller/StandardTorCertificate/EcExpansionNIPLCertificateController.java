package com.backend.controller.StandardTorCertificate;

import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.EcExpansionNIPLCertificate;
import com.backend.model.StandardTORCertificate.EcTransferTemplateCertificate;
import com.backend.response.ResponseHandler;
import com.backend.security.CurrentUser;
import com.backend.service.StandardTORCertificate.EcExpansionNIPLCertificateService;
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
@RequestMapping("/ecExpansionNIPLCertificate")
public class EcExpansionNIPLCertificateController {

    @Autowired
    EcExpansionNIPLCertificateService ecExpansionNIPLCertificateService;

    @PostMapping("/saveEcExpansionNIPLCertificate")
    public ResponseEntity<Object> saveEcExpansionNIPLCertificate(@CurrentUser UserPrincipal principal, @RequestBody EcExpansionNIPLCertificate ecExpansionNIPLCertificate,
                                                                 HttpServletRequest request) throws PariveshException, DocumentException, IOException {

        return ResponseHandler.generateResponse("Ec Expansion NIPL Certificate ", HttpStatus.OK, "",
                ecExpansionNIPLCertificateService.saveEcExpansionNIPLCertificate(principal,ecExpansionNIPLCertificate, request));
    }

    @GetMapping("/getEcExpansionNIPLCertByPropId")
    public ResponseEntity<Object> getEcExpansionNIPLCertByPropId(@RequestParam int propId,
                                                                     @RequestParam("proposal_no") String proposalNo) {
        return ResponseHandler.generateResponse("get Detail for ecExpansionNIPLCertificateService", HttpStatus.OK, "",
                ecExpansionNIPLCertificateService.getStandTorDetailByPropId(propId, proposalNo,"ecExpansionNIPLCertificateService"));
    }
}
