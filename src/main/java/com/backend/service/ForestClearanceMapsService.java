package com.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.model.ForestClearance;
import com.backend.model.ForestClearanceMaps;
import com.backend.repository.postgres.ForestClearanceMapsRepository;
import com.backend.repository.postgres.ForestClearanceRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ForestClearanceMapsService {
	@Autowired
	private ForestClearanceMapsRepository forestClearanceMapsRepository;

	@Autowired
	private ForestClearanceRepository forestClearanceRepository;

	@Autowired
	private UserService userService;

	public ResponseEntity<Object> saveForestMapDetails(Integer fc_id, List<ForestClearanceMaps> forestClearanceMaps) {
		ForestClearance temp = forestClearanceRepository.findById(fc_id).get();
		List<ForestClearanceMaps> maps = forestClearanceMaps.stream().map(value -> {
			value.setForestClearance(temp);
			if(value.getScanCopy() != null) {				
				value.getScanCopy().setProposal_no(temp.getProposal_no());
			}
			return value;
		}).collect(Collectors.toList());
		log.info("INFO ------------ saveForestMapDetails  forest clearance id ----> "+fc_id+" ----DELETED - SUCCESS");
		List<ForestClearanceMaps> forestMapDetails = forestClearanceMapsRepository.saveAll(maps);
		return ResponseHandler.generateResponse("Save Forest Map Data", HttpStatus.OK, "", forestMapDetails);

	}

	public ResponseEntity<Object> getForestMapData(int id) {
		log.info("INFO ------------ getForestMapData forest clearance id ----> "+id+" ----DELETED - SUCCESS");
		return ResponseHandler.generateResponse("get forest map data list by fc_id", HttpStatus.OK, "",
				forestClearanceMapsRepository.findByFCID(id));

	}

}
