package com.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.model.ForestClearance;
import com.backend.model.PriorApprovals;
import com.backend.model.WildLifeClearance.WildLifeClearance;
import com.backend.repository.postgres.ForestClearanceRepository;
import com.backend.repository.postgres.PriorApprovalRepository;
import com.backend.repository.postgres.WildLifeClearance.WildLifeClearanceRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class PriorApprovalService {

	@Autowired
	private PriorApprovalRepository priorApprovalRepository;

	@Autowired
	private ForestClearanceRepository forestClearanceRepository;

	@Autowired
	private WildLifeClearanceRepository wildLifeClearanceRepository;

	@Autowired
	private UserService userService;

	public ResponseEntity<Object> saveForestApprovalDeatails(Integer id, Integer wl_id,
			List<PriorApprovals> ClearancePriorApprovals) {
		List<PriorApprovals> approvals = new ArrayList<PriorApprovals>();

		if (id != null) {
			ForestClearance temp = forestClearanceRepository.findById(id).get();

			approvals = ClearancePriorApprovals.stream().map(value -> {
				value.setForestClearance(temp);
				return value;
			}).collect(Collectors.toList());
		}

		if (wl_id != null) {
			WildLifeClearance temp = wildLifeClearanceRepository.findById(wl_id).get();

			approvals = ClearancePriorApprovals.stream().map(value -> {
				value.setWildLifeClearance(temp);
				return value;
			}).collect(Collectors.toList());
		}
		log.info("INFO ------------ saveForestApprovalDeatails WITH fc_id ----> "+id+" ----wl_id---->"+wl_id+" ---- SAVING FOREST APPROVAL DETAILS - SUCCESS");
		List<PriorApprovals> forestDetails = priorApprovalRepository.saveAll(approvals);

		return ResponseHandler.generateResponse("Save Forest Map Data", HttpStatus.OK, "", forestDetails);

	}

	public ResponseEntity<Object> getForestPriorApprovalData(Integer fc_id, Integer wl_id) {

		if (fc_id != null) {
			log.info("INFO ------------ getForestPriorApprovalData WITH fc_id ----> "+fc_id+" ----wl_id---->"+wl_id+" ---- RETRIEVING FOREST DATA FROM FC_ID - SUCCESS");
			return ResponseHandler.generateResponse("get forest data approval list by fc_id", HttpStatus.OK, "",
					priorApprovalRepository.findByFCID(fc_id));
		}
			
		else {
			log.info("INFO ------------ getForestPriorApprovalData WITH fc_id ----> "+fc_id+" ----wl_id---->"+wl_id+" ---- RETRIEVING WILD LIFE DATA APPROVAL LIST FROM FC_ID - SUCCESS");
			return ResponseHandler.generateResponse("Get wild life data approval list by fc_id", HttpStatus.OK, "",
					priorApprovalRepository.findByWLID(wl_id));
		}
			

	}

	public ResponseEntity<Object> deletePriorApprovalData(Integer priorId) {
		try {
			PriorApprovals priorTemp = priorApprovalRepository.getById(priorId);
			priorTemp.setDelete(true);
			log.info("INFO ------------ deletePriorApprovalData WITH PRIOR APPROVAL ID--- priorId ----> "+priorId+"---- DELETING PRIOR APPROVAL DATA FROM priorId - SUCCESS");
			return ResponseHandler.generateResponse("Delete Prior Approval ", HttpStatus.OK, "",
					priorApprovalRepository.save(priorTemp));
		} catch (Exception ex) {
			log.info("ERROR ------------ EXPECTATION_FAILED: deletePriorApprovalData WITH PRIOR APPROVAL ID--- priorId ----> "+priorId+"---- DELETING PRIOR APPROVAL DATA FROM priorId - FAILURE");
			return ResponseHandler.generateResponse("Delete Prior Approval ", HttpStatus.EXPECTATION_FAILED, "",
					ex.getMessage());
		}
	}
}
