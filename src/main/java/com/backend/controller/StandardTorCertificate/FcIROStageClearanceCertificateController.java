package com.backend.controller.StandardTorCertificate;


import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.FcIROStageClearanceCertificate;
import com.backend.model.StandardTORCertificate.FcIROStageIClearanceCertificate;
import com.backend.response.ResponseHandler;
import com.backend.service.StandardTORCertificate.FcIROStageClearanceCertificateService;
import com.backend.service.StandardTORCertificate.FcIROStageIClearanceCertificateService;
import com.backend.service.StandardTORCertificate.StandardTORCertificateService;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/fcIROStageClearanceCertificate")
public class FcIROStageClearanceCertificateController {

    @Autowired
    FcIROStageClearanceCertificateService fcIROStageClearanceCertificateService;

    @Autowired
    FcIROStageIClearanceCertificateService fcIROStageIClearanceCertificateService;
    
    @Autowired
    StandardTORCertificateService standardTORCertificateService;
    
    @PostMapping("/saveFcIROStageIIClearanceCertificate")
    public ResponseEntity<Object> saveFcIROStageClearanceCertificate(@RequestBody FcIROStageClearanceCertificate fcIROStageClearanceCertificate,
                                                                    HttpServletRequest request) throws PariveshException, DocumentException, IOException {

        return ResponseHandler.generateResponse("Fc IRO Stage II Clearance Certificate ", HttpStatus.OK, "",
                fcIROStageClearanceCertificateService.saveFcIROStageClearanceCertificate(fcIROStageClearanceCertificate, request));
    }
    
    @GetMapping("/getFcIROStageIIClearanceCertByPropId")
    public ResponseEntity<Object> getFcIROStageClearanceCertByPropId(@RequestParam int propId,
                                                                    @RequestParam("proposal_no") String proposalNo,
                                                                    @RequestParam(name = "office_type", required = false) String officeType
                                                                	) {
        return ResponseHandler.generateResponse("get Fc IRO Stage II Clearance certificate", HttpStatus.OK, "",
                fcIROStageClearanceCertificateService.getAllDataByPropId(propId, proposalNo, officeType));
    }

    @GetMapping("/getFcIROStageClearanceCertFormTypeByPropId")
    public ResponseEntity<Object> getFcIROStageClearanceCertificateFormTypeInfoBytPropId(@RequestParam int propId ,
                                                                                         @RequestParam ("proposal_no") String proposal_no) {
        String s = standardTORCertificateService.getFcIROStageClearanceCertificateFormTypeInfoBytPropId(propId , proposal_no);
        log.info(" FORM TYPE ______________________ "+s);
        return ResponseHandler.generateResponse("get Fc IRO Stage Clearance certificate Form Type", HttpStatus.OK, "",
                s);
    }
    
    @PostMapping("/saveFcIROStageIClearanceCertificate")
    public ResponseEntity<Object> saveFcIROStageIClearanceCertificate(@RequestBody FcIROStageIClearanceCertificate fcIROStageIClearanceCertificate,
                                                                    HttpServletRequest request) throws PariveshException, DocumentException, IOException {

        return ResponseHandler.generateResponse("Fc IRO Stage I Clearance Certificate ", HttpStatus.OK, "",
                fcIROStageIClearanceCertificateService.saveFcIROStageIClearanceCertificate(fcIROStageIClearanceCertificate, request));
    }
    
    @GetMapping("/getFcIROStageIClearanceCertByPropId")
    public ResponseEntity<Object> getFcIROStageIClearanceCertByPropId(@RequestParam int propId,
                                                                    @RequestParam("proposal_no") String proposalNo,
                                                                    @RequestParam(name = "office_type", required = false) String officeType
                                                                	) {
        return ResponseHandler.generateResponse("get Fc IRO Stage I Clearance certificate", HttpStatus.OK, "",
                fcIROStageIClearanceCertificateService.getAllDataByPropId(propId, proposalNo, officeType));
    }


}
