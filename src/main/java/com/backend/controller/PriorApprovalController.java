package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.PriorApprovals;
import com.backend.service.PriorApprovalService;

@RestController
@RequestMapping("/priorApprovalDetails/")
public class PriorApprovalController {
	
	@Autowired
	PriorApprovalService priorApprovalService;
	
	
	@PostMapping("/save")
	public ResponseEntity<Object> savePriorApprovalDetails(@RequestParam(value = "fc_id",required = false) Integer id, 
														   @RequestParam(value = "wl_id",required = false) Integer wl_id,
														   @RequestBody List<PriorApprovals> forestClearancePriorApprovals) {
		
		ResponseEntity<Object> status = priorApprovalService.saveForestApprovalDeatails(id,wl_id,forestClearancePriorApprovals);
		return status;
	}
	
	
	@GetMapping("/list")
	public ResponseEntity<Object> getPriorAprovalDetails(@RequestParam(value = "fc_id",required = false) Integer id,
														 @RequestParam(value = "wl_id",required = false) Integer wl_id){
		
		return priorApprovalService.getForestPriorApprovalData(id,wl_id);
	}
	
	@PostMapping("/delete")
		public ResponseEntity<Object> deletePriorAprovalDetails(@RequestParam Integer priorId){
	
			return priorApprovalService.deletePriorApprovalData(priorId);
		}
}
	

	
	
	
	
	


