package com.backend.service.EcForm9;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.EcForm9.EcForm9Basicdetails;
import com.backend.model.EcForm9.EcForm9Undertaking;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.CommonFormDetailRepository;
import com.backend.repository.postgres.EcForm9.EcForm9BasicdetailsRepository;
import com.backend.repository.postgres.EcForm9.EcForm9UndertakingRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.StateRepository;
import com.backend.response.ResponseHandler;
import com.backend.service.CommonFormDetailService;
import com.backend.service.NotificationService;
import com.backend.util.ServerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class EcForm9Service {

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private EcForm9BasicdetailsRepository ecForm9BasicdetailsRepository;

	@Autowired
	private CommonFormDetailRepository commonFormDetailRepository;

	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;

	@Autowired
	private ApplicationsRepository applicationsRepository;

	@Autowired
	private EcForm9UndertakingRepository ecForm9UndertakingRepository;

	@Autowired
	private CommonFormDetailService commonFormDetailService;

	@Autowired
	private ServerUtil util;

	@Autowired
	private NotificationService notificationSevice;

	public Object saveEcFormV(EcForm9Basicdetails ecForm9, Integer caf_id, HttpServletRequest request) {

		try {
			CommonFormDetail commonForm = commonFormDetailRepository.findByCaf(caf_id)
					.orElseThrow(() -> new ProjectNotFoundException("Caf form not found for caf id ----> " + caf_id));
			ecForm9.setCommonFormDetail(commonForm);
			if (ecForm9.getId() != null && ecForm9.getId() != 0) {
//				EcForm9Basicdetails form = ecForm9BasicdetailsRepository.findByFormId(ecForm9.getId())
//						.orElseThrow(() -> new ProjectNotFoundException("EC form not found with id"));
//				ecForm9.setProposal_no(form.getProposal_no());
				ecForm9.setProposal_no(
						ecForm9BasicdetailsRepository.getProposalNo(ecForm9.getId()).replaceAll("\\s", ""));
				return ecForm9BasicdetailsRepository.save(ecForm9);
			}
			List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();

			ProponentApplications applications = new ProponentApplications();

			if (tempClearances.isEmpty()) {
				String proposal_no = null;
				proposal_no = "IA/" + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
						.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
						+ "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
				applications.setProposal_sequence(400000);
				proposal_no = proposal_no.replaceAll("\\s", "");
				applications.setProposal_no(proposal_no);
				ecForm9.setProposal_no(proposal_no);
			} else {
				Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence())
						.max(Comparator.comparing(Integer::valueOf)).get();
				if (maxCount != null) {
					applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
					applications.setProposal_no(generateProposalNo(maxCount, ecForm9, commonForm));
					ecForm9.setProposal_no(applications.getProposal_no().replaceAll("\\s", ""));
				}
			}
			EcForm9Basicdetails temp2 = ecForm9BasicdetailsRepository.save(ecForm9);
			Applications app = applicationsRepository.findById(5).get();
			applications.setCaf_id(caf_id);
			applications.setProposal_id(temp2.getId());
			applications.setState(stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
					.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getName());

			applications.setState_id(stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
					.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getCode());
			applications.setProjectDetails(commonForm.getProjectDetails());
			applications.setApplications(app);
			applications.setLast_status(Caf_Status.DRAFT.toString());
			applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);

			proponentApplicationRepository.save(applications);
			return temp2;
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC form V for ecFormVId- " + ecForm9.getId(), e);
		}
	}

	public Object getEcFormV(Integer id) {

		try {
			EcForm9Basicdetails ecForm9 = ecForm9BasicdetailsRepository.findById(id)
					.orElseThrow(() -> new ProjectNotFoundException("EC form  not found with id"));
			ecForm9.setProponentApplications(
					proponentApplicationRepository.getApplicationByProposalId(ecForm9.getId()));
			ecForm9.setEcForm9Undertaking(ecForm9UndertakingRepository.getDataByEcId(id).orElse(null));

			return ecForm9;
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Getting EC form V for Id- " + id, e);
		}
	}

	public ResponseEntity<Object> saveUndertaking(EcForm9Undertaking ecForm9Undertaking, Integer ecForm9Id,
			Boolean is_submit, HttpServletRequest request) {

		try {

			EcForm9Basicdetails temp = ecForm9BasicdetailsRepository.findById(ecForm9Id)
					.orElseThrow(() -> new ProjectNotFoundException("EC form V form not found"));

			ecForm9Undertaking.setEcForm9(temp);

			ProponentApplications proponentApplications = proponentApplicationRepository
					.getApplicationByProposalNo(temp.getProposal_no());

			proponentApplications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
			Optional<CommonFormDetail> cafDetail = commonFormDetailRepository
					.findByCaf((Integer) ecForm9BasicdetailsRepository.getCafByEcId(temp.getId()).get(0)[0]);
			proponentApplications.setProposal_no(
					generateProposalNo(proponentApplications.getProposal_sequence() - 1, temp, cafDetail.get()));

			if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString()) 
					|| proponentApplications.getLast_status().equals(Caf_Status.EDS_RAISED.name())) {
            	proponentApplications.setLast_submission_date(new Date());
            }
            if (proponentApplications.getLast_status().equals(Caf_Status.EDS_RAISED.name())) {
                proponentApplications.setLast_status(Caf_Status.EDS_REPLIED.toString());
                proponentApplications.setMigration_status(false);
            } else if (is_submit == true) {
                proponentApplications.setLast_status(Caf_Status.SUBMITTED.toString());
                
                /*
                 * Sending Notification For First Submit
                 */
                notificationSevice.sendProposalSMS(proponentApplications.getProposal_no());
                notificationSevice.sendProposalEmail(proponentApplications.getProposal_no());
            }
			
			/*
			if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString())
					|| proponentApplications.getLast_status().equals(Caf_Status.EDS_REPLIED.toString())) {
				if (ecForm9Undertaking.getSubmission_date() != null)
					proponentApplications.setLast_submission_date(ecForm9Undertaking.getSubmission_date());
				else {
					proponentApplications.setLast_submission_date(new Date());
				}
			}
			if (is_submit == true)
				proponentApplications.setLast_status(Caf_Status.SUBMITTED.toString());
				*/
			commonFormDetailService.saveCommonForm(cafDetail.get());
			proponentApplicationRepository.save(proponentApplications);
			/*if (is_submit == true) {
				notificationSevice.sendProposalSMS(proponentApplications.getProposal_no());

				notificationSevice.sendProposalEmail(proponentApplications.getProposal_no());

			}*/

			return ResponseHandler.generateResponse("Save Ec Undertaking details form", HttpStatus.OK, null,
					ecForm9UndertakingRepository.save(ecForm9Undertaking));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving Fc FormA PartB DC Undertaking details form id-" + ecForm9Id,
					e);
		}

	}

	public String generateSequenceNumber(int maxcount) {
		// this will convert any number sequence into 6 character.
		AtomicInteger sequence = new AtomicInteger(maxcount);

		return String.format("%06d", sequence.addAndGet(1));
	}

	private String generateProposalNo(int maxcount, EcForm9Basicdetails form, CommonFormDetail commonForm) {
		String cafId = "IA/"
				+ stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
						.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
				+ "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
		cafId = cafId.replaceAll("\\s", "");
		return cafId;

	}

}