package com.backend.controller.FcFormBPartIV;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.FcFormBPartIV.FcFormBPartIVBasicDetails;
import com.backend.service.FcFormBPartIV.FcFormBPartIVService;

@RestController
@RequestMapping("/fc/formB/partIV")
public class FcFormBPartIVController {

	@Autowired
	private FcFormBPartIVService fcFormBPartIVService;
	
	@PostMapping("/saveBasicDetails")
    public ResponseEntity<Object> saveFCformBPartIVBasicDetails(@RequestBody FcFormBPartIVBasicDetails fcFormBPartIVBasicDetails,
    		@RequestParam Integer clearenceID, HttpServletRequest request) throws PariveshException{
		return fcFormBPartIVService.saveFCformBPartIVBasicDetails(fcFormBPartIVBasicDetails, clearenceID, request);
	}
	
	@PostMapping("/getBasicDetails")
	public ResponseEntity<Object> getFCformBPartIVBasicDetails(@RequestParam Integer fcFormBPartIVBasicDetailsId) throws PariveshException{
		return fcFormBPartIVService.getFCformBPartIVBasicDetails(fcFormBPartIVBasicDetailsId);
	}
}
