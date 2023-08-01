package com.backend.controller.StandardTorCertificate;

import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.EcMiningExpansionTemplate;
import com.backend.response.ResponseHandler;
import com.backend.security.CurrentUser;
import com.backend.service.StandardTORCertificate.EcMiningExpansionTemplateService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;


@RestController
@RequestMapping("/ecMiningExpansionTemplate")
public class EcMiningExpansionTemplateController {

    @Autowired
    EcMiningExpansionTemplateService ecMiningExpansionTemplateService;

    @PostMapping("/saveEcMiningExpansionTemplate")
    public ResponseEntity<Object> saveEcMiningExpansionTemplate(@CurrentUser UserPrincipal principal, @RequestBody EcMiningExpansionTemplate EcMiningExpansionTemplate,
                                                                HttpServletRequest request) throws PariveshException, DocumentException, IOException {

        return ResponseHandler.generateResponse("Ec Mining Expansion Template", HttpStatus.OK, "",
                ecMiningExpansionTemplateService.saveEcMiningExpansionTemplate(principal,EcMiningExpansionTemplate, request));
    }

    @GetMapping("/getEcMiningExpansionTemplateCertByPropId")
    public ResponseEntity<Object> getEcMiningExpansionTemplateCertByPropId(@RequestParam int propId,
                                                                                     @RequestParam("proposal_no") String proposalNo) {
        return ResponseHandler.generateResponse("get EC Detail for EcMiningExpansionTemplate certificate", HttpStatus.OK, "",
                ecMiningExpansionTemplateService.getStandTorDetailByPropId(propId, proposalNo));
    }

    @GetMapping("/getIdentificationNo")
    public ResponseEntity<Object> getIdentificationNoByPropId (@RequestParam int propId) {
        return ResponseHandler.generateResponse("get EC Detail for EcMiningExpansionTemplate certificate", HttpStatus.OK, "",
                ecMiningExpansionTemplateService.getIdentificationNo(propId));
    }

}
