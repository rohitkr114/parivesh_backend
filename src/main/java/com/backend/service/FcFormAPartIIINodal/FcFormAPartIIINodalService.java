package com.backend.service.FcFormAPartIIINodal;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.model.Applications;
import com.backend.model.DepartmentApplication;
import com.backend.model.ProponentApplications;
import com.backend.model.FcFormAPartIIINodal.FcFormAPartIIINodalBasicDetails;
import com.backend.model.FcFormAPartIIINodal.FcFormAPartIIINodalCheckListDetails;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.FcFormAPartIIINodal.FcFormAPartIIINodalBasicDetailsRepository;
import com.backend.repository.postgres.FcFormAPartIIINodal.FcFormAPartIIINodalCheckListRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class FcFormAPartIIINodalService {

	@Autowired
	private FcFormAPartIIINodalBasicDetailsRepository fcFormAPartIIIBasicDetailsRepository;

	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;

	@Autowired
	private DepartmentApplicationRepository departmentApplicationRepository;

	@Autowired
	private ApplicationsRepository applicationsRepository;

	@Autowired
	private FcFormAPartIIINodalCheckListRepository fcFormAPartIIICheckListRepository;

	// Save Basic Details
	public ResponseEntity<Object> saveFCformAPartIIIBasicDetails(FcFormAPartIIINodalBasicDetails fcFormAPartIIIBasicDetails,
			Integer fcFormAPartIIICheckListDetailsId, HttpServletRequest request) {
		try {
			FcFormAPartIIINodalCheckListDetails fcFormAPartIIINodalCheckListDetails;
			fcFormAPartIIINodalCheckListDetails = fcFormAPartIIICheckListRepository.findByIdActive(fcFormAPartIIICheckListDetailsId);
			fcFormAPartIIIBasicDetails.setFcFormAPartIIINodalCheckListDetails(fcFormAPartIIINodalCheckListDetails);			
			fcFormAPartIIIBasicDetails = fcFormAPartIIIBasicDetailsRepository.save(fcFormAPartIIIBasicDetails);

			DepartmentApplication departmentApplication = departmentApplicationRepository
					.getApplicationByProposalId(fcFormAPartIIICheckListDetailsId);
			if (departmentApplication != null) {
				departmentApplication.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
				departmentApplicationRepository.save(departmentApplication);
			}
			return ResponseHandler.generateResponse("Save FC Form A Part III BasicDetails form", HttpStatus.OK, null,
					fcFormAPartIIIBasicDetails);

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving FC Form A Part III saveFCPartBBasicDetails id- ", e);
		}

	}// end of Save basic details

//	// Get Basic Details
//	public ResponseEntity<Object> getFCformAPartIIIBasicDetails(Integer fcFormAPartIIIBasicDetailsId) {
//		try {
//			FcFormAPartIIINodalBasicDetails basicDetails;
//			basicDetails = fcFormAPartIIIBasicDetailsRepository.findByIdActive(fcFormAPartIIIBasicDetailsId);
//			basicDetails.setDepartmentApplication(
//					departmentApplicationRepository.getDepartmentApplicationByProposalId(basicDetails.getId()));
//
//			return ResponseHandler.generateResponse("Fetched FC Form A Part III BasicDetails form", HttpStatus.OK, null,
//					basicDetails);
//		} catch (Exception e) {
//			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
//			throw new PariveshException(
//					"Error in fetching FC Form A Part III getFCPartBBasicDetails id- " + fcFormAPartIIIBasicDetailsId,
//					e);
//		}
//	} // end of get basic details
	
	// Get Basic Details
	public ResponseEntity<Object> getFCformAPartIIIChecklist(Integer fcFormAPartIIIChecklistId) {
		try {
			FcFormAPartIIINodalCheckListDetails fcFormAPartIIICheckListDetails;
			fcFormAPartIIICheckListDetails = fcFormAPartIIICheckListRepository.findByIdActive(fcFormAPartIIIChecklistId);
			fcFormAPartIIICheckListDetails.setDepartmentApplication(
					departmentApplicationRepository.getDepartmentApplicationByProposalId(fcFormAPartIIICheckListDetails.getId()));
			
			return ResponseHandler.generateResponse("Fetched FC Form A Part III ChecklistDetails form", HttpStatus.OK, null,
					fcFormAPartIIICheckListDetails);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in fetching FC Form A Part III getFCPartAChecklistDetails id- " + fcFormAPartIIIChecklistId,
					e);
		}
	} // end of get basic details

	public ResponseEntity<Object> saveFcFormAPartIIIChecklist(
			FcFormAPartIIINodalCheckListDetails fcFormAPartIIINodalCheckListDetails, Integer clearenceID, HttpServletRequest request) {
		try {
			FcFormAPartIIINodalCheckListDetails returnCheckListDetails;
			returnCheckListDetails = fcFormAPartIIICheckListRepository.save(fcFormAPartIIINodalCheckListDetails);
			
			ProponentApplications proponentApplications = proponentApplicationRepository
					.getApplicationByProposalId(fcFormAPartIIINodalCheckListDetails.getFc_id());

			if (departmentApplicationRepository
					.getDepartmentApplicationByProposalId(returnCheckListDetails.getId()) == null) {
				DepartmentApplication applications = new DepartmentApplication();
				Applications app = applicationsRepository.findById(clearenceID).get();
				applications.setApplications(app);
				applications.setProponentApplications(proponentApplications);
				applications.setProposal_sequence(proponentApplications.getProposal_sequence());
				applications.setProposal_no(proponentApplications.getProposal_no().replaceAll("\\s", ""));
				applications.setProposal_id(returnCheckListDetails.getId());
				setStatus(fcFormAPartIIINodalCheckListDetails.getStatus(), applications);
				applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);

				departmentApplicationRepository.save(applications);
			} else {
				DepartmentApplication applications = departmentApplicationRepository
						.getDepartmentApplicationByProposalId(returnCheckListDetails.getId());
				setStatus(fcFormAPartIIINodalCheckListDetails.getStatus(), applications);
				departmentApplicationRepository.save(applications);

			}

			return ResponseHandler.generateResponse("Save FC Form A part III Checklist", HttpStatus.OK, null,
					fcFormAPartIIICheckListRepository.save(fcFormAPartIIINodalCheckListDetails));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Saving FC A part III checklist ", e);
		}
	}
	
	public void setStatus(String status,DepartmentApplication applications) {
		if(status!=null && status.equalsIgnoreCase("submitted")) {
			applications.setStatus(Caf_Status.SUBMITTED.toString());
		}else {
			applications.setStatus(Caf_Status.DRAFT.toString());
		}
	}
}
