package com.backend.service.EcForm8;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.dto.EcForm8.EcForm8OrganisationByNameDTO;
import com.backend.dto.EcForm8.EcForm8OrganisationDTO;
import com.backend.dto.EcForm8.EcForm8dto;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.EnvironmentClearence;
import com.backend.model.ProponentApplications;
import com.backend.model.EcForm8TransferOfTOR.ECForm8TransferCOP;
import com.backend.model.EcForm8TransferOfTOR.EcForm8AdditionalDocument;
import com.backend.model.EcForm8TransferOfTOR.EcForm8DetailOfTOR;
import com.backend.model.EcForm8TransferOfTOR.EcForm8DocumentAttached;
import com.backend.model.EcForm8TransferOfTOR.EcForm8LocationOfProject;
import com.backend.model.EcForm8TransferOfTOR.EcForm8ProjectActivityDetails;
import com.backend.model.EcForm8TransferOfTOR.EcForm8TransferOfTOR;
import com.backend.model.EcForm8TransferOfTOR.EcForm8Undertaking;
import com.backend.repository.postgres.ActivityRepository;
import com.backend.repository.postgres.ActivitySubActivitySectorRepository;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.CommonFormDetailRepository;
import com.backend.repository.postgres.EnvironmentClearenceRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.StateRepository;
import com.backend.repository.postgres.SubActivityRepository;
import com.backend.repository.postgres.EcForm8.ECForm8AdditionalDocumentsRepository;
import com.backend.repository.postgres.EcForm8.ECForm8TransferCOPRepository;
import com.backend.repository.postgres.EcForm8.EcForm8AdditionalDocumentRepository;
import com.backend.repository.postgres.EcForm8.EcForm8DetailsOfTORRepository;
import com.backend.repository.postgres.EcForm8.EcForm8DocumentAttachedRepository;
import com.backend.repository.postgres.EcForm8.EcForm8LocationOfProjectRepository;
import com.backend.repository.postgres.EcForm8.EcForm8MinorActivitySelectionRepository;
import com.backend.repository.postgres.EcForm8.EcForm8ProjectActivityDetailsRepository;
import com.backend.repository.postgres.EcForm8.EcForm8TransferOfTORRepository;
import com.backend.repository.postgres.EcForm8.EcForm8UndertakingRepository;
import com.backend.response.ResponseHandler;
import com.backend.service.CommonFormDetailService;
import com.backend.service.NotificationService;
import com.backend.service.UpdateOtherPropertyService;
import com.backend.util.ServerUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EcForm8Service {

	@Autowired
	EcForm8TransferOfTORRepository ecForm8TransferOfTORRepository;

	@Autowired
	ECForm8TransferCOPRepository ecForm8TransferCOPRepository;

	@Autowired
	EcForm8DocumentAttachedRepository ecForm8DocumentAttachedRepository;

	@Autowired
	EcForm8LocationOfProjectRepository ecForm8LocationOfProjectRepository;

	@Autowired
	EcForm8MinorActivitySelectionRepository ecForm8MinorActivitySelectionRepository;

	@Autowired
	EcForm8UndertakingRepository ecForm8UndertakingRepository;

	@Autowired
	EcForm8DetailsOfTORRepository ecForm8DetailsOfTORRepository;

	@Autowired
	EcForm8AdditionalDocumentRepository ecForm8AdditionalDocumentRepository;

	@Autowired
	ECForm8AdditionalDocumentsRepository ecForm8AdditionalDocumentsRepository;

	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	ApplicationsRepository applicationsRepository;

	@Autowired
	CommonFormDetailRepository commonFormDetailRepository;

	@Autowired
	EnvironmentClearenceRepository environmentClearenceRepository;

	@Autowired
	private CommonFormDetailService commonFormDetailService;

	@Autowired
	private ActivitySubActivitySectorRepository activitySectorRepository;

	@Autowired
	private UpdateOtherPropertyService updateOtherPropertyService;

	@Autowired
	private ServerUtil util;

	@Autowired
	private NotificationService notificationSevice;

	@Autowired
	private EcForm8ProjectActivityDetailsRepository ecForm8ProjectActivityDetailsRepository;

	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private SubActivityRepository subActivityRepository;

	public Object saveEcForm8TransferOfTOR(EcForm8TransferOfTOR ecForm8TransferOfTOR, Integer caf_id, Integer ec_id,
			HttpServletRequest request) throws PariveshException {
		try {
			HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
			EnvironmentClearence ecClearence = new EnvironmentClearence();
			if (ec_id != null) {
				ecClearence = environmentClearenceRepository.findByFormId(ec_id).orElse(null);
				ecForm8TransferOfTOR.setEnvironmentClearence(ecClearence);
				ecForm8TransferOfTOR.setEc_id(ec_id);
			}
			CommonFormDetail commonForm = commonFormDetailRepository.findByCaf(caf_id)
					.orElseThrow(() -> new ProjectNotFoundException("Caf form not found for caf id ----> " + caf_id));
			ecForm8TransferOfTOR.setCommonFormDetail(commonForm);
			if (ecForm8TransferOfTOR.getId() != null && ecForm8TransferOfTOR.getId() != 0) {
				EcForm8TransferOfTOR form = ecForm8TransferOfTORRepository.getFormById(ecForm8TransferOfTOR.getId())
						.orElseThrow(() -> new ProjectNotFoundException("EC form not found with id"));
				ecForm8TransferOfTOR.setProposal_no(form.getProposal_no().replaceAll("\\s", ""));
				return ecForm8TransferOfTORRepository.save(ecForm8TransferOfTOR);
			}
			List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();

			ProponentApplications applications = new ProponentApplications();

			if (tempClearances.isEmpty()) {

				String proposal_no = null;
				if (ecForm8TransferOfTOR.getProject_category().equalsIgnoreCase("A")) {
					proposal_no = "IA/"
							+ stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
									.orElseThrow(() -> new ProjectNotFoundException("state not found with code"))
									.getState_abbr()
							+ "/"
							+ activitySectorRepository.getSector(ecForm8TransferOfTOR.getMajor_activity_id(),
									ecForm8TransferOfTOR.getMajor_sub_activity_id()).getSector_code()
							+ "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
				} else {
					if (ecForm8TransferOfTOR.getIs_proposed_required()) {
						proposal_no = "IA/"
								+ stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
										.orElseThrow(() -> new ProjectNotFoundException("state not found with code"))
										.getState_abbr()
								+ "/"
								+ activitySectorRepository.getSector(ecForm8TransferOfTOR.getMajor_activity_id(),
										ecForm8TransferOfTOR.getMajor_sub_activity_id()).getSector_code()
								+ "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());

					} else {
						proposal_no = "SIA/"
								+ stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
										.orElseThrow(() -> new ProjectNotFoundException("state not found with code"))
										.getState_abbr()
								+ "/"
								+ activitySectorRepository.getSector(ecForm8TransferOfTOR.getMajor_activity_id(),
										ecForm8TransferOfTOR.getMajor_sub_activity_id()).getSector_code()
								+ "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
					}
				}

				/*
				 * String proposal_no = null; proposal_no = "IA/" +
				 * stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state()
				 * ) .orElseThrow(() -> new
				 * ProjectNotFoundException("state not found with code")).getState_abbr() + "/"
				 * + activitySectorRepository .getSector(ecClearence.getMajor_activity_id(),
				 * ecClearence.getMajor_sub_activity_id()) .getSector_code() + "/" + 400000 +
				 * "/" + String.valueOf(LocalDate.now().getYear());
				 */
				applications.setProposal_sequence(400000);
				proposal_no = proposal_no.replaceAll("\\s", "");
				applications.setProposal_no(proposal_no);
				if (ecClearence != null)
					ecClearence.setProposal_no(proposal_no);
				ecForm8TransferOfTOR.setProposal_no(proposal_no);
			} else {
				Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence())
						.max(Comparator.comparing(Integer::valueOf)).get();
				if (maxCount != null) {
					applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
					applications
							.setProposal_no(
									generateProposalNo(maxCount, ecForm8TransferOfTOR,commonForm));
					ecForm8TransferOfTOR.setProposal_no(applications.getProposal_no().replaceAll("\\s", ""));
				}
			}
			EcForm8TransferOfTOR temp2 = ecForm8TransferOfTORRepository.save(ecForm8TransferOfTOR);
			Applications app = applicationsRepository.findById(39).get();
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

			/*
			 * Updating the Proponent Application JSON String
			 */
//            OtherPropString.put("Proposal For", app.getProposal_for());
			OtherPropString.put("Proposal For", app.getGeneral_name());
			String activity_name = ecForm8TransferOfTORRepository.getMajorActivityName(temp2.getId());
			String itemNo = ecForm8TransferOfTORRepository.getItemNo(temp2.getId());
			
			OtherPropString.put("Activity", itemNo.concat(" " + activity_name));
			/*OtherPropString.put("Sector",
					activitySectorRepository
							.getSector(ecClearence.getMajor_activity_id(), ecClearence.getMajor_sub_activity_id())
							.getSector_code());*/
			OtherPropString.put("Sector", activitySectorRepository
					.getSector(temp2.getMajor_activity_id(), temp2.getMajor_sub_activity_id()).getSector_code());
			updateOtherPropertyService.updateOtherProperty(temp2.getId(), OtherPropString);

			return temp2;
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving Ec Other details form ec_id-", e);
		}
	}

	public Object saveEcForm8LocationOfProject(EcForm8LocationOfProject ecForm8LocationOfProject, Integer ecForm8Id,
			HttpServletRequest request) throws PariveshException {
		try {
			if (ecForm8LocationOfProject == null) {
				throw new ProjectNotFoundException("EC Form 8 Loaction Of Project is NULL");
			} else {
				EcForm8TransferOfTOR ecForm8TransferOfTOR = ecForm8TransferOfTORRepository
						.getEcForm8TransferOfTorByid(ecForm8Id)
						.orElseThrow(() -> new ProjectNotFoundException("Ec form 8 transfer of TOR not found"));
				ecForm8LocationOfProject.setEcForm8TransferOfTOR(ecForm8TransferOfTOR);

			}
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving Ec Other details form ec_id-", e);
		}
		return ecForm8LocationOfProjectRepository.save(ecForm8LocationOfProject);
	}

	public Object saveECForm8TransferCOP(ECForm8TransferCOP ecForm8TransferCOP, Integer ecForm8Id,
			HttpServletRequest request) throws PariveshException {
		try {
			if (ecForm8TransferCOP == null) {
				throw new ProjectNotFoundException("EC Form 8 Transfer COP is NULL");
			} else {
				EcForm8TransferOfTOR ecForm8TransferOfTOR = ecForm8TransferOfTORRepository
						.getEcForm8TransferOfTorByid(ecForm8Id)
						.orElseThrow(() -> new ProjectNotFoundException("Ec form 8 transfer of TOR not found"));
				ecForm8TransferCOP.setEcForm8TransferOfTOR(ecForm8TransferOfTOR);
			}
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving Ec Other details form ec_id-", e);
		}
		return ecForm8TransferCOPRepository.save(ecForm8TransferCOP);
	}

	public Object saveEcForm8DetailOfTOR(EcForm8DetailOfTOR ecForm8DetailOfTOR, Integer ecForm8Id,
			HttpServletRequest request) throws PariveshException {
		try {
			if (ecForm8DetailOfTOR == null) {
				throw new ProjectNotFoundException("EC Form 8 Detail of TOR is NULL");
			} else {
				EcForm8TransferOfTOR ecForm8TransferOfTOR = ecForm8TransferOfTORRepository
						.getEcForm8TransferOfTorByid(ecForm8Id)
						.orElseThrow(() -> new ProjectNotFoundException("Ec form 8 transfer of TOR not found"));
				ecForm8DetailOfTOR.setEcForm8TransferOfTOR(ecForm8TransferOfTOR);
			}
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving Ec Other details form ec_id-", e);
		}
		return ecForm8DetailsOfTORRepository.save(ecForm8DetailOfTOR);
	}

	public Object saveEcForm8DocumentAttached(EcForm8DocumentAttached ecForm8DocumentAttached, Integer ecForm8Id,
			HttpServletRequest request) throws PariveshException {
		try {
			if (ecForm8DocumentAttached == null) {
				throw new ProjectNotFoundException("EC Form 8 Document Attached is NULL");
			} else {
				EcForm8TransferOfTOR ecForm8TransferOfTOR = ecForm8TransferOfTORRepository
						.getEcForm8TransferOfTorByid(ecForm8Id)
						.orElseThrow(() -> new ProjectNotFoundException("Ec form 8 transfer of TOR not found"));
				ecForm8DocumentAttached.setEcForm8TransferOfTOR(ecForm8TransferOfTOR);
			}

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving Ec Other details form ec_id-", e);
		}
		return ecForm8DocumentAttachedRepository.save(ecForm8DocumentAttached);
	}

	public Object saveEcForm8Undertaking(EcForm8Undertaking ecForm8Undertaking, Integer ecForm8Id, Integer caf_id,
			Boolean is_submit, HttpServletRequest request) throws PariveshException {

		EcForm8TransferOfTOR ecForm8TransferOfTOR = ecForm8TransferOfTORRepository
				.getEcForm8TransferOfTorByid(ecForm8Id)
				.orElseThrow(() -> new ProjectNotFoundException("Ec form 8 transfer of TOR not found"));

		EnvironmentClearence ecClearence = environmentClearenceRepository.findByFormId(ecForm8TransferOfTOR.getEc_id())
				.orElse(null);

		String proposalNo = proponentApplicationRepository.getProposalNo(ecForm8Id);
		log.info(proposalNo);
		String identificationNo = ecForm8TransferOfTORRepository.getIdentificationNo(proposalNo);
		ecForm8Undertaking.setIdentification_no(identificationNo);
		log.info(identificationNo);

		ecForm8Undertaking.setEcForm8TransferOfTOR(ecForm8TransferOfTOR);
		ProponentApplications proponentApplications = proponentApplicationRepository
				.getApplicationByProposalNo(ecForm8TransferOfTOR.getProposal_no());

		proponentApplications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
		Optional<CommonFormDetail> cafDetail = commonFormDetailRepository.findByCaf(
				(Integer) ecForm8TransferOfTORRepository.getCafByEcId(ecForm8TransferOfTOR.getId()).get(0)[0]);
		proponentApplications.setProposal_no(
				generateProposalNo(proponentApplications.getProposal_sequence() - 1, ecForm8TransferOfTOR,cafDetail.get()));

		if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString()) || proponentApplications.getLast_status().equals(Caf_Status.EDS_RAISED.name())) {
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
			if (ecForm8Undertaking.getSubmission_date() != null)
				proponentApplications.setLast_submission_date(ecForm8Undertaking.getSubmission_date());
			else {
				proponentApplications.setLast_submission_date(new Date());
			}
		}

		if (is_submit == true)
			proponentApplications.setLast_status(Caf_Status.SUBMITTED.toString());*/
		commonFormDetailService.saveCommonForm(cafDetail.get());
		proponentApplicationRepository.save(proponentApplications);
		/*if (is_submit == true) {
			notificationSevice.sendProposalSMS(proponentApplications.getProposal_no());

			notificationSevice.sendProposalEmail(proponentApplications.getProposal_no());
		}*/
		return ecForm8UndertakingRepository.save(ecForm8Undertaking);
	}

	public Object saveEcForm8AdditionalDocument(EcForm8AdditionalDocument ecForm8AdditionalDocument, Integer ecForm8Id,
			HttpServletRequest request) {
		try {
			if (ecForm8AdditionalDocument == null) {
				throw new ProjectNotFoundException("EC Form 8 Document Attached is NULL");
			} else {
				EcForm8TransferOfTOR ecForm8TransferOfTOR = ecForm8TransferOfTORRepository
						.getEcForm8TransferOfTorByid(ecForm8Id)
						.orElseThrow(() -> new ProjectNotFoundException("Ec form 8 transfer of TOR not found"));

				ecForm8AdditionalDocument.setEcForm8TransferOfTOR(ecForm8TransferOfTOR);
			}

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving Ec Other details form ec_id-", e);
		}
		return ecForm8AdditionalDocumentRepository.save(ecForm8AdditionalDocument);
	}

	public String generateSequenceNumber(int maxcount) {
		// this will convert any number sequence into 6 character.
		AtomicInteger sequence = new AtomicInteger(maxcount);

		return String.format("%06d", sequence.addAndGet(1));
	}

	public Object getEcForm8(Integer id) {
		EcForm8dto ecForm8dto = ecForm8TransferOfTORRepository.getFormByIdDet(id)
				.orElseThrow(() -> new ProjectNotFoundException("Form 8 not found with code"));
		Integer caf_id = ecForm8TransferOfTORRepository.getCafIdByFormId(ecForm8dto.getId());
		CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(caf_id);
		Optional<EnvironmentClearence> environmentClearence = null;
		if(ecForm8dto.getEc_id()!=null) {
		environmentClearence = environmentClearenceRepository
				.findById(ecForm8dto.getEc_id());
		}
		ProponentApplications proponentApplications = proponentApplicationRepository
				.getApplicationByProposalId(ecForm8dto.getId());
		EcForm8Undertaking ecForm8Undertaking = ecForm8UndertakingRepository.getByEc8Id(ecForm8dto.getId());
		EcForm8LocationOfProject ecForm8LocationOfProject = ecForm8LocationOfProjectRepository
				.getByEc8Id(ecForm8dto.getId());
		ECForm8TransferCOP ecForm8TransferCOP = ecForm8TransferCOPRepository.getByEc8Id(ecForm8dto.getId());
		EcForm8DocumentAttached ecForm8DocumentAttached = ecForm8DocumentAttachedRepository
				.getByEc8Id(ecForm8dto.getId());
		EcForm8AdditionalDocument additional_documents = ecForm8AdditionalDocumentRepository
				.findAllByFormId1(ecForm8dto.getId());
		EcForm8DetailOfTOR ecForm8DetailOfTOR = ecForm8DetailsOfTORRepository.getByEc8Id(ecForm8dto.getId());

		EcForm8TransferOfTOR ecForm8TransferOfTOR = new EcForm8TransferOfTOR();

		BeanUtils.copyProperties(ecForm8dto, ecForm8TransferOfTOR);
		ecForm8TransferOfTOR.setCommonFormDetail(commonFormDetail);
		ecForm8TransferOfTOR.setProponentApplications(proponentApplications);
		ecForm8TransferOfTOR.setEcForm8Undertaking(ecForm8Undertaking);
		ecForm8TransferOfTOR.setEcForm8LocationOfProject(ecForm8LocationOfProject);
		ecForm8TransferOfTOR.setEcForm8TransferCOP(ecForm8TransferCOP);
		ecForm8TransferOfTOR.setEcForm8DocumentAttached(ecForm8DocumentAttached);
		ecForm8TransferOfTOR.setAdditional_documents(additional_documents);
		ecForm8TransferOfTOR.setEcForm8DetailOfTOR(ecForm8DetailOfTOR);
		if (!ObjectUtils.isEmpty(environmentClearence)) {
			ecForm8TransferOfTOR.setEnvironmentClearence(environmentClearence.get());
		}

		return ResponseHandler.generateResponse("Save Ec Other Details form", HttpStatus.OK, null,
				ecForm8TransferOfTOR);
	}

	private String generateProposalNo1(int maxcount, EcForm8TransferOfTOR form, String sector_code,
			CommonFormDetail commonForm) {
		/*
		 * String cafId = "IA/" +
		 * stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state()
		 * ) .orElseThrow(() -> new
		 * ProjectNotFoundException("state not found with code")).getState_abbr() + "/"
		 * + sector_code + "/" + generateSequenceNumber(maxcount) + "/" +
		 * String.valueOf(LocalDate.now().getYear()); cafId = cafId.replaceAll("\\s",
		 * ""); return cafId;
		 */

		if (form.getProject_category().equalsIgnoreCase("A")) {
			String cafId = "IA/" + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
					.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr() + "/"
					+ activitySectorRepository.getSector(form.getMajor_activity_id(), form.getMajor_sub_activity_id())
							.getSector_code()
					+ "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());

			cafId = cafId.replaceAll("\\s", "");
			return cafId;
		} else {
			if (form.getIs_proposed_required()) {
				String cafId = "IA/" + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
						.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
						+ "/"
						+ activitySectorRepository
								.getSector(form.getMajor_activity_id(), form.getMajor_sub_activity_id())
								.getSector_code()
						+ "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
				cafId = cafId.replaceAll("\\s", "");
				return cafId;
			} else {
				String cafId = "SIA/" + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
						.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
						+ "/"
						+ activitySectorRepository
								.getSector(form.getMajor_activity_id(), form.getMajor_sub_activity_id())
								.getSector_code()
						+ "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
				cafId = cafId.replaceAll("\\s", "");
				return cafId;

			}
		}

	}
	
	private String generateProposalNo(int maxcount, EcForm8TransferOfTOR form,CommonFormDetail commonForm) {
		/*
		 * String cafId = "IA/" +
		 * stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state()
		 * ) .orElseThrow(() -> new
		 * ProjectNotFoundException("state not found with code")).getState_abbr() + "/"
		 * + sector_code + "/" + generateSequenceNumber(maxcount) + "/" +
		 * String.valueOf(LocalDate.now().getYear()); cafId = cafId.replaceAll("\\s",
		 * ""); return cafId;
		 */

		if (form.getProject_category().equalsIgnoreCase("A")) {
			String cafId = "IA/" + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
					.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr() + "/"
					+ activitySectorRepository.getSector(form.getMajor_activity_id(), form.getMajor_sub_activity_id())
							.getSector_code()
					+ "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());

			cafId = cafId.replaceAll("\\s", "");
			return cafId;
		} else {
			if (form.getIs_proposed_required()) {
				String cafId = "IA/" + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
						.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
						+ "/"
						+ activitySectorRepository
								.getSector(form.getMajor_activity_id(), form.getMajor_sub_activity_id())
								.getSector_code()
						+ "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
				cafId = cafId.replaceAll("\\s", "");
				return cafId;
			} else {
				String cafId = "SIA/" + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
						.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
						+ "/"
						+ activitySectorRepository
								.getSector(form.getMajor_activity_id(), form.getMajor_sub_activity_id())
								.getSector_code()
						+ "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
				cafId = cafId.replaceAll("\\s", "");
				return cafId;

			}
		}

	}

	public EnvironmentClearence getTorDetailByMofeCC(Integer moefcc) {
		return environmentClearenceRepository.findByMoefFileNo(moefcc).get();
	}

	public Object getEcForgetAdditionalInformationm8(Integer ecForm8Id) {
		return ecForm8AdditionalDocumentRepository.findAllByFormId1(ecForm8Id);
	}

	public Object deleteAdditionalInformation(Integer ecForm8Id) {
		try {
			if (ecForm8AdditionalDocumentRepository.findAllByFormId1(ecForm8Id) != null) {
				ecForm8AdditionalDocumentRepository.deleteAdditionalDocumentsByFormId(ecForm8Id);
				ecForm8AdditionalDocumentRepository.deleteAllByFormId(ecForm8Id);
				return "Deleted Successfully";
			} else {
				throw new ProjectNotFoundException("Additional Information not found with ID ---> " + ecForm8Id);
			}
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error occurred while deleting Additional information with Form 8 Id ->  " + ecForm8Id, e);
		}
	}

	public List<EcForm8OrganisationDTO> getOrganizationDetails(String name) {
		name.strip();
		name = "%" + name + "%";
		return ecForm8TransferOfTORRepository.getOrganizationDetails(name);
	}

	public List<EcForm8OrganisationByNameDTO> getOrganizationDetailsByName(String name) {
		name.strip();
		name = "%" + name + "%";
		return ecForm8TransferOfTORRepository.getOrganizationDetailsByName(name);
	}

	/*
	 * EcForm8ProjectActivityDetails
	 */

	public List<EcForm8ProjectActivityDetails> saveEcForm8ProjectActivityDetails(Integer ecForm8Id,
			List<EcForm8ProjectActivityDetails> environmentClearanceProjectActivityDetails) {

		EcForm8TransferOfTOR temp = ecForm8TransferOfTORRepository.findById(ecForm8Id).get();

		List<EcForm8ProjectActivityDetails> projectActivityDetails = environmentClearanceProjectActivityDetails.stream()
				.map(value -> {

					value.setEcForm8TransferOfTOR(temp);
					value.setProposalNo(temp.getProposal_no());
					value.setActivities(activityRepository.findById(value.getActivityId())
							.orElseThrow(() -> new ProjectNotFoundException("activity not found for activity ID")));
					value.setSubActivities(subActivityRepository.findById(value.getSubActivityId()).orElseThrow(
							() -> new ProjectNotFoundException("subactivity not found for subactivity ID")));
					return value;
				}).collect(Collectors.toList());
		List<EcForm8ProjectActivityDetails> environmentDetails = ecForm8ProjectActivityDetailsRepository
				.saveAll(projectActivityDetails);
		log.info(
				"INFO ------------ saveEnvironmentClearanceProjectActivityDetails Environment Clearance Project Activity Details WITH ecId ----> "
						+ ecForm8Id + " ----SAVED - SUCCESS");
		return environmentDetails;
	}

	public ResponseEntity<Object> getECProjectActivityData(int id) {
		log.info(
				"INFO ------------ getECProjectActivityData Environment Clearance Project Activity Data WITH EcID ----> "
						+ id + " ---- FOUND and RETRIEVED - SUCCESS");
		return ResponseHandler.generateResponse("get environment data list by ec_id", HttpStatus.OK, "",
				ecForm8ProjectActivityDetailsRepository.findDetailByEcId(id));

	}

	public ResponseEntity<Object> deleteECProjectActivityData(int id) {

		EcForm8ProjectActivityDetails environmentClearanceProjectActivityDetails = ecForm8ProjectActivityDetailsRepository
				.findById(id).get();
		environmentClearanceProjectActivityDetails.setDelete(true);
		ecForm8ProjectActivityDetailsRepository.save(environmentClearanceProjectActivityDetails);
		log.info(
				"INFO ------------ deleteECProjectActivityData Environment Clearance Project Activity Data WITH EcID ----> "
						+ id + " ---- FOUND and DELETED - SUCCESS");
		return ResponseHandler.generateResponse("delete environment data list", HttpStatus.OK, "",
				"deleted successfully");

	}


}
