package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.service.ApplicationCertificateService;

@RestController
@RequestMapping("/applicationCertificate")
public class ApplicationCertificateController {
	
	@Autowired
	private ApplicationCertificateService applicationCertificateService;
	
	@PostMapping("/get")
	public ResponseEntity<Object> getApplicationCertificate(@RequestParam Integer application_id, @RequestParam String certificate_type) throws PariveshException{
		
		return applicationCertificateService.getApplicationCertificate(application_id, certificate_type);
	}
}
