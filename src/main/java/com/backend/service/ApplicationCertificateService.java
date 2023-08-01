package com.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.ApplicationCertificate;
import com.backend.repository.postgres.ApplicationCertificateRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApplicationCertificateService {
	
	@Autowired
	private ApplicationCertificateRepository applicationCertificateRepository;
	
	public ResponseEntity<Object> getApplicationCertificate(Integer application_id, String certificate_type){
		try {
			ApplicationCertificate app= applicationCertificateRepository.getCertificateByIds(application_id, certificate_type)
					.orElseThrow(() -> new ProjectNotFoundException("Application Certificate not found with application id " + application_id+" and certificate type "+ certificate_type));
			
			return ResponseHandler.generateResponse("getting application certificate", HttpStatus.OK, "", app);
		} catch(Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting application certificate - ", e);
		}
	}

}
