package com.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.model.FcProposedDiversionsDetails;
import com.backend.model.ForestClearance;
import com.backend.model.ForestClearanceDivisionPatchDetails;
import com.backend.model.ForestClearanceMaps;
import com.backend.model.ForestClearanceProposedDiversions;
import com.backend.model.WildLifeClearance.WildLifeClearance;
import com.backend.repository.postgres.FcProposedDiversionDetailsRepository;
import com.backend.repository.postgres.ForestClearanceDivisionPatchDetailsRepository;
import com.backend.repository.postgres.ForestClearanceProposedDiversionsRepository;
import com.backend.repository.postgres.ForestClearanceRepository;
import com.backend.repository.postgres.WildLifeClearance.WildLifeClearanceRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ForestClearanceProposedDiversionsService {

	@Autowired
	private ForestClearanceProposedDiversionsRepository forestClearanceProposedDiversionsRepository;

	@Autowired
	private ForestClearanceRepository forestClearanceRepository;
	
	@Autowired
	private WildLifeClearanceRepository wildLifeClearanceRepository;
	
	private ForestClearanceDivisionPatchDetailsRepository forestClearanceDivisionPatchDetailsRepository;

	@Autowired
	private UserService userService;
	
	@Autowired
	private FcProposedDiversionDetailsRepository fcProposedDiversionDetailsRepository;
	
	public ResponseEntity<Object> saveForestProposedDiversionsDetails(Integer id,
			List<ForestClearanceProposedDiversions> forestClearanceProposedDiversionsList) {
		List<ForestClearanceProposedDiversions> diversions = new ArrayList<>();
		ForestClearance temp = forestClearanceRepository.findById(id).get();
		forestClearanceProposedDiversionsList.forEach(value -> {
					value.setForestClearance(temp);
					if(value.getDiversion_map_copy() != null) {						
						value.getDiversion_map_copy().setProposal_no(temp.getProposal_no());
					}
					ForestClearanceProposedDiversions forestClearanceProposedDiversions = forestClearanceProposedDiversionsRepository.save(value);
					diversions.add(forestClearanceProposedDiversions);
				});
		log.info("INFO ------------ saveForestProposedDiversionsDetails WITH forest clearance id ----> "+id+" ---- SAVE- SUCCESS");
		return ResponseHandler.generateResponse("Save Forest Proposed Diversion Data", HttpStatus.OK, " ",
				diversions);

	}

	public ResponseEntity<Object> saveWLProposedDiversionsDetails(Integer id,
			List<ForestClearanceProposedDiversions> forestClearanceProposedDiversionsList) {
		List<ForestClearanceProposedDiversions> diversions = new ArrayList<>();
		WildLifeClearance temp = wildLifeClearanceRepository.findById(id).get();
		forestClearanceProposedDiversionsList.forEach(value -> {
					value.setWildLifeClearance(temp);
					if(value.getDiversion_map_copy() != null) {						
						value.getDiversion_map_copy().setProposal_no(temp.getProposal_no());
					}
					ForestClearanceProposedDiversions forestClearanceProposedDiversions = forestClearanceProposedDiversionsRepository.save(value);
					diversions.add(forestClearanceProposedDiversions);
				});
		log.info("INFO ------------ saveWLProposedDiversionsDetails WITH forest clearance id ----> "+id+" ---- SAVE- SUCCESS");
		return ResponseHandler.generateResponse("Save WL Proposed Diversion Data", HttpStatus.OK, " ",
				diversions);

	}
	
	public ResponseEntity<Object> getForestClearancePropesdDiversionData(int id) {
		log.info("INFO ------------ getForestClearancePropesdDiversionData WITH forest clearance id ----> "+id+" ---- RETRIEVING- SUCCESS");
		return ResponseHandler.generateResponse("get forest kmls data list by fc_id", HttpStatus.OK, "",
				forestClearanceProposedDiversionsRepository.findByFCID(id));

	}
	
	public ResponseEntity<Object> getWLPropesdDiversionData(int id) {
		log.info("INFO ------------ getWLPropesdDiversionData WITH wild life proposed diversion id ----> "+id+" ---- RETRIEVING- SUCCESS");
		return ResponseHandler.generateResponse("Get WL kmls data list by wl_id", HttpStatus.OK, "",
				forestClearanceProposedDiversionsRepository.findByWLID(id));

	}
	
	public ResponseEntity<Object> deleteForestClearancePropesdDiversionData(int id) {
		ForestClearanceProposedDiversions temp=forestClearanceProposedDiversionsRepository.getById(id);
		
		temp.setDelete(true);
		log.info("INFO ------------ deleteForestClearancePropesdDiversionData WITH FC id ----> "+id+" ---- DELETING - SUCCESS");
		return ResponseHandler.generateResponse("Forest Clearance Proposed Diversion", HttpStatus.OK, "",
				forestClearanceProposedDiversionsRepository.save(temp));

	}
	
	public ResponseEntity<Object> deleteIntersection(int id){
		FcProposedDiversionsDetails details = fcProposedDiversionDetailsRepository.findById(id).orElseThrow();
		details.setDelete(true);
		return ResponseHandler.generateResponse("DELETE TRUE fcProposedDiversionDetails BY ID ------>"+id, HttpStatus.OK, "NO ERROR", fcProposedDiversionDetailsRepository.save(details));
	}

	/*public ResponseEntity<Object> addFCDivisionPatchDetails(
			List<ForestClearanceDivisionPatchDetails> forestClearanceDivisionPatchDetails,
			Integer FcProposedDiversionId) {

		List<ForestClearanceDivisionPatchDetails> forestClearanceDivisionPatchDetails2=new ArrayList<>();
		ForestClearanceProposedDiversions forestClearanceProposedDiversions =forestClearanceProposedDiversionsRepository.findById(FcProposedDiversionId).get(); 
		forestClearanceDivisionPatchDetails.forEach(value -> {
			value.setForestClearanceProposedDiversions(forestClearanceProposedDiversions);
			ForestClearanceDivisionPatchDetails clearanceDivisionPatchDetails=forestClearanceDivisionPatchDetailsRepository.save(value);
			forestClearanceDivisionPatchDetails2.add(clearanceDivisionPatchDetails);
		});
		return ResponseHandler.generateResponse("Save DC Division Patch Details", HttpStatus.OK, " ", forestClearanceDivisionPatchDetails2);

	}*/

}
