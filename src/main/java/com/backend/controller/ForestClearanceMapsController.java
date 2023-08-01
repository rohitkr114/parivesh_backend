package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.ForestClearanceMaps;
import com.backend.service.ForestClearanceMapsService;


@RestController
@RequestMapping("/forestClearanceMapDetails/")
public class ForestClearanceMapsController {
	@Autowired
	ForestClearanceMapsService forestClearanceMapsService;
	
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveForestApprovalDetails(@RequestParam(value = "fc_id") Integer id,    @RequestBody List<ForestClearanceMaps> forestClearanceMaps) {
		
		ResponseEntity<Object> status = forestClearanceMapsService.saveForestMapDetails(id ,forestClearanceMaps);
		return status;
	}
	
	@GetMapping("/list")
	public ResponseEntity<Object> getForestPriorAprovalDetails(@RequestParam(value = "fc_id") Integer id){
		
		return forestClearanceMapsService.getForestMapData(id);
	}
	
	
}
