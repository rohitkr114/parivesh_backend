package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.GeneralRemarks;
import com.backend.security.CurrentUser;
import com.backend.service.GeneralRemarksService;

@RestController
@RequestMapping("/generalRemarks")
public class GeneralRemarksController {
	
	@Autowired
	private GeneralRemarksService generalRemarksService;
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveGeneralRemarks(@RequestBody GeneralRemarks generalRemarks, @CurrentUser UserPrincipal principal) throws PariveshException{
		
		return generalRemarksService.saveGeneralRemarks(generalRemarks,principal);
	} 
	
	@PostMapping("/get")
	public ResponseEntity<Object> getGeneralRemarks(@RequestParam Integer ref_id, @RequestParam String ref_type) throws PariveshException{
		
		
		return generalRemarksService.getGeneralRemarks(ref_id, ref_type);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<Object> deleteGeneralRemarks(@RequestParam Integer id) throws PariveshException{
		
		return generalRemarksService.deleteGeneralRemarks(id);
	}
}
