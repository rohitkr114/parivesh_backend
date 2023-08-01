package com.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.ApplicationAmendmentLogs;
import com.backend.repository.postgres.ApplicationAmendmentLogsRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApplicationAmendmentLogsService {

	@Autowired
	private ApplicationAmendmentLogsRepository applicationAmendmentLogsRepository;

	public ResponseEntity<Object> saveAmendmentLogs(List<ApplicationAmendmentLogs> applicationAmendmentLogs) {
		try {
			return ResponseHandler.generateResponse("saving amendment logs", HttpStatus.OK, "",
					applicationAmendmentLogsRepository.saveAll(applicationAmendmentLogs));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving amendment logs", e);
		}
	}

	public ResponseEntity<Object> getAmendmentLogs(Integer ref_id, String ref_type) {
		try {
			List<ApplicationAmendmentLogs> logs = new ArrayList<ApplicationAmendmentLogs>();
			if (ref_type != null) {
				logs = applicationAmendmentLogsRepository.getLogs(ref_id, ref_type);
			} else {
				logs = applicationAmendmentLogsRepository.getLogs(ref_id);
			}
			return ResponseHandler.generateResponse("getting amendment logs", HttpStatus.OK, "", logs);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting amendment logs", e);
		}
	}

	public ResponseEntity<Object> deleteAmendmentLogs(Integer id) {
		try {
			ApplicationAmendmentLogs temp = applicationAmendmentLogsRepository.findById(id)
					.orElseThrow(() -> new ProjectNotFoundException("amendment logs not found with id:" + id));

			temp.setIs_active(false);
			temp.setIs_deleted(true);

			return ResponseHandler.generateResponse("deleting amendment logs", HttpStatus.OK, "",
					applicationAmendmentLogsRepository.save(temp));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in deleting amendment logs", e);
		}
	}
}
