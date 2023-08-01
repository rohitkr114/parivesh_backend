package com.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.model.EcProdTransportDetails;
import com.backend.model.EnvironmentClearanceProjectAreaDetails;
import com.backend.model.EnvironmentClearence;
import com.backend.repository.postgres.EnvironmentClearanceProjectAreaDetailsRepository;
import com.backend.repository.postgres.EnvironmentClearenceRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ECProjectAreaDetailsService {

	@Autowired
	EnvironmentClearanceProjectAreaDetailsRepository environmentClearanceProjectAreaDetailsRepository;

	@Autowired
	EnvironmentClearenceRepository environmentClearanceRepository;

	@Autowired
	UserService userService;

	public List<EnvironmentClearanceProjectAreaDetails> saveEnvironmentClearanceProjectAreaDetails(Integer id,
			List<EnvironmentClearanceProjectAreaDetails> environmentClearanceProjectAreaDetails) {

		EnvironmentClearence temp = environmentClearanceRepository.findById(id).get();

		List<EnvironmentClearanceProjectAreaDetails> projectAreaDetails = environmentClearanceProjectAreaDetails
				.stream().map(value -> {
					value.setEnvironmentClearence(temp);
					value.setProposalNo(temp.getProposal_no());
					return value;
				}).collect(Collectors.toList());
		List<EnvironmentClearanceProjectAreaDetails> environmentDetails = environmentClearanceProjectAreaDetailsRepository
				.saveAll(projectAreaDetails);
		log.info("INFO ------------ saveEnvironmentClearanceProjectAreaDetails WITH EcID ----> "+id+" ---- SAVED - SUCCESS");
		return environmentDetails;

	}

	public ResponseEntity<Object> getECProjectAreaData(int id) {
		log.info("INFO ------------ getECProjectAreaData WITH EcID WITH EcID ----> "+id+" ---- FOUND and RETRIEVED - SUCCESS");
		return ResponseHandler.generateResponse("get environment data list by ec_id", HttpStatus.OK, "",
				environmentClearanceProjectAreaDetailsRepository.findDetailByEcId(id));

	}

	public ResponseEntity<Object> deleteECProjectAreaData(int id) {

		EnvironmentClearanceProjectAreaDetails environmentClearanceProjectAreaDetails = environmentClearanceProjectAreaDetailsRepository
				.findById(id).get();
		environmentClearanceProjectAreaDetails.setDelete(true);
		environmentClearanceProjectAreaDetailsRepository.save(environmentClearanceProjectAreaDetails);
		log.info("INFO ------------ deleteECProjectAreaData WITH EcID WITH EcID ----> "+id+" ---- FOUND and DELETED - SUCCESS");
		return ResponseHandler.generateResponse("delete environment data list by id", HttpStatus.OK, "",
				"deleted successfully");

	}

}
