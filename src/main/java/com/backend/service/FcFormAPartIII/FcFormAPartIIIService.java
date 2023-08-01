package com.backend.service.FcFormAPartIII;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.constant.AppConstant;
import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.DepartmentApplication;
import com.backend.model.ProponentApplications;
import com.backend.model.FcFormAPartIII.FcFormAPartIIIBasicDetails;
import com.backend.model.FcFormAPartIII.FcFormAPartIIICheckListDetails;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.ForestClearanceFormAPartIII.FcFormAPartIIIBasicDetailsRepository;
import com.backend.repository.postgres.ForestClearanceFormAPartIII.FcFormAPartIIICheckListRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class FcFormAPartIIIService {

	@Autowired
	private FcFormAPartIIIBasicDetailsRepository fcFormAPartIIIBasicDetailsRepository;

	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;

	@Autowired
	private DepartmentApplicationRepository departmentApplicationRepository;

	@Autowired
	private ApplicationsRepository applicationsRepository;

	@Autowired
	private FcFormAPartIIICheckListRepository fcFormAPartIIICheckListRepository;

	// Save Basic Details
	public ResponseEntity<Object> saveFCformAPartIIIBasicDetails(FcFormAPartIIIBasicDetails fcFormAPartIIIBasicDetails,
			Integer clearenceID, HttpServletRequest request) {
		try {
			FcFormAPartIIIBasicDetails returnBasicDetails;
			returnBasicDetails = fcFormAPartIIIBasicDetailsRepository.save(fcFormAPartIIIBasicDetails);

			ProponentApplications proponentApplications = proponentApplicationRepository
					.getApplicationByProposalId(fcFormAPartIIIBasicDetails.getFc_id());

			if (departmentApplicationRepository
					.getDepartmentApplicationByProposalId(returnBasicDetails.getId()) == null) {
				DepartmentApplication applications = new DepartmentApplication();
				Applications app = applicationsRepository.findById(clearenceID).get();
				applications.setApplications(app);
				applications.setProponentApplications(proponentApplications);
				applications.setProposal_sequence(proponentApplications.getProposal_sequence());
				applications.setProposal_no(proponentApplications.getProposal_no().replaceAll("\\s", ""));
				applications.setProposal_id(returnBasicDetails.getId());
				setStatus(fcFormAPartIIIBasicDetails.getStatus(), applications);
				applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);

				departmentApplicationRepository.save(applications);
			} else {
				DepartmentApplication applications = departmentApplicationRepository
						.getDepartmentApplicationByProposalId(returnBasicDetails.getId());
				setStatus(fcFormAPartIIIBasicDetails.getStatus(), applications);
				departmentApplicationRepository.save(applications);

			}
			return ResponseHandler.generateResponse("Save FC Form A Part III BasicDetails form", HttpStatus.OK, null,
					returnBasicDetails);

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving FC Form A Part III saveFCPartBBasicDetails id- ", e);
		}

	}// end of Save basic details

	// Get Basic Details
	public ResponseEntity<Object> getFCformAPartIIIBasicDetails(Integer fcFormAPartIIIBasicDetailsId) {
		try {
			FcFormAPartIIIBasicDetails basicDetails;
			basicDetails = fcFormAPartIIIBasicDetailsRepository.findByIdActive(fcFormAPartIIIBasicDetailsId);
			basicDetails.setDepartmentApplication(
					departmentApplicationRepository.getDepartmentApplicationByProposalId(basicDetails.getId()));

			return ResponseHandler.generateResponse("Fetched FC Form A Part III BasicDetails form", HttpStatus.OK, null,
					basicDetails);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in fetching FC Form A Part III getFCPartBBasicDetails id- " + fcFormAPartIIIBasicDetailsId,
					e);
		}
	} // end of get basic details

	public ResponseEntity<Object> saveFcFormAPartIIIChecklist(
			FcFormAPartIIICheckListDetails fcFormAPartIIICheckListDetails, Integer fcFormAPartIIIBasicDetailsId,
			HttpServletRequest request) {
		try {
			FcFormAPartIIIBasicDetails fcFormAPartIIIBasicDetails = fcFormAPartIIIBasicDetailsRepository
					.findById(fcFormAPartIIIBasicDetailsId)
					.orElseThrow(() -> new ProjectNotFoundException("FC Form A Part III not found with ID"));

			fcFormAPartIIICheckListDetails.setFcFormAPartIIIBasicDetails(fcFormAPartIIIBasicDetails);
			DepartmentApplication departmentApplication = departmentApplicationRepository
					.getApplicationByProposalId(fcFormAPartIIIBasicDetailsId);
			if (departmentApplication != null) {
				departmentApplication.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
				departmentApplicationRepository.save(departmentApplication);
			}

			return ResponseHandler.generateResponse("Save FC Form A part III Checklist", HttpStatus.OK, null,
					fcFormAPartIIICheckListRepository.save(fcFormAPartIIICheckListDetails));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Saving FC A part III checklist with id- " + fcFormAPartIIIBasicDetailsId, e);
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
