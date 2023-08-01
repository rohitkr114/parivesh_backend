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
import com.backend.model.DesignationEntity;
import com.backend.security.CurrentUser;
import com.backend.service.UserDesignationChangeLogService;

@RestController
@RequestMapping("/userDesignationChangeLog")
public class UserDesignationChangeLogContoller {
	
	@Autowired
	private UserDesignationChangeLogService userDesignationChangeLogService;
	
	@PostMapping("/saveDesignation")
	public ResponseEntity<Object> saveDesignation(@RequestBody DesignationEntity designation) throws PariveshException{
		
		return userDesignationChangeLogService.saveDesignation(designation);
	}
	
	@PostMapping("/getAllDesignation")
	public ResponseEntity<Object> getAllDesignation() throws PariveshException{
		
		return userDesignationChangeLogService.getAllDesignation();
	}
	
	@PostMapping("/updateDesignation")
	public ResponseEntity<Object> updateDesignation(@CurrentUser UserPrincipal principal, @RequestParam String currentDesignationName, @RequestParam String newDesignationName) throws PariveshException{
		
		return userDesignationChangeLogService.updateDesignation(principal,currentDesignationName, newDesignationName);
	}
}
