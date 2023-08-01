package com.backend.controller.StandardTorCertificate;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.CrzGrantCertificate;
import com.backend.model.StandardTORCertificate.CrzRejectionCertificate;
import com.backend.response.ResponseHandler;
import com.backend.service.StandardTORCertificate.CrzRejectionLetterService;
import com.backend.service.certificate.CertificatesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/crzRejectionCertificate")
public class CrzRejectionLetterController {

	@Autowired
	private CertificatesService certificatesService;
	
	@Autowired
	private CrzRejectionLetterService crzRejectionCertificateService;
	
	@PostMapping("/crzRejectionCertificate")
	public ResponseEntity<Object> saveCrzRejectionCertificate(@RequestBody CrzRejectionCertificate crzRejcetionCertificate,
			HttpServletRequest request, HttpServletResponse response) throws PariveshException, IOException {
		log.info("Save: /crzRejectionCertificate/crzRejctionCertificate");
		return ResponseHandler.generateResponse("Save CRZ Rejection Certificate ", HttpStatus.OK, "",
				crzRejectionCertificateService.saveCrzRejectionCertificate(crzRejcetionCertificate, request, response));
	}
	
	@GetMapping("/getCrzRejectionCertByPropId")
	public ResponseEntity<Object> getCrzRejectionCertDetails(@RequestParam int propId,
			@RequestParam("proposal_no") String proposalNo, @RequestParam("Authority") String authority) throws PariveshException, ParseException {
		log.info("get: /crzRejectioncertificate/getCrzRejectionCertByPropId?propId=" + propId + "&proposalNo=" + proposalNo);
		return ResponseHandler.generateResponse("get CRZ Detail", HttpStatus.OK, "",
				crzRejectionCertificateService.getCrzRejectionByPropId(propId, proposalNo,authority));
	}
	
}
