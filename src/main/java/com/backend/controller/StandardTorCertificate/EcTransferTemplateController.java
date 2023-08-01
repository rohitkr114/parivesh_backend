package com.backend.controller.StandardTorCertificate;

import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.EcTransferTemplate;
import com.backend.model.StandardTORCertificate.StandardTorCertificate;
import com.backend.response.ResponseHandler;
import com.backend.security.CurrentUser;
import com.backend.service.StandardTORCertificate.EcTransferTemplateService;
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
@RequestMapping("/transferTemplate")
public class EcTransferTemplateController {


    @Autowired
    EcTransferTemplateService ecTransferTemplateService;

    @PostMapping("/saveTransferTemplate")
    public ResponseEntity<Object> saveTransferTemplate(@CurrentUser UserPrincipal principal, @RequestBody EcTransferTemplate ecTransferTemplate,
                                                       HttpServletRequest request, HttpServletResponse response) throws PariveshException, DocumentException, IOException, JRException {
        log.info("Save: /transferTemplate/saveTransferTemplate");
        return ResponseHandler.generateResponse("Save Ec transfer template ", HttpStatus.OK, "",
                ecTransferTemplateService.saveEcTransferTemplate(principal,ecTransferTemplate, request,response));
    }

    @GetMapping("/getEcTransferTemplateCertByPropId")
    public ResponseEntity<Object> getEcTransferTemplateCertByPropId(@RequestParam int propId,
                                                                         @RequestParam("proposal_no") String proposalNo) {
        return ResponseHandler.generateResponse("get EcTransferTemplate certificate", HttpStatus.OK, "",
                ecTransferTemplateService.getStandTorDetailByPropId(propId, proposalNo));
    }

    @GetMapping("/getIdentificationNo")
    public ResponseEntity<Object> getIdentificationNoByPropId (@RequestParam int propId) {
        return ResponseHandler.generateResponse("get EcTransferTemplate certificate", HttpStatus.OK, "",
                ecTransferTemplateService.getIdentificationNo(propId));
    }
}
