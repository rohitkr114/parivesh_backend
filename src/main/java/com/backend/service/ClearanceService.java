package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.exceptions.ProjectNotFoundException;
import com.backend.exceptions.UserNotFoundException;
import com.backend.model.Applications;
import com.backend.model.ClearanceAction;
import com.backend.model.ClearanceFactSheet;
import com.backend.model.ClearanceHistory;
import com.backend.model.ProponentApplications;
import com.backend.model.Role;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.ClearanceActionRepository;
import com.backend.repository.postgres.ClearanceFactSheetRepository;
import com.backend.repository.postgres.ClearanceHistoryRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.RoleRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ClearanceService {

	@Autowired
	private ClearanceActionRepository clearanceActionRepository;

	@Autowired
	private ClearanceHistoryRepository clearanceHistoryRepository;

	@Autowired
	private ClearanceFactSheetRepository clearanceFactSheetRepository;

	@Autowired
	private ApplicationsRepository applicationsRepository;

	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;

	@Autowired
	private RoleRepository roleRepository;

	public ClearanceAction saveClearanceAction(ClearanceAction clearanceAction, Integer application_id) {
		Applications applications = applicationsRepository.findById(application_id)
				.orElseThrow(() -> new ProjectNotFoundException("Application not found with id"));
		clearanceAction.setApplications(applications);
		log.info("INFO ------------ saveClearanceAction saving WITH APPLICATION ID ---->" + application_id
				+ " -SUCCESS");
		return clearanceActionRepository.save(clearanceAction);
	}

	public ClearanceFactSheet saveClearanceFactSheet(ClearanceFactSheet clearanceFactSheet) {
		log.info("INFO ------------ saveClearanceFactSheet  clearanceFactSheet ---->" + clearanceFactSheet
				+ " -SUCCESS");
		return clearanceFactSheetRepository.save(clearanceFactSheet);
	}

	public ClearanceHistory saveClearanceHistory(ClearanceHistory clearanceHistory, Integer proposal_id,
			Long clearance_action_id) {
		ProponentApplications applications = proponentApplicationRepository.findById(proposal_id)
				.orElseThrow(() -> new ProjectNotFoundException("Proponent Application not found with id"));
		
		ClearanceAction action=clearanceActionRepository.findById(clearance_action_id).orElseThrow(()-> new ProjectNotFoundException("Clearance Action not Found with Id"));
		clearanceHistory.setClearanceAction(action);
		clearanceHistory.setProposal_no(applications.getProposal_no());
		clearanceHistory.setProponentApplications(applications);
		
		log.info("INFO ------------ saveClearanceHistory saving WITH clearanceHistory ----->" + clearanceHistory
				+ "-----proposal_id---->" + proposal_id + "-SUCCESS");
		return clearanceHistoryRepository.save(clearanceHistory);
	}

	public List<ClearanceAction> getClearanceActions(Integer application_id) {
		if (application_id != null) {
			log.info("INFO ------------ getClearanceActions WHERE APPLICATION_ID is NOT NULL ----application_id----> "
					+ application_id + "- SUCCESS");
			return clearanceActionRepository.findAllByApplicationId(application_id);
		} else {
			log.info("INFO ------------ getClearanceActions WHERE APPLICATION_ID is NULL----application_id---->"
					+ application_id + "----therefore: GET ALL - SUCCESS");
			return clearanceActionRepository.findAll();
		}
	}

	public ResponseEntity<Object> getClearanceHistories(Integer proposal_id, String proposal_no) {
		try {
			if (proposal_id != null) {
				log.info("INFO ------------ getClearanceHistories WHERE PROPOSAL_ID is NOT NULL----proposal_id---->"
						+ proposal_id + " - SUCCESS");
				return ResponseHandler.generateResponse("Get Clearance History", HttpStatus.OK, "",
						clearanceHistoryRepository.findAllByProposalId(proposal_id));
			} else {
				log.info("INFO ------------ getClearanceHistories WHERE PROPOSAL_ID is NULL----proposal_no---->"
						+ proposal_no + "----therefore: GET ALL - SUCCESS");
				return ResponseHandler.generateResponse("Get Clearance History", HttpStatus.OK, "",
						clearanceHistoryRepository.findAllByProposalNo(proposal_no));
			}
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("Get Clearance History", HttpStatus.BAD_REQUEST, "",
					ex.getMessage());
		}
	}

	public List<ClearanceFactSheet> getClearanceFactSheet(String proposal_no) {
		if (proposal_no != null) {
			log.info("INFO ------------ getClearanceFactSheet WHERE PROPOSAL_ID is NOT NULL ----proposal_no---->"
					+ proposal_no + "- SUCCESS");
			return clearanceFactSheetRepository.findAllByProposalId(proposal_no);
		} else {
			log.info("INFO ------------ getClearanceFactSheet WHERE PROPOSAL_ID is NULL----proposal_no---->"
					+ proposal_no + "----therefore: GET ALL - SUCCESS");
			return clearanceFactSheetRepository.findAll();
		}
	}

	public String deleteClearanceAction(Long clearance_action_id) {
		ClearanceAction clearanceAction = clearanceActionRepository.findById(clearance_action_id)
				.orElseThrow(() -> new ProjectNotFoundException("Clearance action not found with id"));
		clearanceAction.setIs_active(false);
		clearanceAction.setIs_deleted(true);
		clearanceActionRepository.save(clearanceAction);
		log.info("INFO ------------ deleteClearanceAction WITH clearance_action_id---->" + clearance_action_id
				+ " - SUCCESS");
		return "Record deleted successfully";
	}

	public String deleteClearanceHistory(Long clearance_history_id) {
		ClearanceHistory clearanceHistory = clearanceHistoryRepository.findById(clearance_history_id)
				.orElseThrow(() -> new ProjectNotFoundException("Clearance action not found with id"));
		clearanceHistory.setIs_active(false);
		clearanceHistory.setIs_deleted(true);
		clearanceHistoryRepository.save(clearanceHistory);
		log.info("INFO ------------ deleteClearanceHistory WITH clearance_history_id---->" + clearance_history_id
				+ " - SUCCESS");
		return "Record deleted successfully";
	}

	public String deleteFactSheet(Long fact_sheet_id) {
		ClearanceFactSheet clearanceFactSheet = clearanceFactSheetRepository.findById(fact_sheet_id)
				.orElseThrow(() -> new ProjectNotFoundException("Clearance fact sheet not found with id"));
		clearanceFactSheet.setIs_active(false);
		clearanceFactSheet.setIs_deleted(true);
		clearanceFactSheetRepository.save(clearanceFactSheet);
		log.info("INFO ------------ deleteFactSheet WITH fact_sheet_id ---->" + fact_sheet_id + "- SUCCESS");
		return "Record deleted successfully";
	}
}
