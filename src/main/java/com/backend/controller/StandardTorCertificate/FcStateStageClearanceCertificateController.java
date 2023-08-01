package com.backend.controller.StandardTorCertificate;

import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.FcStateStageClearanceCertificate;
import com.backend.model.StandardTORCertificate.FcStateStageIClearanceCertificate;
import com.backend.response.ResponseHandler;
import com.backend.service.StandardTORCertificate.FcStateStageClearanceCertificateService;
import com.backend.service.StandardTORCertificate.FcStateStageIClearanceCertificateService;
import com.backend.service.StandardTORCertificate.StandardTORCertificateService;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
@Slf4j
@RestController
@RequestMapping("/fcStateStageClearanceCertificate")
public class FcStateStageClearanceCertificateController {

    @Autowired
    FcStateStageClearanceCertificateService fcStateStageClearanceCertificateService;

    @Autowired
    FcStateStageIClearanceCertificateService fcStateStageIClearanceCertificateService;
    
    @Autowired
    StandardTORCertificateService standardTORCertificateService;

    @PostMapping("/saveFcStateStageIIClearanceCertificate")
    public ResponseEntity<Object> saveFcStateStageClearanceCertificate(@RequestBody FcStateStageClearanceCertificate fcStateStageClearanceCertificate,
                                                                          HttpServletRequest request) throws PariveshException, DocumentException, IOException {

        return ResponseHandler.generateResponse("Fc State Stage Clearance Certificate ", HttpStatus.OK, "",
                fcStateStageClearanceCertificateService.saveFcStateStageClearanceCertificate(fcStateStageClearanceCertificate, request));
    }

    @GetMapping("/getFcStateStageIIClearanceCertByPropId")
    public ResponseEntity<Object> getFcStateStageClearanceCertByPropId(@RequestParam int propId,
                                                                          @RequestParam("proposal_no") String proposalNo,
                                                                          @RequestParam(name = "office_type", required = false) String officeType
                                                                      	) {
        return ResponseHandler.generateResponse("get Fc State Stage Clearance certificate", HttpStatus.OK, "",
                fcStateStageClearanceCertificateService.getAllDataByPropId(propId, proposalNo, officeType));
    }

    @GetMapping("/getFcStateStageClearanceCertFormTypeByPropId")
    public ResponseEntity<Object> getFcStateStageClearanceCertificateFormTypeInfoBytPropId(@RequestParam int propId ,
                                                                                         @RequestParam ("proposal_no") String proposal_no) {
        String s = standardTORCertificateService.getFcStateStageClearanceCertificateFormTypeInfoBytPropId(propId , proposal_no);
        log.info(" FORM TYPE ______________________ "+s);
        return ResponseHandler.generateResponse("get Fc State Stage Clearance certificate Form Type", HttpStatus.OK, "",
                s);
    }
    
    @PostMapping("/saveFcStateStageIClearanceCertificate")
    public ResponseEntity<Object> saveFcStateStageIClearanceCertificate(@RequestBody FcStateStageIClearanceCertificate fcStateStageIClearanceCertificate,
                                                                          HttpServletRequest request) throws PariveshException, DocumentException, IOException {

        return ResponseHandler.generateResponse("Fc State Stage I Clearance Certificate ", HttpStatus.OK, "",
                fcStateStageIClearanceCertificateService.saveFcStateStageIClearanceCertificate(fcStateStageIClearanceCertificate, request));
    }

    @GetMapping("/getFcStateStageIClearanceCertByPropId")
    public ResponseEntity<Object> getFcStateStageIClearanceCertByPropId(@RequestParam int propId,
                                                                          @RequestParam("proposal_no") String proposalNo,
                                                                          @RequestParam(name = "office_type", required = false) String officeType
                                                                      	) {
        return ResponseHandler.generateResponse("get Fc State Stage II Clearance certificate", HttpStatus.OK, "",
                fcStateStageIClearanceCertificateService.getAllDataByPropId(propId, proposalNo, officeType));
    }
}
