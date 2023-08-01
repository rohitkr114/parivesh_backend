package com.backend.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.UserPrincipal;
import com.backend.model.Consultant;
import com.backend.security.CurrentUser;
import com.backend.service.ConsultantService;

@RestController
@RequestMapping("/consultant/")
public class ConsultantController {

	/*
	 * C R U D
	 * 
	 */
	
	@Autowired
	ConsultantService consultantService;
	
	@PostMapping("save")
	public ResponseEntity<Object> saveConsultant(@RequestBody List<Consultant> consultants,@RequestParam Integer ConsultantOrganisationId){
		return consultantService.saveConsultant(consultants, ConsultantOrganisationId);
	}
	
	@PostMapping("get")
	public ResponseEntity<Object> getConsultant(@RequestParam Integer ConsultantId){
		return consultantService.getConsultant(ConsultantId);
	}
	
	@PostMapping("getByProjectId")
	public ResponseEntity<Object> getConsultantByProjectId(@RequestParam Integer project_id){
		return consultantService.getConsultantByProjectId(project_id);
	}
	
	@PostMapping("delete")
	public ResponseEntity<Object> deleteConsultant(@RequestParam Integer Consultantid){
		return consultantService.deleteConsultantorganisation(Consultantid);
	}
	
	@Transactional
	@PostMapping("activate")
	public ResponseEntity<Object> activateConsultant(@RequestBody List<Integer> consultants){
		return consultantService.activateConsultant(consultants);
	}
	
	@PostMapping("update")
	public ResponseEntity<Object> updateConsultant(@RequestBody Consultant consultant){
		return consultantService.updateConsultant(consultant);
	}
	
	@PostMapping("consultantList")
	public ResponseEntity<Object> getUserId(@RequestParam(required = false) String active, @CurrentUser UserPrincipal currentuser){
		return consultantService.getConsultantList(active,currentuser);
	}
	
}
