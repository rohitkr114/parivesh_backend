package com.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.GeneralRemarks;
import com.backend.repository.postgres.GeneralRemarksRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GeneralRemarksService {

	@Autowired
	private GeneralRemarksRepository generalRemarksRepository;

	public ResponseEntity<Object> saveGeneralRemarks(GeneralRemarks generalRemarks,UserPrincipal principal) {
		try {

			Integer entity_id= principal.getId();
			String role_name= generalRemarksRepository.getRoleName(entity_id);
			generalRemarks.setUser_role_name(role_name);
			
			return ResponseHandler.generateResponse("saving general remarks", HttpStatus.OK, "",
					generalRemarksRepository.save(generalRemarks));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving general remarks", e);
		}
	}

	
	public ResponseEntity<Object> getGeneralRemarks(Integer ref_id, String ref_type) {
		try {
			List<GeneralRemarks> remarks = generalRemarksRepository.getGeneralRemarks(ref_id, ref_type);
//			Integer entity_id= principal.getId();
//			String role_name= generalRemarksRepository.getRoleName(entity_id);
//			
//			remarks.stream().map(value ->{
//				value.setUser_role_name(role_name);
//				return value;
//			}).collect(Collectors.toList());

			return ResponseHandler.generateResponse("getting general remarks", HttpStatus.OK, "", remarks);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting general remarks", e);
		}
	}

	
	public ResponseEntity<Object> deleteGeneralRemarks(Integer id) {
		try {
			GeneralRemarks temp = generalRemarksRepository.findById(id)
					.orElseThrow(() -> new ProjectNotFoundException("general remarks not found with id:" + id));

			temp.setIs_active(false);
			temp.setIs_deleted(true);

			return ResponseHandler.generateResponse("deleting general remarks", HttpStatus.OK, "",
					generalRemarksRepository.save(temp));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in deleting general remarks", e);
		}
	}
}
