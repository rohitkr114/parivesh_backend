package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.CenterDepartments;
import com.backend.model.ForestClearanceComponentDetail;
import com.backend.response.ResponseHandler;
import com.backend.service.ForestClearanceComponentDetailService;


@RestController
@RequestMapping("/forestClearanceComponentDetails")
public class ForestClearanceComponentDetailController {
   
	@Autowired
	private ForestClearanceComponentDetailService forestClearanceComponentDetailService;

	@PostMapping("/save")
	public ResponseEntity<Object> saveForestComponentDetails(@RequestParam("fc_id") Integer id, @RequestBody ForestClearanceComponentDetail forestClearanceComponentDetail ) {
		
		return ResponseHandler.generateResponse("Save Forest Component Data",HttpStatus.OK,"",forestClearanceComponentDetailService.saveForestComponentDetails(id, forestClearanceComponentDetail));
	   
	}
	
	
	
	@PostMapping("/list")
	public ResponseEntity<Object> getForestComponentDetails(@RequestParam(value = "fc_id") Integer id){
		
		ResponseEntity<Object> status = forestClearanceComponentDetailService.getForestClearanceComponentData(id);
		
		return status;
		
	}
	
	
	@PostMapping("/delete")
	public ResponseEntity<Object> deleteForestComponentDetail(@RequestParam(value = "id") Integer id){
		
		ResponseEntity<Object> status = forestClearanceComponentDetailService.deleteForestClearanceComponentData(id);
		return status;
		
	}
	
	
	
}
