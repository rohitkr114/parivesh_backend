package com.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.model.EcAmendmentTransferDetails;
import com.backend.model.EcProdTransportDetails;
import com.backend.model.EnvironmentClearence;
import com.backend.repository.postgres.EcProdTransportDetailsRepository;
import com.backend.repository.postgres.EnvironmentClearenceRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class EcProdTransportDetailsService {

	@Autowired
	EcProdTransportDetailsRepository ecProdTransportDetailsRepository;

	@Autowired
	EnvironmentClearenceRepository environmentClearanceRepository;

	@Autowired
	UserService userService;

	public List<EcProdTransportDetails> saveEcProdTransportDetails(Integer ecId,
			List<EcProdTransportDetails> ecProdTransportDetails) {

		EnvironmentClearence temp = environmentClearanceRepository.findById(ecId).get();
		List<EcProdTransportDetails> prodTransportDetails = ecProdTransportDetails.stream().map(value -> {
			value.setEnvironmentClearence(temp);
			value.setProposalNo(temp.getProposal_no());
			return value;
		}).collect(Collectors.toList());
		List<EcProdTransportDetails> environmentDetails = ecProdTransportDetailsRepository
				.saveAll(prodTransportDetails);
		log.info("INFO ------------ saveEcProdTransportDetails Ec Prod Transport Details ecId ----> "+ecId+" ---- SAVED - SUCCESS");
		return environmentDetails;

	}

	public ResponseEntity<Object> getEcProdTransportDetails(int id) {
		log.info("INFO ------------ getEcProdTransportDetails Ec Prod Transport Details WITH EcID ----> "+id+" ---- FOUND and RETRIEVED - SUCCESS");
		return ResponseHandler.generateResponse("get environment data list by ec_id", HttpStatus.OK, "",
				ecProdTransportDetailsRepository.findDetailByEcId(id));

	}

	public ResponseEntity<Object> deleteEcProdTransportDetails(int id) {

		EcProdTransportDetails ecProdTransportDetails = ecProdTransportDetailsRepository.findById(id).get();
		ecProdTransportDetails.setDelete(true);
		ecProdTransportDetailsRepository.save(ecProdTransportDetails);
		log.info("INFO ------------ deleteEcProdTransportDetails Ec Prod Transport Details WITH EcID ----> "+id+" ---- FOUND and DELETED - SUCCESS");
		return ResponseHandler.generateResponse("delete environment data list", HttpStatus.OK, "",
				"deleted successfully");

	}

}
