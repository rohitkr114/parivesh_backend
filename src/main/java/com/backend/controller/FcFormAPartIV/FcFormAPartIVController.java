package com.backend.controller.FcFormAPartIV;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.FcFormAPartIII.FcFormAPartIIICheckListDetails;
import com.backend.model.FcFormAPartIV.FcFormAPartIVBasicDetails;
import com.backend.service.FcFormAPartIV.FcFormAPartIVService;

@RestController
@RequestMapping("/fc/forma/partd")
public class FcFormAPartIVController {

	@Autowired
	private FcFormAPartIVService fcFormAPartDService;

	// Section Basic details
	// for saving the basic details
	@PostMapping("/saveBasicDetails")
	public ResponseEntity<Object> saveFCformAPartIVBasicDetails(
			@RequestBody FcFormAPartIVBasicDetails fcFormAPartIVBasicDetails, @RequestParam Integer clearenceID,
			HttpServletRequest request) throws PariveshException {
		return fcFormAPartDService.saveFCformAPartIVBasicDetails(fcFormAPartIVBasicDetails, clearenceID, request);
	}

	// Section Basic details
	// for fetching the basic details
	@PostMapping("/getBasicDetails")
	public ResponseEntity<Object> getFCformAPartIVBasicDetails(@RequestParam Integer fcFormAPartIVBasicDetailsId)
			throws PariveshException {
		return fcFormAPartDService.getFCformAPartIVBasicDetails(fcFormAPartIVBasicDetailsId);
	}

}
