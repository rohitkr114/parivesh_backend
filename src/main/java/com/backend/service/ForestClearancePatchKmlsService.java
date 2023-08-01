package com.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.ForestClearance;
import com.backend.model.ForestClearancePatchKmls;
import com.backend.repository.postgres.ForestClearancePatchKmlDetailsRepository;
import com.backend.repository.postgres.ForestClearancePatchKmlsRepository;
import com.backend.repository.postgres.ForestClearanceRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ForestClearancePatchKmlsService {

	@Autowired
	private ForestClearancePatchKmlsRepository forestClearancePatchKmlsRepository;

	@Autowired
	private ForestClearanceRepository forestClearanceRepository;

	@Autowired
	private ForestClearancePatchKmlDetailsRepository forestClearancePatchKmlDetailsRepository;
	
	@Autowired
	private UserService userService;

	public ResponseEntity<Object> saveForestPatchKmlsDetails(Integer fc_id,
			List<ForestClearancePatchKmls> forestClearancePatchKmls) {
		ForestClearance temp = forestClearanceRepository.findById(fc_id).get();
		List<ForestClearancePatchKmls> kmls = forestClearancePatchKmls.stream().map(value -> {
			value.setForestClearance(temp);
			if(value.getPatch_kml() !=null) {				
				value.getPatch_kml().setProposal_no(temp.getProposal_no());
			}
			//value.setCreatedBy(userService.getuserId());
			return value;
		}).collect(Collectors.toList());
		log.info("INFO ------------ saveForestPatchKmlsDetails  forest clearance id ----> "+fc_id+" ----SAVE ALL CALL - SUCCESS");
		List<ForestClearancePatchKmls> forestKmlsDetails = forestClearancePatchKmlsRepository.saveAll(kmls);
		return ResponseHandler.generateResponse("Save Forest Kmls Data", HttpStatus.OK, "", forestKmlsDetails);

	}

	public ResponseEntity<Object> getForestKmlsData(int id) {
		log.info("INFO ------------ getForestKmlsData  forest clearance id ----> "+id+" ---- RETRIEVING- SUCCESS");
		return ResponseHandler.generateResponse("get forest kmls data list by fc_id", HttpStatus.OK, "",
				forestClearancePatchKmlsRepository.findByFCID(id));

	}
	
	public ResponseEntity<Object> deleteForestPatchKmlsDetails(List<Integer> fc_kml_Id) {
		List<ForestClearancePatchKmls> kmls = fc_kml_Id.stream().map(value -> {
			ForestClearancePatchKmls temp = forestClearancePatchKmlsRepository.findById(value).orElseThrow(()-> new ProjectNotFoundException("Forest Clearance Patch KML not found"));
			temp.setIs_active(false);
			temp.setIs_deleted(true);
			return temp;
		}).collect(Collectors.toList());
		log.info("INFO ------------ deleteForestPatchKmlsDetails  forest patch kmls id ----> "+fc_kml_Id+" ---- DELETING- SUCCESS");
		List<ForestClearancePatchKmls> forestKmlsDetails = forestClearancePatchKmlsRepository.saveAll(kmls);
		return ResponseHandler.generateResponse("Delete Forest Kmls Data", HttpStatus.OK, "", forestKmlsDetails);

	}

}
