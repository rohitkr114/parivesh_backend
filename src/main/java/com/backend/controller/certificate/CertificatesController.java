package com.backend.controller.certificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.exceptions.PariveshException;
import com.backend.model.certificate.Certificates;
import com.backend.response.ResponseHandler;
import com.backend.service.certificate.CertificatesService;

@RestController
@RequestMapping("/certificate")
public class CertificatesController {

	@Autowired
	private CertificatesService certificatesService;

	@PostMapping("/save")
	public ResponseEntity<Object> saveCertificates(@RequestBody Certificates certificates) throws PariveshException {
		return ResponseHandler.generateResponse("Save Certificates", HttpStatus.OK, "",
				certificatesService.saveCertificates(certificates));
	}


}
