package com.backend.controller.StandardTorCertificate;


import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.EcTransferOfMiningLeaseAcknowledgmentCertificate;
import com.backend.response.ResponseHandler;
import com.backend.security.CurrentUser;
import com.backend.service.StandardTORCertificate.EcTransferOfMiningLeaseAcknowledgmentCertificateService;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/ecTransferOfMiningLeaseAcknowledgmentCertificate")
public class EcTransferOfMiningLeaseCertificateController {

    @Autowired
    EcTransferOfMiningLeaseAcknowledgmentCertificateService ecTransferOfMiningLeaseAcknowledgmentCertificateService;


    @PostMapping("/saveEcTransferOfMiningLeaseAcknowledgmentCertificate")
    public ResponseEntity<Object> saveEcTransferOfMiningLeaseCertificate(@CurrentUser UserPrincipal principal, @RequestBody EcTransferOfMiningLeaseAcknowledgmentCertificate ecTransferOfMiningLeaseAcknowledgmentCertificate,
                                                                         HttpServletRequest request, HttpServletResponse response) throws PariveshException, DocumentException, IOException, JRException {
        log.info("Save: /ecTransferOfMiningLeaseCertificate/saveEcTransferOfMiningLeaseCertificate");
        return ResponseHandler.generateResponse("Save Ec Transfer Of Mining Lease Certificate", HttpStatus.OK, "",
                ecTransferOfMiningLeaseAcknowledgmentCertificateService.saveEcTransferOfMiningLeaseCertificate(principal,ecTransferOfMiningLeaseAcknowledgmentCertificate, request,response));
    }

    @GetMapping("/getEcTransferOfMiningLeaseAcknowledgmentCertByPropId")
    public ResponseEntity<Object> getEcTransferOfMiningLeaseCertByPropId(@RequestParam int propId,
                                                                       @RequestParam("proposal_no") String proposalNo) {
        return ResponseHandler.generateResponse("get EC Detail for specific certificate", HttpStatus.OK, "",
                ecTransferOfMiningLeaseAcknowledgmentCertificateService.getStandTorDetailByPropId(propId, proposalNo));
    }

    @GetMapping("/getIdentificationNo")
    public ResponseEntity<Object> getIdentificationNoByPropId (@RequestParam int propId) {
        return ResponseHandler.generateResponse("get EC Detail for specific certificate", HttpStatus.OK, "",
                ecTransferOfMiningLeaseAcknowledgmentCertificateService.getIdentificationNo(propId));
    }


}
