package com.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.UserPrincipal;
import com.backend.model.ConsultantOrganisation;
import com.backend.security.CurrentUser;
import com.backend.service.ConsultantOrganisationService;
import com.backend.service.ConsultantService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ConsultantOrganisation")
public class ConsultantOrganisationController {

	/*
	 * C R U D
	 * 
	 */
	@Autowired
	private ConsultantOrganisationService consultantOrganisationService;
	
	@Autowired
	private ConsultantService consultantService;
	
	
	@PostMapping("save")
	public ResponseEntity<Object> saveConsultantOrganisation(@RequestBody List<ConsultantOrganisation> consultantOrganisation){
		return consultantOrganisationService.saveConsultantOrganisation(consultantOrganisation);
	}
	
	@Transactional
	@PostMapping("get")
	public ResponseEntity<Object> getConsultantOrganisation(@RequestParam String ConsultantOrgId,@RequestParam(required = false,value="Step") Integer Step,HttpServletRequest request){
		//return consultantOrganisationService.getConsultantOrganisationByAPI(ConsultantOrgId,Step);
		return consultantOrganisationService.getQCIConsultantOrganisation(ConsultantOrgId,Step, request);
	}
	
//	@Transactional
//	@PostMapping("getConsultantorganisation")
//	public ResponseEntity<Object> getQCIConsultantOrganisation(@RequestParam String ConsultantOrgId,@RequestParam(required = false,value="Step") Integer Step){
//		//return consultantOrganisationService.getConsultantOrganisationByAPI(ConsultantOrgId,Step);
//		return consultantOrganisationService.getQCIConsultantOrganisation(ConsultantOrgId,Step);
//	}
//	
//	@Transactional
//	@PostMapping("getByAPI")
//	public ResponseEntity<Object> getConsultantOrganisationByAPI(@RequestParam String ConsultantOrgId,@RequestParam(required = false,value="Step") Integer Step){
//		return consultantOrganisationService.getConsultantOrganisationByAPI(ConsultantOrgId,Step);
//	}
	
	@PostMapping("delete")
	public ResponseEntity<Object> deleteConsultantorganisation(@RequestParam Integer ConsultantOrgid){
		return consultantOrganisationService.deleteConsultantorganisation(ConsultantOrgid);
	}
	
	@PostMapping("update")
	public ResponseEntity<Object> updateConsultantOrganisation(@RequestBody ConsultantOrganisation consultantOrganisation){
		return consultantOrganisationService.updateConsultantOrganisation(consultantOrganisation);
	}
	
	
	@PostMapping("allConsultant")
	public ResponseEntity<Object> getAllConsultant(@RequestParam(required = false,value="orgId") Integer orgId){
		return consultantService.getAllConsultants(orgId);
	}
	
	@PostMapping("listOrganisations")
	public ResponseEntity<Object> getAllConsultantOrganisation(){
		return consultantOrganisationService.getAllOrganisation();
	}
	
	@PostMapping("syncConsultantOrganisation")
	public ResponseEntity<Object> syncConsultantOrganisation(@RequestParam(required = false,value="orgId") String orgId,
			@CurrentUser UserPrincipal currentUser,HttpServletRequest request){
		log.info("--------ORG ID in syncConsultantOrganisation"+orgId);
		return consultantOrganisationService.syncQCIConsultantOrganisation(orgId,currentUser,request);
	}
	
	
	@PostMapping("getUpdateConsultantList")
	public String getUpdateConsultantOrganisation(){

		consultantOrganisationService.getupdateConsultantsOrganisation();
		return "Success";
	}
	
	
	
	
	
	
}

