package com.backend.controller.StandardTorCertificate;

import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.EcMiningExtensionofValidityTemplateCertificate;
import com.backend.response.ResponseHandler;
import com.backend.security.CurrentUser;
import com.backend.service.StandardTORCertificate.EcMiningExtensionofValidityTemplateCertificateService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/ecMiningExtensionofValidityTemplateCertificate")
public class EcMiningExtensionofValidityTemplateCertificateController {


    @Autowired
    EcMiningExtensionofValidityTemplateCertificateService ecMiningExtensionofValidityTemplateCertificateService;

    @PostMapping("/saveEcMiningExtensionofValidityTemplateCertificate")
    public ResponseEntity<Object> saveEcMiningExtensionofValidityTemplateCertificate(@CurrentUser UserPrincipal principal, @RequestBody EcMiningExtensionofValidityTemplateCertificate ecMiningExtensionofValidityTemplateCertificate,
                                                                                     HttpServletRequest request) throws PariveshException, DocumentException, IOException {

        return ResponseHandler.generateResponse("Save Specific TOR Certificate ", HttpStatus.OK, "",
                ecMiningExtensionofValidityTemplateCertificateService.saveEcMiningExtensionofValidityTemplateCertificate(principal,ecMiningExtensionofValidityTemplateCertificate, request));
    }

    @GetMapping("/getEcMiningExtensionOfValidityTemplateCertByPropId")
    public ResponseEntity<Object> getEcMiningExtensionOfValidityTemplateCertByPropId(@RequestParam int propId,
                                                                     @RequestParam("proposal_no") String proposalNo) {
        return ResponseHandler.generateResponse("get EC Detail for EcMiningExtensionofValidityTemplate certificate", HttpStatus.OK, "",
                ecMiningExtensionofValidityTemplateCertificateService.getStandTorDetailByPropId(propId, proposalNo));
    }
}
