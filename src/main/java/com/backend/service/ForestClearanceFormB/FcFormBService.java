package com.backend.service.ForestClearanceFormB;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.client.NotifyClient;
import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.ProponentApplications;
import com.backend.model.ForestClearanceFormB.FcFormBAfforestationDetails;
import com.backend.model.ForestClearanceFormB.FcFormBApprovalDetails;
import com.backend.model.ForestClearanceFormB.FcFormBComplianceDetails;
import com.backend.model.ForestClearanceFormB.FcFormBComponentDetails;
import com.backend.model.ForestClearanceFormB.FcFormBLeaseDetails;
import com.backend.model.ForestClearanceFormB.FcFormBMiningProposals;
import com.backend.model.ForestClearanceFormB.FcFormBOthersDetail;
import com.backend.model.ForestClearanceFormB.FcFormBPatchAreaDetails;
import com.backend.model.ForestClearanceFormB.FcFormBPatchKmls;
import com.backend.model.ForestClearanceFormB.FcFormBPaymentDetails;
import com.backend.model.ForestClearanceFormB.FcFormBPriorProposals;
import com.backend.model.ForestClearanceFormB.FcFormBProjectDetails;
import com.backend.model.ForestClearanceFormB.FcFormBProposedDiversions;
import com.backend.model.ForestClearanceFormB.FcFormBProposedLand;
import com.backend.model.ForestClearanceFormB.FcFormBUndertaking;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.CommonFormDetailRepository;
import com.backend.repository.postgres.DocumentDetailsRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.StateRepository;
import com.backend.repository.postgres.UserRepository;
import com.backend.repository.postgres.FcFormB.FcFormBAfforestationDetailsRepository;
import com.backend.repository.postgres.FcFormB.FcFormBApprovalDetailsRepository;
import com.backend.repository.postgres.FcFormB.FcFormBComplianceDetailsRepository;
import com.backend.repository.postgres.FcFormB.FcFormBComponentDetailsRepository;
import com.backend.repository.postgres.FcFormB.FcFormBDivisionPatchDetailsRepository;
import com.backend.repository.postgres.FcFormB.FcFormBLeaseDetailsRepository;
import com.backend.repository.postgres.FcFormB.FcFormBMiningProposalsRepository;
import com.backend.repository.postgres.FcFormB.FcFormBOthersDetailRepository;
import com.backend.repository.postgres.FcFormB.FcFormBPatchAreaDetailsRepository;
import com.backend.repository.postgres.FcFormB.FcFormBPatchKmlsRepository;
import com.backend.repository.postgres.FcFormB.FcFormBPaymentDetailsRepository;
import com.backend.repository.postgres.FcFormB.FcFormBPriorProposalsRepository;
import com.backend.repository.postgres.FcFormB.FcFormBProjectDetailsRepository;
import com.backend.repository.postgres.FcFormB.FcFormBProposedDiversionsDetailsRepositoy;
import com.backend.repository.postgres.FcFormB.FcFormBProposedDiversionsRepository;
import com.backend.repository.postgres.FcFormB.FcFormBProposedLandRepository;
import com.backend.repository.postgres.FcFormB.FcFormBUndertakingRepository;
import com.backend.response.ResponseHandler;
import com.backend.service.CommonFormDetailService;
import com.backend.service.NotificationService;
import com.backend.service.ProponentApplicationService;
import com.backend.util.ServerUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class FcFormBService {

	@Autowired
	private CommonFormDetailRepository commonFormDetailRepository;

	@Autowired
	private CommonFormDetailService commonFormDetailService;

	@Autowired
	private FcFormBProjectDetailsRepository fcFormBProjectDetailsRepository;

	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private FcFormBProposedDiversionsRepository fcFormBProposedDiversionsRepository;

	@Autowired
	private ApplicationsRepository applicationsRepository;

	@Autowired
	private FcFormBMiningProposalsRepository fcFormBMiningProposalsRepository;

	@Autowired
	private FcFormBPriorProposalsRepository fcFormBPriorProposalsRepository;

	@Autowired
	private FcFormBComplianceDetailsRepository fcFormBComplianceDetailsRepository;

	@Autowired
	private FcFormBPaymentDetailsRepository fcFormBPaymentDetailsRepository;

	@Autowired
	private FcFormBApprovalDetailsRepository fcFormBApprovalDetailsRepository;

	@Autowired
	private FcFormBPatchAreaDetailsRepository fcFormBPatchAreaDetailsRepository;

	@Autowired
	private FcFormBLeaseDetailsRepository fcFormBLeaseDetailsRepository;

	@Autowired
	private FcFormBAfforestationDetailsRepository fcFormBAfforestationDetailsRepository;

	@Autowired
	private FcFormBOthersDetailRepository fcFormBOthersDetailRepository;

	@Autowired
	private FcFormBProposedLandRepository fcFormBProposedLandRepository;

	@Autowired
	private FcFormBComponentDetailsRepository fcFormBComponentDetailsRepository;

	@Autowired
	private FcFormBUndertakingRepository fcFormBUndertakingRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NotifyClient notifyClient;

	@Autowired
	private DocumentDetailsRepository documentdetailsRepository;

	@Autowired
	private FcFormBDivisionPatchDetailsRepository fcFormBDivisionPatchDetailsRepository;

	@Autowired
	private FcFormBProposedDiversionsDetailsRepositoy fcFormBProposedDiversionsDetailsRepositoy;

	@Autowired
	private FcFormBPatchKmlsRepository fcFormBPatchKmlsRepository;

	@Autowired
	private ServerUtil util;

	@Autowired
	private NotificationService notificationSevice;

	@Autowired
	private ProponentApplicationService proponentApplicationService;

	public ResponseEntity<Object> saveFcFormBProjectdetails(FcFormBProjectDetails fcFormBProjectDetails, Integer fc_id,
			Integer caf_id, Integer clearance_id, HttpServletRequest request) throws PariveshException {

		try {
			HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
			CommonFormDetail cafDetail = commonFormDetailRepository.findByCaf(caf_id)
					.orElseThrow(() -> new ProjectNotFoundException("Caf form not found with id"));
			fcFormBProjectDetails.setCommonFormDetail(cafDetail);
			fcFormBProjectDetails.setFc_id(fc_id);
			if (fcFormBProjectDetails.getId() != null && fcFormBProjectDetails.getId() != 0) {
				FcFormBProjectDetails form = fcFormBProjectDetailsRepository.getFormById(fcFormBProjectDetails.getId());
				fcFormBProjectDetails.setProposal_no(form.getProposal_no().replaceAll("\\s", ""));
				return ResponseHandler.generateResponse("Update Fc FormB ", HttpStatus.OK, "",
						fcFormBProjectDetailsRepository.save(fcFormBProjectDetails));
			}
			List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();

			ProponentApplications applications = new ProponentApplications();
			if (tempClearances.isEmpty()) {
				String proposal_no = "FP/"
						+ stateRepository.getStateByCode(cafDetail.getProjectDetails().getMain_state())
								.orElseThrow(() -> new ProjectNotFoundException("state not found with code"))
								.getState_abbr()
						+ "/" + fcFormBProjectDetails.getProject_category_code() + "/" + 400000 + "/"
						+ String.valueOf(LocalDate.now().getYear());
				applications.setProposal_sequence(400000);
				proposal_no = proposal_no.replaceAll("\\s", "");
				applications.setProposal_no(proposal_no);
				fcFormBProjectDetails.setProposal_no(proposal_no);
			} else {
				Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence())
						.max(Comparator.comparing(Integer::valueOf)).get();
				if (maxCount != null) {
					applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
					applications.setProposal_no(generateProposalNo(maxCount, fcFormBProjectDetails));
					fcFormBProjectDetails.setProposal_no(applications.getProposal_no().replaceAll("\\s", ""));
				}
			}
			FcFormBProjectDetails temp2 = fcFormBProjectDetailsRepository.save(fcFormBProjectDetails);
			Applications app = applicationsRepository.findById(clearance_id).get();
			applications.setCaf_id(caf_id);
			applications.setProposal_id(temp2.getId());
			applications.setState(stateRepository.getStateByCode(fcFormBProjectDetails.getState_code())
					.orElseThrow(() -> new ProjectNotFoundException("state data not found for state id")).getName());
			applications.setState_id(stateRepository.getStateByCode(fcFormBProjectDetails.getState_code())
					.orElseThrow(() -> new ProjectNotFoundException("state data not found for state code")).getCode());
			applications.setProjectDetails(cafDetail.getProjectDetails());
			applications.setApplications(app);
			applications.setLast_status(Caf_Status.DRAFT.toString());
			applications.setLast_remarks("test");
			applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
			proponentApplicationRepository.save(applications);

//			OtherPropString.put("Form", app.getDd_name());
//			OtherPropString.put("Project Category", fcFormBProjectDetails.getProject_category_code());
//			proponentApplicationService.updateOtherProperty(temp2.getId(), OtherPropString);

			return ResponseHandler.generateResponse("Update Fc FormB ", HttpStatus.OK, "", temp2);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in adding Forest Clearance Form for caf_id- " + caf_id, e);
		}
	}

	public String generateSequenceNumber(int maxcount) {
		// this will convert any number sequence into 6 character.
		AtomicInteger sequence = new AtomicInteger(maxcount);
		// return ResponseHandler.generateResponse("CAF
		// Others",HttpStatus.OK,"",String.format("%06d", sequence.addAndGet(1)));
		return String.format("%06d", sequence.addAndGet(1));
	}

	private String generateProposalNo(int maxcount, FcFormBProjectDetails fcFormBProjectDetails) {

		String proposalNo = "FP/" + stateRepository.getStateByCode(fcFormBProjectDetails.getState_code())
				.orElseThrow(() -> new ProjectNotFoundException("state data not found for state id")).getState_abbr()
				+ "/" + fcFormBProjectDetails.getProject_category_code() + "/" + generateSequenceNumber(maxcount) + "/"
				+ String.valueOf(LocalDate.now().getYear());
		proposalNo = proposalNo.replaceAll("\\s", "");
		return proposalNo;
	}
	// project cat code

	public ResponseEntity<Object> getFcFormBByProposalNo(String proposal_no) throws PariveshException {
		try {
			ProponentApplications applications = proponentApplicationRepository.getApplicationByProposalNo(proposal_no);
			FcFormBProjectDetails form = fcFormBProjectDetailsRepository.findById(applications.getProposal_id())
					.orElseThrow(() -> new ProjectNotFoundException("Forest Clearance form not found with id"));

			return ResponseHandler.generateResponse("Save Forest Patch KML", HttpStatus.OK, "", form);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getFcFormBByProposalNo for proposal_no- " + proposal_no, e);
		}
	}

	public ResponseEntity<Object> saveFcFormBMiningProposals(Integer fc_form_b_id,
			FcFormBMiningProposals fcFormBMiningProposals) {
		try {
//			if (fc_form_b_id != null) {

			if (fcFormBMiningProposals.getId() == null || fcFormBMiningProposals.getId() == 0) {
				FcFormBMiningProposals fcFormBMiningProposals2 = fcFormBMiningProposalsRepository
						.getDataByFcFormBId(fc_form_b_id);
				if (fcFormBMiningProposals2 != null) {
					fcFormBMiningProposals.setId(fcFormBMiningProposals2.getId());
				}
			}
			FcFormBProjectDetails fc = fcFormBProjectDetailsRepository.getDetailsByFcIdProp(fc_form_b_id);
			fcFormBMiningProposals.setFcFormBProjectDetails(fc);
			if (fcFormBMiningProposals.getCopy_approval_accorded_moefcc() != null) {
				fcFormBMiningProposals.getCopy_approval_accorded_moefcc().setProposal_no(fc.getProposal_no());
			}
			if (fcFormBMiningProposals.getCopy_approved_mining_plan() != null) {
				fcFormBMiningProposals.getCopy_approved_mining_plan().setProposal_no(fc.getProposal_no());
			}
			if (fcFormBMiningProposals.getCopy_detailed_land_use_plan() != null) {
				fcFormBMiningProposals.getCopy_detailed_land_use_plan().setProposal_no(fc.getProposal_no());
			}
			if (fcFormBMiningProposals.getCopy_map_outer_boundary() != null) {
				fcFormBMiningProposals.getCopy_map_outer_boundary().setProposal_no(fc.getProposal_no());
			}
			if (fcFormBMiningProposals.getCopy_perspecting_licence() != null) {
				fcFormBMiningProposals.getCopy_perspecting_licence().setProposal_no(fc.getProposal_no());
			}
			if (fcFormBMiningProposals.getCopy_transportation_minerals_proposed() != null) {
				fcFormBMiningProposals.getCopy_transportation_minerals_proposed().setProposal_no(fc.getProposal_no());
			}
//			} 
//			else {
//				WildLifeClearance wlc = wildLifeClearanceRepository.getDetailsByWlId(wlcId)
//						.orElseThrow(() -> new ProjectNotFoundException("Fc part b form not found"));
//				fcFormBMiningProposals.setWildLifeClearance(wlc);
//			}
			return ResponseHandler.generateResponse("Save Mining Proposals Project", HttpStatus.OK, "",
					fcFormBMiningProposalsRepository.save(fcFormBMiningProposals));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in saveFcMiningProposals for fcFormBId- " + fc_form_b_id, e);
		}
	}

	public ResponseEntity<Object> saveFcFormBPriorProposals(Integer fc_form_b_id,
			List<FcFormBPriorProposals> listPriorProposals) {
		try {
			List<FcFormBPriorProposals> priorProposals = new ArrayList<>();
			FcFormBProjectDetails fc = fcFormBProjectDetailsRepository.getDetailsByFcId(fc_form_b_id);
			priorProposals = listPriorProposals.stream().map(value -> {
				value.setFcFormBProjectDetails(fc);
				return value;
			}).collect(Collectors.toList());
			return ResponseHandler.generateResponse("Save FC Form B saveFcFormBPriorProposals form", HttpStatus.OK,
					null, fcFormBPriorProposalsRepository.saveAll(priorProposals));
		} catch (Exception e) {
			return ResponseHandler.generateResponse(
					"Save FC Form B saveFcFormBPriorProposals form =======================>>>>>>>>>>> for fcFormBId"
							+ fc_form_b_id,
					HttpStatus.INTERNAL_SERVER_ERROR, null, e);
		}
	}

	public ResponseEntity<Object> saveFcFormBComplianceDetails(Integer fc_form_b_id,
			List<FcFormBComplianceDetails> listComplianceDetails) {
		try {
			List<FcFormBComplianceDetails> complianceDetails = new ArrayList<>();
			FcFormBProjectDetails fc = fcFormBProjectDetailsRepository.getDetailsByFcId(fc_form_b_id);
			complianceDetails = listComplianceDetails.stream().map(value -> {
				value.setFcFormBProjectDetails(fc);
				return value;
			}).collect(Collectors.toList());
			return ResponseHandler.generateResponse("Save FC Form B saveFcFormBComplianceDetails form", HttpStatus.OK,
					null, fcFormBComplianceDetailsRepository.saveAll(complianceDetails));
		} catch (Exception e) {
			return ResponseHandler.generateResponse(
					"Save FC Form B saveFcFormBComplianceDetails form =======================>>>>>>>>>>> for fcFormBId"
							+ fc_form_b_id,
					HttpStatus.INTERNAL_SERVER_ERROR, null, e);
		}
	}

	public ResponseEntity<Object> saveFcFormBPaymentDetails(Integer fc_form_b_id,
			List<FcFormBPaymentDetails> listPaymentDetails) {
		try {
			List<FcFormBPaymentDetails> paymentDetails = new ArrayList<>();
			FcFormBProjectDetails fc = fcFormBProjectDetailsRepository.getDetailsByFcId(fc_form_b_id);
			paymentDetails = listPaymentDetails.stream().map(value -> {
				value.setFcFormBProjectDetails(fc);
				return value;
			}).collect(Collectors.toList());
			return ResponseHandler.generateResponse("Save FC Form B saveFcFormBPaymentDetails form", HttpStatus.OK,
					null, fcFormBPaymentDetailsRepository.saveAll(paymentDetails));
		} catch (Exception e) {
			return ResponseHandler.generateResponse(
					"Save FC Form B saveFcFormBPaymentDetails form =======================>>>>>>>>>>> for fcFormBId"
							+ fc_form_b_id,
					HttpStatus.INTERNAL_SERVER_ERROR, null, e);
		}
	}

	public ResponseEntity<Object> saveFcFormBApprovalDetails(Integer fc_form_b_id,
			FcFormBApprovalDetails approvalDetails) {
		try {
			if (approvalDetails.getId() == null || approvalDetails.getId() == 0) {
				FcFormBApprovalDetails fcFormBApprovalDetails = fcFormBApprovalDetailsRepository
						.getDataByFcFormBId(fc_form_b_id);
				if (fcFormBApprovalDetails != null) {
					approvalDetails.setId(fcFormBApprovalDetails.getId());
				}
			}
			FcFormBProjectDetails fc = fcFormBProjectDetailsRepository.getDetailsByFcId(fc_form_b_id);
			approvalDetails.setFcFormBProjectDetails(fc);
			return ResponseHandler.generateResponse("Save FC Form B saveFcFormBApprovalDetails form", HttpStatus.OK,
					null, fcFormBApprovalDetailsRepository.save(approvalDetails));
		} catch (Exception e) {
			return ResponseHandler.generateResponse(
					"Save FC Form B saveFcFormBApprovalDetails form =======================>>>>>>>>>>> for fcFormBId"
							+ fc_form_b_id,
					HttpStatus.OK, null, e);
		}
	}

	public ResponseEntity<Object> saveFcFormBPatchAreaDetails(Integer fc_form_b_id,
			List<FcFormBPatchAreaDetails> listPatchAreaDetails) {
		try {
			List<FcFormBPatchAreaDetails> patchAreaDetails = new ArrayList<>();
			FcFormBProjectDetails fc = fcFormBProjectDetailsRepository.getDetailsByFcId(fc_form_b_id);
			patchAreaDetails = listPatchAreaDetails.stream().map(value -> {
				value.setFcFormBProjectDetails(fc);
				return value;
			}).collect(Collectors.toList());
			return ResponseHandler.generateResponse("Save FC Form B saveFcFormBPatchAreaDetails form", HttpStatus.OK,
					null, fcFormBPatchAreaDetailsRepository.saveAll(patchAreaDetails));
		} catch (Exception e) {
			return ResponseHandler.generateResponse(
					"Save FC Form B saveFcFormBPatchAreaDetails form =======================>>>>>>>>>>> for fcFormBId"
							+ fc_form_b_id,
					HttpStatus.OK, null, e);
		}
	}

	public ResponseEntity<Object> saveFcFormBProposedLand(Integer fc_form_b_id, FcFormBProposedLand proposedLands) {
		try {
			HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
			if (proposedLands.getId() == null || proposedLands.getId() == 0) {
				FcFormBProposedLand fcFormBProposedLand2 = fcFormBProposedLandRepository
						.getDataByFcFormBId(fc_form_b_id);
				if (fcFormBProposedLand2 != null) {
					proposedLands.setId(fcFormBProposedLand2.getId());
				}
			}
//			FcFormBProjectDetails fc = fcFormBProjectDetailsRepository.getDetailsByFcId(fc_form_b_id);
			FcFormBProjectDetails fc= fcFormBProjectDetailsRepository.getDetailsByFcId2(fc_form_b_id);
//			log.info("category:"+fc.getProject_category_code());
			proposedLands.setFcFormBProjectDetails(fc);
			
			 /*
             * Updating the Proponent Application JSON String
             */
            ProponentApplications proponentApplications = proponentApplicationRepository.getApplicationByProposalId(fc.getId());
            Applications app= proponentApplications.getApplications();
			OtherPropString.put("Form", app.getDd_name());
			OtherPropString.put("Project Category", fc.getProject_category_code());
			OtherPropString.put("Forest Area", proposedLands.getTotal_proposed_diversion_area().toString());
			proponentApplicationService.updateOtherProperty(fc_form_b_id, OtherPropString);

			return ResponseHandler.generateResponse("Save FC Form B saveFcFormBProposedLand form", HttpStatus.OK, null,
					fcFormBProposedLandRepository.save(proposedLands));
		} catch (Exception e) {
			return ResponseHandler.generateResponse(
					"Save FC Form B saveFcFormBProposedLand form =======================>>>>>>>>>>> for fcFormBId"
							+ fc_form_b_id,
					HttpStatus.INTERNAL_SERVER_ERROR, null, e);
		}
	}

	public ResponseEntity<Object> saveFcFormBLeaseDetails(Integer fc_form_b_id,
			List<FcFormBLeaseDetails> listLeaseDetails) {
		try {
			List<FcFormBLeaseDetails> leaseDetails = new ArrayList<>();
			FcFormBProjectDetails fc = fcFormBProjectDetailsRepository.getDetailsByFcId(fc_form_b_id);
			leaseDetails = listLeaseDetails.stream().map(value -> {
				value.setFcFormBProjectDetails(fc);
				return value;
			}).collect(Collectors.toList());
			return ResponseHandler.generateResponse("Save FC Form B saveFcFormBLeaseDetails form", HttpStatus.OK, null,
					fcFormBLeaseDetailsRepository.saveAll(leaseDetails));
		} catch (Exception e) {
			return ResponseHandler.generateResponse(
					"Save FC Form B saveFcFormBLeaseDetails form =======================>>>>>>>>>>> for fcFormBId"
							+ fc_form_b_id,
					HttpStatus.OK, null, e);
		}
	}

	public ResponseEntity<Object> saveFcFormBAfforestationDetails(Integer fc_form_b_id,
			FcFormBAfforestationDetails afforestationDetails) {
		try {
			if (afforestationDetails.getId() == null || afforestationDetails.getId() == 0) {
				FcFormBAfforestationDetails bAfforestationDetails = fcFormBAfforestationDetailsRepository
						.getDataByFcFormBId(fc_form_b_id);
				if (bAfforestationDetails != null) {
					afforestationDetails.setId(bAfforestationDetails.getId());
				}
			}
			FcFormBProjectDetails fc = fcFormBProjectDetailsRepository.getDetailsByFcId(fc_form_b_id);
			afforestationDetails.setFcFormBProjectDetails(fc);
			return ResponseHandler.generateResponse("Save FC Form B saveFcFormBAfforestationDetails form",
					HttpStatus.OK, null, fcFormBAfforestationDetailsRepository.save(afforestationDetails));
		} catch (Exception e) {
			return ResponseHandler.generateResponse(
					"Save FC Form B saveFcFormBAfforestationDetails form =======================>>>>>>>>>>> for fcFormBId"
							+ fc_form_b_id,
					HttpStatus.OK, null, e);
		}
	}

	public ResponseEntity<Object> saveFcFormBOthersDetail(Integer fc_form_b_id, FcFormBOthersDetail othersDetail) {
		try {
			if (othersDetail.getId() == null || othersDetail.getId() == 0) {
				FcFormBOthersDetail fcFormBOthersDetail = fcFormBOthersDetailRepository
						.getDataByFcFormBId(fc_form_b_id);
				if (fcFormBOthersDetail != null) {
					othersDetail.setId(fcFormBOthersDetail.getId());
				}
			}
			FcFormBProjectDetails fc = fcFormBProjectDetailsRepository.getDetailsByFcId(fc_form_b_id);
			othersDetail.setFcFormBProjectDetails(fc);
			return ResponseHandler.generateResponse("Save FC Form B saveFcFormBOthersDetail form", HttpStatus.OK, null,
					fcFormBOthersDetailRepository.save(othersDetail));
		} catch (Exception e) {
			return ResponseHandler.generateResponse(
					"Save FC Form B saveFcFormBOthersDetail form =======================>>>>>>>>>>> for fcFormBId"
							+ fc_form_b_id,
					HttpStatus.OK, null, e);
		}
	}

	public ResponseEntity<Object> saveFcFormBComponentDetails(Integer fc_form_b_id,
			List<FcFormBComponentDetails> listComponentDetails) {
		try {
			FcFormBProjectDetails fc = fcFormBProjectDetailsRepository.getDetailsByFcId(fc_form_b_id);
			List<FcFormBComponentDetails> listComponentDetails2 = new ArrayList<>();
			listComponentDetails2 = listComponentDetails.stream().map(value -> {
				value.setFcFormBProjectDetails(fc);
				return value;
			}).collect(Collectors.toList());
			return ResponseHandler.generateResponse("Save FC Form B saveFcFormBComponentDetails form", HttpStatus.OK,
					null, fcFormBComponentDetailsRepository.saveAll(listComponentDetails2));
		} catch (Exception e) {
			return ResponseHandler.generateResponse(
					"Save FC Form B saveFcFormBComponentDetails form =======================>>>>>>>>>>> for fcFormBId"
							+ fc_form_b_id,
					HttpStatus.INTERNAL_SERVER_ERROR, null, e);

		}
	}

	public ResponseEntity<Object> getFcFormBProjectDetails(Integer id, Integer step) throws PariveshException {
		FcFormBProjectDetails form = null;
		try {
			if (step != null) {
				if (step == 1) {
					form = fcFormBProjectDetailsRepository.getFormById(id);
					System.out.println(form);
					form.setFcFormBPriorProposals(fcFormBPriorProposalsRepository.getByFcIdAll(id));
					System.out.println(form);
					form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
							? proponentApplicationRepository.getAppById(form.getId())
							: null);
				} else if (step == 2) {
					form = fcFormBProjectDetailsRepository.getFormById(id);
					form.setFcFormBComplianceDetails(fcFormBComplianceDetailsRepository.getByFcID(id));
					form.setFcFormBPaymentDetails(fcFormBPaymentDetailsRepository.getByFcID(id));

					form.setFcFormBApprovalDetails(fcFormBApprovalDetailsRepository.getByFcID(id));
					List<Object[]> docData = fcFormBApprovalDetailsRepository.getDocuments(id);
					for (Object[] obj : docData) {
						if (obj != null) {
							form.getFcFormBApprovalDetails().setTransfer_deed_copy(
									obj[0] != null ? documentdetailsRepository.findById((Integer) obj[0]).get() : null);
							form.getFcFormBApprovalDetails().setAll_patch_kml(
									obj[1] != null ? documentdetailsRepository.findById((Integer) obj[1]).get() : null);
						}
					}
					form.setFcFormBPatchAreaDetails(fcFormBPatchAreaDetailsRepository.getByFcID(form.getId()));
//					List<Object[]> docData2 = fcFormBPatchAreaDetailsRepository.getDocuments(id);
//					for (Object[] obj : docData2) {
//						if(obj!=null) {
//						((FcFormBPatchAreaDetails) form.getFcFormBPatchAreaDetails()).setPatch_kml(
//								obj[0] != null ? documentdetailsRepository.findById((Integer) obj[0]).get() : null);
//						((FcFormBPatchAreaDetails) form.getFcFormBPatchAreaDetails()).setGps_patch(
//								obj[1] != null ? documentdetailsRepository.findById((Integer) obj[1]).get() : null);
//						((FcFormBPatchAreaDetails) form.getFcFormBPatchAreaDetails()).setDocumentary_proof_copy(
//								obj[2] != null ? documentdetailsRepository.findById((Integer) obj[2]).get() : null);
//						}
//					}
					form.setFcFormBLeaseDetails(fcFormBLeaseDetailsRepository.getByFcID(form.getId()));
//					List<Object[]> docData3 = fcFormBLeaseDetailsRepository.getDocuments(id);
//					for (Object[] obj : docData3) {
//						if(obj!=null) {
//							form.getFcFormBLeaseDetails().setApproval_copy(
//								obj[0] != null ? documentdetailsRepository.findById((Integer) obj[0]).get() : null);
//						}
//					};
					form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
							? proponentApplicationRepository.getAppById(form.getId())
							: null);

				} else if (step == 3) {
					form = fcFormBProjectDetailsRepository.getFormById(id);
					form.setFormBProposedLands(fcFormBProposedLandRepository.getDataByFcFormBIdAll(id));
//					List<Object[]> docData = fcFormBProposedLandRepository.getDocuments(id);
//					for (Object[] obj : docData) {
//						if (obj != null) {
//							form.getFormBProposedLands().setGeo_referenced_map(
//									obj[0] != null ? documentdetailsRepository.findById((Integer) obj[0]).get() : null);
//						}
//					}
					form.setFcFormBComponentDetails(fcFormBComponentDetailsRepository.getByFcID(id));
//					form.setFcFormBDivisionPatchDetails(fcFormBDivisionPatchDetailsRepository.getByFcID(id));
					form.setFcFormBProposedDiversions(fcFormBProposedDiversionsRepository.getByFcID(id));
//					form.setFcFormBProposedDiversionsDetails(fcFormBProposedDiversionsDetailsRepositoy.getByFcID(id));
					form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
							? proponentApplicationRepository.getAppById(form.getId())
							: null);

				} else if (step == 4) {
					form = fcFormBProjectDetailsRepository.getFormById(id);
					form.setFcFormBAfforestationDetails(fcFormBAfforestationDetailsRepository.getByFcID(id));
					;
					form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
							? proponentApplicationRepository.getAppById(form.getId())
							: null);
				} else if (step == 5) {
					form = fcFormBProjectDetailsRepository.getFormById(id);
					form.setFcFormBOthersDetails(fcFormBOthersDetailRepository.getByFcID(id));
					form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
							? proponentApplicationRepository.getAppById(form.getId())
							: null);
				} else if (step == 6) {
					form = fcFormBProjectDetailsRepository.getFormById(id);
					form.setFcFormBMiningProposals(fcFormBMiningProposalsRepository.getByFcID(id));
					form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
							? proponentApplicationRepository.getAppById(form.getId())
							: null);
				} else if (step == 7) {
					form = fcFormBProjectDetailsRepository.getFormById(id);
					form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
							? proponentApplicationRepository.getAppById(form.getId())
							: null);
				} else if (step == 8) {

					/*
					 * Step: Undertaking
					 */

					form = fcFormBProjectDetailsRepository.getFormById(id);
					form.setFcFormBUndertaking(fcFormBUndertakingRepository.getRecordExists(id));
					form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
							? proponentApplicationRepository.getAppById(form.getId())
							: null);

				}
			}

			return ResponseHandler.generateResponse("Retrieve getFcFormBProjectDetails with id ----->>>" + id,
					HttpStatus.OK, "", form);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Retrieving getFcFormBProjectDetails with id ----->>>" + id, e);
		}
	}


    public ResponseEntity<Object> addUndertaking(Integer fc_form_b_id, FcFormBUndertaking fcFormBUndertaking,Boolean is_submit,
                                                 HttpServletRequest request) {
        try {
            if (fcFormBUndertaking.getId() == null || fcFormBUndertaking.getId() == 0) {
                FcFormBUndertaking fcFormBUndertaking2 = fcFormBUndertakingRepository.getRecordExist(fc_form_b_id)
                        .orElse(null);
                if (fcFormBUndertaking2 != null) {
                    fcFormBUndertaking.setId(fcFormBUndertaking2.getId());
                }
            }
            FcFormBProjectDetails temp = fcFormBProjectDetailsRepository.findById(fc_form_b_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Forest clearance form not found"));
            fcFormBUndertaking.setFcFormBProjectDetails(temp);
            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalNo(temp.getProposal_no());

//			if (proponentApplications.getLast_status().equals(Caf_Status.EDS_RAISED.name())) {
//				proponentApplications.setLast_status(Caf_Status.EDS_REPLIED.toString());
//				proponentApplications.setMigration_status(false);
//			} else {
//				proponentApplications.setLast_status(Caf_Status.SUBMITTED.toString());
//			}
			proponentApplications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
			if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString())
					|| proponentApplications.getLast_status().equals(Caf_Status.EDS_REPLIED.toString())) {
				if (fcFormBUndertaking.getSubmission_date() != null)
					proponentApplications.setLast_submission_date(fcFormBUndertaking.getSubmission_date());
				else {
					proponentApplications.setLast_submission_date(new Date());
				}
			}
			commonFormDetailService.saveCommonForm(temp.getCommonFormDetail());

			proponentApplicationRepository.save(proponentApplications);

			fcFormBUndertakingRepository.save(fcFormBUndertaking);

			FcFormBProjectDetails fcFormBProjectDetails = fcFormBProjectDetailsRepository.findById(fc_form_b_id).get();


            if (is_submit == true) {
                notificationSevice.sendProposalSMS(proponentApplications.getProposal_no());

                notificationSevice.sendProposalEmail(proponentApplications.getProposal_no());
            }


			return ResponseHandler.generateResponse("Save Forest Clearance ", HttpStatus.OK, "", fcFormBProjectDetails);

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in addUndertaking for fc_form_b_id- " + fc_form_b_id, e);
		}
	}

	public ResponseEntity<Object> saveFcFormBProposedDiversion(
			List<FcFormBProposedDiversions> fcFormBProposedDiversions, Integer fc_form_b_id) {
		try {
			List<FcFormBProposedDiversions> diversions = new ArrayList<>();
			FcFormBProjectDetails temp = fcFormBProjectDetailsRepository.getDetailsByFcId(fc_form_b_id);
			fcFormBProposedDiversions.forEach(value -> {
				value.setFcFormBProjectDetails(temp);
				if (value.getDiversion_map_copy() != null) {
					value.getDiversion_map_copy().setProposal_no(temp.getProposal_no());
				}
				FcFormBProposedDiversions fcFormBProposedDiversions2 = fcFormBProposedDiversionsRepository.save(value);
				diversions.add(fcFormBProposedDiversions2);
			});
			log.info("INFO ------------ saveFcFormBProposedDiversion WITH forest clearance id ----> " + fc_form_b_id
					+ " ---- SAVE- SUCCESS");
			return ResponseHandler.generateResponse("Save saveFcFormBProposedDiversion", HttpStatus.OK, " ",
					diversions);

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in saveFcFormBProposedDiversion for fc_id ");
		}
	}

	public ResponseEntity<Object> getFcFormBPropesdDiversion(int id) throws PariveshException {
		try {

			return ResponseHandler.generateResponse("get forest kmls data list by fc_id", HttpStatus.OK, "",
					fcFormBProposedDiversionsRepository.findByFcFormBByID(id));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getFcFormBPropesdDiversion id- " + id, e);
		}

	}

	public ResponseEntity<Object> deleteFcFormBPropesdDiversion(int id) throws PariveshException {
		try {
			System.out.println("Deleting the proposed Diversion");
			Integer upadate = fcFormBProposedDiversionsRepository.updateFcFormBById(id);
			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return ResponseHandler.generateResponse("Forest Clearance Proposed Diversion", HttpStatus.OK, "",
					"Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in deleteFcFormBPropesdDiversion id- " + id, e);
		}
	}

	public ResponseEntity<Object> deleteFcFormBApprovalDetails(int id) throws PariveshException {
		try {
			Integer upadate = fcFormBApprovalDetailsRepository.updateFcFormBById(id);
			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return ResponseHandler.generateResponse("deleteFcFormBApprovalDetails", HttpStatus.OK, "---------->>>>>>",
					"---------->>>>>>");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in deleteFcFormBApprovalDetails id- " + id, e);
		}
	}

	public ResponseEntity<Object> deleteFcFormBComplianceDetails(int id) throws PariveshException {
		try {
			Integer upadate = fcFormBComplianceDetailsRepository.updateFcFormBById(id);
			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return ResponseHandler.generateResponse("deleteFcFormBComplianceDetails", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in deleteFcFormBComplianceDetails id- " + id, e);
		}
	}

	public ResponseEntity<Object> deleteFcFormBComponentDetails(int id) throws PariveshException {
		try {
			Integer upadate = fcFormBComponentDetailsRepository.updateFcFormBById(id);
			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return ResponseHandler.generateResponse("deleteFcFormBComplianceDetails", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in deleteFcFormBComplianceDetails id- " + id, e);
		}
	}

	public ResponseEntity<Object> deleteFcFormBPaymentDetails(int id) throws PariveshException {
		try {
			Integer upadate = fcFormBPaymentDetailsRepository.updateFcFormBById(id);
			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return ResponseHandler.generateResponse("deleteFcFormBPaymentDetails", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in deleteFcFormBPaymentDetails id- " + id, e);
		}
	}

	public ResponseEntity<Object> deleteFcFormBPriorProposals(int id) throws PariveshException {
		try {
			Integer upadate = fcFormBPriorProposalsRepository.updateFcFormBById(id);
			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return ResponseHandler.generateResponse("deleteFcFormBPriorProposals", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in deleteFcFormBPriorProposals id- " + id, e);
		}
	}

	public ResponseEntity<Object> deleteFcFormBPatchAreaDetails(int id) throws PariveshException {
		try {
			Integer upadate = fcFormBPatchAreaDetailsRepository.updateFcFormBById(id);
			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return ResponseHandler.generateResponse("deleteFcFormBPatchAreaDetails", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in deleteFcFormBPatchAreaDetails id- " + id, e);
		}
	}

	public ResponseEntity<Object> saveFCFormBPatchKML(Integer fc_form_b_id,
			List<FcFormBPatchKmls> forestClearancePatchKmls) throws PariveshException {
		List<FcFormBPatchKmls> listtemp = new ArrayList<>();
		try {
			FcFormBProjectDetails temp = fcFormBProjectDetailsRepository.getDetailsByFcId(fc_form_b_id);
			for (FcFormBPatchKmls patchkml : forestClearancePatchKmls) {
				patchkml.setFcFormBProjectDetails(temp);
				/*
				 * if (patchkml.getPatch_kml() != null) {
				 * patchkml.getPatch_kml().setProposal_no(temp.getProposal_no()); }
				 */
				FcFormBPatchKmls temp1 = fcFormBPatchKmlsRepository.save(patchkml);
				listtemp.add(temp1);
			}

			return ResponseHandler.generateResponse("Save Patch KML", HttpStatus.OK, "", listtemp);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in saveFCFormCPatchKML for FcFormCId- " + fc_form_b_id, e);
		}
	}

	public ResponseEntity<Object> getFcFormBPatchKML(Integer id) throws PariveshException {
		try {

			return ResponseHandler.generateResponse("Get Form C Patch KML", HttpStatus.OK, "",
					fcFormBPatchKmlsRepository.findfcKMLbyfcFormBID(id));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getFcFormBPatchKML for FcFormB- " + id, e);
		}
	}

	public ResponseEntity<Object> deleteFcFormBPatchKML(Integer id) throws PariveshException {
		try {
			FcFormBPatchKmls fcFormBPatchKmls = fcFormBPatchKmlsRepository.getById(id);
			fcFormBPatchKmls.set_deleted(true);
			return ResponseHandler.generateResponse("Get Form B Patch KML", HttpStatus.OK, "",
					fcFormBPatchKmlsRepository.save(fcFormBPatchKmls));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in deleteFcFormBPatchKML for id- " + id, e);
		}
	}
}
