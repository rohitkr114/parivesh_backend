package com.backend.service.WildLifeService;

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
import com.backend.model.WildLifeClearance.WildLifeClearanceProposedDiversions;
import com.backend.model.WildLifeClearance.WlProposedDiversionsDetails;
import com.backend.repository.postgres.FcProposedDiversionDetailsRepository;
import com.backend.repository.postgres.ForestClearanceDivisionPatchDetailsRepository;
import com.backend.repository.postgres.ForestClearanceProposedDiversionsRepository;
import com.backend.repository.postgres.ForestClearanceRepository;
import com.backend.repository.postgres.WildLifeClearance.WildLifeClearanceDivisionPatchDetailsRepository;
import com.backend.repository.postgres.WildLifeClearance.WildLifeClearanceProposedDiversionsRepository;
import com.backend.repository.postgres.WildLifeClearance.WildLifeClearanceRepository;
import com.backend.repository.postgres.WildLifeClearance.WlProposedDiversionDetailsRepository;
import com.backend.response.ResponseHandler;
import com.backend.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class WildLifeClearanceProposedDiversionsService {

	@Autowired
	private WildLifeClearanceProposedDiversionsRepository wildlifeClearanceProposedDiversionsRepository;
	
	@Autowired
	private WildLifeClearanceRepository wildLifeClearanceRepository;
	
	private WildLifeClearanceDivisionPatchDetailsRepository wildlifeClearanceDivisionPatchDetailsRepository;

	@Autowired
	private UserService userService;
	
	@Autowired
	private WlProposedDiversionDetailsRepository wlProposedDiversionDetailsRepository;
	
	public ResponseEntity<Object> saveWLProposedDiversionsDetails(Integer id,
			List<WildLifeClearanceProposedDiversions> wildlifeClearanceProposedDiversionsList) {
		List<WildLifeClearanceProposedDiversions> diversions = new ArrayList<>();
		WildLifeClearance temp = wildLifeClearanceRepository.findById(id).get();
		wildlifeClearanceProposedDiversionsList.forEach(value -> {
					value.setWildLifeClearance(temp);
					if(value.getDiversion_map_copy() != null) {						
						value.getDiversion_map_copy().setProposal_no(temp.getProposal_no());
					}
					WildLifeClearanceProposedDiversions wildlifeClearanceProposedDiversions = wildlifeClearanceProposedDiversionsRepository.save(value);
					diversions.add(wildlifeClearanceProposedDiversions);
				});
		log.info("INFO ------------ saveWLProposedDiversionsDetails WITH wildlife clearance id ----> "+id+" ---- SAVE- SUCCESS");
		return ResponseHandler.generateResponse("Save WL Proposed Diversion Data", HttpStatus.OK, " ",
				diversions);

	}
	
	public ResponseEntity<Object> getWLPropesdDiversionData(int id) {
		log.info("INFO ------------ getWLPropesdDiversionData WITH wild life proposed diversion id ----> "+id+" ---- RETRIEVING- SUCCESS");
		return ResponseHandler.generateResponse("Get WL kmls data list by wl_id", HttpStatus.OK, "",
				wildlifeClearanceProposedDiversionsRepository.findByWLID(id));

	}
	
	public ResponseEntity<Object> deleteWildLifeClearancePropesdDiversionData(int id) {
		WildLifeClearanceProposedDiversions temp=wildlifeClearanceProposedDiversionsRepository.getById(id);
		
		temp.setDelete(true);
		log.info("INFO ------------ deleteWildLifeClearancePropesdDiversionData WITH WL id ----> "+id+" ---- DELETING - SUCCESS");
		return ResponseHandler.generateResponse("WildLife Clearance Proposed Diversion", HttpStatus.OK, "",
				wildlifeClearanceProposedDiversionsRepository.save(temp));

	}
	
	public ResponseEntity<Object> deleteIntersection(int id){
		WlProposedDiversionsDetails details = wlProposedDiversionDetailsRepository.findById(id).orElseThrow();
		details.setDelete(true);
		return ResponseHandler.generateResponse("DELETE TRUE wlProposedDiversionDetails BY ID ------>"+id, HttpStatus.OK, "NO ERROR", wlProposedDiversionDetailsRepository.save(details));
	}


}
