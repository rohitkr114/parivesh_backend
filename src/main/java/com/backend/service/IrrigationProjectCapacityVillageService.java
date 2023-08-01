package com.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.exceptions.PariveshException;
import com.backend.model.IrrigationProjectCapacityVillage;
import com.backend.repository.postgres.IrrigationProjectCapacityVillageRepository;
import com.backend.response.ResponseHandler;

@Service
@Transactional
public class IrrigationProjectCapacityVillageService {

	@Autowired
	private IrrigationProjectCapacityVillageRepository irrigationRepository;

	public ResponseEntity<Object> getIrrigationProjectCapacityVillageDetails(Integer cafId) throws PariveshException {
		try {

			return ResponseHandler.generateResponse("Get IrrigationProjectCapacityVillageDetails for caf_id",
					HttpStatus.OK, "", irrigationRepository.findIrrigationProjectCapacityVillageByCafId(cafId));
		} catch (Exception e) {
			// log.error(e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Getting Irrigation Project Capacity Village Details for caf_id- " + cafId);
		}
	}
}
