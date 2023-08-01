package com.backend.controller.StandardTorCertificate;


import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.EcTorTransferTemplate;
import com.backend.model.StandardTORCertificate.StandardTorCertificate;
import com.backend.response.ResponseHandler;
import com.backend.security.CurrentUser;
import com.backend.service.StandardTORCertificate.EcTorTransferTemplateService;
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
@RequestMapping("/ecTorTransferTemplate")
public class EcTorTransferTemplateController {

    @Autowired
    EcTorTransferTemplateService ecTorTransferTemplateService;

    @PostMapping("/saveEcTorTransferTemplate")
    public ResponseEntity<Object> saveEcTorTransferTemplate(@CurrentUser UserPrincipal principal, @RequestBody EcTorTransferTemplate ecTorTransferTemplate,
                                                            HttpServletRequest request, HttpServletResponse response) throws PariveshException, DocumentException, IOException, JRException {
        log.info("Save: /ecTorTransferTemplate/saveEcTorTransferTemplate");
        return ResponseHandler.generateResponse("Save Ec TOR Transfer Template", HttpStatus.OK, "",
                 ecTorTransferTemplateService.saveEcTorTransferTemplate(principal,ecTorTransferTemplate, request,response));
    }

    @GetMapping("/getEcTorTransferTemplateCertByPropId")
    public ResponseEntity<Object> getEcTorTransferTemplateCertByPropId(@RequestParam int propId,
                                                                     @RequestParam("proposal_no") String proposalNo) {
        return ResponseHandler.generateResponse("get EC Detail for specific certificate", HttpStatus.OK, "",
                ecTorTransferTemplateService.getStandTorDetailByPropId(propId, proposalNo));
    }

    @GetMapping ("/getIdentificationNo")
    public ResponseEntity<Object> getIdentificationNoByProp (@RequestParam int propId) {
        return ResponseHandler.generateResponse("get EC Detail for specific certificate", HttpStatus.OK, "",
                ecTorTransferTemplateService.getIdentificationNo(propId));
    }

}
