package com.backend.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.dto.EDS_Details_Dto;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.DepartmentApplication;
import com.backend.model.EDS;
import com.backend.model.EDS_Details;
import com.backend.model.ProponentApplications;
import com.backend.model.Agenda.AgendaRemarks;
import com.backend.model.Withdrawal.WithdrawalRemarks;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.EDSDetailsRepository;
//import com.backend.repository.postgres.EDS_Details_Dto_Repo;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.Agenda.AgendaRemarksRepository;
import com.backend.repository.postgres.Withdrawal.WithdrawalRespository;
import com.backend.response.ResponseHandler;

@Service
public class EDSDetailsService {

	@Autowired
	private EDSDetailsRepository edsDetailsRepository;
	
	@Autowired
	private DepartmentApplicationRepository departmentApplicationRepository;
	
	@Autowired 
	private ProponentApplicationRepository proponentApplicationRepository;
	
	@Autowired
	private ApplicationsRepository applicationsRepository;
	
	@Autowired
	private AgendaRemarksRepository agendaRemarksRepository;
	
	@Autowired
	private WithdrawalRespository withdrawalRespository;
	
//	@Autowired
//	private EDS_Details_Dto_Repo eds_Details_Dto_Repo;
	
	public ResponseEntity<Object> saveEDSDetails(EDS_Details eds,HttpServletRequest request) {
		try {
			EDS_Details eds_Details = edsDetailsRepository.save(eds);
			Applications app = applicationsRepository.findById(10).get();
			if(app!=null) {
				ProponentApplications proponentApplications = proponentApplicationRepository.findById(eds.getProponent_application_id()).orElseThrow();
				DepartmentApplication departmentApplication = new DepartmentApplication();
				departmentApplication.setStatus(Caf_Status.SUBMITTED.toString());
				departmentApplication.setApplications(app);
				departmentApplication.setProposal_no(proponentApplications.getProposal_no());
				departmentApplication.setProponentApplications(proponentApplications);
				departmentApplication.setProposal_sequence(proponentApplications.getProposal_sequence());
				departmentApplication.setProposal_id(eds_Details.getId());
				departmentApplication.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
				departmentApplicationRepository.save(departmentApplication);
			}
			else {
				ProponentApplications proponentApplications = proponentApplicationRepository.findById(eds.getProponent_application_id()).orElseThrow();
				DepartmentApplication departmentApplication = new DepartmentApplication();
				departmentApplication.setStatus(Caf_Status.SUBMITTED.toString());
				departmentApplication.setProposal_no(proponentApplications.getProposal_no());
				departmentApplication.setProponentApplications(proponentApplications);
				departmentApplication.setProposal_sequence(proponentApplications.getProposal_sequence());
				departmentApplication.setProposal_id(eds_Details.getId());
				departmentApplication.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
				departmentApplicationRepository.save(departmentApplication);
			}
			return ResponseHandler.generateResponse("saveEDSDetails", HttpStatus.OK, null,
					eds_Details);
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("saveEDSDetails", HttpStatus.BAD_REQUEST,
					null, ex.getMessage());
		}
	}
	
	public ResponseEntity<Object> saveAgendaRemarks(AgendaRemarks agendaRemarks,Integer clearanceId,HttpServletRequest request) {
		try {
			//EDS_Details eds_Details = edsDetailsRepository.save(eds);
			AgendaRemarks agendaRemarks2=agendaRemarksRepository.save(agendaRemarks);
			
			/*
			Applications app = applicationsRepository.findById(10).get();
			if(app!=null) {
				
				
				ProponentApplications proponentApplications = proponentApplicationRepository.findById(eds.getProponent_application_id()).orElseThrow();
				
				DepartmentApplication departmentApplication = new DepartmentApplication();
				departmentApplication.setStatus(Caf_Status.SUBMITTED.toString());
				departmentApplication.setApplications(app);
				departmentApplication.setProposal_no(proponentApplications.getProposal_no());
				departmentApplication.setProponentApplications(proponentApplications);
				departmentApplication.setProposal_sequence(proponentApplications.getProposal_sequence());
				departmentApplication.setProposal_id(eds_Details.getId());
				departmentApplication.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
				departmentApplicationRepository.save(departmentApplication);
			}
			else {
				ProponentApplications proponentApplications = proponentApplicationRepository.findById(eds.getProponent_application_id()).orElseThrow();
				DepartmentApplication departmentApplication = new DepartmentApplication();
				departmentApplication.setStatus(Caf_Status.SUBMITTED.toString());
				departmentApplication.setProposal_no(proponentApplications.getProposal_no());
				departmentApplication.setProponentApplications(proponentApplications);
				departmentApplication.setProposal_sequence(proponentApplications.getProposal_sequence());
				departmentApplication.setProposal_id(eds_Details.getId());
				departmentApplication.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
				departmentApplicationRepository.save(departmentApplication);
			}*/
			return ResponseHandler.generateResponse("saveEDSDetails", HttpStatus.OK, null,
					agendaRemarks2);
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("saveEDSDetails", HttpStatus.BAD_REQUEST,
					null, ex.getMessage());
		}
	}
	
	public ResponseEntity<Object> saveWithdrawalRemarks(WithdrawalRemarks withdrawalRemarks,Integer clearanceId,HttpServletRequest request) {
		try {
			
			WithdrawalRemarks withdrawalRemarks2=withdrawalRespository.save(withdrawalRemarks);
			
			/*
			Applications app = applicationsRepository.findById(10).get();
			if(app!=null) {
				
				
				ProponentApplications proponentApplications = proponentApplicationRepository.findById(eds.getProponent_application_id()).orElseThrow();
				
				DepartmentApplication departmentApplication = new DepartmentApplication();
				departmentApplication.setStatus(Caf_Status.SUBMITTED.toString());
				departmentApplication.setApplications(app);
				departmentApplication.setProposal_no(proponentApplications.getProposal_no());
				departmentApplication.setProponentApplications(proponentApplications);
				departmentApplication.setProposal_sequence(proponentApplications.getProposal_sequence());
				departmentApplication.setProposal_id(eds_Details.getId());
				departmentApplication.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
				departmentApplicationRepository.save(departmentApplication);
			}
			else {
				ProponentApplications proponentApplications = proponentApplicationRepository.findById(eds.getProponent_application_id()).orElseThrow();
				DepartmentApplication departmentApplication = new DepartmentApplication();
				departmentApplication.setStatus(Caf_Status.SUBMITTED.toString());
				departmentApplication.setProposal_no(proponentApplications.getProposal_no());
				departmentApplication.setProponentApplications(proponentApplications);
				departmentApplication.setProposal_sequence(proponentApplications.getProposal_sequence());
				departmentApplication.setProposal_id(eds_Details.getId());
				departmentApplication.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
				departmentApplicationRepository.save(departmentApplication);
			}*/
			return ResponseHandler.generateResponse("saveWithdrawalRemarks", HttpStatus.OK, null,
					withdrawalRemarks2);
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("saveWithdrawalRemarks", HttpStatus.BAD_REQUEST,
					null, ex.getMessage());
		}
	}
	
	public ResponseEntity<Object> getWithdrawalRemarks(Integer WithdrawalId,HttpServletRequest request) {
		try {
			
			WithdrawalRemarks withdrawalRemarks2=withdrawalRespository.findById(WithdrawalId).
					orElseThrow(()->new ProjectNotFoundException("Withdrawal Not Found"));
			
			return ResponseHandler.generateResponse("getWithdrawalRemarks", HttpStatus.OK, null,
					withdrawalRemarks2);
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("getWithdrawalRemarks", HttpStatus.BAD_REQUEST,
					null, ex.getMessage());
		}
	}
	public ResponseEntity<Object>getEDSDetails(Integer id) {
		try {
			EDS_Details eds_Details = edsDetailsRepository.findById(id).orElseThrow();
				return ResponseHandler.generateResponse("getEDSDetails", HttpStatus.OK,
						null, eds_Details);
			}
		catch(Exception ex) {
			return ResponseHandler.generateResponse("getEDSDetails", HttpStatus.BAD_REQUEST,
					null, ex.getMessage());
		}
	}
		
	
}
