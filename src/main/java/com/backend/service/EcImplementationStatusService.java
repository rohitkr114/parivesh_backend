package com.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.model.EcStatus;
import com.backend.repository.postgres.EcImplementationStatusRepository;
import com.backend.repository.postgres.EnvironmentClearenceRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class EcImplementationStatusService {

	@Autowired
	EcImplementationStatusRepository ecImplementationStatusRepository;

	@Autowired
	EnvironmentClearenceRepository environmentClearanceRepository;

	@Autowired
	UserService userService;

	public ResponseEntity<Object> deleteEcImplementationStatus(int id) {

		EcStatus ecStatus = ecImplementationStatusRepository.findById(id).get();
		ecStatus.setIs_deleted(true);
		ecStatus.setIs_active(false);
		ecImplementationStatusRepository.save(ecStatus);
		log.info("INFO ------------ deleteEcImplementationStatus Ec Implementation Status id----> "+id+" ---- DELETED - SUCCESS");
		return ResponseHandler.generateResponse("delete environment status data list", HttpStatus.OK, "",
				"deleted successfully");

	}

}
