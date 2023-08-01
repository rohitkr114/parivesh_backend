package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.dto.ComplianceDTO;
import com.backend.repository.postgres.FAQRepository;
import com.backend.response.ResponseHandler;

@Service
public class TestService {

	@Autowired
	private FAQRepository faqRepository;
	
	public ResponseEntity<Object> getCompliance(String email) {
		try {
			List<ComplianceDTO> responsePd = faqRepository.getCompliance(email);
			return ResponseHandler.generateResponse("Compliance Data by Email Id", HttpStatus.OK, null, responsePd);
		} catch (Exception e) {
			return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, null, e.getLocalizedMessage());
		}
}}
