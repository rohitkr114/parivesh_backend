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

import com.backend.model.EcProjectImplementationDetails;
import com.backend.response.ResponseHandler;
import com.backend.service.EcProjectImplementationDetailsService;

@RestController
@RequestMapping("/ecProjectImplementationDetails")
public class EcProjectImplementationDetailsController {

	@Autowired
	EcProjectImplementationDetailsService ecProjectImplementationDetailsService;
	
	
	
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveProdTransportDetails(@RequestParam("id") Integer id, @RequestBody List<EcProjectImplementationDetails> ecProjectImplementationDetails ) {
		
		return ResponseHandler.generateResponse("Save Environmement Data",HttpStatus.OK,"", ecProjectImplementationDetailsService.saveEcProjectImplementationDetails(id, ecProjectImplementationDetails));
	   
	}
	
	
	
	@PostMapping("/list")
	public ResponseEntity<Object> getECProjectActivityDetails(@RequestParam(value = "ec_id") Integer id){
		
		ResponseEntity<Object> status = ecProjectImplementationDetailsService.getEcProjectImplementationDetails(id);
		return status;
		
	}
	
	@PostMapping("/delete")
	public ResponseEntity<Object> deleteECProjectActivityDetails(@RequestParam(value = "id") Integer id){
		
		ResponseEntity<Object> status = ecProjectImplementationDetailsService.deleteEcProjectImplementationDetails(id);
		return status;
		
	}
	
	
}
