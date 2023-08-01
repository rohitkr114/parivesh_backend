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
import com.backend.model.MsRecommendation;
import com.backend.model.MsRecommendationEACDetails;
import com.backend.service.MsRecommendationService;

@RestController
@RequestMapping("/msRecommendation")
public class MsRecommendationController {
	
	@Autowired 
	private MsRecommendationService msRecommendationService;
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveMsRecommendation(@RequestBody MsRecommendation msRecommendation,
			@RequestParam(required = false) Integer proposalId,HttpServletRequest request) throws PariveshException{
		return msRecommendationService.saveMsRecommendation(msRecommendation,proposalId,request);
	}
	
	@PostMapping("/get")
	public ResponseEntity<Object> getMsRecommendation(@RequestParam Integer id){
		return msRecommendationService.getMsRecommendation(id);
	}
	
	@PostMapping("/getList")
	public ResponseEntity<Object> getMsRecommendationList(){
		return msRecommendationService.getMsRecommendationList();
	}
	
	@PostMapping("/saveEACDetails")
	public ResponseEntity<Object> saveMsRecommendationEACDetails(@RequestBody MsRecommendationEACDetails msRecommendationEACDetails,@RequestParam Integer proposalId,HttpServletRequest request){
		return msRecommendationService.saveMsRecommendationEACDetails(msRecommendationEACDetails,proposalId,request);
	}
	
	@PostMapping("/getEACDetails")
	public ResponseEntity<Object> getMsRecommendationEACDetails(@RequestParam Integer id){
		return msRecommendationService.getMsRecommendationEACDetails(id);
	}

}
