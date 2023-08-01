package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.UserPrincipal;
import com.backend.repository.postgres.ProjectDetailRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.security.CurrentUser;
import com.backend.service.ProjectApplicantService;

@RestController
@RequestMapping("/projectapplicants/")
public class ProjectApplicantController {
	
	@Autowired
	ProjectApplicantService projectApplicantService;
	

	
	@PostMapping("/list")
	public ResponseEntity<Object> getApplicantsByProject(@RequestParam(value = "project_id") Integer id){
		
		ResponseEntity<Object> status = projectApplicantService.getApplicantsByProject(id);
		return status;
	}


}
