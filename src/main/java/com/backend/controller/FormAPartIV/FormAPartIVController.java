package com.backend.controller.FormAPartIV;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.FormAPartIV.FormAPartIVBasicDetails;
import com.backend.service.FormAPartIV.FormAPartIVService;


@RestController
@RequestMapping("/fc/formA/partIV")
public class FormAPartIVController {

	@Autowired
	private FormAPartIVService formAPartIVService;
	
	@PostMapping("/saveBasicDetails")
	public ResponseEntity<Object> saveFCformAPartIVBasicDetails(@RequestBody FormAPartIVBasicDetails formAPartIVBasicDetails,
			@RequestParam Integer clearenceID, HttpServletRequest request) throws PariveshException{
		return formAPartIVService.saveFCformAPartIVBasicDetails(formAPartIVBasicDetails, clearenceID, request);
	}

	@PostMapping("/getBasicDetails")
	public ResponseEntity<Object> getFCformAPartIVBasicDetails(@RequestParam Integer fcFormAPartIVBasicDetailsId) throws PariveshException{
		return formAPartIVService.getFCformAPartIVBasicDetails(fcFormAPartIVBasicDetailsId);
	}

}
