package com.backend.controller.EcForm11;

import java.util.List;

import com.backend.model.EcForm11.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.service.EcForm11.EcForm11Service;

@RestController
@RequestMapping("/ecForm11")
public class EcForm11Controller {
	
	@Autowired
	private EcForm11Service ecForm11Service;
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveProjectDetails(@RequestParam(required=false) Integer ecId,@RequestParam(required=false) Integer ecPartAId, @RequestParam Integer clearanceId,
			@RequestParam Integer cafId, @RequestBody EcForm11ProjectDetails ecForm11ProjectDetails) throws PariveshException{
		
		return ecForm11Service.saveProjectDetails(ecId, ecPartAId, clearanceId, cafId, ecForm11ProjectDetails);
	}
	
	@PostMapping("/get")
	public ResponseEntity<Object> getEcForm11(@RequestParam Integer id) throws PariveshException{
		
		return ecForm11Service.getEcForm11(id);
	}
	
	@PostMapping("/saveProjectActivityDetails")
	public ResponseEntity<Object> saveProjectActivityDetails(@RequestBody List<EcForm11ProjectActivityDetails> activityDetails,
			@RequestParam Integer ecForm11Id) throws PariveshException{
		
		return ecForm11Service.saveProjectActivityDetails(activityDetails, ecForm11Id);
	}
	
	@PostMapping("/getProjectActivityDetails")
	public ResponseEntity<Object> getProjectActivityDetails(@RequestParam Integer ecForm11Id) throws PariveshException{
		
		return ecForm11Service.getProjectActivityDetails(ecForm11Id);
	}
	
	@PostMapping("/deleteProjectActivityDetails")
	public ResponseEntity<Object> deleteProjectActivityDetails(@RequestParam Integer id) throws PariveshException{
		 
		 return ecForm11Service.deleteProjectActivityDetails(id);
	 }

	@PostMapping("/saveOtherDetails")
	public ResponseEntity<Object> saveOtherDetails(@RequestBody EcForm11OtherDetails otherDetails, @RequestParam Integer ecForm11Id){
		return ecForm11Service.saveOtherDetails(otherDetails,ecForm11Id);
	}
	
	@PostMapping("/saveSPCB")
	public ResponseEntity<Object> saveSPCBDetails(@RequestBody List<EcForm11SPCBDetails> ecForm11SPCBDetails,
			@RequestParam Integer ecForm11Id) throws PariveshException{
		
		return ecForm11Service.saveSPCBDetails(ecForm11SPCBDetails, ecForm11Id);
	}
	
	@PostMapping("/getSPCB")
	public ResponseEntity<Object> getSPCBDetails(@RequestParam Integer ecForm11Id) throws PariveshException{
		
		return ecForm11Service.getSPCBDetails(ecForm11Id);
	}
	
	@PostMapping("/deleteSPCB")
	public ResponseEntity<Object> deleteSPCBDetails(@RequestParam Integer id) throws PariveshException{
		
		return ecForm11Service.deleteSPCBDetails(id);
	}
	
	@PostMapping("/saveUndertaking")
	public ResponseEntity<Object> saveUndertaking(@RequestParam Integer ecForm11Id, @RequestBody EcForm11Undertaking ecForm11Undertaking,@RequestParam(required = false) Boolean is_submit) throws PariveshException{
		
		return ecForm11Service.saveUndertaking(ecForm11Id, ecForm11Undertaking,is_submit);
	}
		
	
}
