package com.backend.controller.StandardTorCertificate;

import com.backend.dto.UserPrincipal;
import com.backend.security.CurrentUser;
import com.backend.service.DFAService;
import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.SpecificTorCertificate;
import com.backend.response.ResponseHandler;
import com.backend.service.StandardTORCertificate.SpecificTORCertificateService;
import com.backend.service.StandardTORCertificate.StandardTORCertificateService;
import com.itextpdf.text.DocumentException;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/specificCertificate")
public class SpecificTorCertificateController {


    @Autowired
    SpecificTORCertificateService specificTORCertificateService;

    @Autowired
    DFAService dfaService;
    @Autowired
    private StandardTORCertificateService standardTORCertificateService;


    @PostMapping("/saveSpecificCertificate")
    public ResponseEntity<Object> saveSpecificTorCertificate(@CurrentUser UserPrincipal principal, @RequestBody SpecificTorCertificate specificTorCertificate,
                                                             HttpServletRequest request, HttpServletResponse response) throws PariveshException, DocumentException, IOException, JRException {

        return ResponseHandler.generateResponse("Save Specific TOR Certificate ", HttpStatus.OK, "",
                specificTORCertificateService.saveSpecificTorCertificate(principal,specificTorCertificate, request, response));
    }

    @GetMapping("/getSpecificTorCertByPropId")
    public ResponseEntity<Object> getStandTorCertDetails(@RequestParam int propId,
                                                         @RequestParam("proposal_no") String proposalNo) throws ParseException {
        return ResponseHandler.generateResponse("get EC Detail for specific certificate", HttpStatus.OK, "",
                standardTORCertificateService.getStandTorDetailByPropId(propId, proposalNo, "specific"));
    }

    @GetMapping("/getSpecificTorCertById")
    public ResponseEntity<Object> getSpecificTorCertData(@RequestParam int certId,
                                                         @RequestParam("proposal_no") String proposalNo) {
        return ResponseHandler.generateResponse("get EC Specific Tor Certificate Detail", HttpStatus.OK, "",
                specificTORCertificateService.getSpecificTorData(certId, proposalNo));
    }

    @GetMapping("/getDFAByTypeById")
    public ResponseEntity<Object> getDFAByTypeById(@RequestParam int certId,@RequestParam int clearenceId,
                                                   @RequestParam String type) {
        return ResponseHandler.generateResponse("get Certificate Detail", HttpStatus.OK, "",
                dfaService.getDFAByTypeById(certId, clearenceId,type));
    }

    @GetMapping("/getDFAVersions")
    public ResponseEntity<Object> getDFAVersions(@RequestParam("proposal_id") Integer proposalId,@RequestParam int clearenceId,
                                                 @RequestParam String type) {
        return ResponseHandler.generateResponse("Get versions of specific tor by proposal id", HttpStatus.OK, "",
                dfaService.getDFAVersions(proposalId,clearenceId,type));
    }

}
