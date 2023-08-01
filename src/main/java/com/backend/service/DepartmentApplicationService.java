package com.backend.service;

import java.util.List;

import com.backend.dto.UserDashboardCountDto;
import com.backend.dto.ViewProposalCountDto;
import com.backend.dto.ViewProposalsDto;
import com.backend.exceptions.PariveshException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.dto.UserPrincipal;
import com.backend.model.DepartmentApplication;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.response.ResponseHandler;
import com.backend.security.CurrentUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DepartmentApplicationService {
	
	
	@Autowired
	DepartmentApplicationRepository departmentApplicationRepository;
	
	public ResponseEntity<Object> getDepartmentApplication(String proposalNo,@CurrentUser UserPrincipal user){
		try {
			log.info("INFO ------------ getDepartmentApplication RETRIEVED - SUCCESS");
			List<DepartmentApplication> temp=departmentApplicationRepository.findByLoggedInUser(proposalNo,user.getId());
			if(temp==null)
				return ResponseHandler.generateResponse("Department Application",HttpStatus.OK,"",null);
			else
				return ResponseHandler.generateResponse("Department Application",HttpStatus.OK,"",temp);
		}
		catch(Exception ex) {
			log.info("ERROR ------------ BAD_REQUEST: getDepartmentApplication NOT RETRIEVED - FAILURE");
			return ResponseHandler.generateResponse("Department Application",HttpStatus.BAD_REQUEST,"Exception Occurred",ex.getMessage());	
		}
	}

	public ResponseEntity<Object> viewProposalList(UserPrincipal userPrincipal,String authority){
		try {
			log.info("getting proposal list for user:"+userPrincipal.getId());
			List<ViewProposalsDto> response= departmentApplicationRepository.getProposalList(userPrincipal.getId(),authority);

			return ResponseHandler.generateResponse("getting proposal list",HttpStatus.OK,"",response);

		}catch (Exception e){
			log.error("encountered exception",e);
			throw new PariveshException("error in getting proposal list",e);
		}
	}

	public ResponseEntity<Object> getUserDashboardCount(UserPrincipal userPrincipal){
		try {
			List<UserDashboardCountDto> response= departmentApplicationRepository.getUserDashboardCount(userPrincipal.getId());

			return ResponseHandler.generateResponse("getting user dashboard count",HttpStatus.OK,"",response);
		}catch (Exception e){
			log.error("encountered exception",e);
			throw new PariveshException("error in getting count",e);
		}
	}

	public ResponseEntity<Object> getViewProposalCountByAuthority(UserPrincipal userPrincipal){
		try {
			List<ViewProposalCountDto> response= departmentApplicationRepository.getViewProposalCountByAuthority(userPrincipal.getId());
			return ResponseHandler.generateResponse("getting count",HttpStatus.OK,"",response);
		}catch (Exception e){
			log.error("encountered exception",e);
			throw new PariveshException("error in getting count",e);
		}
	}

}
