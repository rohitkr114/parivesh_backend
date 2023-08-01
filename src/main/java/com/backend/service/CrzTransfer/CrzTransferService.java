package com.backend.service.CrzTransfer;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.ProponentApplications;
import com.backend.model.CrzTransfer.CrzTransferDetails;
import com.backend.model.CrzTransfer.CrzTransferUndertaking;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.CommonFormDetailRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.StateRepository;
import com.backend.repository.postgres.CrzTransfer.CrzTransferDetailsRepository;
import com.backend.repository.postgres.CrzTransfer.CrzTransferUndertakingRepository;
import com.backend.response.ResponseHandler;
import com.backend.service.CommonFormDetailService;
import com.backend.service.NotificationService;
import com.backend.util.ServerUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CrzTransferService {

	@Autowired
	private CrzTransferDetailsRepository crzTransferDetailsRepository;

	@Autowired
	private CrzTransferUndertakingRepository crzTransferUndertakingRepository;

	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;

	@Autowired
	private CommonFormDetailService commonFormDetailService;

	@Autowired
	private CommonFormDetailRepository commonFormDetailRepository;

	@Autowired
	private ApplicationsRepository applicationsRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private ServerUtil util;

	@Autowired
	private NotificationService notificationSevice;

	public ResponseEntity<Object> saveCRZTransferDetails(CrzTransferDetails crzTransferDetails, Integer caf_id,
			HttpServletRequest request) throws PariveshException {
		CommonFormDetail temp = null;
		CrzTransferDetails temp2 = null;
		try {
			temp = new CommonFormDetail();
			temp = commonFormDetailRepository.findDetailByCafId(caf_id);
			crzTransferDetails.setCommonFormDetail(temp);
			if (crzTransferDetails.getId() != null && crzTransferDetails.getId() != 0) {
				CrzTransferDetails form = crzTransferDetailsRepository.findById(crzTransferDetails.getId())
						.orElseThrow(() -> new ProjectNotFoundException("CRZ clearance form not found"));
				crzTransferDetails.setProposal_no(form.getProposal_no().replaceAll("\\s", ""));

				return ResponseHandler.generateResponse("Save crz transfer Details form", HttpStatus.OK, null,
						crzTransferDetailsRepository.save(crzTransferDetails));
			}
			List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();
			ProponentApplications applications = new ProponentApplications();
			if (tempClearances.isEmpty()) {
				String proposal_no = "IA/" + stateRepository.getStateByCode(temp.getProjectDetails().getMain_state())
						.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
						+ "/" + "CRZ" + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
				applications.setProposal_sequence(400000);
				proposal_no = proposal_no.replaceAll("\\s", "");
				applications.setProposal_no(proposal_no);
				crzTransferDetails.setProposal_no(proposal_no);
			} else {
				Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence())
						.max(Comparator.comparing(Integer::valueOf)).get();
				if (maxCount != null) {
					applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
					applications.setProposal_no(generateProposalNo(maxCount, temp));
					crzTransferDetails.setProposal_no(applications.getProposal_no().replaceAll("\\s", ""));
				}
			}
			temp2 = new CrzTransferDetails();
			temp2 = crzTransferDetailsRepository.save(crzTransferDetails);
			Applications app = applicationsRepository.findById(4).get();
			applications.setCaf_id(caf_id);
			applications.setProposal_id(temp2.getId());
			applications.setState(stateRepository.getStateByCode(temp.getProjectDetails().getMain_state())
					.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getName());

			/*
			 * applications.setState_id(stateRepository.getStateByCode(temp.
			 * getProjectDetails().getMain_state()) .orElseThrow(() -> new
			 * ProjectNotFoundException("state not found with code")).getId());
			 */

			applications.setState_id(stateRepository.getStateByCode(temp.getProjectDetails().getMain_state())
					.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getCode());

			applications.setProjectDetails(temp.getProjectDetails());
			applications.setApplications(app);
			applications.setLast_status(Caf_Status.DRAFT.toString());
			applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
			proponentApplicationRepository.save(applications);
		} catch (Exception e) {
			// log.error(e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving CRZ Transfer Details for caf_id- " + caf_id);
		}
		return ResponseHandler.generateResponse("Save crz Transfer Details form", HttpStatus.OK, null, temp2);
	}

	public String generateSequenceNumber(int maxcount) {
		// this will convert any number sequence into 6 character.
		AtomicInteger sequence = new AtomicInteger(maxcount);
		// return ResponseHandler.generateResponse("CAF
		// Others",HttpStatus.OK,"",String.format("%06d", sequence.addAndGet(1)));
		return String.format("%06d", sequence.addAndGet(1));
	}

	private String generateProposalNo(int maxcount, CommonFormDetail temp) {
		String cafId = "IA/"
				+ stateRepository.getStateByCode(temp.getProjectDetails().getMain_state())
						.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
				+ "/" + "CRZ" + "/" + generateSequenceNumber(maxcount) + "/"
				+ String.valueOf(LocalDate.now().getYear());
		// return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
		cafId = cafId.replaceAll("\\s", "");
		return cafId;
	}

	public ResponseEntity<Object> saveCrzTransferUndertaking(CrzTransferUndertaking crzUndertaking, Integer crz_id,
			HttpServletRequest request) throws PariveshException {
		try {
			CrzTransferDetails crzTransferDetails = crzTransferDetailsRepository.findById(crz_id)
					.orElseThrow(() -> new ProjectNotFoundException("Crz form not found"));
			crzUndertaking.setCrzTransferDetails(crzTransferDetails);
			ProponentApplications proponentApplications = proponentApplicationRepository
					.getApplicationByProposalNo(crzTransferDetails.getProposal_no());
			if (proponentApplications.getLast_status().equals(Caf_Status.EDS_RAISED.name())) {
				proponentApplications.setLast_status(Caf_Status.EDS_REPLIED.toString());
				proponentApplications.setMigration_status(false);
			} else {
				proponentApplications.setLast_status(Caf_Status.SUBMITTED.toString());
			}
			proponentApplications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
			if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString())
					|| proponentApplications.getLast_status().equals(Caf_Status.EDS_REPLIED.toString())) {
				if (crzUndertaking.getSubmission_date() != null)
					proponentApplications.setLast_submission_date(crzUndertaking.getSubmission_date());
				else {
					proponentApplications.setLast_submission_date(new Date());
				}
			}
			commonFormDetailService.saveCommonForm(crzTransferDetails.getCommonFormDetail());
			proponentApplicationRepository.save(proponentApplications);

			try {

				notificationSevice.sendProposalSMS(proponentApplications.getProposal_no());

				util.sendProposalEmail(proponentApplications);

			} catch (Exception e) {
				log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
				return ResponseHandler.generateResponse("Save crz transfer Undertaking for crz_id ", HttpStatus.OK, "",
						crzTransferUndertakingRepository.save(crzUndertaking));
			}
			return ResponseHandler.generateResponse("Save crz undertaking form", HttpStatus.OK, null,
					crzTransferUndertakingRepository.save(crzUndertaking));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving CRZ Undertaking Form for crz_id-" + crz_id);
		}
	}

	public ResponseEntity<Object> getCrzTransfer(Integer id) {

		try {
			CrzTransferDetails crzTransferDetails = crzTransferDetailsRepository.findById(id)
					.orElseThrow(() -> new ProjectNotFoundException("Crz transfer not found with id"));
			crzTransferDetails.setProponentApplications(
					proponentApplicationRepository.getApplicationByProposalId(crzTransferDetails.getId()));
			crzTransferDetails
					.setCrzTransferUndertaking(crzTransferUndertakingRepository.getDataByCrz(id).orElse(null));

			return ResponseHandler.generateResponse("Getting Crz transfer with id ---->", HttpStatus.OK, null,
					crzTransferDetails);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Getting Crz transfer for Id- " + id, e);
		}
	}

}