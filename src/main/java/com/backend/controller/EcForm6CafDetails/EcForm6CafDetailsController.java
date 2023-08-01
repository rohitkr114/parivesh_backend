package com.backend.controller.EcForm6CafDetails;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.EcForm6CafDetails.EcForm6CafDetails;
import com.backend.response.ResponseHandler;
import com.backend.service.EcForm6CafDetailsService.EcForm6CafDetailsService;

@RestController
@RequestMapping("/ecForm6")
public class EcForm6CafDetailsController {

	@Autowired
	private EcForm6CafDetailsService ecForm6CafDetailsService;

	@Transactional
	@PostMapping("/saveCafDetails")
	//public ResponseEntity<Object> saveEcForm6(@RequestParam Integer new_caf_id, @RequestParam Integer parent_caf_id,
		//	@RequestParam Integer ecId, @RequestParam Integer status) throws PariveshException {

	public ResponseEntity<Object> saveEcForm6(@RequestParam Integer new_caf_id,@RequestParam  Integer parent_caf_id,
			@RequestParam Integer ecId,@RequestParam Integer status) throws PariveshException {
	
		EcForm6CafDetails ecForm6CafDetails = new EcForm6CafDetails();
		ecForm6CafDetails.setNewCaf(new_caf_id);
		ecForm6CafDetails.setParentCaf(parent_caf_id);
		ecForm6CafDetails.setStatus(status);
		ecForm6CafDetails.setEcId(ecId);
		return ResponseHandler.generateResponse("Save Ec Form 6 Caf Details", HttpStatus.OK, "",
				ecForm6CafDetailsService.saveEcForm6CafDetails(ecForm6CafDetails));
	}

	@PostMapping("/getCafDetails")
	public ResponseEntity<Object> getEcForm6CafDetailsByNewCafId(@RequestParam Integer new_caf_id)
			throws PariveshException {
		return ResponseHandler.generateResponse("Success", HttpStatus.OK, "",
				ecForm6CafDetailsService.getEcForm6CafDetailsByNewCafId(new_caf_id));
	}

	@PostMapping("/updateCafDetailsStatus")
	public ResponseEntity<Object> updateEcForm6CafDetailsByNewCafId(@RequestParam Integer new_caf_id,Integer ec_id,Integer status)
			throws PariveshException {
		return ResponseHandler.generateResponse("Success", HttpStatus.OK, "",
				ecForm6CafDetailsService.updateEcForm6CafDetailsByNewCafId(new_caf_id,ec_id,status));
	}



}
