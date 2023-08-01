package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.ActivityActionMapping;
import com.backend.model.ActivityActionMappingDTO;
import com.backend.repository.postgres.ActivityActionMappingDTORespository;
import com.backend.repository.postgres.ActivityActionMappingRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ActivityActionMappingService {

	@Autowired
	private ActivityActionMappingRepository actionMappingRepository;
	
	@Autowired
	private ActivityActionMappingDTORespository actionMappingDTORespository;
	
	public ResponseEntity<Object> saveActivityMapping(ActivityActionMapping activityActionMapping) {

		try {
			
			return ResponseHandler.generateResponse("Save Activity Action Mapping", HttpStatus.OK, "",
					actionMappingRepository.save(activityActionMapping));
		}
		catch(Exception ex) {
			log.error("=======================>>>>>>>>>>>" + ex.toString() + " " + ex.getStackTrace()[0]);
			throw new PariveshException("Error in Save Activity Action Mapping id- ", ex);
		}
	}
	
	public ResponseEntity<Object> getActivityMappingById(Integer id) {

		try {
			ActivityActionMapping act=actionMappingRepository.findById(id).orElseThrow(()->new ProjectNotFoundException("Activity Action not found"));
			return ResponseHandler.generateResponse("Get Activity Action Mapping", HttpStatus.OK, "",
					act);
		}
		catch(Exception ex) {
			log.error("=======================>>>>>>>>>>>" + ex.toString() + " " + ex.getStackTrace()[0]);
			throw new PariveshException("Error in Get Activity Action Mapping By id- ", ex);
		}
	}
	
	public ResponseEntity<Object> getAllActivityMapping() {
		try {
			//List<ActivityActionMapping> ac=actionMappingRepository.findAll();
			List<ActivityActionMappingDTO> ac=actionMappingDTORespository.getAllActivities();
			return ResponseHandler.generateResponse("Get All Activity Action Mapping", HttpStatus.OK, "",
					ac);
		}
		catch(Exception ex) {
			log.error("=======================>>>>>>>>>>>" + ex.toString() + " " + ex.getStackTrace()[0]);
			throw new PariveshException("Error in Get All Activity Action Mapping ", ex);
		}
	}

	public ResponseEntity<Object> getActivityMappingByClearenceId(Integer clearenceId) {
		try {
			List<ActivityActionMappingDTO> ac=actionMappingDTORespository.getAllActivitiesByClearenceId(clearenceId);
			return ResponseHandler.generateResponse("Get Activity Action Mapping", HttpStatus.OK, "",
					ac);
		}
		catch(Exception ex) {
			log.error("=======================>>>>>>>>>>>" + ex.toString() + " " + ex.getStackTrace()[0]);
			throw new PariveshException("Error in Get Activity Action Mapping By id- ", ex);
		}
	}
}
