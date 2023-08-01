package com.backend.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.DepartmentApplication;
import com.backend.model.MsRecommendation;
import com.backend.model.MsRecommendationEACDetails;
import com.backend.model.ProponentApplications;
import com.backend.model.FcFormDPartB.FcFormDPartBBasicDetails;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.MsRecommendationEACDetailsRepository;
import com.backend.repository.postgres.MsRecommendationRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MsRecommendationService {

	@Autowired
	private MsRecommendationRepository msRecommendationRepository;

	@Autowired
	private MsRecommendationEACDetailsRepository msDetailsRepository;

	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;

	@Autowired
	private DepartmentApplicationRepository departmentApplicationRepository;
	
	@Autowired
	private ApplicationsRepository applicationsRepository;

	public ResponseEntity<Object> saveMsRecommendation(MsRecommendation msRecommendation, Integer proposalId,
			HttpServletRequest request) {
		try {

			MsRecommendation msRecommendation2;
			msRecommendation2 = msRecommendationRepository.save(msRecommendation);

			ProponentApplications proponentApplications = proponentApplicationRepository
					.getApplicationByProposalId(proposalId);

			if (departmentApplicationRepository
					.getDepartmentApplicationByProposalId(msRecommendation2.getId()) == null) {
				DepartmentApplication applications = new DepartmentApplication();
				Applications app = applicationsRepository.findById(52).get();
				applications.setApplications(app);
				applications.setProponentApplications(proponentApplications);
				applications.setProposal_sequence(proponentApplications.getProposal_sequence());
				applications.setProposal_no(proponentApplications.getProposal_no());
				applications.setProposal_id(msRecommendation2.getId());
				applications.setStatus(Caf_Status.SUBMITTED.toString());
				applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);

				departmentApplicationRepository.save(applications);
			}

			return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
					msRecommendation2);

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving Ms Recommendation", e);
		}
	}

	public ResponseEntity<Object> getMsRecommendation(Integer id) {
		try {
			MsRecommendation msRecommendation=msRecommendationRepository.findById(id).orElseThrow(()->new ProjectNotFoundException("Ms Recommendation not found"));
			msRecommendation.setDepartmentApplication(
					departmentApplicationRepository.getDepartmentApplicationByProposalId(msRecommendation.getId()));
			
			return ResponseHandler.generateResponse("Getting Ms Recommendation", HttpStatus.OK, "",
					msRecommendation);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting Ms Recommendation", e);
		}
	}

	public ResponseEntity<Object> getMsRecommendationList() {
		try {
			return ResponseHandler.generateResponse("Getting Ms Recommendation", HttpStatus.OK, "",
					msRecommendationRepository.findAll());
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting Ms Recommendation", e);
		}
	}

	public ResponseEntity<Object> saveMsRecommendationEACDetails(MsRecommendationEACDetails msRecommendationEACDetails,
			Integer proposalId, HttpServletRequest request) {
		try {

			/*
			 * if (msRecommendationEACDetails.getId() == null || msRecommendationEACDetails.getId() == 0) {
				MsRecommendationEACDetails msRecommendation = msDetailsRepository.getDataByMsRecommendationId(id);
				if (msRecommendation != null) {
					msRecommendationEACDetails.setId(msRecommendation.getId());
				}
			}
			*/
			MsRecommendationEACDetails eacDetails;
			eacDetails=msDetailsRepository.save(msRecommendationEACDetails);
			
			ProponentApplications proponentApplications = proponentApplicationRepository
					.getApplicationByProposalId(proposalId);

			if (departmentApplicationRepository
					.getDepartmentApplicationByProposalId(eacDetails.getId()) == null) {
				DepartmentApplication applications = new DepartmentApplication();
				Applications app = applicationsRepository.findById(50).get();
				applications.setApplications(app);
				applications.setProponentApplications(proponentApplications);
				applications.setProposal_sequence(proponentApplications.getProposal_sequence());
				applications.setProposal_no(proponentApplications.getProposal_no());
				applications.setProposal_id(eacDetails.getId());
				applications.setStatus(Caf_Status.SUBMITTED.toString());
				applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);

				departmentApplicationRepository.save(applications);
			}
			return ResponseHandler.generateResponse("Save MsRecommendationEAC form", HttpStatus.OK, null,
					eacDetails);

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving Ms Recommendation", e);
		}
	}

	public ResponseEntity<Object> getMsRecommendationEACDetails(Integer id) {
		try {
			MsRecommendationEACDetails msEACRecommendation=msDetailsRepository.findById(id).orElseThrow(()->new ProjectNotFoundException("Ms Recommendation EAC Details not found"));
			msEACRecommendation.setDepartmentApplication(
					departmentApplicationRepository.getDepartmentApplicationByProposalId(msEACRecommendation.getId()));
		
			
			return ResponseHandler.generateResponse("Getting Ms Recommendation EAC Details", HttpStatus.OK, "",
					msEACRecommendation);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting Ms Recommendation", e);
		}
	}

}
