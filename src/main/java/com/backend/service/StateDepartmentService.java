package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.exceptions.PariveshException;
import com.backend.model.StateDepartments;
import com.backend.repository.postgres.StateDepartmentRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StateDepartmentService {
	
	@Autowired
	StateDepartmentRepository stateDepartmentRepository;

	public ResponseEntity<Object> getAllStateDepartments(){
		
		try {
		//return (stateDepartmentRepository.findAllStateDepartments());
			log.info("INFO ------------ getAllStateDepartments ---- RETRIEVING ALL STATE DEPARTMENTS - SUCCESS");
		return ResponseHandler.generateResponse("State Departments",HttpStatus.OK,"",stateDepartmentRepository.findAllStateDepartments());
		}
		catch(Exception ex) {
			//System.out.println(ex.getMessage());
			log.info("ERROR ------------ EXCEPTION: getAllStateDepartments ---- RETRIEVING ALL STATE DEPARTMENTS - FAILURE");
			return ResponseHandler.generateResponse("State Departments",HttpStatus.OK,"Exception",ex.getMessage());
		}
	}
	
	public ResponseEntity<Object> getDepartment(Integer StateCode) {
		try {
		//return(stateDepartmentRepository.findStateDepartmentByCode(StateCode));
			log.info("INFO ------------ getDepartment WITH StateCode----> "+StateCode+" ---- RETRIEVING STATE DEPARTMENTS BY STATE CODE - SUCCESS");
		return ResponseHandler.generateResponse("Get State Department",HttpStatus.OK,"",stateDepartmentRepository.findStateDepartmentByCode(StateCode));
		}
		catch(Exception ex) {
			log.info("ERROR ------------ EXCEPTION: getDepartment WITH StateCode----> "+StateCode+" ---- RETRIEVING STATE DEPARTMENTS BY STATE CODE - FAILURE");
			return ResponseHandler.generateResponse("Get State Department",HttpStatus.OK,"Exception",ex.getMessage());
			//System.out.println(ex.getMessage());
		}
		
	}
	
	public ResponseEntity<Object> addStateDepartment(StateDepartments stateDepartments){
		try {
			if (stateDepartments.getId() != null) {
				log.info("INFO ------------ Adding Departments "+stateDepartments);
				return ResponseHandler.generateResponse("State Department by id ", HttpStatus.OK, "",
						stateDepartmentRepository.save(stateDepartments));
			} else {
				log.info("INFO ------------ Adding Departments "+stateDepartments);
				return ResponseHandler.generateResponse("State Department by id ", HttpStatus.OK, "",
						"State Department Id is null");
			}
		} catch(Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in adding department - ", e);
		}
	}
	
	public ResponseEntity<Object> deleteStateDepartment(Integer id){
		try {
			log.info("INFO ------------ Delete State Department ");
			StateDepartments stateDepartments = stateDepartmentRepository.getById(id);
			stateDepartments.setIs_active(false);
			stateDepartments.setIs_deleted(true);
			
			return ResponseHandler.generateResponse("Department deleted", HttpStatus.OK, "",
					stateDepartmentRepository.save(stateDepartments));
		} catch(Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in deleting department for id - " + id, e);
		}
	}
}
