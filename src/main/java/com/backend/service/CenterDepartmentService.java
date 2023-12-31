package com.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.model.CenterDepartments;
import com.backend.repository.postgres.CenterDepartmentRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CenterDepartmentService {

	@Autowired
	CenterDepartmentRepository centerDepartmentsRepository;

	public ResponseEntity<Object> getAllDepartments() {

		try {
			log.info("INFO ------------ All departments data generated - SUCCESS");
			return ResponseHandler.generateResponse("Center Departments", HttpStatus.OK, "",
					centerDepartmentsRepository.findAllDepartments());
		} catch (Exception ex) {
			log.info("ERROR ------------ BAD_REQUEST: All departments data can't begenerated - FAILURE");
			return ResponseHandler.generateResponse("Center Departments", HttpStatus.BAD_REQUEST, "Exception Occurred",
					ex.getMessage());
		}
	}

	public ResponseEntity<Object> getDepartment(Integer Id) {
		try {
			log.info("INFO ------------ Departments data generated by Id----> " + Id + "- SUCCESS");
			return ResponseHandler.generateResponse("Center Departments by Id", HttpStatus.OK, "",
					centerDepartmentsRepository.getDepartmentById(Id));
		} catch (Exception ex) {
			log.info("ERROR ------------ BAD_REQUEST: Departments data can't be generated by Id----> " + Id
					+ " - FAILURE");
			return ResponseHandler.generateResponse("Center Departments by Id", HttpStatus.BAD_REQUEST,
					"Exception Occurred", ex.getMessage());
		}
	}

	public ResponseEntity<Object> addDepartment(CenterDepartments centerDepartments) {
		try {
			if (centerDepartments.getId() != null) {
				log.info("INFO ------------ Adding Departments "+centerDepartments);
				return ResponseHandler.generateResponse("Center Departments by Id", HttpStatus.OK, "",
						centerDepartmentsRepository.save(centerDepartments));
			} else {
				log.info("INFO ------------ Adding Departments "+centerDepartments);
				return ResponseHandler.generateResponse("Center Departments by Id", HttpStatus.OK, "",
						"Center Department Id is null");
			}
		} catch (Exception ex) {
			log.info("ERROR ------------ Adding Departments ---->FAILURE");
			return ResponseHandler.generateResponse("Center Departments by Id", HttpStatus.BAD_REQUEST,
					"Exception Occurred", ex.getMessage());
		}
	}
	
	public ResponseEntity<Object> deleteDepartment(Integer DepartmentId) {
		try {
			
				log.info("INFO ------------ Delete Center Department ");
				CenterDepartments departments=centerDepartmentsRepository.getById(DepartmentId);
				departments.set_active(false);
				departments.set_deleted(true);
				
				return ResponseHandler.generateResponse("Department Deleted", HttpStatus.OK, "",
						centerDepartmentsRepository.save(departments));
			
		} catch (Exception ex) {
			log.info("ERROR ------------ Delete Departments ---->FAILURE"+DepartmentId);
			return ResponseHandler.generateResponse("Center Departments by Id", HttpStatus.BAD_REQUEST,
					"Exception Occurred", ex.getMessage());
		}
	}

}
