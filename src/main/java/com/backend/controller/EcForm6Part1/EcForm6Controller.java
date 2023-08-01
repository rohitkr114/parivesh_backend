package com.backend.controller.EcForm6Part1;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.EcForm6Part1.EcForm6BasicDetails;
import com.backend.response.ResponseHandler;
import com.backend.service.EcForm6Part1.EcForm6BasicDetailsService;

@RestController
@RequestMapping("/ecForm6")
public class EcForm6Controller {

	@Autowired
	private EcForm6BasicDetailsService ecForm6Service;

	
	// Generate Proposal No.
	/*
	@PostMapping("/generateProposalNoWithoutApp")
	public ResponseEntity<Object> generateProposalNoWithoutApp(@RequestParam Integer caf_id,
			Integer ecId, HttpServletRequest request) 
	{
		
	return ResponseHandler.generateResponse("Save Ec Form 6 Basic Details", HttpStatus.OK, "",
				ecForm6Service.generateProposalNoWithoutApp(caf_id, ecId, request));
		
	}
	*/

	//@Autowired
	//private EcForm6EiaConsultantDetailsService ecForm6EiaConsultantDetailsService;

	// [Method to save EcForm6 Basic Details]
	//,@RequestParam Integer parent_caf_id
	@Transactional
	@PostMapping("/saveBasicDetails")
	public ResponseEntity<Object> saveEcForm6(@RequestBody EcForm6BasicDetails ecForm6, @RequestParam Integer caf_id,
			Integer ecId, HttpServletRequest request) {
		Integer integrityCheck = ecForm6Service.isIntegrityCheck(ecForm6, caf_id);
		if (integrityCheck == 0) {
			return ResponseHandler.generateResponse("CAF " + caf_id + " does not Exist", HttpStatus.OK, "0", null);
		} else if (integrityCheck == 1) {
			return ResponseHandler.generateResponse(
					"CAF " + caf_id + " does not Exist in project Id " + ecForm6.getProject_id(), HttpStatus.OK, "0",
					null);
			//Save Ec Form 6 Basic Details
		}
		return ResponseHandler.generateResponse("Basic Information Saved Successfully", HttpStatus.OK, "",
				ecForm6Service.saveEcForm6(ecForm6, caf_id, ecId, request));
	}

	// [Method to View EC Form 6 Basic Details]
	//@PostMapping("/viewBasicDetails")
	@PostMapping("/getBasicDetails")
	public ResponseEntity<Object> view(@RequestParam Integer ecId, HttpServletRequest request) throws PariveshException {
		return ResponseHandler.generateResponse("Success", HttpStatus.OK, "", ecForm6Service.view(ecId));
	}
}