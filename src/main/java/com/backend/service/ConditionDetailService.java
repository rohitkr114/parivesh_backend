package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.backend.dto.CompliancedetailDTO;
import com.backend.repository.postgres.FAQRepository;
import com.backend.response.ResponseHandler;

@Service
public class ConditionDetailService {

	@Autowired
	private FAQRepository faqRepository;
	
	public ResponseEntity<Object> getCompliancedetail(Integer proposal_id) {
		try {
			List<CompliancedetailDTO> responsePd = faqRepository.getCompliancedetail(proposal_id);
			return ResponseHandler.generateResponse("Compliance Detail Data by Proposal Id", HttpStatus.OK, null, responsePd);
		} catch (Exception e) {
			return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, null, e.getLocalizedMessage());
		}
}
	
}
