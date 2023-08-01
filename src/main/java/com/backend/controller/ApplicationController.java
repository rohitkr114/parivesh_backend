package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.restart.Restarter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.constant.AppConstant;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.exceptions.UserNotFoundException;
import com.backend.model.Applications;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.response.ResponseHandler;
import com.backend.service.ApplicationsService;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

	@Autowired
	ApplicationsService applicationsService;

	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	public ResponseEntity<Object> getApplicationData(
			@RequestParam(value = "form_for", required = false) AppConstant.Form_for form_for,
			@RequestParam(value = "department_for", required = false) String department_for) throws PariveshException {

		return applicationsService.getApplicationData(form_for, department_for);
	}

	
	@RequestMapping(value = "/getById", method = RequestMethod.POST)
	public ResponseEntity<Object> getApplicationById(@RequestParam Integer Id) throws PariveshException{

		return applicationsService.getApplicationById(Id);
	}

	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ResponseEntity<Object> saveApplication(@RequestBody Applications application) throws PariveshException{
		return applicationsService.saveApplication(application);
	}
	
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ResponseEntity<Object> deleteApplication(@RequestParam Integer id) throws PariveshException{
		return applicationsService.deleteApplication(id);
	}
	
//	@RequestMapping(value="/update", method=RequestMethod.POST)
//	public ResponseEntity<Object> updateApplication(@RequestBody Applications application,
//			@RequestParam Integer id) throws PariveshException{
//		return applicationsService.updateApplication(application, id);
//	}
	
}
