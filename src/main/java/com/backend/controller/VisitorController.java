package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.service.VisitorService;

@RestController
@RequestMapping("/visitor/")
public class VisitorController {
	
	@Autowired
	VisitorService visitorService;
	
	@PostMapping("list")
	public ResponseEntity<Object> getVisitor(){
		return visitorService.getVisitors();
	}
	
	@PostMapping("increment")
	public ResponseEntity<Object> incrementVisitor(){
		return visitorService.incrementTotal();
	}
}
