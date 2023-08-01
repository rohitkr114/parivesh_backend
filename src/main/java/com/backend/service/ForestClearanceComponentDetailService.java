package com.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.model.ForestClearance;
import com.backend.model.ForestClearanceComponentDetail;
import com.backend.model.ForestClearanceProposedDiversions;
import com.backend.repository.postgres.ForestClearanceComponentDetailRepository;
import com.backend.repository.postgres.ForestClearanceRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ForestClearanceComponentDetailService {
	
	@Autowired
	ForestClearanceComponentDetailRepository forestClearanceComponentDetailRepository;
	
	@Autowired
	ForestClearanceRepository forestClearanceRepository;
	
	@Autowired
	 UserService userService;

	public ForestClearanceComponentDetail saveForestComponentDetails(Integer id,
			ForestClearanceComponentDetail forestClearanceComponentDetail) {
		
		ForestClearance temp = forestClearanceRepository.findById(id).get();
		
		forestClearanceComponentDetail.setForestClearance(temp);
		
		log.info("INFO ------------ saveForestComponentDetails forest clearance id ----> "+id+" ----SAVING - SUCCESS");
		ForestClearanceComponentDetail forestDetails = forestClearanceComponentDetailRepository.save(forestClearanceComponentDetail);
				
		return forestDetails;
		

	}

	public ResponseEntity<Object> getForestClearanceComponentData(int id) {
		log.info("INFO ------------ getForestClearanceComponentData forest clearance id ----> "+id+" ----RETRIEVING - SUCCESS");
		return ResponseHandler.generateResponse("get forest Component data list by fc_id", HttpStatus.OK, "",
				forestClearanceComponentDetailRepository.findDetailByFcId(id));
		

	}
	
	public ResponseEntity<Object> deleteForestClearanceComponentData(int id) {
        
		ForestClearanceComponentDetail forestClearanceComponentDetail = forestClearanceComponentDetailRepository.findById(id).get();
		forestClearanceComponentDetail.setDelete(true);
		forestClearanceComponentDetailRepository.save(forestClearanceComponentDetail);
		log.info("INFO ------------ deleteForestClearanceComponentData forest clearance id ----> "+id+" ----DELETING - SUCCESS");
		return ResponseHandler.generateResponse("delete forest Component data", HttpStatus.OK, "",
				"deleted successfully");
		

	}
	
}
