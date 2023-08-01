package com.backend.controller.FcFormAPartIII;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.FcFormAPartIII.FcFormAPartIIIBasicDetails;
import com.backend.model.FcFormAPartIII.FcFormAPartIIICheckListDetails;
import com.backend.service.FcFormAPartIII.FcFormAPartIIIService;

@RestController
@RequestMapping("/fc/forma/partc")
public class FcFormAPartIIIController {

	@Autowired
	private FcFormAPartIIIService fcFormAPartCService;

	// Section Basic details
	// for saving the basic details
	@PostMapping("/saveBasicDetails")
	public ResponseEntity<Object> saveFCformAPartIIIBasicDetails(
			@RequestBody FcFormAPartIIIBasicDetails fcFormAPartIIIBasicDetails, @RequestParam Integer clearenceID,
			HttpServletRequest request) throws PariveshException {
		return fcFormAPartCService.saveFCformAPartIIIBasicDetails(fcFormAPartIIIBasicDetails, clearenceID, request);
	}

	// Section Basic details
	// for fetching the basic details
	@PostMapping("/getBasicDetails")
	public ResponseEntity<Object> getFCformAPartIIIBasicDetails(@RequestParam Integer fcFormAPartIIIBasicDetailsId)
			throws PariveshException {
		return fcFormAPartCService.getFCformAPartIIIBasicDetails(fcFormAPartIIIBasicDetailsId);
	}

	// CheckList Section
	@PostMapping("/saveCheckListData")
	public ResponseEntity<Object> saveFcFormAPartIIIChecklist(
			@RequestBody FcFormAPartIIICheckListDetails fcFormAPartIIICheckListDetails,
			@RequestParam Integer fcFormAPartIIIBasicDetailsId,HttpServletRequest request) throws PariveshException {
		return fcFormAPartCService.saveFcFormAPartIIIChecklist(fcFormAPartIIICheckListDetails,
				fcFormAPartIIIBasicDetailsId,request);
	}

}
