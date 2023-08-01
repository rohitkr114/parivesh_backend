package com.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Activities;
import com.backend.model.EcProdTransportDetails;
import com.backend.model.EnvironmentClearanceProjectActivityDetails;
import com.backend.model.EnvironmentClearence;
import com.backend.model.SubActivities;
import com.backend.repository.postgres.ActivityRepository;
import com.backend.repository.postgres.EnvironmentClearanceProjectActivityDetailsRepository;
import com.backend.repository.postgres.EnvironmentClearenceRepository;
import com.backend.repository.postgres.SubActivityRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ECProjectActivityDetailsServive {

	@Autowired
	EnvironmentClearanceProjectActivityDetailsRepository environmentClearanceProjectActivityDetailsRepository;

	@Autowired
	EnvironmentClearenceRepository environmentClearanceRepository;

	@Autowired
	ActivityRepository activityRepository;

	@Autowired
	SubActivityRepository subActivityRepository;

	@Autowired
	UserService userService;

	public List<EnvironmentClearanceProjectActivityDetails> saveEnvironmentClearanceProjectActivityDetails(Integer ecId,
			List<EnvironmentClearanceProjectActivityDetails> environmentClearanceProjectActivityDetails) {

		EnvironmentClearence temp = environmentClearanceRepository.findById(ecId).get();

		List<EnvironmentClearanceProjectActivityDetails> projectActivityDetails = environmentClearanceProjectActivityDetails
				.stream().map(value -> {
					value.setEnvironmentClearence(temp);
					value.setProposalNo(temp.getProposal_no());
					value.setActivities(activityRepository.findById(value.getActivityId())
							.orElseThrow(() -> new ProjectNotFoundException("activity not found for activity ID")));
					value.setSubActivities(subActivityRepository.findById(value.getSubActivityId()).orElseThrow(
							() -> new ProjectNotFoundException("subactivity not found for subactivity ID")));

					return value;
				}).collect(Collectors.toList());
		List<EnvironmentClearanceProjectActivityDetails> environmentDetails = environmentClearanceProjectActivityDetailsRepository
				.saveAll(projectActivityDetails);
		log.info("INFO ------------ saveEnvironmentClearanceProjectActivityDetails Environment Clearance Project Activity Details WITH ecId ----> "+ecId+" ----SAVED - SUCCESS");
		return environmentDetails;
	}

	public ResponseEntity<Object> getECProjectActivityData(int id) {
		log.info("INFO ------------ getECProjectActivityData Environment Clearance Project Activity Data WITH EcID ----> "+id+" ---- FOUND and RETRIEVED - SUCCESS");
		return ResponseHandler.generateResponse("get environment data list by ec_id", HttpStatus.OK, "",
				environmentClearanceProjectActivityDetailsRepository.findDetailByEcId(id));

	}

	public ResponseEntity<Object> deleteECProjectActivityData(int id) {

		EnvironmentClearanceProjectActivityDetails environmentClearanceProjectActivityDetails = environmentClearanceProjectActivityDetailsRepository
				.findById(id).get();
		environmentClearanceProjectActivityDetails.setDelete(true);
		environmentClearanceProjectActivityDetailsRepository.save(environmentClearanceProjectActivityDetails);
		log.info("INFO ------------ deleteECProjectActivityData Environment Clearance Project Activity Data WITH EcID ----> "+id+" ---- FOUND and DELETED - SUCCESS");
		return ResponseHandler.generateResponse("delete environment data list", HttpStatus.OK, "",
				"deleted successfully");

	}

}
