package com.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.exceptions.PariveshException;
import com.backend.model.StandardToRSequence;
import com.backend.repository.postgres.StandardToRSequenceRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StandardToRSequenceService {
	
	@Autowired
	private StandardToRSequenceRepository standardToRSequenceRepository;
	
	public ResponseEntity<Object> saveStandardToRSequence(StandardToRSequence standardToRSequence){
		try {
			StandardToRSequence response= standardToRSequenceRepository.save(standardToRSequence);
			
			return ResponseHandler.generateResponse("saving standard tor sequence", HttpStatus.OK, "", response);
		} catch(Exception e) {
			log.error("Encountered Exception", e);
			throw new PariveshException("Error in Saving standard tor sequence", e);
		}
	}
	
	public ResponseEntity<Object> getStandardToRSequence(Integer activity_id){
		try {
			StandardToRSequence response= standardToRSequenceRepository.getStandardToRSequenceByActivityId(activity_id);
			
			return ResponseHandler.generateResponse("getting standard tor sequence", HttpStatus.OK, "", response);
		} catch(Exception e) {
			log.error("Encountered Exception", e);
			throw new PariveshException("Error in getting standard tor sequence", e);
		}
	}
}
