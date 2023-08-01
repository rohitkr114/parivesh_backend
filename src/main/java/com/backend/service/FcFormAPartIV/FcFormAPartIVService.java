package com.backend.service.FcFormAPartIV;

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
import com.backend.model.FcFormAPartIV.FcFormAPartIVBasicDetails;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.ForestClearanceFormAPartIV.FcFormAPartIVBasicDetailsRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class FcFormAPartIVService {
	@Autowired
	private FcFormAPartIVBasicDetailsRepository fcFormAPartIVBasicDetailsRepository;

	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;

	@Autowired
	private DepartmentApplicationRepository departmentApplicationRepository;

	@Autowired
	private ApplicationsRepository applicationsRepository;

	// Save Basic Details
	public ResponseEntity<Object> saveFCformAPartIVBasicDetails(FcFormAPartIVBasicDetails fcFormAPartIVBasicDetails,
			Integer clearenceID, HttpServletRequest request) {
		try {
			FcFormAPartIVBasicDetails returnBasicDetails;
			returnBasicDetails = fcFormAPartIVBasicDetailsRepository.save(fcFormAPartIVBasicDetails);

			ProponentApplications proponentApplications = proponentApplicationRepository
					.getApplicationByProposalId(fcFormAPartIVBasicDetails.getFc_id());

			if (departmentApplicationRepository
					.getDepartmentApplicationByProposalId(returnBasicDetails.getId()) == null) {
				DepartmentApplication applications = new DepartmentApplication();
				Applications app = applicationsRepository.findById(clearenceID).get();
				applications.setApplications(app);
				applications.setProponentApplications(proponentApplications);
				applications.setProposal_sequence(proponentApplications.getProposal_sequence());
				applications.setProposal_no(proponentApplications.getProposal_no().replaceAll("\\s", ""));
				applications.setProposal_id(returnBasicDetails.getId());
				applications.setStatus(Caf_Status.SUBMITTED.toString());
				applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);

				departmentApplicationRepository.save(applications);
			}
			return ResponseHandler.generateResponse("Save FC Form A Part IV BasicDetails form", HttpStatus.OK, null,
					returnBasicDetails);

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving FC Form A Part IV saveFCPartBBasicDetails id- ", e);
		}

	}// end of Save basic details

	// Get Basic Details
	public ResponseEntity<Object> getFCformAPartIVBasicDetails(Integer fcFormAPartIVBasicDetailsId) {
		try {
			FcFormAPartIVBasicDetails basicDetails;
			basicDetails = fcFormAPartIVBasicDetailsRepository.findByIdActive(fcFormAPartIVBasicDetailsId);
			basicDetails.setDepartmentApplication(
					departmentApplicationRepository.getDepartmentApplicationByProposalId(basicDetails.getId()));

			return ResponseHandler.generateResponse("Fetched FC Form A Part IV BasicDetails form", HttpStatus.OK, null,
					basicDetails);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in fetching FC Form A Part IV getFCPartBBasicDetails id- " + fcFormAPartIVBasicDetailsId, e);
		}
	} // end of get basic details
}
