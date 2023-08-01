package com.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.model.EcProdTransportDetails;
import com.backend.model.EcProjectImplementationDetails;
import com.backend.model.EnvironmentClearence;
import com.backend.repository.postgres.EcProjectImplementationDetailsRepository;
import com.backend.repository.postgres.EnvironmentClearenceRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class EcProjectImplementationDetailsService {
	@Autowired
	EcProjectImplementationDetailsRepository ecProjectImplementationDetailsRepository;

	@Autowired
	EnvironmentClearenceRepository environmentClearanceRepository;

	@Autowired
	UserService userService;

	public List<EcProjectImplementationDetails> saveEcProjectImplementationDetails(Integer ecId,
			List<EcProjectImplementationDetails> ecProjectImplementationDetails) {

		EnvironmentClearence temp = environmentClearanceRepository.findById(ecId).get();
		List<EcProjectImplementationDetails> projectImplementationDetails = ecProjectImplementationDetails.stream()
				.map(value -> {
					value.setEnvironmentClearence(temp);
					value.setProposalNo(temp.getProposal_no());
					return value;
				}).collect(Collectors.toList());
		List<EcProjectImplementationDetails> environmentDetails = ecProjectImplementationDetailsRepository
				.saveAll(projectImplementationDetails);
		log.info("INFO ------------ saveEcProjectImplementationDetails WITH EcID ----> "+ecId+" ---- SAVED - SUCCESS");
		return environmentDetails;

	}

	public ResponseEntity<Object> getEcProjectImplementationDetails(int id) {
		log.info("INFO ------------ getEcProjectImplementationDetails WITH EcID FOUND and RETRIEVED - SUCCESS");
		return ResponseHandler.generateResponse("get environment data list by ec_id", HttpStatus.OK, "",
				ecProjectImplementationDetailsRepository.findDetailByEcId(id));

	}

	public ResponseEntity<Object> deleteEcProjectImplementationDetails(int id) {

		EcProjectImplementationDetails ecProjectImplementationDetails = ecProjectImplementationDetailsRepository
				.findById(id).get();
		ecProjectImplementationDetails.setDelete(true);
		ecProjectImplementationDetailsRepository.save(ecProjectImplementationDetails);
		log.info("INFO ------------ deleteEcProjectImplementationDetails WITH EcID FOUND and DELETED - SUCCESS");
		return ResponseHandler.generateResponse("delete environment data list", HttpStatus.OK, "",
				"deleted successfully");

	}

}
