package com.backend.controller.fcPriorApproval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.UserPrincipal;
import com.backend.model.fcPriorApproval.FcPriorApproval;
import com.backend.security.CurrentUser;
import com.backend.service.fcPriorApproval.FcPriorApprovalService;

@RestController
@RequestMapping("/fcPriorApproval")
public class FcPriorApprovalController {
	
	@Autowired
	FcPriorApprovalService listService;
	
	@PostMapping("/save")
	ResponseEntity<Object> save(@RequestBody FcPriorApproval listProject){
		return listService.save(listProject);
	}

	@PostMapping("/get")
	ResponseEntity<Object> getByProject(@RequestParam(required = false) Integer id, @RequestParam (required=false) Integer project_id, @RequestParam(required=false) String sw_proposal_no, @CurrentUser UserPrincipal user){
		return listService.getList(id,project_id, sw_proposal_no, user);
	}
	
	@PostMapping("/setProcessedFlag")
	ResponseEntity<Object> setProcessedFlag(@RequestParam Integer id, @CurrentUser UserPrincipal user){
		return listService.setProcessedFlag(id, user);
	}
	
	@PostMapping("/updatePriorApproval")
	ResponseEntity<Object> updatePriorApproval(@RequestParam Integer id, @RequestParam (required = false) Integer fc_id, @RequestParam (required = false) Integer caf_id, @CurrentUser UserPrincipal user){
		return listService.updatePriorApproval(id, fc_id, caf_id, user);
	}
}
