package com.backend.controller.StandardTorCertificate;

import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.StandardTorCertificate;
import com.backend.response.ResponseHandler;
import com.backend.service.StandardTORCertificate.StandardTORCertificateService;
import com.backend.service.certificate.CertificatesService;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

/**
 * StandardTorCertificateController : Application for Certificate
 */
@Slf4j
@RestController
@RequestMapping("/standardCertificate")
public class StandardTorCertificateController {
    @Autowired
    private StandardTORCertificateService standardTORCertificateService;


    @Autowired
    private CertificatesService certificatesService;


    @PostMapping("/ecCertificate")
    public ResponseEntity<Object> saveStandardTorCertificate(@RequestBody StandardTorCertificate standardTorCertificate,
                                                             HttpServletRequest request, HttpServletResponse response) throws PariveshException, IOException {
        log.info("Save: /standardCertificate/ecCertificate");
        return ResponseHandler.generateResponse("Save Standard TOR Certificate ", HttpStatus.OK, "",
                standardTORCertificateService.saveStandardTorCertificate(standardTorCertificate, request,response));
    }

    /**
     * THis API not in use.
     * @param id
     * @param request
     * @return
     * @throws PariveshException
     */
    @GetMapping("/getTORformData")
    public ResponseEntity<Object> getDataForCertificate(@RequestParam("id") Integer id,
                                                        HttpServletRequest request) throws PariveshException {
        log.info("get: /standardCertificate/getTORformData?id="+id+"&proposalNo=");
        return ResponseHandler.generateResponse("Save Standard TOR Certificate ", HttpStatus.OK, "",
                standardTORCertificateService.getDataForCertificate(id, "IA/RJ/CRZ/100264/2022", request));
    }


    /**
     * THis API will  return data for tor certificate first time
     * @param propId
     * @return
     */
    @GetMapping("/getStandTorCertByPropId")
    public ResponseEntity<Object> getStandTorCertDetails(@RequestParam int propId,
                                                         @RequestParam("proposal_no") String proposalNo) throws PariveshException, ParseException {
        log.info("get: /standardCertificate/getStandTorCertByPropId?propId="+propId+"&proposalNo="+proposalNo);
        return ResponseHandler.generateResponse("get EC Detail", HttpStatus.OK, "",
                standardTORCertificateService.getStandTorDetailByPropId(propId, proposalNo,"standard"));
    }

    @GetMapping("/getStandTorCertById")
    public ResponseEntity<Object> getStandTorCertData(@RequestParam int certId,
                                                      @RequestParam("proposal_no") String proposalNo) throws PariveshException {
        log.info("get: /standardCertificate/getStandTorCertById?id="+certId+"&proposalNo=");
        return ResponseHandler.generateResponse("get EC Detail", HttpStatus.OK, "IA/RJ/CRZ/100264/2022",
                standardTORCertificateService.getStandTorData(certId));
    }

    @GetMapping("/genIdentificationNo")
    public ResponseEntity<Object> genIdentificationNo(@RequestParam int propId,
                                                      @RequestParam("proposal_no") String proposalNo) throws PariveshException  {
        log.info("get: /standardCertificate/genIdentificationNo?propId="+propId+"&proposalNo="+"\"IA/RJ/CRZ/100264/2022\"");
        return ResponseHandler.generateResponse("get EC Detail", HttpStatus.OK, "",
                standardTORCertificateService.getIdentificationNo(propId, proposalNo));
    }

    @GetMapping("/getIndustryProposalDetailByEcId")
    public ResponseEntity<Object> getIndustryProposalDetailByEcId (@RequestParam int propId,
                                                                   @RequestParam("proposal_no") String proposalNo) throws PariveshException {
        log.info("get: /standardCertificate/getIndustryProposalDetailByEcId?propId="+propId+"&proposalNo="+"IA/RJ/CRZ/100264/2022");
        return ResponseHandler.generateResponse("get EC Industry Proposal Details", HttpStatus.OK, "",
                standardTORCertificateService.getEcIndustryProposalDetailById(propId, proposalNo));
    }
}
