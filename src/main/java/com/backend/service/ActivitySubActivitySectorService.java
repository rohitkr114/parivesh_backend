package com.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.repository.postgres.ActivitySubActivitySectorRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Service
public class ActivitySubActivitySectorService {

	@Autowired
	private ActivitySubActivitySectorRepository activitySectorRepository;
	
	public ResponseEntity<Object> getSector(Integer actId,Integer subActId){
		
	try {	
		return ResponseHandler.generateResponse("Get Sector by Activity and SubActivity", HttpStatus.OK, "",
				activitySectorRepository.getSector(actId, subActId));
	} catch (Exception ex) {
		return ResponseHandler.generateResponse("Save EC Sector Form", HttpStatus.EXPECTATION_FAILED, "",
				ex.getMessage());
	}
	}
}
