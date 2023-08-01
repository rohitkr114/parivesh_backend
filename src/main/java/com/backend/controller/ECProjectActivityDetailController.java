package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.EnvironmentClearanceProjectActivityDetails;
import com.backend.response.ResponseHandler;
import com.backend.service.ECProjectActivityDetailsServive;

@RestController
@RequestMapping("/ECProjectActivityDetails")
public class ECProjectActivityDetailController {
	
	
	@Autowired
	ECProjectActivityDetailsServive eCProjectActivityDetailsServive;
	
	
	
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveECprojectActivityDetails(@RequestParam("ec_id") Integer ecId, @RequestBody List<EnvironmentClearanceProjectActivityDetails> environmentClearanceProjectActivityDetails ) {
		
		return ResponseHandler.generateResponse("Save EC Project Activity Data",HttpStatus.OK,"",eCProjectActivityDetailsServive.saveEnvironmentClearanceProjectActivityDetails(ecId, environmentClearanceProjectActivityDetails));
	   
	}
	
	
	
	@PostMapping("/list")
	public ResponseEntity<Object> getECProjectActivityDetails(@RequestParam(value = "ec_id") Integer id){
		
		ResponseEntity<Object> status = eCProjectActivityDetailsServive.getECProjectActivityData(id);
		
		return status;
		
	}
	
	@PostMapping("/delete")
	public ResponseEntity<Object> deleteECProjectActivityDetails(@RequestParam(value = "id") Integer id){
		
		ResponseEntity<Object> status = eCProjectActivityDetailsServive.deleteECProjectActivityData(id);
		
		return status;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
