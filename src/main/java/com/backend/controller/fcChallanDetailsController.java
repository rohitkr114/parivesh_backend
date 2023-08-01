package com.backend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.FcChallanDetails;
import com.backend.service.FcChallanDetailsService;

@RestController()
@RequestMapping("/fcChallanDetails")
public class fcChallanDetailsController {
	
	@Autowired
	private FcChallanDetailsService fcChallanDetailsService;
	
	
	@PostMapping("/saveFcChallanDetails")
	public ResponseEntity<Object> saveFcChallanDetails(
			@RequestBody FcChallanDetails fcChallanDetails, HttpServletRequest request)
			throws PariveshException {
		return fcChallanDetailsService.saveFcChallanDetails(fcChallanDetails, request);
	}
	
	@PostMapping("/getFcChallanDetails")
	public ResponseEntity<Object> getFcChallanDetailsByApplicationId(@RequestParam Integer applicationId)
			throws PariveshException {
		return fcChallanDetailsService.getFcChallanDetailsByApplicationId(applicationId);
	}
}
