package com.backend.service;

import com.backend.dto.*;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.ProponentApplications;
import com.backend.model.RemovedProposals;
import com.backend.repository.postgres.ForestClearanceRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.RemovedProposalsRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class RemovedProposalsService {

	@Autowired
	private RemovedProposalsRepository removedProposalsRepository;

	@Autowired
	private ForestClearanceRepository forestClearanceRepository;

	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;

	@Autowired
	NotificationService notificationService;

	@Transactional
	public ResponseEntity<Object> removeProposal(RemovedProposals proposals, UserPrincipal principal) {
		try {
			ProponentApplications applications = proponentApplicationRepository
					.findById(proposals.getProponentApplicationId()).orElseThrow(() -> new ProjectNotFoundException(
							"proponent application not found for id-" + proposals.getProponentApplicationId()));
			applications.setLast_status("REMOVED");
			applications.setLast_visible_status("REMOVED");

			proponentApplicationRepository.save(applications);

			Integer count = removedProposalsRepository.deleteHistory(proposals.getProponentApplicationId());
			if (count != 0) {
				log.info("history deleted successfully for application id-" + proposals.getProponentApplicationId());
			}

			Integer count2 = removedProposalsRepository.deleteStepAuthority(proposals.getProponentApplicationId());
			if (count2 != 0) {
				log.info("step authority deleted successfully for application id-"
						+ proposals.getProponentApplicationId());
			}

			// send email functionality
			sendEmailNotificationInfo(proposals, principal);

			return ResponseHandler.generateResponse("proposal removed successfully", HttpStatus.OK, "",
					removedProposalsRepository.save(proposals));

		} catch (Exception e) {
			log.error("Encountered exception", e);
			throw new PariveshException("error in deleting proposal", e);
		}
	}

	public ResponseEntity<Object> getDeletedProposals(RemovedLogsRequest request, UserPrincipal principal) {
		try {
			log.info("getDeletedProposals-> RemovedLogsRequest: {}", request);
			List<RemovedProposals> proposals = new ArrayList<RemovedProposals>();
			proposals = removedProposalsRepository.getProposalList(request.getFromDate(), request.getToDate(),
					principal.getId());

			return ResponseHandler.generateResponse("getting deleted proposal list for userId: " + principal.getId(),
					HttpStatus.OK, "", proposals);
		} catch (Exception e) {
			log.error("encountered exception", e);
			throw new PariveshException("error in getting list", e);
		}
	}

	public ResponseEntity<Object> getProposalForRemoval(UserPrincipal principal){
		try{
			List<ProposalForRemovalDto> response=removedProposalsRepository.getProposalForRemoval(principal.getId());

			return ResponseHandler.generateResponse("getting proposal for removal",HttpStatus.OK,"",response);
		} catch (Exception e) {
			log.error("encountered exception", e);
			throw new PariveshException("error in getting proposal for removal", e);
		}
	}

	public void sendEmailNotificationInfo(RemovedProposals proposals, UserPrincipal principal) {
		FcEmailDto userInfo = forestClearanceRepository.getUserInfo(principal.getId());
		FcEmailDto proponentDetails = forestClearanceRepository.getFcEmailData(proposals.getProposalNo());

		List<FcEmailDto> emailDtoList = new ArrayList<FcEmailDto>();
		emailDtoList.add(proponentDetails);

		FCEmailDtoImpl fcEmailImpl = new FCEmailDtoImpl();
		fcEmailImpl.setClearance_type(proponentDetails.getClearance_type());
		fcEmailImpl.setProposal_no(proponentDetails.getProposal_no());
		fcEmailImpl.setProject_name(proponentDetails.getProject_name());
		fcEmailImpl.setOrgname(proponentDetails.getOrgname());
		fcEmailImpl.setForest_land(proponentDetails.getForest_land());
		fcEmailImpl.setShape_of_project(proponentDetails.getShape_of_project());
		fcEmailImpl.setProposal_applied_for(proponentDetails.getProposal_applied_for());
		fcEmailImpl.setApproval_granting_authority(proponentDetails.getApproval_granting_authority());
		fcEmailImpl.setStatename(proponentDetails.getStatename());
		fcEmailImpl.setDistrictname(proponentDetails.getDistrictname());
		fcEmailImpl.setLocation(proponentDetails.getLocation());
		fcEmailImpl.setProposal_category(proponentDetails.getProposal_category());
		fcEmailImpl.setApplication_status(proponentDetails.getApplication_status());
		fcEmailImpl.setEmailid(principal.getEmail());
		fcEmailImpl.setMobilenumber(userInfo.getMobilenumber());
		fcEmailImpl.setMeeting_start_date(proponentDetails.getMeeting_start_date());
		fcEmailImpl.setMeeting_mode(proponentDetails.getMeeting_mode());
		fcEmailImpl.setMeeting_venue(proponentDetails.getMeeting_venue());

		emailDtoList.add(fcEmailImpl);

		notificationService.sendRemoveProposalsNotification(proposals, emailDtoList);
	}
}
