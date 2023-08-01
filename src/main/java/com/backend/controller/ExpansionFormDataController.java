package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.ExpansionFormData;
import com.backend.service.ExpansionFormDataService;

@RestController
@RequestMapping("/expansionFormData")
public class ExpansionFormDataController {
	
	@Autowired
	private ExpansionFormDataService expansionFormDataService;
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveExpansionFormData(@RequestBody ExpansionFormData expansionFormData) throws PariveshException
	{
		return expansionFormDataService.saveExpansionFormData(expansionFormData);
	}
	
	@PostMapping("/get")
	public ResponseEntity<Object> getExpansionFormData(@RequestParam Integer id,
			@RequestParam(required=false) Integer application_id, @RequestParam(required=false) Integer step)throws PariveshException{
		if(step==null) {
			if(application_id==null) {
				return expansionFormDataService.getExpansionFormData(id);
			}else {
				return expansionFormDataService.getExpansionFormData(id,application_id);
			}
		}
		else {
		return expansionFormDataService.getExpansionFormData(id, application_id, step);
		}
	}

}
