package com.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.model.ForestClearanceMaps;
import com.backend.model.SubDistrict;
import com.backend.model.Visitor;
import com.backend.repository.postgres.VisitorRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VisitorService {

	@Autowired
	VisitorRepository visitorRepository;

	public ResponseEntity<Object> getVisitors() {
		try {

			List<Visitor> visitors = visitorRepository.findAll();
			log.info("INFO ------------ getVisitors --- RETRIEVING ALL VISITORS - SUCCESS");
			return ResponseHandler.generateResponse("Get Visitors", HttpStatus.OK, "", visitors);

		} catch (Exception ex) {
			log.info("ERROR ------------ BAD_REQUEST: getVisitors --- RETRIEVING ALL VISITORS - FAILURE");
			return ResponseHandler.generateResponse("Get Visitors", HttpStatus.BAD_REQUEST, "Exception Occurred",
					ex.getMessage());
		}

	}
	
	public ResponseEntity<Object> incrementTotal() {
		try {

			List<Visitor> visitors = visitorRepository.findAll();
			
					List<Visitor> visitors2=visitors.stream().map(value -> {
						Integer count=value.getTotal()+1;
						value.setTotal(count);
						return value;
					}).collect(Collectors.toList());
			
			
			return ResponseHandler.generateResponse("Get Visitors", HttpStatus.OK, "", visitorRepository.saveAll(visitors2));

		} catch (Exception ex) {
			log.info("ERROR ------------ BAD_REQUEST: incrementTotal --- UPDATING ALL VISITORS - FAILURE");
			return ResponseHandler.generateResponse("Get Visitors", HttpStatus.BAD_REQUEST, "Exception Occurred",
					ex.getMessage());
		}

	}
	
}
