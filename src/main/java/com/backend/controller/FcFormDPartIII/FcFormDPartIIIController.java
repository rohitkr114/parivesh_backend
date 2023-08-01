package com.backend.controller.FcFormDPartIII;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.FcFormDPartIII.FcFormDPartIIICheckListDetails;
import com.backend.model.FcFormDPartIII.FcFormDPartIIIProjectDetails;
import com.backend.service.FcFormDPartIII.FcFormDPartIIIService;

@RestController
@RequestMapping("/fcFormD/PartIII")
public class FcFormDPartIIIController {

	@Autowired
	private FcFormDPartIIIService fcFormDPartIIIService;
	
	@PostMapping("/saveCheckListDetails")
	public ResponseEntity<Object> saveCheckListDetails(@RequestBody FcFormDPartIIICheckListDetails checkListDetails,
			@RequestParam Integer clearanceId,HttpServletRequest request) throws PariveshException{		
		return fcFormDPartIIIService.saveCheckListDetails(checkListDetails, clearanceId, request);
	}
	
	@PostMapping("/get")
	public ResponseEntity<Object> getForm(@RequestParam Integer checkListId) throws PariveshException{
		return fcFormDPartIIIService.getForm(checkListId);
	}
	
	@PostMapping("/saveProjectDetails")
	public ResponseEntity<Object> saveProjectDetails(@RequestBody FcFormDPartIIIProjectDetails projectDetails, @RequestParam Integer checkListDetailsId,
			HttpServletRequest request) throws PariveshException{
		return fcFormDPartIIIService.saveProjectDetails(projectDetails, checkListDetailsId, request);
	}
}
