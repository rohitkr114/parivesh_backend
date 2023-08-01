package com.backend.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.exceptions.PariveshException;
import com.backend.model.ExpansionFormData;
import com.backend.repository.postgres.ExpansionFormDataRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExpansionFormDataService {

	@Autowired
	private ExpansionFormDataRepository expansionFormDataRepository;

	public ResponseEntity<Object> saveExpansionFormData(ExpansionFormData expansionFormData) {
		try {
			if (expansionFormData.getId() != null) {
				return ResponseHandler.generateResponse("Saving expansion form data", HttpStatus.OK, null,
						expansionFormDataRepository.save(expansionFormData));
			} else {
				return ResponseHandler.generateResponse("Saving expansion form data", HttpStatus.OK, null,
						"id is null");

			}
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving expansion form data", e);
		}
	}
	
	@Transactional
	public ResponseEntity<Object> getExpansionFormData(Integer id) {
		try {
			return ResponseHandler.generateResponse("getting expansion form details", HttpStatus.OK, null,
					expansionFormDataRepository.findById(id));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting expansion form data", e);
		}
	}
	
	@Transactional
	public ResponseEntity<Object> getExpansionFormData(Integer id, Integer application_id) {
		try {
			return ResponseHandler.generateResponse("getting expansion form details", HttpStatus.OK, null,
					expansionFormDataRepository.findByIdAndApplicationId(id, application_id));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting expansion form data", e);
		}
	}
	
	@Transactional
	public ResponseEntity<Object> getExpansionFormData(Integer id, Integer application_id, Integer step) {
		try {
		
				return ResponseHandler.generateResponse("getting expansion form details", HttpStatus.OK, null,
						expansionFormDataRepository.findByIdAndApplicationIdAndStep(id, application_id, step));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting expansion form data", e);
		}
	}
}
