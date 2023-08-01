package com.backend.controller.FcComplianceCondition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.UserPrincipal;
import com.backend.model.FcComplianceCondition.FcComplianceCondition;
import com.backend.security.CurrentUser;
import com.backend.service.FcComplianceCondition.FcComplianceConditionService;

@RestController
@RequestMapping("/fcComplianceController")
public class FcComplianceConditionController {
	
	@Autowired
	private FcComplianceConditionService fcComplianceConditionService;
	
	@PostMapping("/save")
	public ResponseEntity<Object> save(@RequestBody FcComplianceCondition fcComplianceCondition,@CurrentUser UserPrincipal principal){
		return fcComplianceConditionService.save(fcComplianceCondition,principal);
	}
	
	@PostMapping("/getByApplicationId")
	public ResponseEntity<Object> get(@RequestParam Integer application_id){
		return fcComplianceConditionService.get(application_id);
	}
	
	@PostMapping("/getById")
	public ResponseEntity<Object> getById(@RequestParam Integer id){
		return fcComplianceConditionService.getById(id);
	}
}
