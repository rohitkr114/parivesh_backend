package com.backend.controller.StandardTorCertificate;

import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.EcExtensionOfValidityCertificate;
import com.backend.model.StandardTORCertificate.EcMiningTransferCertificate;
import com.backend.response.ResponseHandler;
import com.backend.security.CurrentUser;
import com.backend.service.StandardTORCertificate.EcMiningTransferCertificateService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/ecMiningTransferCertificateController")
public class EcMiningTransferCertificateController {


    @Autowired
    EcMiningTransferCertificateService ecMiningTransferCertificateService;

    @PostMapping("/saveEcMiningTransferCertificate")
    public ResponseEntity<Object> saveEcExtensionOfValidityCertificate(@CurrentUser UserPrincipal principal, @RequestBody EcMiningTransferCertificate ecMiningTransferCertificate,
                                                                       HttpServletRequest request) throws PariveshException, DocumentException, IOException {

        return ResponseHandler.generateResponse("Save Specific TOR Certificate ", HttpStatus.OK, "",
                ecMiningTransferCertificateService.saveEcMiningTransferCertificate(principal,ecMiningTransferCertificate, request));
    }

    @GetMapping("/getEcMiningTransferCertByPropId")
    public ResponseEntity<Object> getEcMiningTransferCertByPropId(@RequestParam int propId,
                                                                     @RequestParam("proposal_no") String proposalNo) {
        return ResponseHandler.generateResponse("get EC Detail for EcMiningTransfer certificate", HttpStatus.OK, "",
                ecMiningTransferCertificateService.getStandTorDetailByPropId(propId, proposalNo));
    }
}
