package com.backend.service.FcFormBPartIII;

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
import com.backend.model.ProponentApplications;
import com.backend.model.FcFormBPartIII.FcFormBPartIIICheckListDetails;
import com.backend.model.FcFormBPartIII.FcFormBPartIIIProjectDetails;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.FcFormBPartIII.FcFormBPartIIICheckListDetailsRepository;
import com.backend.repository.postgres.FcFormBPartIII.FcFormBPartIIIProjectDetailsRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FcFormBPartIIIService {
	
	@Autowired
	private FcFormBPartIIICheckListDetailsRepository fcFormBPartIIICheckListDetailsRepository;
	
	@Autowired
	private FcFormBPartIIIProjectDetailsRepository fcFormBPartIIIProjectDetailsRepository;
	
	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;
	
	@Autowired
	private DepartmentApplicationRepository departmentApplicationRepository;
	
	@Autowired
	private ApplicationsRepository applicationsRepository;
	
	public ResponseEntity<Object> saveCheckListDetails(FcFormBPartIIICheckListDetails checkListDetails, Integer clearanceId,HttpServletRequest request){
		try {
			if(checkListDetails.getId()!=0 && checkListDetails.getId()!=null) {
				FcFormBPartIIICheckListDetails temp= fcFormBPartIIICheckListDetailsRepository.findById(checkListDetails.getId())
						.orElseThrow(()->new ProjectNotFoundException("checklist details Not Found for id:"+checkListDetails.getId()));
				checkListDetails.setId(temp.getId());
			}
			
			FcFormBPartIIICheckListDetails checkList=fcFormBPartIIICheckListDetailsRepository.save(checkListDetails);
			
			ProponentApplications proponentApplications= proponentApplicationRepository.getApplicationByProposalId(checkListDetails.getFc_id());
			
			if (departmentApplicationRepository.getDepartmentApplicationByProposalId(checkList.getId()) == null) {
				DepartmentApplication applications = new DepartmentApplication();
				Applications app = applicationsRepository.findById(clearanceId).get();
				applications.setApplications(app);
				applications.setProponentApplications(proponentApplications);
				applications.setProposal_sequence(proponentApplications.getProposal_sequence());
				applications.setProposal_no(proponentApplications.getProposal_no());
				applications.setProposal_id(checkList.getId());
				applications.setStatus(Caf_Status.DRAFT.toString());
				applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);

				departmentApplicationRepository.save(applications);
			}
			
			return ResponseHandler.generateResponse("saving checklist details", HttpStatus.OK, "", checkList);
		}catch(Exception e){
			log.error("Encountered exception",e);
			throw new PariveshException("errror in saving checklist details",e);
		}
	}
	
	public ResponseEntity<Object> getForm(Integer checkListId){
		try {
			FcFormBPartIIICheckListDetails response= fcFormBPartIIICheckListDetailsRepository.findById(checkListId)
					.orElseThrow(()->new ProjectNotFoundException("checklist details Not Found for id:"+checkListId));
			
			response.setDepartmentApplication(departmentApplicationRepository.getDepartmentApplicationByProposalId(checkListId));
			
			return ResponseHandler.generateResponse("getting form", HttpStatus.OK, "", response);			
		}catch(Exception e){
			log.error("Encountered exception",e);
			throw new PariveshException("errror in getting checklist details",e);
		}
	}
	
	public ResponseEntity<Object> saveProjectDetails(FcFormBPartIIIProjectDetails projectDetails, Integer checkListDetailsId,HttpServletRequest request){
		try {
			if(projectDetails.getId()==0 || projectDetails.getId()==null) {
				FcFormBPartIIIProjectDetails temp= fcFormBPartIIIProjectDetailsRepository.getProjectDetailsByCheckListId(checkListDetailsId);
				if(temp!=null) {
					projectDetails.setId(temp.getId());
				}
			}
			
			FcFormBPartIIICheckListDetails checkListDetails= fcFormBPartIIICheckListDetailsRepository.getDetailsById(checkListDetailsId);
			projectDetails.setFormBPartIIIChecklistDetails(checkListDetails);
			
			DepartmentApplication departmentApplication = departmentApplicationRepository
					.getDepartmentApplicationByProposalId(checkListDetailsId);
			if (departmentApplication != null) {
				departmentApplication.setStatus(Caf_Status.SUBMITTED.toString());
				departmentApplication.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
				departmentApplicationRepository.save(departmentApplication);
			}
			
			return ResponseHandler.generateResponse("saving project Details", HttpStatus.OK, "",
					fcFormBPartIIIProjectDetailsRepository.save(projectDetails));
			
			
		}catch(Exception e){
			log.error("Encountered exception",e);
			throw new PariveshException("errror in saving project details",e);
		}
	}
}
