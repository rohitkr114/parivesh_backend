package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.StandardToRSequence;
import com.backend.service.StandardToRSequenceService;

@RestController
@RequestMapping("/standardToRSequence")
public class StandardToRSequenceController {
	
	@Autowired
	private StandardToRSequenceService standardToRSequenceService;
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveStandardToRSequence(@RequestBody StandardToRSequence standardToRSequence) throws PariveshException{
		
		return standardToRSequenceService.saveStandardToRSequence(standardToRSequence);
	}
	
	@PostMapping("/get")
	public ResponseEntity<Object> getStandardToRSequence(@RequestParam Integer activity_id) throws PariveshException{
		
		return standardToRSequenceService.getStandardToRSequence(activity_id);
	}

}
