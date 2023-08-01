package com.backend.controller.FcFormCPartIII;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.FcFormCPartIII.FcFormCPartIIICheckListDetails;
import com.backend.model.FcFormCPartIII.FcFormCPartIIIProjectDetails;
import com.backend.service.FcFormCPartIII.FcFormCPartIIIService;

@RestController
@RequestMapping("/fcFormC/PartIII")
public class FcFormCPartIIIController {
	
	@Autowired
	private FcFormCPartIIIService fcFormCPartIIIService;
	
	@PostMapping("/saveCheckListDetails")
	public ResponseEntity<Object> saveCheckListDetails(@RequestBody FcFormCPartIIICheckListDetails checkListDetails,
			@RequestParam Integer clearanceId,HttpServletRequest request) throws PariveshException{		
		return fcFormCPartIIIService.saveCheckListDetails(checkListDetails, clearanceId, request);
	}
	
	@PostMapping("/get")
	public ResponseEntity<Object> getForm(@RequestParam Integer checkListId) throws PariveshException{
		return fcFormCPartIIIService.getForm(checkListId);
	}
	
	@PostMapping("/saveProjectDetails")
	public ResponseEntity<Object> saveProjectDetails(@RequestBody FcFormCPartIIIProjectDetails projectDetails, @RequestParam Integer checkListDetailsId,
			HttpServletRequest request) throws PariveshException{
		return fcFormCPartIIIService.saveProjectDetails(projectDetails, checkListDetailsId, request);
	}
}
