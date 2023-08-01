package com.backend.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ScenarioService {
	
	public ResponseEntity<Object> getScenario(Double area){
		try {			
			if(area>5)
				return ResponseHandler.generateResponse("Country Codes",HttpStatus.OK,"","Scenario 6");
			else if(area<=5)
				return ResponseHandler.generateResponse("Country Codes",HttpStatus.OK,"","Scenario 3");
			else
				return ResponseHandler.generateResponse("Country Codes",HttpStatus.OK,"","No Scenario Found");
		}
		catch(Exception ex) {
			return ResponseHandler.generateResponse("Get Scenario",HttpStatus.BAD_REQUEST,"Exception Occurred",ex.getMessage());	
		}
		
	}

}
