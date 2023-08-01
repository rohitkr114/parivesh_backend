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

import com.backend.model.EnvironmentClearanceProjectAreaDetails;
import com.backend.response.ResponseHandler;
import com.backend.service.ECProjectAreaDetailsService;

@RestController
@RequestMapping("/ECProjectAreaDetails")
public class ECProjectAreaDetailController {

	@Autowired
	ECProjectAreaDetailsService eCProjectAreaDetailsService;
	
	
	
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveECprojectAreaDetails(@RequestParam("id") Integer id, @RequestBody List<EnvironmentClearanceProjectAreaDetails> environmentClearanceProjectAreaDetails ) {
		
		return ResponseHandler.generateResponse("Save Environment Clearance Data",HttpStatus.OK,"",eCProjectAreaDetailsService.saveEnvironmentClearanceProjectAreaDetails(id ,environmentClearanceProjectAreaDetails));
	   
	}
	
	
	
	@PostMapping("/list")
	public ResponseEntity<Object> getECProjectActivityDetails(@RequestParam(value = "ec_id") Integer id){
		
		ResponseEntity<Object> status = eCProjectAreaDetailsService.getECProjectAreaData(id);
		
		return status;
		
	}
	
	
	@PostMapping("/delete")
	public ResponseEntity<Object> deleteECProjectActivityDetails(@RequestParam(value = "id") Integer id){
		
		ResponseEntity<Object> status = eCProjectAreaDetailsService.deleteECProjectAreaData(id);
		
		return status;
		
	}
	
	
	
}
