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
import com.backend.response.ResponseHandler;
import com.backend.service.StandardTORCertificate.CrzGrantCertificateService;
import com.backend.service.certificate.CertificatesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/crzcertificate")
public class CRZGrantCertificateController {

	@Autowired
	private CrzGrantCertificateService crzGrantCertificateService;

	@Autowired
	private CertificatesService certificatesService;

	@PostMapping("/crzCertificate")
	public ResponseEntity<Object> saveCrzGrantCertificate(@RequestBody CrzGrantCertificate crzGrantCertificate,
			HttpServletRequest request, HttpServletResponse response) throws PariveshException, IOException {
		log.info("Save: /crzcertificate/ecCertificate");
		return ResponseHandler.generateResponse("Save CRZ Grant Certificate ", HttpStatus.OK, "",
				crzGrantCertificateService.saveCrzGrantCertificate(crzGrantCertificate, request, response));
	}

	@GetMapping("/getCrzGrantCertByPropId")
	public ResponseEntity<Object> getCrzGrantCertDetails(@RequestParam int propId,
			@RequestParam("proposal_no") String proposalNo) throws PariveshException, ParseException {
		log.info("get: /crzcertificate/getCrzGrantCertByPropId?propId=" + propId + "&proposalNo=" + proposalNo);
		return ResponseHandler.generateResponse("get CRZ Detail", HttpStatus.OK, "",
				crzGrantCertificateService.getCrzGrantByPropId(propId, proposalNo));
	}
	
//	 @PostMapping("/saveCrzFreshLetterCertificate")
//	    public ResponseEntity<Object> saveCrzFreshLetterCertificate(@RequestBody CrzFreshLetterTemplateCertificate CrzFreshLetterTemplateCertificate,
//	                                                              HttpServletRequest request) throws PariveshException, DocumentException, IOException {
//
//	        return ResponseHandler.generateResponse("Save Crz Save fresh letter Certificate ", HttpStatus.OK, "",
//	        		crzGrantCertificateService.saveCrzFreshLetterCertificate(CrzFreshLetterTemplateCertificate, request));
//	    }

}
