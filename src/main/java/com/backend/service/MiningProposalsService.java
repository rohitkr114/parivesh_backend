package com.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.exceptions.PariveshException;
import com.backend.repository.postgres.MiningProposalsRepository;
import com.backend.response.ResponseHandler;

@Service
@Transactional
public class MiningProposalsService {

	@Autowired
	private MiningProposalsRepository miningProposalsRepository;

	public ResponseEntity<Object> getMiningProposalsDetails(Integer cafId) throws PariveshException {
		try {

			return ResponseHandler.generateResponse("Get Mining Proposals for caf_id", HttpStatus.OK, "",
					miningProposalsRepository.findMiningProposalsByCafId(cafId));
		} catch (Exception e) {
			// log.error(e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Getting Mining Proposals for caf_id- " + cafId);
		}
	}

}
