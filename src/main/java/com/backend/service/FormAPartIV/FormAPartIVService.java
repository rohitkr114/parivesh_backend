package com.backend.service.FormAPartIV;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.model.Applications;
import com.backend.model.DepartmentApplication;
import com.backend.model.ProponentApplications;
import com.backend.model.FcFormBPartIV.FcFormBPartIVBasicDetails;
import com.backend.model.FormAPartIV.FormAPartIVBasicDetails;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.FormAPartIV.FormAPartIVBasicDetailsRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FormAPartIVService {

	@Autowired
	private FormAPartIVBasicDetailsRepository formAPartIVBasicDetailsRepository;

	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;

	@Autowired
	private DepartmentApplicationRepository departmentApplicationRepository;

	@Autowired
	private ApplicationsRepository applicationsRepository;

	public ResponseEntity<Object> saveFCformAPartIVBasicDetails(FormAPartIVBasicDetails formAPartIVBasicDetails,
			Integer clearenceID, HttpServletRequest request) {
		try {
			FormAPartIVBasicDetails returnBasicDetails;
			returnBasicDetails = formAPartIVBasicDetailsRepository.save(formAPartIVBasicDetails);

			ProponentApplications proponentApplications = proponentApplicationRepository
					.getApplicationByProposalId(formAPartIVBasicDetails.getFc_id());

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
			throw new PariveshException("Error in Saving FC Form A Part IV saveFCPartABasicDetails id- ", e);
		}

	}// end of Save basic details

// Get Basic Details
	public ResponseEntity<Object> getFCformAPartIVBasicDetails(Integer fcFormAPartIVBasicDetailsId) {
		try {
			FormAPartIVBasicDetails basicDetails;
			basicDetails = formAPartIVBasicDetailsRepository.findByIdActive(fcFormAPartIVBasicDetailsId);
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
