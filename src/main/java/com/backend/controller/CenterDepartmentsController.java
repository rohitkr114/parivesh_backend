package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.ProjectListDto;
import com.backend.model.CenterDepartments;
import com.backend.service.CenterDepartmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/kyc/")
public class CenterDepartmentsController {

	@Autowired
	CenterDepartmentService centerDepartmentService;
	
	@GetMapping("/getCenterDepartments")
	public ResponseEntity<Object> getAllDepartments() {
		return(centerDepartmentService.getAllDepartments());		
	}
	
	@GetMapping("/getCenterDepartment")
	public ResponseEntity<Object> getDepartment(@RequestParam("id") Integer Id) {
		return(centerDepartmentService.getDepartment(Id));
	}
	
	@PostMapping("/deleteCenterDepartment")
	public ResponseEntity<Object> deleteDepartment(@RequestParam("id") Integer id){
		return centerDepartmentService.deleteDepartment(id);
	}
	
	@PostMapping("/addCenterDepartment")
	public ResponseEntity<Object> addDepartment(@RequestBody CenterDepartments centerDepartments){
		log.info("INFO ------------ Adding Departments "+centerDepartments);
		log.info("INFO ------------ After Adding Departments ");
		return centerDepartmentService.addDepartment(centerDepartments);
	}
}
