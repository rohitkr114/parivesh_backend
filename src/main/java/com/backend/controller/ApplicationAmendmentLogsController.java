package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.ApplicationAmendmentLogs;
import com.backend.service.ApplicationAmendmentLogsService;

@RestController
@RequestMapping("/amendmentLogs")
public class ApplicationAmendmentLogsController {
	
	@Autowired
	private ApplicationAmendmentLogsService applicationAmendmentLogsService;
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveAmendmentLogs(@RequestBody List<ApplicationAmendmentLogs> applicationAmendmentLogs) throws PariveshException{
		
		return applicationAmendmentLogsService.saveAmendmentLogs(applicationAmendmentLogs);
	}
	
	@PostMapping("/get")
	public ResponseEntity<Object> getAmendmentLogs(@RequestParam Integer ref_id, @RequestParam(required=false) String ref_type) throws PariveshException{
		
		return applicationAmendmentLogsService.getAmendmentLogs(ref_id, ref_type);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<Object> deleteAmendmentLogs(@RequestParam Integer id) throws PariveshException{
		
		return applicationAmendmentLogsService.deleteAmendmentLogs(id);
	}
	
	
}
