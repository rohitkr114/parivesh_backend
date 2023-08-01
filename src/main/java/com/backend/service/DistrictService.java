package com.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.District;
import com.backend.model.State;
import com.backend.repository.postgres.DistrictRepository;
import com.backend.repository.postgres.StateRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DistrictService {

	@Autowired
	DistrictRepository districtRepository;

	@Autowired
	StateRepository stateRepository;

	public ResponseEntity<Object> getAllDistricts() {

		try {
			log.info("INFO ------------ getAllDistricts Generating list of all districts RETRIEVED - SUCCESS");
			return ResponseHandler.generateResponse("Districts", HttpStatus.OK, "",
					districtRepository.findAllDistricts());
		} catch (Exception ex) {
			log.info("ERROR ------------ BAD_REQUEST : getAllDistricts generating list of all districts NOT RETRIEVED - FAILURE");
			return ResponseHandler.generateResponse("Districts", HttpStatus.BAD_REQUEST, "Exception Occurred",
					ex.getMessage());
		}
	}

	public ResponseEntity<Object> getDistrictByCode(Integer Code) {
		try {
			log.info("INFO ------------ getDistrictByCode Generating list of all districts WITH State Code ----> "+Code+" RETRIEVED - SUCCESS");
			return ResponseHandler.generateResponse("Districts", HttpStatus.OK, "",
					districtRepository.findAllDistrictsByStateCode(Code));
		} catch (Exception ex) {
			log.info("ERROR ------------ BAD_REQUEST : getDistrictByCode generating list of all districts WITH State Code ----> "+Code+" ---- NOT RETRIEVED - FAILURE");

			return ResponseHandler.generateResponse("Districts", HttpStatus.BAD_REQUEST, "Exception Occurred",
					ex.getMessage());
		}
	}

	public ResponseEntity<Object> addDistricts(List<District> districts, Integer state_id) {
		try {
			State temp = stateRepository.findById(state_id)
					.orElseThrow(() -> new ProjectNotFoundException("State Not Present"));
			List<District> districtTemp = districts.stream().map(value -> {

				value.setState(temp);
				value.setState_code(temp.getCode());
				return value;
			}).collect(Collectors.toList());
			log.info("INFO ------------ addDistricts in state_id ---->"+state_id+" ----INSERTION - SUCCESS");
			return ResponseHandler.generateResponse("Districts", HttpStatus.OK, "",
					districtRepository.saveAll(districtTemp));
		} catch (Exception ex) {
			log.info("ERROR ------------ BAD_REQUEST: addDistricts in state_id ---->"+state_id+" INSERTION - FAILURE");
			return ResponseHandler.generateResponse("Districts", HttpStatus.BAD_REQUEST, "Exception Occurred",
					ex.getMessage());
		}
	}

	public ResponseEntity<Object> deleteDistricts(Integer district) {
		try {
			District temp = districtRepository.findById(district)
					.orElseThrow(() -> new ProjectNotFoundException("District Not Found"));
			temp.setIs_active(false);
			temp.setIs_deleted(true);
			log.info("INFO ------------ deleteDistricts DISTRICT WITH ID ---->"+district+" ---- FOUND and DELETED - SUCCESS");
			return ResponseHandler.generateResponse("Districts Deletion", HttpStatus.OK, "",
					districtRepository.save(temp));
		} catch (Exception ex) {
			log.info("ERROR ------------ BAD_REQUEST: deleteDistricts DISTRICT WITH ID ---->"+district+" ---- NOT FOUND (DELETION FAILED) - FAILURE");
			return ResponseHandler.generateResponse("Districts Deletion", HttpStatus.BAD_REQUEST, "Exception Occurred",
					ex.getMessage());
		}
	}
	
	public District getStatebyDistrictCode(Integer districtCode) {
		
			District temp= districtRepository.findStateByDistrictCode(districtCode);
			log.info("INFO ------------ getStatebyDistrictCode STATE WITH District Code ----> "+districtCode+" ----FOUND and RETRIEVED - SUCCESS");
			return temp;
	}
}
