package com.backend.controller.FcFormAPartIIINodal;

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
import com.backend.model.FcFormAPartIIINodal.FcFormAPartIIINodalBasicDetails;
import com.backend.model.FcFormAPartIIINodal.FcFormAPartIIINodalCheckListDetails;
import com.backend.service.FcFormAPartIII.FcFormAPartIIIService;
import com.backend.service.FcFormAPartIIINodal.FcFormAPartIIINodalService;

@RestController
@RequestMapping("/fc/formaNodal/partIII")
public class FcFormAPartIIINodalController {

	@Autowired
	private FcFormAPartIIINodalService fcFormAPartCService;

	// Section Basic details
	// for saving the basic details
	@PostMapping("/saveBasicDetails")
	public ResponseEntity<Object> saveFCformAPartIIIBasicDetails(
			@RequestBody FcFormAPartIIINodalBasicDetails fcFormAPartIIIBasicDetails, @RequestParam Integer fcFormAPartIIICheckListDetailsId,  HttpServletRequest request) throws PariveshException {
		return fcFormAPartCService.saveFCformAPartIIIBasicDetails(fcFormAPartIIIBasicDetails, fcFormAPartIIICheckListDetailsId, request);
	}

	// Section Basic details
	// for fetching the basic details
//	@PostMapping("/getBasicDetails")
//	public ResponseEntity<Object> getFCformAPartIIIBasicDetails(@RequestParam Integer fcFormAPartIIIBasicDetailsId)
//			throws PariveshException {
//		return fcFormAPartCService.getFCformAPartIIIBasicDetails(fcFormAPartIIIBasicDetailsId);
//	}
	
	// for fetching the basic details
	@PostMapping("/getCheckListDetails")
	public ResponseEntity<Object> getFCformAPartIIICheckList(@RequestParam Integer fcFormAPartIIICheckListDetailsId)
			throws PariveshException {
		return fcFormAPartCService.getFCformAPartIIIChecklist(fcFormAPartIIICheckListDetailsId);
	}

	// CheckList Section
	@PostMapping("/saveCheckListData")
	public ResponseEntity<Object> saveFcFormAPartIIIChecklist(
			@RequestBody FcFormAPartIIINodalCheckListDetails fcFormAPartIIICheckListDetails, @RequestParam Integer clearenceID,
			HttpServletRequest request) throws PariveshException {
		return fcFormAPartCService.saveFcFormAPartIIIChecklist(fcFormAPartIIICheckListDetails, clearenceID, request);
	}

}
