package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.response.ResponseHandler;
import com.backend.service.CertificateConditionService;


@RestController
@RequestMapping("fc/certificate/Condition")
public class ConditionController {

	@Autowired
	public CertificateConditionService certificateConditionService;

	@PostMapping("/getCondition")
	public ResponseEntity<Object> getConditionByApplicationId(
			@RequestParam(value = "application_id") Integer applicationId) {
		return certificateConditionService.getConditionByApplicationId(applicationId);
	}
}
