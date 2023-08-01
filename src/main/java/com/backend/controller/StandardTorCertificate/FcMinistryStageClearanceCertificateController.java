package com.backend.controller.StandardTorCertificate;


import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.FcMinistryStageClearanceCertificate;
import com.backend.model.StandardTORCertificate.FcMinistryStageIClearanceCertificate;
import com.backend.response.ResponseHandler;
import com.backend.service.StandardTORCertificate.FcMinistryStageClearanceCertificateService;
import com.backend.service.StandardTORCertificate.FcMinistryStageIClearanceCertificateService;
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
@RequestMapping("/fcMinistryStageClearanceCertificate")
public class FcMinistryStageClearanceCertificateController {


    @Autowired
    FcMinistryStageClearanceCertificateService fcMinistryStageClearanceCertificateService;

    @Autowired
    FcMinistryStageIClearanceCertificateService fcMinistryStageIClearanceCertificateService;

    
    @Autowired
    StandardTORCertificateService standardTORCertificateService;

    @PostMapping("/saveFcMinistryStageIIClearanceCertificate")
    public ResponseEntity<Object> saveFcMinistryStageClearanceCertificate(@RequestBody FcMinistryStageClearanceCertificate fcMinistryStageClearanceCertificate,
                                                                     HttpServletRequest request) throws PariveshException, DocumentException, IOException {

        return ResponseHandler.generateResponse("Fc Ministry Stage II Clearance Certificate ", HttpStatus.OK, "",
                fcMinistryStageClearanceCertificateService.saveFcMinistryStageClearanceCertificate(fcMinistryStageClearanceCertificate, request));
    }

    @GetMapping("/getFcMinistryStageIIClearanceCertByPropId")
    public ResponseEntity<Object> getFcMinistryStageClearanceCertByPropId(@RequestParam int propId,
                                                                     @RequestParam("proposal_no") String proposalNo,
                                                                     @RequestParam(name = "office_type", required = false) String officeType
                                                                 	 ) {
        return ResponseHandler.generateResponse("get Fc Ministry Stage II Clearance certificate", HttpStatus.OK, "",
                fcMinistryStageClearanceCertificateService.getAllDataByPropId(propId, proposalNo, officeType));
    }
//   Below  API is for FC Certificates.
        @GetMapping("/getFcMinistryStageClearanceCertFormTypeByPropId")
    public ResponseEntity<Object> getFcMinistryStageClearanceCertificateFormTypeInfoBytPropId(@RequestParam int propId ,
                                                                                         @RequestParam ("proposal_no") String proposal_no) {
        String s = standardTORCertificateService.getFcMinistryStageClearanceCertificateFormTypeInfoBytPropId(propId , proposal_no);
        log.info(" FORM TYPE ______________________ "+s);
        return ResponseHandler.generateResponse("get Fc Ministry Stage II Clearance certificate Form Type", HttpStatus.OK, "",
                s);
    }

        
        @PostMapping("saveFcMinistryStageIClearanceCertificate")
        public ResponseEntity<Object> saveFcMinistryStageIClearanceCertificate(@RequestBody FcMinistryStageIClearanceCertificate fcMinistryStageClearanceCertificate,
                                                                         HttpServletRequest request) throws PariveshException, DocumentException, IOException {

            return ResponseHandler.generateResponse("Fc Ministry Stage I Clearance Certificate ", HttpStatus.OK, "",
                    fcMinistryStageIClearanceCertificateService.saveFcMinistryStageIClearanceCertificate(fcMinistryStageClearanceCertificate, request));
        }

        @GetMapping("/getFcMinistryStageIClearanceCertByPropId")
        public ResponseEntity<Object> getFcMinistryStageIClearanceCertByPropId(@RequestParam int propId,
                                                                         @RequestParam("proposal_no") String proposalNo,
                                                                         @RequestParam(name = "office_type", required = false) String officeType
                                                                     	 ) {
            return ResponseHandler.generateResponse("get Fc Ministry Stage I Clearance certificate", HttpStatus.OK, "",
                    fcMinistryStageIClearanceCertificateService.getAllDataByPropId(propId, proposalNo, officeType));
        }
}
