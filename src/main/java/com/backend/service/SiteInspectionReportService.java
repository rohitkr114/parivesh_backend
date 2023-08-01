package com.backend.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.model.Applications;
import com.backend.model.DepartmentApplication;
import com.backend.model.ProponentApplications;
import com.backend.model.SiteInspectionReport;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.SiteInspectionReportRepository;
import com.backend.response.ResponseHandler;

@Service
public class SiteInspectionReportService {

	@Autowired
	SiteInspectionReportRepository siteInspectionReportRepository;

	@Autowired
	private DepartmentApplicationRepository departmentApplicationRepository;

	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;

	@Autowired
	private ApplicationsRepository applicationsRepository;

	public ResponseEntity<Object> saveDetails(SiteInspectionReport siteInspectionReport, HttpServletRequest request) {
		try {
			SiteInspectionReport siteInspectionReport2 = siteInspectionReportRepository.save(siteInspectionReport);
			Applications app = applicationsRepository.findById(10).get();
			if (app != null) {
				ProponentApplications proponentApplications = proponentApplicationRepository
						.findById(siteInspectionReport.getProponent_application_id()).orElseThrow();
				DepartmentApplication departmentApplication = new DepartmentApplication();
				departmentApplication.setStatus(Caf_Status.SUBMITTED.toString());
				departmentApplication.setApplications(app);
				departmentApplication.setProposal_no(proponentApplications.getProposal_no());
				departmentApplication.setProponentApplications(proponentApplications);
				departmentApplication.setProposal_sequence(proponentApplications.getProposal_sequence());
				departmentApplication.setProposal_id(siteInspectionReport2.getId());
				departmentApplication.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
				departmentApplicationRepository.save(departmentApplication);
			} else {
				ProponentApplications proponentApplications = proponentApplicationRepository
						.findById(siteInspectionReport.getProponent_application_id()).orElseThrow();
				DepartmentApplication departmentApplication = new DepartmentApplication();
				departmentApplication.setStatus(Caf_Status.SUBMITTED.toString());
				departmentApplication.setProposal_no(proponentApplications.getProposal_no());
				departmentApplication.setProponentApplications(proponentApplications);
				departmentApplication.setProposal_sequence(proponentApplications.getProposal_sequence());
				departmentApplication.setProposal_id(siteInspectionReport2.getId());
				departmentApplication.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
				departmentApplicationRepository.save(departmentApplication);
			}
			return ResponseHandler.generateResponse("SiteInspectionReport saveDetails", HttpStatus.OK, null,
					siteInspectionReport2);
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("SiteInspectionReport saveDetails", HttpStatus.BAD_REQUEST, null,
					ex.getMessage());
		}
	}

	public ResponseEntity<Object> getDetails(Integer id) {
		try {
			SiteInspectionReport siteInspectionReport = siteInspectionReportRepository.findById(id).orElseThrow();
			return ResponseHandler.generateResponse("SiteInspectionReport getDetails", HttpStatus.OK, null,
					siteInspectionReport);
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("SiteInspectionReport getDetails", HttpStatus.BAD_REQUEST, null,
					ex.getMessage());
		}
	}
}
