package com.backend.controller.FcFormBPartIII;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.FcFormBPartIII.FcFormBPartIIICheckListDetails;
import com.backend.model.FcFormBPartIII.FcFormBPartIIIProjectDetails;
import com.backend.service.FcFormBPartIII.FcFormBPartIIIService;

@RestController
@RequestMapping("/fcFormB/PartIII")
public class FcFormBPartIIIController {

	@Autowired
	private FcFormBPartIIIService fcFormBPartIIIService;
	
	@PostMapping("/saveCheckListDetails")
	public ResponseEntity<Object> saveCheckListDetails(@RequestBody FcFormBPartIIICheckListDetails checkListDetails,
			@RequestParam Integer clearanceId,HttpServletRequest request) throws PariveshException{		
		return fcFormBPartIIIService.saveCheckListDetails(checkListDetails, clearanceId, request);
	}
	
	@PostMapping("/get")
	public ResponseEntity<Object> getForm(@RequestParam Integer checkListId) throws PariveshException{
		return fcFormBPartIIIService.getForm(checkListId);
	}
	
	@PostMapping("/saveProjectDetails")
	public ResponseEntity<Object> saveProjectDetails(@RequestBody FcFormBPartIIIProjectDetails projectDetails, @RequestParam Integer checkListDetailsId,
			HttpServletRequest request) throws PariveshException{
		return fcFormBPartIIIService.saveProjectDetails(projectDetails, checkListDetailsId, request);
	}
}
