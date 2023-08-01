package com.backend.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.UserPrincipal;
import com.backend.model.Employee;
import com.backend.response.ResponseHandler;
import com.backend.security.CurrentUser;
import com.backend.service.UserService;


@RestController
@RequestMapping("/employee/")
public class EmployeeController {

	@Autowired
	UserService userService;
	
	@PostMapping("add")
	private ResponseEntity<Object> addEmployee(@RequestBody @Valid Employee employee, @CurrentUser UserPrincipal currentUser) {
		return userService.addEmployee(employee, currentUser);
	}
	
	/*@PostMapping("list")
    public List<Employee> getEmployeeList() {
        //Pageable paging = PageRequest.of(page, size);
        //return userService.findAll(paging);    
		return userService.getEmployeeList();
    }*/
	
	@PostMapping("list")
	public ResponseEntity<Object> getRecentMasterData(@RequestParam(value = "active", required = false) String active,
			@RequestParam(value="search",required=false) String search, @CurrentUser UserPrincipal currentUser) {
		if(active != null) {
			if(search==null) {
				return userService.getEmployeeList(active,currentUser);
			}
			else {
				return userService.getEmployeeListwithSearch(active,search,currentUser);
			}
		} else {
			if(search==null)
				return userService.getEmployeeList(active,currentUser);
			else
				return userService.getEmployeeListwithSearch(active,search,currentUser);
		}
		
	}
	
	
	@PostMapping("me")
	public ResponseEntity<Object> getLoggedInEmployeeDetails(@CurrentUser UserPrincipal currentUser) {
		ResponseEntity<Object> e=userService.getProjectEmployeeDetail(currentUser.getId());
		return e;
	}
	
	@PostMapping("detail")
	public ResponseEntity<Object> getEmployeeDetails(@RequestParam Integer Id) {
		ResponseEntity<Object> e=userService.getEmployeeDetail(Id);
		return e;
	}
	
	@PostMapping("update")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee, @CurrentUser UserPrincipal currentUser) {
		ResponseEntity<Object> s=userService.updateEmployee(employee,currentUser);
		return s;
	}
	
	@PostMapping("delete")
	public ResponseEntity<Object> deleteEmployee(@RequestBody List<Integer> empId) {
		ResponseEntity<Object> s=userService.deleteEmployee(empId);
		return s;
	}
	
	@GetMapping("getDetailsByEmail")
	public ResponseEntity<Object> getEmployeeDetailsByEmail(@RequestParam String email) {
		ResponseEntity<Object> e=userService.getEmployeeDetailByEmail(email);
		return e;
	}
	
	@GetMapping("getDetailsByStatus")
	public ResponseEntity<Object> getEmployeeDetailsByStatus(@RequestParam String status) {
		ResponseEntity<Object> e=userService.getEmployeeDetailByStatus(status);
		return e;
	}
	
	@PostMapping("updateStatus")
	private ResponseEntity<Object> updateStatus(@RequestBody @Valid Employee employee) {
		return userService.updateStatus(employee);
	}
	
	@GetMapping("/duplicateEmail")
	public ResponseEntity<Object> duplicateEmail(@RequestParam("email") @Valid @Email String email,
			@RequestParam("entityType") String type) {
		return ResponseHandler.generateResponse("Check Duplicate Email", HttpStatus.OK, "",
				userService.duplicateEmail(email, type));

	}
}
