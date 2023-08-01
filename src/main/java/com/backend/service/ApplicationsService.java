package com.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.backend.constant.AppConstant;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApplicationsService {

	@Autowired
	ApplicationsRepository applicationsRepository;

	public ResponseEntity<Object> getApplicationData(AppConstant.Form_for form_for, String department_for) {

		if (form_for == null) {
			if (department_for == null) {
				return ResponseHandler.generateResponse("application List", HttpStatus.OK, "",
						applicationsRepository.findAllApplications());
			} else {
				return ResponseHandler.generateResponse("application List", HttpStatus.OK, "",
						applicationsRepository.findAllApplicationsByStatus(department_for));
			}
		} else {
			if (department_for == null) {
				return ResponseHandler.generateResponse("application List", HttpStatus.OK, "",
						applicationsRepository.findAllApplicationsbyform_for(form_for));
			} else {
				return ResponseHandler.generateResponse("application List", HttpStatus.OK, "",
						applicationsRepository.findAllApplicationsbyform_forByStatus(form_for, department_for));
			}
		}
	}

	public ResponseEntity<Object> getApplicationById(Integer Id) {

		try {
			return ResponseHandler.generateResponse("Application By Id", HttpStatus.OK, "", applicationsRepository
					.findById(Id).orElseThrow(() -> new ProjectNotFoundException("user not found with id")));
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("Application By Id", HttpStatus.EXPECTATION_FAILED, "",
					ex.getMessage());
		}
	}

	public ResponseEntity<Object> saveApplication(Applications applications) {
		try {
			if (applications.getId() != null) {
				
				return ResponseHandler.generateResponse("saving application", HttpStatus.OK, null,
						applicationsRepository.save(applications));
			}
			else {
				return ResponseHandler.generateResponse("saving application", HttpStatus.OK, null,
						"Id is null");
			}
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving application ", e);
		}

	}

	public ResponseEntity<Object> deleteApplication(Integer id) {
		try {
			Applications temp = applicationsRepository.findById(id)
					.orElseThrow(() -> new ProjectNotFoundException("Application Not Found"));
			temp.setIs_deleted(true);

			return ResponseHandler.generateResponse("deleting application", HttpStatus.OK, null,
					applicationsRepository.save(temp));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in deleting application ", e);
		}
	}



}
