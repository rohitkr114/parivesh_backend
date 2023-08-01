package com.backend.service.EcForm6Part1;

import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.exceptions.UserNotFoundException;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.ProponentApplications;
import com.backend.model.EcForm6Part1.EcForm6BasicDetails;
import com.backend.model.EcForm6Part1.EcForm6BasicDetailsEarlierEc;
import com.backend.model.EcForm6Part1.Form6BasicDetailsActivity;
import com.backend.model.EcForm6Part1.Form6BasicDetailsMinorActivity;
import com.backend.model.EcPartC.EcPartC;
import com.backend.repository.postgres.ActivitySubActivitySectorRepository;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.CommonFormDetailRepository;
import com.backend.repository.postgres.DocumentDetailsRepository;
import com.backend.repository.postgres.EnvironmentClearenceRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.StateRepository;
import com.backend.repository.postgres.EcForm6CafDetails.EcForm6CafDetailsRepository;
import com.backend.repository.postgres.EcForm6Part1.EcForm6BasicDetailsRepository;
import com.backend.repository.postgres.EcForm6Part1.EcForm6BasicdetailsEarlierEcRepository;
import com.backend.repository.postgres.EcForm6Part1.Form6BasicDetailsActivityRepository;
import com.backend.repository.postgres.EcForm6Part1.Form6BasicDetailsMinorActivityRepository;
import com.backend.repository.postgres.EcPartC.EcPartCRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class EcForm6BasicDetailsService {

	@Autowired
	private EcForm6BasicDetailsRepository ecForm6BasicdetailsRepository;
	
	@Autowired
	EcForm6BasicdetailsEarlierEcRepository ecForm6BasicdetailsEarlierEcRepository;
	
	@Autowired
	DocumentDetailsRepository documentDetailsRepository;
	
	@Autowired
	private CommonFormDetailRepository commonFormDetailRepository;
	
	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private ApplicationsRepository applicationsRepository;
	
	@Autowired
	private Form6BasicDetailsMinorActivityRepository form6BasicDetailsMinorActivityRepository;
	
	@Autowired
	private Form6BasicDetailsActivityRepository lform6BasicDetailsActivityRepository;
	
	@Autowired 
	private EcPartCRepository ecPartCRepository;
	
	@Autowired
	private ActivitySubActivitySectorRepository activitySectorRepository;
	
	@Autowired
	private EcForm6CafDetailsRepository ecForm6CafDetailsRepository;
	
	@Autowired
	private EnvironmentClearenceRepository environmentClearenceRepository;

	// Generate Proposal No.
	public ProponentApplications generateProposalNoWithoutApp(Integer caf_id,Integer ecId,HttpServletRequest request)
	{
		ProponentApplications applications = null;
		try
		{
			// Generate Proposal No
			
			//documentDetailsRepository.save(ecForm6.getEcForm6BasicDetailsEarlierec().get(0).getEarlier_ec_upload_doc().get(0));
			CommonFormDetail commonForm = commonFormDetailRepository.findByCaf(caf_id)
					.orElseThrow(() -> new ProjectNotFoundException("Caf form not found for caf id ----> " + caf_id));
			
			
			System.out.println("caf_id-------proposal no without app-----------"+caf_id);
			// PROPONENT APPLICATION
			
			// to get the category of a project we are get the FORMPARTC with respect to perticular EC.
			EcPartC ecPartC = ecPartCRepository.getFormPartC(ecId);
			
			
			
			List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();

			applications = new ProponentApplications();
			if (tempClearances.isEmpty()) {
				String proposal_no = null;
				if (ecPartC.getProject_category().equalsIgnoreCase("A")) {
					proposal_no = "IA/"
							+ stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
									.orElseThrow(() -> new ProjectNotFoundException("state not found with code"))
									.getState_abbr()
							+ "/"
							+ activitySectorRepository
									.getSector(ecPartC.getMajor_activity_id(), ecPartC.getMajor_sub_activity_id())
									.getSector_code()
							+ "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
				} else {
					if (ecPartC.getIs_proposed_required()) {
						proposal_no = "IA/"
								+ stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
										.orElseThrow(() -> new ProjectNotFoundException("state not found with code"))
										.getState_abbr()
								+ "/"
								+ activitySectorRepository
										.getSector(ecPartC.getMajor_activity_id(), ecPartC.getMajor_sub_activity_id())
										.getSector_code()
								+ "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
						// return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
					} else {
//						ResponseEntity<List<sieea>> resp = sieaaStatusAPI.getSieeaStatus(projectDetailRepository
//								.findById(cafDetail.getProject_id())
//								.orElseThrow(() -> new ProjectNotFoundException("project not found")).getMain_state());
//						if (resp.getBody().get(0).getStatus().equals("active")) {
						proposal_no = "SIA/"
								+ stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
										.orElseThrow(() -> new ProjectNotFoundException("state not found with code"))
										.getState_abbr()
								+ "/"
								+ activitySectorRepository
										.getSector(ecPartC.getMajor_activity_id(), ecPartC.getMajor_sub_activity_id())
										.getSector_code()
								+ "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
						// return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);

					}

				}
				
				System.out.println("proposal_no-------proposal no without app-----------"+proposal_no);
				
				applications.setProposal_sequence(400000);
				applications.setProposal_no(proposal_no);
				//ecClearence.setProposal_no(proposal_no); WHAT IS THIS ?
			} else {
				Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence())
						.max(Comparator.comparing(Integer::valueOf)).get();
				if (maxCount != null) {
					applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
					applications.setProposal_no(generateProposalNo(maxCount, commonForm, ecPartC));  // ecPartC TAKEN FOR PROPOSAL GENERATION
					//ecForm6.setProposal_no(applications.getProposal_no());
				}
			}
			/*
			 * if (tempClearances.isEmpty()) { String proposal_no = "IA/" + stateRepository
			 * .getStateByCode(projectDetailRepository.findById(ecClearence.
			 * getCommonFormDetail().getProject_id()) .orElseThrow(() -> new
			 * ProjectNotFoundException("project not found")).getMain_state())
			 * .orElseThrow(() -> new
			 * ProjectNotFoundException("state not found with code")).getState_abbr() + "/"
			 * + activityRepository
			 * .findById(ecClearence.getEnvironmentClearanceProjectActivityDetails().get(0).
			 * getActivities() .getId()) .orElseThrow(() -> new
			 * ProjectNotFoundException("activity not found with id"))
			 * .getSectorEntity().getSectorCode() + "/" + 100000 + "/" +
			 * String.valueOf(LocalDate.now().getYear());
			 * applications.setProposal_sequence(100000);
			 * applications.setProposal_no(proposal_no);
			 * ecPartC.setProposal_no(proposal_no); } else { Integer maxCount =
			 * tempClearances.stream().map(e -> e.getProposal_sequence())
			 * .max(Comparator.comparing(Integer::valueOf)).get(); if (maxCount != null) {
			 * applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(
			 * maxCount))); applications.setProposal_no(generateProposalNo(maxCount,
			 * ecClearence)); ecPartC.setProposal_no(applications.getProposal_no()); } }
			 */
//			if (ecPartC.getAction_plan_raised() != null) {
//				ecPartC.getAction_plan_raised().setProposal_no(ecPartC.getProposal_no());
//			}
//			if (ecPartC.getEac_recommendation() != null) {
//				ecPartC.getEac_recommendation().setProposal_no(ecPartC.getProposal_no());
//			}
//			if (ecPartC.getTor_letter() != null) {
//				ecPartC.getTor_letter().setProposal_no(ecPartC.getProposal_no());
//			}
//			if (ecPartC.getTor_letter_copy() != null) {
//				ecPartC.getTor_letter_copy().setProposal_no(ecPartC.getProposal_no());
//			}
			
			//EcForm6BasicDetails temp2 = ecForm6BasicdetailsRepository.save(ecForm6);
			
			Applications app = applicationsRepository.findById(65).get();
			applications.setCaf_id(commonForm.getId());
			//applications.setProposal_id(temp2.getId());  FROM WHERE THIS ID GOT.
			
			// THIS IS PROBLEM AREA,WHERE PROPOSAL_ID = ID OF FORM 6 
			applications.setProposal_id(0); //0 means no application is exist
			
			applications.setState(stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
					.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getName());
			/*
			 * applications.setState_id(stateRepository.getStateByCode(cafDetail.
			 * getProjectDetails().getMain_state()) .orElseThrow(() -> new
			 * ProjectNotFoundException("state not found with code")).getId());
			 */

			applications.setState_id(stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
					.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getCode());
			applications.setProjectDetails(commonForm.getProjectDetails());
			applications.setApplications(app);
			applications.setLast_status(Caf_Status.DRAFT.toString());
			applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
			proponentApplicationRepository.save(applications);

			//Generate Proposal No
			
		}catch(Exception ex)
		{
			ex.getStackTrace();
		}
		
		
		
		return applications;
	}
	
	
	
	// public Object saveEcForm6(EcForm6Basicdetails ecForm6, Integer caf_id,
	// HttpServletRequest request) {
	//,Integer ec_id
	// Here ecId : Environmental clearance ID.
	// there is a issue in the use of this variable. many plances where ecId
	public Object saveEcForm6(EcForm6BasicDetails ecForm6, Integer caf_id,Integer ecId, HttpServletRequest request) {

		try {			
			// back case : if the record is already exixt :
			//ecForm6.setId(11);
			//System.out.println("id is >>>>"+ecForm6.getId());			
			
			if(ecForm6!=null)
			if(ecForm6.getId()>0)
			{
				//System.out.println("Delete block");
				// Basic Detail master data object
				EcForm6BasicDetails EcForm6BasicDetails1 =ecForm6BasicdetailsRepository.getBasicDetailsById(ecForm6.getId());		
			
				// Deletion of child Entity : List<EcForm6BasicDetailsEarlierEc>
                if(EcForm6BasicDetails1!=null)
                {
                List<EcForm6BasicDetailsEarlierEc> lecForm6BasicDetailsEarlierEc= EcForm6BasicDetails1.getEcFormSixEcObtained();
				
                if(lecForm6BasicDetailsEarlierEc!=null)
                {
			    // iterate the nodes and delete each by its id
				for(int i=0;i<lecForm6BasicDetailsEarlierEc.size();i++)
				{
					int id= lecForm6BasicDetailsEarlierEc.get(i).getId();
					
					// Repository
					ecForm6BasicdetailsEarlierEcRepository.deleteById(id);
					
				}
                }
				
				// Deletion of  the child Entity : Form6BasicDetailsActivity 
			   	
                 List<Form6BasicDetailsActivity> lform6BasicDetailsActivity= EcForm6BasicDetails1.getEcActivities();
				
                 if(lform6BasicDetailsActivity!=null)
                 {
			    // iterate the nodes and delete each by its id
				for(int i=0;i<lform6BasicDetailsActivity.size();i++)
				{
					int id= lform6BasicDetailsActivity.get(i).getId();
					
					// Repository
					lform6BasicDetailsActivityRepository.deleteById(id);
					
				}
                 }
				
				
			// Deletion of  the child Entity : Form6BasicDetailsActivity 
			List<Form6BasicDetailsMinorActivity> lform6BasicDetailsMinorActivity= EcForm6BasicDetails1.getEcFormSixMinorActivity();
					
			if(lform6BasicDetailsMinorActivity!=null)
			{
				    // iterate the nodes and delete each by its id
					for(int i=0;i<lform6BasicDetailsMinorActivity.size();i++)
					{
						int id= lform6BasicDetailsMinorActivity.get(i).getId();
						
						// Repository
						form6BasicDetailsMinorActivityRepository.deleteById(id);
						
					}
			}		
		}
			}
			//System.out.println("id is 11111 >>>>"+ecForm6.getId());
			//ecForm6="{\"id\":0,\"proposal_no\":null,\"is_multiple_item_envolved\":\"true\",\"is_multiple_item_envolved_remarks\":null,\"moef_file_no\":\"33\",\"prior_environmental_clearance_date\":\"2022-11-17\",\"ec_letter_copy\":{\"id\":0,\"ref_id\":\"523\",\"type\":\"EC_FORM_SIX\",\"document_mapping_id\":327757,\"document_name\":\"jgjg.pdf\",\"version\":\"1.4\",\"uuid\":\"39d2a859-8b7d-4f30-8211-f498c54ce7b1\"},\"is_earlier_ec_obtained\":\"true\",\"ecFormSixEcObtained\":[{\"id\":0,\"ec_obtained_select\":\"Amendment\",\"ec_obtained_date\":\"2022-11-18\",\"ec_obtained_document\":{\"id\":0,\"ref_id\":\"523\",\"type\":\"EC_FORM_SIX\",\"document_mapping_id\":327902,\"document_name\":\"rerer.pdf\",\"version\":\"1.4\",\"uuid\":\"0ce58348-f27c-4e7f-b07a-6937570c1ba2\"}]},{\"id\":0,\"ec_obtained_select\":\"Corrigendum\",\"ec_obtained_date\":\"2022-11-19\",\"ec_obtained_document\":[{\"id\":0,\"ref_id\":\"523\",\"type\":\"EC_FORM_SIX\",\"document_mapping_id\":327902,\"document_name\":\"myfile.pdf\",\"version\":\"1.4\",\"uuid\":\"9b32b598-9ee6-4868-ab6d-b856bef320d0\"}]},\"major_activity_id\":null,\"major_sub_activity_id\":null,\"ec_obtained_select\":null,\"ec_obtained_date\":null,\"ec_obtained_document\":null,\"chronology_of_clearances\":{\"id\":0,\"ref_id\":\"523\",\"type\":\"EC_FORM_SIX\",\"document_mapping_id\":327913,\"document_name\":\"Norman.pdf\",\"version\":\"1.2\",\"uuid\":\"1e4d9052-e977-45c1-bd6c-47d50bb0bdc6\"},\"ecActivities\":[{\"id\":0,\"activityId\":\"32\",\"subActivityId\":\"106\",\"activity_type\":\"Major / 2006 \",\"threshold\":[{\"threshold\":\"11\",\"unit\":\"TPA\"}]},{\"id\":0,\"activityId\":null,\"subActivityId\":null,\"activity_type\":null,\"threshold\":[]}]}";
			//System.out.println("EcForm6 Child Object is => " + ecForm6.getEcFormSixEcObtained() + "" + ecForm6.getEcFormSixEcObtained().get(0));
			//System.out.println("EcForm6 Child Object is => " + ecForm6.getEcFormSixEcObtained());
			/*16112022
			 * if (ecForm6 == null) {
				return ResponseHandler.generateResponse("Values should not be null", HttpStatus.OK, "Data Not found",
						request);
			}*/

			/*CommonFormDetail commonForm = commonFormDetailRepository.findByCaf(caf_id)
					.orElseThrow(() -> new ProjectNotFoundException("Caf form not found for caf id ----> " + caf_id));
			ecForm6.setCommonFormDetail(commonForm);
			if (ecForm6.getId() != null && ecForm6.getId() != 0) {
				ecForm6.setProposal_no(ecForm6BasicdetailsRepository.getProposalNo(ecForm6.getId()));
				return ecForm6BasicdetailsRepository.save(ecForm6);
			}*/
			
			/*******************************************ecForm6******************************************/
			
		//	ecForm6.setCopy_ec_letter_uuid(ecForm6.getCopy_ec_letter_doc().get(0).getUUID());
		//	ecForm6.setChronology_brief_uuid(ecForm6.getChronology_brief_doc().get(0).getUUID());
			
			// get the common details for this caf_id from the common details.
			
			
			/*16112022
			 * CommonFormDetail commonForm = commonFormDetailRepository.findByCaf(caf_id)
					.orElseThrow(() -> new ProjectNotFoundException("Caf form not found for caf id ----> " + caf_id));
			
			ecForm6.setCommonFormDetail(commonForm);*/
			
			// check the id of ecform6 then save it else 
			/*16112022
			 * if (ecForm6.getId() != null && ecForm6.getId() != 0) {
				//ecForm6.setProposal_no(ecForm6BasicdetailsRepository.getProposalNo(ecForm6.getId())); // This is take care later
				ecForm6.setProposal_no(""); // This is take care later
				return ecForm6BasicdetailsRepository.save(ecForm6);
			}*/
			
			// common details
			
			
			/*16112022
			 * ecForm6BasicdetailsRepository.save(ecForm6);*/

			/*******************************************ecForm6******************************************/
			
			
			/*******************************************EcForm6BasicDetailsEarlierec=ecForm6.getEcForm6BasicDetailsEarlierec().get(0)***********************************/
			//ecForm6.getEcForm6BasicDetailsEarlierec().get(0).setForm6_basic_id(ecForm6.getId());  // Foreign key reference
			
			//ecForm6.getEcForm6BasicDetailsEarlierec().get(0).setCaf_id(ecForm6.getCaf_id());
			
			// Document UUID Set
			//ecForm6.getEcForm6BasicDetailsEarlierec().get(0).setEarlier_ec_upload_uuid(
				//	ecForm6.getEcForm6BasicDetailsEarlierec().get(0).getEarlier_ec_upload_doc().get(0).getUUID());
			
			//ecForm6BasicdetailsEarlierEcRepository.save(ecForm6.getEcForm6BasicDetailsEarlierec().get(0));
			
			/*******************************************EcForm6BasicDetailsEarlierec=ecForm6.getEcForm6BasicDetailsEarlierec().get(0)***********************************/
			
			//documentDetailsRepository.save(ecForm6.getEcForm6BasicDetailsEarlierec().get(0).getEarlier_ec_upload_doc().get(0));
			CommonFormDetail commonForm = commonFormDetailRepository.findByCaf(caf_id)
					.orElseThrow(() -> new ProjectNotFoundException("Caf form not found for caf id ----> " + caf_id));
			
			ecForm6.setCommonFormDetail(commonForm);
			
			// get the object of EC for ecId and set the reference in the basic detail object.
			//ecForm6.setEnvironmentClearence(environmentClearenceRepository.getById(ecId));
			
			if (ecForm6.getId() != null && ecForm6.getId() != 0) {
				ecForm6.setProposal_no(ecForm6BasicdetailsRepository.getProposalNo(ecForm6.getId()).replaceAll("\\s", ""));
				return ecForm6BasicdetailsRepository.save(ecForm6);
			}
			
			
			// This is the case when CAF is the integrated part of form6 and generate the Proposal No. on step 1.
			// Here only update the proposal no. here.
			// getApplicationByCafId
			//ProponentApplications papplications=null;
			
			//papplications= proponentApplicationRepository.getApplicationByProposalId(0);
			
			// PROPONENT APPLICATION
			
			// to get the category of a project we are get the FORMPARTC with respect to perticular EC.
			
			/*
			 This is the Proponent Application Code which is running when Generate the Proposal No on this step.
			*/
			
			EcPartC ecPartC = ecPartCRepository.getFormPartC(ecId);
			
			
			
			List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();

			ProponentApplications applications = new ProponentApplications();
			if (tempClearances.isEmpty()) {
				String proposal_no = null;
				if (ecPartC.getProject_category().equalsIgnoreCase("A")) {
					proposal_no = "IA/"
							+ stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
									.orElseThrow(() -> new ProjectNotFoundException("state not found with code"))
									.getState_abbr()
							+ "/"
							+ activitySectorRepository
									.getSector(ecPartC.getMajor_activity_id(), ecPartC.getMajor_sub_activity_id())
									.getSector_code()
							+ "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
				} else {
					if (ecPartC.getIs_proposed_required()) {
						proposal_no = "IA/"
								+ stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
										.orElseThrow(() -> new ProjectNotFoundException("state not found with code"))
										.getState_abbr()
								+ "/"
								+ activitySectorRepository
										.getSector(ecPartC.getMajor_activity_id(), ecPartC.getMajor_sub_activity_id())
										.getSector_code()
								+ "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
						// return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
					} else {
//						ResponseEntity<List<sieea>> resp = sieaaStatusAPI.getSieeaStatus(projectDetailRepository
//								.findById(cafDetail.getProject_id())
//								.orElseThrow(() -> new ProjectNotFoundException("project not found")).getMain_state());
//						if (resp.getBody().get(0).getStatus().equals("active")) {
						proposal_no = "SIA/"
								+ stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
										.orElseThrow(() -> new ProjectNotFoundException("state not found with code"))
										.getState_abbr()
								+ "/"
								+ activitySectorRepository
										.getSector(ecPartC.getMajor_activity_id(), ecPartC.getMajor_sub_activity_id())
										.getSector_code()
								+ "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
						// return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);

					}

				}
				applications.setProposal_sequence(400000);
				applications.setProposal_no(proposal_no);
				//ecClearence.setProposal_no(proposal_no); WHAT IS THIS ?
			} else {
				Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence())
						.max(Comparator.comparing(Integer::valueOf)).get();
				if (maxCount != null) {
					applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
					applications.setProposal_no(generateProposalNo(maxCount, commonForm, ecPartC));  // ecPartC TAKEN FOR PROPOSAL GENERATION
					ecForm6.setProposal_no(applications.getProposal_no());
				}
			}
			/*
			This is one segment of code which is used when Generate the Proposal No. on This step.
			*/
			/*
			 * if (tempClearances.isEmpty()) { String proposal_no = "IA/" + stateRepository
			 * .getStateByCode(projectDetailRepository.findById(ecClearence.
			 * getCommonFormDetail().getProject_id()) .orElseThrow(() -> new
			 * ProjectNotFoundException("project not found")).getMain_state())
			 * .orElseThrow(() -> new
			 * ProjectNotFoundException("state not found with code")).getState_abbr() + "/"
			 * + activityRepository
			 * .findById(ecClearence.getEnvironmentClearanceProjectActivityDetails().get(0).
			 * getActivities() .getId()) .orElseThrow(() -> new
			 * ProjectNotFoundException("activity not found with id"))
			 * .getSectorEntity().getSectorCode() + "/" + 100000 + "/" +
			 * String.valueOf(LocalDate.now().getYear());
			 * applications.setProposal_sequence(100000);
			 * applications.setProposal_no(proposal_no);
			 * ecPartC.setProposal_no(proposal_no); } else { Integer maxCount =
			 * tempClearances.stream().map(e -> e.getProposal_sequence())
			 * .max(Comparator.comparing(Integer::valueOf)).get(); if (maxCount != null) {
			 * applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(
			 * maxCount))); applications.setProposal_no(generateProposalNo(maxCount,
			 * ecClearence)); ecPartC.setProposal_no(applications.getProposal_no()); } }
			 */
//			if (ecPartC.getAction_plan_raised() != null) {
//				ecPartC.getAction_plan_raised().setProposal_no(ecPartC.getProposal_no());
//			}
//			if (ecPartC.getEac_recommendation() != null) {
//				ecPartC.getEac_recommendation().setProposal_no(ecPartC.getProposal_no());
//			}
//			if (ecPartC.getTor_letter() != null) {
//				ecPartC.getTor_letter().setProposal_no(ecPartC.getProposal_no());
//			}
//			if (ecPartC.getTor_letter_copy() != null) {
//				ecPartC.getTor_letter_copy().setProposal_no(ecPartC.getProposal_no());
//			}
			//temp2 = ecForm7Repository.save(ecForm7);
			
			//EcForm6BasicDetails temp2 = ecForm6BasicdetailsRepository.save(ecForm6);
			//papplications.setProposal_id(temp2.getId());
			
			//proponentApplicationRepository.save(papplications); // update the ProponentApplication Code.
			
			/*
			 This is the second segment of code which is used when generate the Proposal No. on this step.
			 */
			EcForm6BasicDetails temp2 = ecForm6BasicdetailsRepository.save(ecForm6);
			
			Applications app = applicationsRepository.findById(65).get();
			applications.setCaf_id(commonForm.getId());
			applications.setProposal_id(temp2.getId());
			
			applications.setState(stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
					.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getName());
			/*		
			End of Second segment which is used when generate proposal No. on this step.
					*/
			/*
			 * applications.setState_id(stateRepository.getStateByCode(cafDetail.
			 * getProjectDetails().getMain_state()) .orElseThrow(() -> new
			 * ProjectNotFoundException("state not found with code")).getId());
			 */

			/*
			 This is third segment which is used when we generate the proposal No. on this segment
			*/
			
			applications.setState_id(stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
					.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getCode());
			applications.setProjectDetails(commonForm.getProjectDetails());
			applications.setApplications(app);
			applications.setLast_status(Caf_Status.DRAFT.toString());
			applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
			proponentApplicationRepository.save(applications);
			/*
This is third segment of code which is used when use the generate proposal no of this step.
*/
			// PROPONENT APPLICATION
			
			// SET THE ecId for this form6
			// Change the status of ECFORM6 CAF DETAIL

			//EcForm6CafDetails ecForm6CafDetails = ecForm6CafDetailsRepository
				//	.getEcForm6CafDetailsByNewCafId(temp2.getCommonFormDetail().getId());

			//ecForm6CafDetails.setEcId(temp2.getId());
			//ecForm6CafDetailsRepository.save(ecForm6CafDetails);
			
			return temp2;			
			/*16112022
			 * return ecForm6;*/
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC form 6 Basic Details- " + ecForm6.getId(), e);
		}
	}
	
	public String generateSequenceNumber(int maxcount) {
		// this will convert any number sequence into 6 character.
		AtomicInteger sequence = new AtomicInteger(maxcount);

		return String.format("%06d", sequence.addAndGet(1));
	}
	
	/*
	private String generateProposalNo(int maxcount, EcForm6BasicDetails form, CommonFormDetail commonForm) {
		String cafId = "IA/"
				+ stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
						.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
				+ "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
		cafId = cafId.replaceAll("\\s", "");
		return cafId;
	}
	*/
	
	private String generateProposalNo(int maxcount, CommonFormDetail form, EcPartC ecPartC) {
		if (ecPartC.getProject_category().equalsIgnoreCase("A")) {
			String cafId = "IA/" + stateRepository.getStateByCode(form.getProjectDetails().getMain_state())
					.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr() + "/"
					+ activitySectorRepository
							.getSector(ecPartC.getMajor_activity_id(), ecPartC.getMajor_sub_activity_id())
							.getSector_code()
					+ "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
			return cafId;
		} else {
			if (ecPartC.getIs_proposed_required()) {
				String cafId = "IA/" + stateRepository.getStateByCode(form.getProjectDetails().getMain_state())
						.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
						+ "/"
						+ activitySectorRepository
								.getSector(ecPartC.getMajor_activity_id(), ecPartC.getMajor_sub_activity_id())
								.getSector_code()
						+ "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
				// return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
				return cafId;
			} else {
//				ResponseEntity<List<sieea>> resp = sieaaStatusAPI
//						.getSieeaStatus(projectDetailRepository.findById(form.getProject_id())
//								.orElseThrow(() -> new ProjectNotFoundException("project not found")).getMain_state());
//				if (resp.getBody().get(0).getStatus().equals("active")) {
				String cafId = "SIA/" + stateRepository.getStateByCode(form.getProjectDetails().getMain_state())
						.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
						+ "/"
						+ activitySectorRepository
								.getSector(ecPartC.getMajor_activity_id(), ecPartC.getMajor_sub_activity_id())
								.getSector_code()
						+ "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
				// return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
				return cafId;

			}
		}
		// return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
	}
	
	
	
	
	// [Method to View EC Form 6 Basic Details]
	public Optional<EcForm6BasicDetails> view1(Integer ecId, HttpServletRequest request) {
		Optional<EcForm6BasicDetails> ecForm6BasicDetails = null;
		try {
			ecForm6BasicDetails = ecForm6BasicdetailsRepository.findById(ecId);
			if (ecForm6BasicDetails == null) {  
				throw new UserNotFoundException("Data not found");
			}
			return ecForm6BasicDetails;

		} catch (Exception e) {
			log.error("===========EcForm6BasictDetails Service view() method============>>>>>>>>>>>" + e.toString()
					+ " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in View EC form 6 Basic Details - " + ecForm6BasicDetails.get().getId(),
					e);
		}
	}
	
	// [Method to View EC Form 6 Basic Details]
	public Object view(Integer ecId) {
		EcForm6BasicDetails ecForm6BasicDetails = null;
		ProponentApplications proponentApplications = null;
		try {
			ecForm6BasicDetails = ecForm6BasicdetailsRepository.getBasicDetailsById(ecId);
			if (ecForm6BasicDetails == null) {
				throw new UserNotFoundException("Data not found");
			}

			proponentApplications = proponentApplicationRepository.getApplicationByProposalId_6(ecId);
			if (proponentApplications == null) {
				ecForm6BasicDetails.setLastStatus(Caf_Status.DRAFT.toString());
			} else {
				ecForm6BasicDetails.setLastStatus(proponentApplications.getLast_status());
			}
			return ecForm6BasicDetails;

		} catch (Exception e) {
			log.error("===========EcForm6BasictDetails Service view() method============>>>>>>>>>>>" + e.toString()
					+ " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in View EC form 6 Basic Details - " + ecForm6BasicDetails, e);
		}
	}	
	
	// METHOD TO CHECK FORM 6 INTEGRITY
	public Integer isIntegrityCheck(EcForm6BasicDetails ecForm6BasicDetails, Integer caf_id) {

		/*
		 * return 0 = caf is not exist 1 = project and caf are not correlated 3 = valid
		 */
		Integer indicator = 3;
		// Project and Caf Integrity Test
		CommonFormDetail commonFormDetail = commonFormDetailRepository.getCAF(caf_id);

		if (commonFormDetail == null) {
			indicator = 0;
		} else if (!commonFormDetail.getProject_id().equals(ecForm6BasicDetails.getProject_id())) {
			indicator = 1;
		}
		// EC INTEGRITY TEST
		return indicator;
	}
}