package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.StateDepartments;
import com.backend.service.StateDepartmentService;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/kyc/")
public class StateDepartmentController {

	@Autowired
	StateDepartmentService stateDepartmentService;
	
	
	@GetMapping("/getAllStateDepartments")
	public ResponseEntity<Object> getAllDepartments() {
		return(stateDepartmentService.getAllStateDepartments());		
	}
	
	@PostMapping("/getStateDepartment")
	public  ResponseEntity<Object> getStateDepartment(@RequestParam("code") Integer State_Code) {
		return(stateDepartmentService.getDepartment(State_Code));
	}
	
	@PostMapping("/addStateDepartment")
	public ResponseEntity<Object> addStateDepartment(@RequestBody StateDepartments stateDepartments) throws PariveshException{
		return stateDepartmentService.addStateDepartment(stateDepartments);
	}
	
	@PostMapping("/deleteStateDepartment")
	public ResponseEntity<Object> deleteStateDepartment(@RequestParam Integer id) throws PariveshException{
		return stateDepartmentService.deleteStateDepartment(id);
	}
}
