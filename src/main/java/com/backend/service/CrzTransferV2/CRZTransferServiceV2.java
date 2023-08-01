package com.backend.service.CrzTransferV2;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

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
import com.backend.model.Crz.CrzBasicDetails;
import com.backend.model.CrzTransferV2.CRZTransferEnclosureDetailsV2;
import com.backend.model.CrzTransferV2.CRZTransferProposalDetailsV2;
import com.backend.model.CrzTransferV2.CRZTransferUndertakingV2;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.CommonFormDetailRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.StateRepository;
import com.backend.repository.postgres.Crz.CrzBasicDetailsRepository;
import com.backend.repository.postgres.CrzTransferV2.CRZTransferEnclosureDetailsRepositoryV2;
import com.backend.repository.postgres.CrzTransferV2.CRZTransferProposalDetailsRepositoryV2;
import com.backend.repository.postgres.CrzTransferV2.CRZTransferUndertakingRepositoryV2;
import com.backend.response.ResponseHandler;
import com.backend.service.CommonFormDetailService;
import com.backend.service.NotificationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CRZTransferServiceV2 {

	@Autowired
	private CRZTransferProposalDetailsRepositoryV2 crzTransferProposalDetailsRepositoryV2;

	@Autowired
	private CRZTransferEnclosureDetailsRepositoryV2 crzTransferEnclosureDetailsRepositoryV2;

	@Autowired
	private CRZTransferUndertakingRepositoryV2 crzTransferUndertakingRepositoryV2;

	@Autowired
	private CommonFormDetailRepository commonFormDetailRepository;

	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;

	@Autowired
	private CommonFormDetailService commonFormDetailService;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private ApplicationsRepository applicationsRepository;

    @Autowired
    private CrzBasicDetailsRepository crzBasicDetailsRepository;

	@Autowired
	private StateRepository stateRepository;

	public ResponseEntity<Object> saveCrzTransferProposalDetails(Integer cafId, Integer clearanceId, Integer crzId,
			CRZTransferProposalDetailsV2 crzTransferProposalDetailsV2) {
		CRZTransferProposalDetailsV2 temp = null;
		try {
			CommonFormDetail caf = commonFormDetailRepository.findById(cafId)
					.orElseThrow(() -> new ProjectNotFoundException("Caf form not found for caf id " + cafId));
			crzTransferProposalDetailsV2.setCommonFormDetail(caf);
			System.out.println(caf);

			CrzBasicDetails crzForm = null;
			if (crzId != null && crzId != 0) {
				crzForm = crzBasicDetailsRepository.findById(crzId).orElse(null);
				crzTransferProposalDetailsV2.setCrzBasicDetails(crzForm);
				crzTransferProposalDetailsV2.setCrz_id(crzId);
			} else {
				crzId = 0;
			}

			if (crzTransferProposalDetailsV2.getId() != 0 && crzTransferProposalDetailsV2.getId() != null) {
				CRZTransferProposalDetailsV2 form = crzTransferProposalDetailsRepositoryV2.findById(crzTransferProposalDetailsV2.getId())
						.orElseThrow(() -> new ProjectNotFoundException("CRZ transfer proposal details not found for id:"+ crzTransferProposalDetailsV2.getId()));
				crzTransferProposalDetailsV2.setProposalNo(form.getProposalNo());

				return ResponseHandler.generateResponse("saving crz transfer proposal details", HttpStatus.OK, "",
						crzTransferProposalDetailsRepositoryV2.save(crzTransferProposalDetailsV2));
			}

			List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();
			ProponentApplications applications = new ProponentApplications();

			if (tempClearances.isEmpty()) {
				String proposal_no = "IA/" + stateRepository.getStateByCode(caf.getProjectDetails().getMain_state())
						.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
						+ "/" + "CRZ" + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
				applications.setProposal_sequence(400000);
				proposal_no = proposal_no.replaceAll("\\s", "");
				applications.setProposal_no(proposal_no);
				crzTransferProposalDetailsV2.setProposalNo(proposal_no);
			} else {
				Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence())
						.max(Comparator.comparing(Integer::valueOf)).get();
				if (maxCount != null) {
					applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
					applications.setProposal_no(generateProposalNo(maxCount, caf));
					crzTransferProposalDetailsV2.setProposalNo(applications.getProposal_no().replaceAll("\\s", ""));
				}
			}

			temp = crzTransferProposalDetailsRepositoryV2.save(crzTransferProposalDetailsV2);
			Applications app = applicationsRepository.findById(clearanceId).get();
			applications.setCaf_id(cafId);
			applications.setProposal_id(temp.getId());
			applications.setState(stateRepository.getStateByCode(caf.getProjectDetails().getMain_state())
					.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getName());

			applications.setState_id(stateRepository.getStateByCode(caf.getProjectDetails().getMain_state())
					.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getCode());
			applications.setProjectDetails(caf.getProjectDetails());
			applications.setApplications(app);
			applications.setLast_status(Caf_Status.DRAFT.toString());

			proponentApplicationRepository.save(applications);

		} catch (Exception e) {
			log.error("Encountered Exception", e);
			throw new PariveshException("Error in saving crz transfer proposal details - ", e);
		}

		return ResponseHandler.generateResponse("saving crz transfer details", HttpStatus.OK, "", temp);
	}

	public ResponseEntity<Object> getCrzTransferForm(Integer id) {
		try {
			CRZTransferProposalDetailsV2 form = crzTransferProposalDetailsRepositoryV2.findById(id)
					.orElseThrow(() -> new ProjectNotFoundException("CRZ transfer proposal details not found for id:" + id));
			form.setProponentApplications(proponentApplicationRepository.getApplicationByProposalId(id));

			return ResponseHandler.generateResponse("getting crz transfer form", HttpStatus.OK, "", form);
		} catch (Exception e) {
			log.error("Encountered Exception", e);
			throw new PariveshException("Error in getting crz transfer form - ", e);
		}
	}

	public ResponseEntity<Object> saveCrzEnclosureDetails(Integer crzTransferId, CRZTransferEnclosureDetailsV2 crzTransferEnclosureDetailsV2){
		try {
			if(crzTransferEnclosureDetailsV2.getId()==null || crzTransferEnclosureDetailsV2.getId()==0) {
				CRZTransferEnclosureDetailsV2 temp= crzTransferEnclosureDetailsRepositoryV2.getDataByFormId(crzTransferId).orElse(null);
				if(temp!=null) {
					crzTransferEnclosureDetailsV2.setId(temp.getId());
				}
			}
			
			CRZTransferProposalDetailsV2 proposalDetails = crzTransferProposalDetailsRepositoryV2.findById(crzTransferId)
					.orElseThrow(() -> new ProjectNotFoundException("CRZ transfer proposal details not found for id:" + crzTransferId));
			crzTransferEnclosureDetailsV2.setCrzTransferProposalDetails(proposalDetails);
			
			CRZTransferEnclosureDetailsV2 response= crzTransferEnclosureDetailsRepositoryV2.save(crzTransferEnclosureDetailsV2);
			
			return ResponseHandler.generateResponse("saving crz transfer enclosure details", HttpStatus.OK, "", response);
		}catch (Exception e) {
			log.error("Encountered Exception", e);
			throw new PariveshException("Error in saving crz transfer enclosure details - ", e);
		}
	}

	public ResponseEntity<Object> saveUndertaking(Integer crzTransferId,
			CRZTransferUndertakingV2 crzTransferUndertakingV2) {
		try {
			if (crzTransferUndertakingV2.getId() == null || crzTransferUndertakingV2.getId() == 0) {
				CRZTransferUndertakingV2 temp = crzTransferUndertakingRepositoryV2.getDataByFormId(crzTransferId).orElse(null);

				if (temp != null) {
					crzTransferUndertakingV2.setId(temp.getId());
				}
			}

			CRZTransferProposalDetailsV2 proposalDetails = crzTransferProposalDetailsRepositoryV2.findById(crzTransferId)
					.orElseThrow(() -> new ProjectNotFoundException("CRZ transfer proposal details not found for id:" + crzTransferId));
			crzTransferUndertakingV2.setCrzTransferProposalDetails(proposalDetails);

			ProponentApplications proponentApplications = proponentApplicationRepository.getApplicationByProposalNo(proposalDetails.getProposalNo());

			proponentApplications.setLast_status(Caf_Status.SUBMITTED.toString());

			Optional<CommonFormDetail> cafDetail = commonFormDetailRepository.findByCaf(proposalDetails.getCommonFormDetail().getId());
//			Optional<CommonFormDetail> cafDetail = commonFormDetailRepository
//					.findByCaf((Integer) ecForm7Repository.getCafByEcId(temp.getId()).get(0)[0]);
			proponentApplications.setProposal_no(generateProposalNo(proponentApplications.getProposal_sequence() - 1, cafDetail.get()));

			if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString())
					|| proponentApplications.getLast_status().equals(Caf_Status.EDS_REPLIED.toString())) {
				if (crzTransferUndertakingV2.getSubmission_date() != null)
					proponentApplications.setLast_submission_date(crzTransferUndertakingV2.getSubmission_date());
				else {
					proponentApplications.setLast_submission_date(new Date());
				}
			}
			commonFormDetailService.saveCommonForm(cafDetail.get());
			proponentApplicationRepository.save(proponentApplications);

			try {
				notificationService.sendProposalSMS(proponentApplications.getProposal_no());

				notificationService.sendProposalEmail(proponentApplications.getProposal_no());

			} catch (Exception e) {
				log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
				return ResponseHandler.generateResponse("Save crz transfer Undertaking details", HttpStatus.OK, null,
						crzTransferUndertakingRepositoryV2.save(crzTransferUndertakingV2));
			}

			return ResponseHandler.generateResponse("Save crz transfer Undertaking details", HttpStatus.OK, null,
					crzTransferUndertakingRepositoryV2.save(crzTransferUndertakingV2));

		} catch (Exception e) {
			log.error("Encountered Exception", e);
			throw new PariveshException("Error in saving crz undertaking details - ", e);
		}
	}

	public String generateSequenceNumber(int maxcount) {
		// this will convert any number sequence into 6 character.
		AtomicInteger sequence = new AtomicInteger(maxcount);
		// return ResponseHandler.generateResponse("CAF
		// Others",HttpStatus.OK,"",String.format("%06d", sequence.addAndGet(1)));
		return String.format("%06d", sequence.addAndGet(1));
	}

	public String generateProposalNo(int maxcount, CommonFormDetail caf) {
		String cafId = "IA/"
				+ stateRepository.getStateByCode(caf.getProjectDetails().getMain_state())
						.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
				+ "/" + "CRZ" + "/" + generateSequenceNumber(maxcount) + "/"
				+ String.valueOf(LocalDate.now().getYear());
		// return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
		cafId = cafId.replaceAll("\\s", "");
		return cafId;
	}
}
