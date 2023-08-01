package com.backend.controller.FcFormCPartIV;

import com.backend.exceptions.PariveshException;
import com.backend.model.FcFormAPartIV.FcFormAPartIVBasicDetails;
import com.backend.model.FcFormCPartIV.FcFormCPartIVBasicDetails;
import com.backend.service.FcFormAPartIV.FcFormAPartIVService;
import com.backend.service.FcFormCPartIV.FcFormCPartIVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/fc/formc/part4")
public class FcFormCPartIVController {

	@Autowired
	private FcFormCPartIVService fcFormCPartDService;

	// Section Basic details
	// for saving the basic details
	@PostMapping("/saveBasicDetails")
	public ResponseEntity<Object> saveFCformCPartIVBasicDetails(
			@RequestBody FcFormCPartIVBasicDetails fcFormCPartIVBasicDetails, @RequestParam Integer clearenceID,
			HttpServletRequest request) throws PariveshException {
		return fcFormCPartDService.saveFCformCPartIVBasicDetails(fcFormCPartIVBasicDetails, clearenceID, request);
	}

	// Section Basic details
	// for fetching the basic details
	@PostMapping("/getBasicDetails")
	public ResponseEntity<Object> getFCformCPartIVBasicDetails(@RequestParam Integer fcFormCPartIVBasicDetailsId)
			throws PariveshException {
		return fcFormCPartDService.getFCformCPartIVBasicDetails(fcFormCPartIVBasicDetailsId);
	}

}
