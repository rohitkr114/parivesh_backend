package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.security.CurrentUser;
import com.backend.service.UserEmailChangeLogService;

@RestController
@RequestMapping("/userEmailChangeLog")
public class UserEmailChangeLogController {
	
	@Autowired
	private UserEmailChangeLogService userEmailChangeLogService;
	
	@PostMapping("/updateEmail")
	public ResponseEntity<Object> updateEmail(@CurrentUser UserPrincipal principal,@RequestParam String currentEmail, @RequestParam String newEmail) throws PariveshException{
		
		return userEmailChangeLogService.updateEmail(principal,currentEmail, newEmail);
	}
}
