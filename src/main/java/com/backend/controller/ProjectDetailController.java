package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.Employee;
import com.backend.model.ProjectDetails;
import com.backend.response.ResponseHandler;
import com.backend.security.CurrentUser;
import com.backend.service.ConsultantService;
import com.backend.service.ProjectDetailsService;
import com.backend.service.ProjectEmployeeService;
import com.backend.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/project/")
public class ProjectDetailController {

	@Autowired
	private ProjectDetailsService projectDetailsService;

	@Autowired
	private ProjectEmployeeService projectEmployeeService;

	@Autowired
	private ConsultantService consultantService;

	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public ResponseEntity<Object> saveProject(@RequestBody ProjectDetails projectDetails,
			@CurrentUser UserPrincipal user) throws PariveshException {
		ResponseEntity<Object> status = projectDetailsService.saveProject(projectDetails, user);
		return status;
	}

	@PostMapping("/get")
	public ResponseEntity<Object> getProjectbyId(@RequestParam("project_id") Integer id) throws PariveshException {

		return ResponseHandler.generateResponse("Get project by id" + id, HttpStatus.OK, "",
				projectDetailsService.getProjectbyId(id));
	}

	@PostMapping("/addEmployee")
	public ResponseEntity<Object> addProjectEmployee(@RequestParam("projectId") Integer id,
			@RequestBody List<Integer> employeeId) {
		return ResponseHandler.generateResponse("added consultant in project", HttpStatus.OK, "",
				projectEmployeeService.addEmployees(id, employeeId));
	}

	@PostMapping("/addConsultant")
	public ResponseEntity<Object> addProjectConsultant(@RequestParam("projectId") Integer id,
			@RequestBody List<Integer> consultantIds) {

		return ResponseHandler.generateResponse("added consultant in project", HttpStatus.OK, "",
				consultantService.addConsultants(id, consultantIds));
	}

	@PostMapping("addConsultantToProject")
	public ResponseEntity<Object> addConsultantToProject(@RequestParam("projectId") Integer id,
			@RequestParam("consultantId") Integer consultantId) {
		log.info("------------In Controller  addConsultantToProject------------");
		return consultantService.addConsultantToProject(id, consultantId);
	}

	@PostMapping("/update")
	public String updateProject(@RequestParam Integer id) {

		return "SUCCESS";
	}
/*
	@PostMapping("addConsultantToProject")
	public ResponseEntity<Object> addConsultantToProject(@RequestParam("projectId")Integer id,
			@RequestParam("consultantId")Integer consultantId){
		log.info("------------In Controller  addConsultantToProject------------");
		return consultantService.addConsultantToProject(id, consultantId);
	}*/
	
	@PostMapping("list")
	public ResponseEntity<Object> getProjectList(@CurrentUser UserPrincipal currentUser,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "500") int size,
			@RequestParam(value = "search", required = false) String search) throws PariveshException {
		if (search == null) {
			Pageable paging = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
			return projectDetailsService.findAll(currentUser.getId(), paging);
		} else {
			Pageable paging = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
			return projectDetailsService.findAllWithSearch(currentUser.getId(), paging, search);
		}
	}
	
	@PostMapping("saveAcoStatus")
	public ResponseEntity<Object> saveAcoStatus(@CurrentUser UserPrincipal currentUser, @RequestParam String status) throws PariveshException{
		return projectDetailsService.saveAcoStatus(currentUser.getId(),status);
	}

	@PostMapping("getAcoStatusbyUserId")
	public ResponseEntity<Object> getAcoStatus(@RequestParam Integer id) throws PariveshException{
		return projectDetailsService.getAcoStatus(id);
	}
	
	@PostMapping("/getProjectbyID")
	public ResponseEntity<Object> getProjectByID(@RequestParam Integer id, @CurrentUser UserPrincipal currentUser)
			throws PariveshException {
		return projectDetailsService.findProjectById(id, currentUser.getId());
	}

	@PostMapping("/getProject")
	public ResponseEntity<Object> getProject(@RequestParam("project_id") Integer id) throws PariveshException {
		return projectDetailsService.findProjectByUserIdAndProjectId(userService.getuserId(), id);
	}

	@PostMapping("/getProjectsbyUserId")
	public ResponseEntity<Object> getProjectByuserId(@RequestParam(value = "user_id") Integer id)
			throws PariveshException {
		return projectDetailsService.findProjectsByUserId(id);
	}

	@PostMapping("/getEmployeesbyProjectId")
	public List<Employee> getProjectAssigneeByProjectId(
			@RequestParam(value = "project_id", required = false) Integer id) {
		return projectEmployeeService.findProjectEmployeeByProjectId(id);
	}

	@PostMapping("/getProjectbySW")
	public ResponseEntity<Object> getProjectBySW(@RequestParam String SW) throws PariveshException {
		return projectDetailsService.findProjectBySW(SW);
	}

	@PostMapping("/deleteEmployee")
	public ResponseEntity<Object> deleteEmployees(@RequestParam("projectId") Integer id,
			@RequestBody List<Integer> employeeId) {
		return ResponseHandler.generateResponse("deleted employee in project", HttpStatus.OK, "",
				projectEmployeeService.deleteEmployees(id, employeeId));
	}

	@PostMapping("/deleteConsultant")
	public ResponseEntity<Object> deleteConsultants(@RequestParam("projectId") Integer id,
			@RequestBody List<Integer> consultantIds) {
		return ResponseHandler.generateResponse("deleted consultant in project", HttpStatus.OK, "",
				consultantService.deleteConsultants(id, consultantIds));
	}

}
