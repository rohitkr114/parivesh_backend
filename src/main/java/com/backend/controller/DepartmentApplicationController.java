package com.backend.controller;

import com.backend.exceptions.PariveshException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.UserPrincipal;
import com.backend.security.CurrentUser;
import com.backend.service.DepartmentApplicationService;

@RestController
@RequestMapping("/departmentApplication/")
public class DepartmentApplicationController {
	
	@Autowired
	private DepartmentApplicationService departmentApplicationService;
	
	@PostMapping("get")
	ResponseEntity<Object> getDepartmentApplications(@RequestParam String proposalNo,@CurrentUser UserPrincipal user){
		return departmentApplicationService.getDepartmentApplication(proposalNo,user);
	}

	@PostMapping("/viewProposalList")
	public ResponseEntity<Object> viewProposalList(@CurrentUser UserPrincipal userPrincipal,@RequestParam(required = false) String authority){
		return departmentApplicationService.viewProposalList(userPrincipal,authority);
	}

	@PostMapping("/getUserDashboardCount")
	public ResponseEntity<Object> getUserDashboardCount(@CurrentUser UserPrincipal userPrincipal)throws PariveshException {
		return departmentApplicationService.getUserDashboardCount(userPrincipal);
	}

	@PostMapping("/getViewProposalCountByAuthority")
	public ResponseEntity<Object> getViewProposalCountByAuthority(@CurrentUser UserPrincipal userPrincipal)throws PariveshException{
		return departmentApplicationService.getViewProposalCountByAuthority(userPrincipal);
	}
}
