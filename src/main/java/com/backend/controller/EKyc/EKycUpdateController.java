package com.backend.controller.EKyc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import com.backend.model.EKyc.UpdateEKyc;
import com.backend.service.EKyc.UpdateEKycService;

import lombok.extern.slf4j.Slf4j;

import com.backend.dto.UserPrincipal;
import com.backend.security.CurrentUser;

@RestController
@RequestMapping("/ekyc")
public class EKycUpdateController {

	@Autowired
	UpdateEKycService updateEKycService;
	
	@PostMapping("/update")
	public ResponseEntity<Object> saveKycDetails(@RequestBody UpdateEKyc updateEKyc){
		
		return updateEKycService.saveEKycDetails(updateEKyc);
	}
	
	@PostMapping("/updateStatus")
	public ResponseEntity<Object> saveKycDetails(@CurrentUser UserPrincipal principal){
		
		return updateEKycService.updateStatus(principal.getId());
	}
	
	@PostMapping("/updateStatusLocal")
	public ResponseEntity<Object> saveKycDetails(Integer user_id){
		
		return updateEKycService.updateStatus(user_id);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Object> getList(@CurrentUser UserPrincipal principal){
		
		return updateEKycService.getList(principal.getId());
	}
	
	@GetMapping("/getAllLocal")
	public ResponseEntity<Object> getList(Integer user_id){
		
		return updateEKycService.getList(user_id);
	}
	
}
