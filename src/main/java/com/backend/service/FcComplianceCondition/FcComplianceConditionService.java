package com.backend.service.FcComplianceCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.FcComplianceCondition.FcComplianceCondition;
import com.backend.repository.postgres.FcComplianceCondition.FcComplianceConditionRepository;
import com.backend.response.ResponseHandler;

@Service
public class FcComplianceConditionService {
	
	@Autowired
	FcComplianceConditionRepository fcComplianceConditionRepository;
	
	public ResponseEntity<Object> save(FcComplianceCondition fcComplianceCondition,UserPrincipal principal){
		FcComplianceCondition fcCC = new FcComplianceCondition();
		try {
//			Integer fc_id = fcComplianceCondition.getApplication_id();
//			FcComplianceCondition complianceCondition = fcComplianceConditionRepository.findByFcId(fc_id);
//			if(complianceCondition != null) {
//				fcComplianceCondition.setId(complianceCondition.getId());
//				fcCC = fcComplianceConditionRepository.save(fcComplianceCondition);
//			}
//			else {
//				fcCC = fcComplianceConditionRepository.save(fcComplianceCondition);
//			}
			fcComplianceCondition.setRole_id(fcComplianceConditionRepository.getUserRole(principal.getId()));
			fcComplianceCondition.setRole_name(fcComplianceConditionRepository.getUserRoleName(principal.getId()));
			fcCC = fcComplianceConditionRepository.save(fcComplianceCondition);
		}
		catch (Exception e) {
			throw new PariveshException("Can't save FcComplianceCondition due to -->>>>>> " + e);
		}

		return ResponseHandler.generateResponse("Data received", HttpStatus.OK, "no error", fcCC);
	}
	
	public ResponseEntity<Object> get(Integer fc_id){
		List<FcComplianceCondition> fcCC = new ArrayList<>();
		try {
			fcCC = fcComplianceConditionRepository.findByFcId(fc_id);
		}
		catch(Exception e) {

			throw new PariveshException("Can't get FcComplianceCondition due to -->>>>>> " + e);
		}
		return ResponseHandler.generateResponse("Data received", HttpStatus.OK, "no error", fcCC);
	}

	public ResponseEntity<Object> getById(Integer id) {
		Optional<FcComplianceCondition> fcCC = null;
		try {
			fcCC = Optional.ofNullable(fcComplianceConditionRepository.findById(id).orElseThrow(()->new PariveshException("Id not found")));
					}
		catch(Exception e) {

			throw new PariveshException("Can't get FcComplianceCondition due to -->>>>>> " + e);
		}
		return ResponseHandler.generateResponse("Data received", HttpStatus.OK, "no error", fcCC);
	}
}
