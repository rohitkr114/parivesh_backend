package com.backend.service.EcFormVPart2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.client.NotifyClient;
import com.backend.constant.AppConstant;
import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.DepartmentApplication;
import com.backend.model.ProponentApplications;
import com.backend.model.EcFormVPart2Model.EcFormVPart2;
import com.backend.model.EcFormVPart2Model.EcFormVPart2PublicHearingdetails;
import com.backend.model.EcFormVPart2Model.EcFormVPart2Undertaking;
import com.backend.model.EcFormVPart2Model.EcFormVpart2HearingIssues;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.CommonFormDetailRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.UserRepository;
import com.backend.repository.postgres.EcFormV.EcFormVRepository;
import com.backend.repository.postgres.EcFormVPart2.EcFormVPart2PublicHearingdetailsRepository;
import com.backend.repository.postgres.EcFormVPart2.EcFormVPart2Repository;
import com.backend.repository.postgres.EcFormVPart2.EcFormVPart2UndertakingRepository;
import com.backend.repository.postgres.EcFormVPart2.EcFormVpart2HearingIssuesRepository;
import com.backend.response.ResponseHandler;
import com.backend.service.EcFormV.EcFormVService;
import com.backend.util.ServerUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EcFormVPart2Service {

	@Autowired
	private NotifyClient notifyClient;

	@Autowired
	private EcFormVPart2Repository ecFormVPart2Repository;

	@Autowired
	private EcFormVPart2PublicHearingdetailsRepository ecFormVPart2PublicHearingdetailsRepository;

	@Autowired
	private EcFormVpart2HearingIssuesRepository ecFormVpart2HearingIssuesRepository;

	@Autowired
	private EcFormVPart2UndertakingRepository ecFormVPart2UndertakingRepository;

	@Autowired
	private DepartmentApplicationRepository departmentApplicationRepository;

	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;

	@Autowired
	private ApplicationsRepository applicationsRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ServerUtil util;
	
	@Autowired
	private EcFormVRepository ecFormVRepository;

	public ResponseEntity<Object> saveEcFormVPart2(EcFormVPart2 ecFormVPart2, Integer clearance_id) {
		try {
			EcFormVPart2 formVPart2 = ecFormVPart2Repository.save(ecFormVPart2);
			
			ProponentApplications proponentApplications = proponentApplicationRepository
					.getApplicationByProposalId(ecFormVPart2.getFormV_id());

			if (departmentApplicationRepository.getDepartmentApplicationByProposalId(formVPart2.getId()) == null) {
				DepartmentApplication applications = new DepartmentApplication();
				Applications app = applicationsRepository.findById(clearance_id).get();
				applications.setApplications(app);
				applications.setProponentApplications(proponentApplications);
				applications.setProposal_sequence(proponentApplications.getProposal_sequence());
				applications.setProposal_no(proponentApplications.getProposal_no().replaceAll("\\s", ""));
				applications.setProposal_id(formVPart2.getId());
				applications.setStatus(Caf_Status.DRAFT.toString());
//				applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);

				departmentApplicationRepository.save(applications);
			}
			return ResponseHandler.generateResponse("Save EC Form V Part-2 Form", HttpStatus.OK, null, formVPart2);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Form V Part 2 - ", e);
		}
	}

	public ResponseEntity<Object> getEcFormVPart2(Integer id) {
		try {
			EcFormVPart2 ecFormVPart2 = ecFormVPart2Repository.findById(id)
					.orElseThrow(() -> new ProjectNotFoundException("EC form V Part-2 not found with id" + id));
			ecFormVPart2.setDepartmentApplication(
					departmentApplicationRepository.getApplicationByProposalId(ecFormVPart2.getId()));
			ecFormVPart2.setEcFormVPart2Undertaking(ecFormVPart2UndertakingRepository.getDataByEcId(id).orElse(null));
			return ResponseHandler.generateResponse("Get EC form V Part2", HttpStatus.OK, null, ecFormVPart2);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Getting EC form V Part2 for Id- " + id, e);
		}
	}

	public ResponseEntity<Object> saveUndertaking(Integer ec_form_v_part2_id,
			EcFormVPart2Undertaking ecFormVPart2Undertaking, HttpServletRequest request) {
		try {
			EcFormVPart2 temp = ecFormVPart2Repository.findById(ec_form_v_part2_id)
					.orElseThrow(() -> new ProjectNotFoundException("EC form V Part2 form not found"));

			ecFormVPart2Undertaking.setEcFormVPart2(temp);
			
			String proposalNo = proponentApplicationRepository.getProposalNo(ec_form_v_part2_id);
            String identificationNo = ecFormVPart2UndertakingRepository.getIdentificationNo(proposalNo);
            ecFormVPart2Undertaking.setIdentification_no(identificationNo);
            log.info(identificationNo);

			DepartmentApplication departmentApplication = departmentApplicationRepository
					.getApplicationByProposalId(ec_form_v_part2_id);
			if (departmentApplication != null) {
				departmentApplication.setStatus(Caf_Status.SUBMITTED.toString());
				departmentApplication.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
				departmentApplicationRepository.save(departmentApplication);
			}

			return ResponseHandler.generateResponse("Save Ec Undertaking details form for EC Form V Part2",
					HttpStatus.OK, null, ecFormVPart2UndertakingRepository.save(ecFormVPart2Undertaking));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Saving EC Form V Part2  Undertaking details form id-" + ec_form_v_part2_id, e);
		}
	}

	public ResponseEntity<Object> getFormFiveParttTwoByTorId(Integer torId) {
		try {
				Integer formVId = ecFormVPart2Repository.getFormVId(torId);
				return ResponseHandler.generateResponse("Get EC form V Part2", HttpStatus.OK, null, getEcFormVPart2(formVId));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Getting EC form V Part2 for Id- " + e);
		}
	}


}

//	public ResponseEntity<Object> savePublicHearingdetails(Integer ec_form_v_part2_id,
//			List<EcFormVPart2PublicHearingdetails> listHearingDetails) {
//		try {
//			List<EcFormVPart2PublicHearingdetails> hearingDetails = new ArrayList<>();
//			EcFormVPart2 ec = ecFormVPart2Repository.getDetailsByEcId(ec_form_v_part2_id);
//			hearingDetails = listHearingDetails.stream().map(value -> {
//				value.setEcFormVPart2(ec);
//				return value;
//			}).collect(Collectors.toList());
//			return ResponseHandler.generateResponse("Save EC Form V Part-2 savePublicHearingdetails form ",
//					HttpStatus.OK, null, ecFormVPart2PublicHearingdetailsRepository.saveAll(hearingDetails));
//		} catch (Exception e) {
//			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
//			throw new PariveshException("Error in Saving EC Form V Part 2 Public hearing details - ", e);
//		}
//	}

//	public ResponseEntity<Object> deletePublicHearingdetails(Integer ph_id) {
//		try {
//			EcFormVPart2PublicHearingdetails temp= ecFormVPart2PublicHearingdetailsRepository.findById(ph_id)
//					.orElseThrow(() -> new ProjectNotFoundException("Ec Form V part-2 public hearing details not found with ID"+ ph_id));
//
//			temp.setIs_active(false);
//			temp.setIs_deleted(true);
//
//			return ResponseHandler.generateResponse("Hearing details deleted", HttpStatus.OK, "",
//					ecFormVPart2PublicHearingdetailsRepository.save(temp));
//		} catch (Exception e) {
//			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
//			throw new PariveshException("Error in Deleting EC Form V part2 deletePublicHearingdetails id- " + ph_id, e);
//		}
//	}
//
//	public ResponseEntity<Object> saveHearingIssues(Integer ec_form_v_part2_id,
//			List<EcFormVpart2HearingIssues> listHearingIssues) {
//		try {
//			List<EcFormVpart2HearingIssues> hearingIssues = new ArrayList();
//			EcFormVPart2 ec = ecFormVPart2Repository.getDetailsByEcId(ec_form_v_part2_id);
//			hearingIssues = listHearingIssues.stream().map(value -> {
//				value.setEcFormVPart2(ec);
//				return value;
//			}).collect(Collectors.toList());
//			return ResponseHandler.generateResponse("Save EC Form V Part-2 saveHearingIssues form ", HttpStatus.OK,
//					null, ecFormVpart2HearingIssuesRepository.saveAll(hearingIssues));
//		}catch (Exception e) {
//			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
//			throw new PariveshException("Error in Saving EC Form V Part 2 Hearing Issues - ", e);
//		}
//	}
//
//	public ResponseEntity<Object> deleteHearingIssues(Integer hi_id) {
//		try{
//			EcFormVpart2HearingIssues temp= ecFormVpart2HearingIssuesRepository.findById(hi_id)
//				.orElseThrow(() -> new ProjectNotFoundException("Ec Form V part-2 hearing issues not found with ID"+ hi_id));
//			
//			temp.setIs_active(false);
//			temp.setIs_deleted(true);
//			
//			return ResponseHandler.generateResponse("Hearing Issues deleted", HttpStatus.OK, "",
//					ecFormVpart2HearingIssuesRepository.save(temp));
//		} catch (Exception e) {
//			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
//			throw new PariveshException("Error in Deleting EC Form V part2 deleteHearingIssues id- " + hi_id, e);
//		}
//	}



