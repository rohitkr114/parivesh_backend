package com.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.exceptions.ProjectNotFoundException;
import com.backend.exceptions.UserAlreadyExistException;
import com.backend.exceptions.UserNotFoundException;
import com.backend.model.Consultant;
import com.backend.model.Employee;
import com.backend.model.ProjectDetails;
import com.backend.repository.postgres.ConsultantRepository;
import com.backend.repository.postgres.ProjectDetailRepository;
import com.backend.repository.postgres.UserEmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ProjectEmployeeService {

	@Autowired
	private ProjectDetailRepository projectDetailRepository;

	@Autowired
	private UserEmployeeRepository userEmployeeRepository;

	public List<Employee> findProjectEmployeeByProjectId(Integer id) {
		return userEmployeeRepository.findEmployeeByProjectId(id);
	}

	public Employee addEmployee(Integer id, Integer employeeId) {
		Employee temp = projectDetailRepository.findById(id).map(project -> {
			Employee temp2 = userEmployeeRepository.findById(employeeId)
					.orElseThrow(() -> new UserNotFoundException("Employee not found"));
			ProjectDetails projectDetails = projectDetailRepository.findProjectByUserIdAndProjectid(temp2.getEntityid(),
					id);
			if (projectDetails != null) {
				throw new UserAlreadyExistException("Employee already mapped with Project");
			}
			project.addEmployee(temp2);
			projectDetailRepository.save(project);
			return temp2;
		}).orElseThrow(() -> new ProjectNotFoundException("project not found with project id"));
		log.info("INFO ------------ addEmployee WITH PROJECT_DETAILS_ID----> "+id+" ----employeeId ---->"+employeeId+" ---- MAPPING EMPLOYEE WITH THE PROJECT - SUCCESS");
		return temp;
	}

	public ProjectDetails deleteEmployee(Integer id, Integer userId) {
		ProjectDetails temp = projectDetailRepository.findById(id)
				.orElseThrow(() -> new ProjectNotFoundException("project details not found with id"));
		temp.removeEmployee(userId);
		log.info("INFO ------------ deleteEmployee WITH PROJECT_DETAILS_ID----> "+id+" ----employeeId ---->"+userId+" ---- DELETING EMPLOYEE FROM THE PROJECT - SUCCESS");
		return projectDetailRepository.save(temp);
	}

	public List<Employee> addEmployees(Integer id, List<Integer> employeeIds) {
		List<Employee> temp = employeeIds.stream().map(e -> addEmployee(id, e)).collect(Collectors.toList());
		return temp;
	}

	public List<ProjectDetails> deleteEmployees(Integer id, List<Integer> employeeIds) {
		List<ProjectDetails> temp = employeeIds.stream().map(e -> deleteEmployee(id, e)).collect(Collectors.toList());
		return temp;
	}

}
