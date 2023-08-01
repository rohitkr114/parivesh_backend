package com.backend.controller.StandardTorCertificate;


import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.RejectionTorCertificate;
import com.backend.response.ResponseHandler;
import com.backend.security.CurrentUser;
import com.backend.service.StandardTORCertificate.RejectionTORCertificateService;
import com.backend.service.StandardTORCertificate.StandardTORCertificateService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/rejectionCertificate")
public class RejectionTorCertificateController {

    @Autowired
    RejectionTORCertificateService rejectionTORCertificateService;

    @Autowired
    private StandardTORCertificateService standardTORCertificateService;

    @PostMapping("/saveRejectionCertificate")
    public ResponseEntity<Object> saveRejectionTorCertificate(@CurrentUser UserPrincipal principal, @RequestBody RejectionTorCertificate rejectionTorCertificate,
                                                              HttpServletRequest request) throws PariveshException, DocumentException, IOException {

        return ResponseHandler.generateResponse("Save Specific TOR Certificate ", HttpStatus.OK, "",
                rejectionTORCertificateService.saveRejectionTorCertificate(principal,rejectionTorCertificate, request));

    }

    @GetMapping("/getRejectionTorCertByPropId")
    public ResponseEntity<Object> getRejectionTorCertByPropId(@RequestParam int propId,
                                                         @RequestParam("proposal_no") String proposalNo) throws ParseException {
        return ResponseHandler.generateResponse("get EC Detail for specific certificate", HttpStatus.OK, "",
                standardTORCertificateService.getStandTorDetailByPropId(propId, proposalNo,"rejection"));
    }
}
