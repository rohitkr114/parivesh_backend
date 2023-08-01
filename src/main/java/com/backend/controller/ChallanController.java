package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.PaymentCompletionDTO;
import com.backend.service.ChallanService;


@RestController
@RequestMapping("/challan/")
public class ChallanController {

	@Autowired
	private ChallanService challanService;
	
	@PostMapping("insert")
	public ResponseEntity<Object> insertChallan(@RequestBody PaymentCompletionDTO completionDTO){
		return challanService.insertChallan(completionDTO);
	}
}
