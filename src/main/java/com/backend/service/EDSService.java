package com.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.EDS;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.EDSRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.response.ResponseHandler;

@Service
public class EDSService {

	@Autowired
	EDSRepository edsRepository;
	
	@Autowired
	ProponentApplicationRepository proponentApplicationRepository;

	public ResponseEntity<Object> addEDS(EDS eds) {
		try {
			if(eds.getProposal_no()==null) {
				ProponentApplications proponentApplications=proponentApplicationRepository.getApplicationByProposalId(eds.getProposal_id());
				eds.setProposal_no(proponentApplications.getProposal_no());
			}
			return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
					edsRepository.save(eds));
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.BAD_REQUEST,
					null, ex.getMessage());
		}
	}
	
	public ResponseEntity<Object> getEDS(Integer EDSId){
		try {
			EDS eds = edsRepository.findById(EDSId)
					.orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));
			
			return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
					eds);
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.BAD_REQUEST,
					null, ex.getMessage());
		}
	}

}
