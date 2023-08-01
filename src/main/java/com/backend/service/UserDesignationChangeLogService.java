package com.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.DesignationEntity;
import com.backend.model.UserDesignationChangeLog;
import com.backend.repository.postgres.DesignationEntityRepository;
import com.backend.repository.postgres.UserDesignationChangeLogRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDesignationChangeLogService {

	@Autowired
	private UserDesignationChangeLogRepository userDesignationChangeLogRepository;

	@Autowired
	private DesignationEntityRepository designationEntityRepository;

	public ResponseEntity<Object> saveDesignation(DesignationEntity designation) {
		try {
			Integer count = designationEntityRepository.checkDuplicateDesignation(designation.getDesignationName());
			if (count == 0) {
				DesignationEntity response = designationEntityRepository.save(designation);

				return ResponseHandler.generateResponse("saving designation", HttpStatus.OK, "", response);
			} else {
				return ResponseHandler.generateResponse("designation name already exists", HttpStatus.CONFLICT, "",
						null);
			}
		} catch (Exception e) {
			log.error("Encountered Exception", e);
			throw new PariveshException("error in saving designation", e);
		}
	}

	public ResponseEntity<Object> getAllDesignation() {
		try {
			List<DesignationEntity> response = new ArrayList<DesignationEntity>();
			response = designationEntityRepository.getAllDesignation();

			return ResponseHandler.generateResponse("getting designation list", HttpStatus.OK, "", response);
		} catch (Exception e) {
			log.error("encountered exception", e);
			throw new PariveshException("error in getting designation list", e);
		}
	}

	public ResponseEntity<Object> updateDesignation(UserPrincipal principal, String currentDesignationName,
			String newDesignationName) {
		try {
			DesignationEntity response = designationEntityRepository.getDesignationEntity(principal.getId());
			if (currentDesignationName.equalsIgnoreCase(response.getDesignationName())) {

				UserDesignationChangeLog temp = new UserDesignationChangeLog();
				temp.setOldDesignation(response.getDesignationName());
				temp.setNewDesignation(newDesignationName);

				response.setDesignationName(newDesignationName);
				designationEntityRepository.save(response);

				UserDesignationChangeLog log = userDesignationChangeLogRepository.save(temp);

				return ResponseHandler.generateResponse("updating designation", HttpStatus.OK, "", log);
			} else {
				return ResponseHandler.generateResponse(
						"current designation name does not match with designation name of user", HttpStatus.NOT_FOUND,
						"", null);
			}
		} catch (Exception e) {
			log.error("encountered exception", e);
			throw new PariveshException("error in updating designation", e);
		}
	}
}
