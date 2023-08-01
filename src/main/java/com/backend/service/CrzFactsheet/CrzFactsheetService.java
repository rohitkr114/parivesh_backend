package com.backend.service.CrzFactsheet;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.DepartmentApplication;
import com.backend.model.ProponentApplications;
import com.backend.model.CrzFactsheet.CrzFactsheet;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.CrzFactsheet.CrzFactsheetRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CrzFactsheetService {

	@Autowired
	CrzFactsheetRepository crzFactsheetRepository;
	
	@Autowired
	DepartmentApplicationRepository departmentApplicationRepository;
	
	@Autowired
	ProponentApplicationRepository proponentApplicationRepository; 
	
	@Autowired
	ApplicationsRepository applicationsRepository;
	
	public ResponseEntity<Object> saveFactsheet(CrzFactsheet crzFactsheet, Integer clearance_id){
		
		try {
			
			log.info(crzFactsheet.toString());
			CrzFactsheet crzFactsheetRet = crzFactsheetRepository.save(crzFactsheet);

			ProponentApplications proponentApplications = proponentApplicationRepository
					.getApplicationByProposalId(crzFactsheetRet.getCrz_id());

			//log.info(crzFactsheetRet.getCrz_id().toString());
			//log.info(proponentApplications.toString());
			if (departmentApplicationRepository.getDepartmentApplicationByProposalId(crzFactsheetRet.getCrz_id()) == null) {
				DepartmentApplication applications = new DepartmentApplication();
				Applications app = applicationsRepository.findById(clearance_id).get();
				applications.setApplications(app);
				applications.setProponentApplications(proponentApplications);
				applications.setProposal_sequence(proponentApplications.getProposal_sequence());
				applications.setProposal_no(proponentApplications.getProposal_no().replaceAll("\\s", ""));
				applications.setProposal_id(crzFactsheetRet.getCrz_id());
				applications.setStatus(Caf_Status.DRAFT.toString());
//				applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);

				departmentApplicationRepository.save(applications);
				
			}

			return ResponseHandler.generateResponse("Crz Factsheet Saved", HttpStatus.OK, "", crzFactsheetRet);			
		}
		catch(Exception e) {
			
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving Crz Factsheet - ", e);
		}
		
	}
	
	public ResponseEntity<Object> getFactsheet(Integer id){
		
		try {
			
			CrzFactsheet crzFactsheetRet = crzFactsheetRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("EC Factsheet not found with id " + id));
			crzFactsheetRet.setDepartmentApplications(
					departmentApplicationRepository.getApplicationByProposalId(crzFactsheetRet.getCrz_id()));
			
			return ResponseHandler.generateResponse("Getting Crz Factsheet", HttpStatus.OK, "", crzFactsheetRet);
		}
		catch(Exception e){
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Getting Crz factsheet for Id- " + id, e);
		
		}
		
	}
	
}
