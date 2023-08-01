package com.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.exceptions.PariveshException;
import com.backend.repository.postgres.RiverValleyProjectRepository;
import com.backend.response.ResponseHandler;

@Service
@Transactional
public class RiverValleyProjectService {

	@Autowired
	private RiverValleyProjectRepository riverValleyProjectRepository;

	public ResponseEntity<Object> getRiverValleyProjectDetails(Integer cafId) throws PariveshException {
		try {

			return ResponseHandler.generateResponse("Get River Valley Project for caf_id", HttpStatus.OK, "",
					riverValleyProjectRepository.findRiverValleyProjectByCafId(cafId));
		} catch (Exception e) {
			// log.error(e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Getting River Valley Project for caf_id- " + cafId);
		}
	}

}
