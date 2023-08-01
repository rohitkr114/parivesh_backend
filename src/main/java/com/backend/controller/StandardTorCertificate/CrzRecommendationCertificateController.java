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
import com.backend.service.StandardTORCertificate.CrzRecommendationCertificateService;
import com.backend.service.certificate.CertificatesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/crzrecommendationcertificate")
public class CrzRecommendationCertificateController {
	
	@Autowired
	CrzRecommendationCertificateService crzRecommendationCertificateService;
	
	@Autowired
	private CertificatesService certificatesService;

	@PostMapping("/crzRecommendationCertificate")
	public ResponseEntity<Object> saveCrzRecommendationCertificate(@RequestBody CrzGrantCertificate crzGrantCertificate,
			HttpServletRequest request, HttpServletResponse response) throws PariveshException, IOException {
		log.info("Save: /crzcertificate/crzRecommendationCertificate");
		return ResponseHandler.generateResponse("Save CRZ Grant Certificate ", HttpStatus.OK, "",
				crzRecommendationCertificateService.saveCrzRecommendationCertificate(crzGrantCertificate, request, response));
	}

	@GetMapping("/getCrzRecomendationCertByPropId")
	public ResponseEntity<Object> getCrzRecommendationCertDetails(@RequestParam int propId,
			@RequestParam("proposal_no") String proposalNo) throws PariveshException, ParseException {
		log.info("get: /crzcertificate/getCrzRecommendationCertDetails?propId=" + propId + "&proposalNo=" + proposalNo);
		return ResponseHandler.generateResponse("get CRZ Detail", HttpStatus.OK, "",
				crzRecommendationCertificateService.getCrzRecommendationCertDetails(propId, proposalNo));
	}
}
