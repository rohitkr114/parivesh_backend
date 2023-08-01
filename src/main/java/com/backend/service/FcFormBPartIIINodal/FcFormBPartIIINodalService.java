package com.backend.service.FcFormBPartIIINodal;

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
import com.backend.model.FcFormBPartIIINodal.FcFormBPartIIINodalBasicDetails;
import com.backend.model.FcFormBPartIIINodal.FcFormBPartIIINodalCheckListDetails;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.FcFormBPartIIINodal.FcFormBPartIIINodalBasicDetailsRepository;
import com.backend.repository.postgres.FcFormBPartIIINodal.FcFormBPartIIINodalCheckListRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class FcFormBPartIIINodalService {

	@Autowired
	private FcFormBPartIIINodalBasicDetailsRepository fcFormBPartIIIBasicDetailsRepository;

	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;

	@Autowired
	private DepartmentApplicationRepository departmentApplicationRepository;

	@Autowired
	private ApplicationsRepository applicationsRepository;

	@Autowired
	private FcFormBPartIIINodalCheckListRepository fcFormAPartIIICheckListRepository;

	// Save Basic Details
	public ResponseEntity<Object> savefcFormBPartIIIBasicDetails(FcFormBPartIIINodalBasicDetails fcFormBPartIIIBasicDetails,
			Integer clearenceID, HttpServletRequest request) {
		try {
			FcFormBPartIIINodalBasicDetails returnBasicDetails;
			returnBasicDetails = fcFormBPartIIIBasicDetailsRepository.save(fcFormBPartIIIBasicDetails);

			ProponentApplications proponentApplications = proponentApplicationRepository
					.getApplicationByProposalId(fcFormBPartIIIBasicDetails.getFc_id());

			if (departmentApplicationRepository
					.getDepartmentApplicationByProposalId(returnBasicDetails.getId()) == null) {
				DepartmentApplication applications = new DepartmentApplication();
				Applications app = applicationsRepository.findById(clearenceID).get();
				applications.setApplications(app);
				applications.setProponentApplications(proponentApplications);
				applications.setProposal_sequence(proponentApplications.getProposal_sequence());
				applications.setProposal_no(proponentApplications.getProposal_no().replaceAll("\\s", ""));
				applications.setProposal_id(returnBasicDetails.getId());
				setStatus(fcFormBPartIIIBasicDetails.getStatus(), applications);
				applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);

				departmentApplicationRepository.save(applications);
			} else {
				DepartmentApplication applications = departmentApplicationRepository
						.getDepartmentApplicationByProposalId(returnBasicDetails.getId());
				setStatus(fcFormBPartIIIBasicDetails.getStatus(), applications);
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
	public ResponseEntity<Object> getfcFormBPartIIIBasicDetails(Integer fcFormBPartIIIBasicDetailsId) {
		try {
			FcFormBPartIIINodalBasicDetails basicDetails;
			basicDetails = fcFormBPartIIIBasicDetailsRepository.findByIdActive(fcFormBPartIIIBasicDetailsId);
			basicDetails.setDepartmentApplication(
					departmentApplicationRepository.getDepartmentApplicationByProposalId(basicDetails.getId()));

			return ResponseHandler.generateResponse("Fetched FC Form A Part III BasicDetails form", HttpStatus.OK, null,
					basicDetails);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in fetching FC Form A Part III getFCPartBBasicDetails id- " + fcFormBPartIIIBasicDetailsId,
					e);
		}
	} // end of get basic details

	public ResponseEntity<Object> saveFcFormAPartIIIChecklist(
			FcFormBPartIIINodalCheckListDetails fcFormBPartIIICheckListDetails, Integer fcFormBPartIIIBasicDetailsId,
			HttpServletRequest request) {
		try {
			FcFormBPartIIINodalBasicDetails fcFormBPartIIIBasicDetails = fcFormBPartIIIBasicDetailsRepository
					.findById(fcFormBPartIIIBasicDetailsId)
					.orElseThrow(() -> new ProjectNotFoundException("FC Form A Part III not found with ID"));

			fcFormBPartIIICheckListDetails.setFcFormBPartIIIBasicDetails(fcFormBPartIIIBasicDetails);
			DepartmentApplication departmentApplication = departmentApplicationRepository
					.getApplicationByProposalId(fcFormBPartIIIBasicDetailsId);
			if (departmentApplication != null) {
				departmentApplication.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
				departmentApplicationRepository.save(departmentApplication);
			}

			return ResponseHandler.generateResponse("Save FC Form A part III Checklist", HttpStatus.OK, null,
					fcFormAPartIIICheckListRepository.save(fcFormBPartIIICheckListDetails));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Saving FC A part III checklist with id- " + fcFormBPartIIIBasicDetailsId, e);
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
