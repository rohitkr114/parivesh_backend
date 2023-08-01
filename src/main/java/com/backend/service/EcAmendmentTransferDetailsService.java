package com.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.model.EcAmendmentTransferDetails;
import com.backend.model.EnvironmentClearence;
import com.backend.repository.postgres.EcAmendmentTransferDetailsRepository;
import com.backend.repository.postgres.EnvironmentClearenceRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class EcAmendmentTransferDetailsService {

	@Autowired
	EcAmendmentTransferDetailsRepository ecAmendmentTransferDetailsRepository;

	@Autowired
	EnvironmentClearenceRepository environmentClearanceRepository;

	@Autowired
	UserService userService;

	public ResponseEntity<Object> deleteEcAmendmentTransferDetails(int id) {

		EcAmendmentTransferDetails ecAmendmentTransferDetails = ecAmendmentTransferDetailsRepository.findById(id).get();
		ecAmendmentTransferDetails.setDelete(true);
		ecAmendmentTransferDetailsRepository.save(ecAmendmentTransferDetails);
		log.info("INFO ------------ deleteEcAmendmentTransferDetails Ec Amendment Transfer Details id ----> "+id+" ----DELETED - SUCCESS");
		return ResponseHandler.generateResponse("delete environment data list", HttpStatus.OK, "",
				"deleted successfully");

	}

}
