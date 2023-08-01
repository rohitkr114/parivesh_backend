package com.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.DocumentDetails;
import com.backend.model.NoteSheetDTO;
import com.backend.model.Notesheet;
import com.backend.repository.postgres.NoteSheetDTORepository;
import com.backend.repository.postgres.NotesheetRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NotesheetService {

	@Autowired
	private NotesheetRepository notesheetRepository;

	@Autowired
	private NoteSheetDTORepository notesheetDTORepository;

	public ResponseEntity<Object> getNotesheet(Integer application_id, Integer user_id) {
		try {
			log.info("Application Id in getNotesheet " + application_id);

			List<NoteSheetDTO> notesheetList = new ArrayList<>();
			//notesheetList = notesheetDTORepository.getNotesheetByHistoryId();
			
			if (application_id == null)
				notesheetList = notesheetDTORepository.getNotesheetByHistoryId(null);
			else
				notesheetList = notesheetDTORepository.getNotesheetByHistoryAppId(application_id,user_id);

			if (notesheetList == null) {
				return ResponseHandler.generateResponse("Save NoteSheet", HttpStatus.INTERNAL_SERVER_ERROR, "no data",
						"");
			}
			return ResponseHandler.generateResponse("Save NoteSheet", HttpStatus.OK, "", notesheetList);

		} catch (Exception ex) {
			log.error("=======================>>>>>>>>>>>" + ex.toString() + " " + ex.getStackTrace()[0]);
			throw new PariveshException("Error in get Notesheet ");
		}

	}

	public ResponseEntity<Object> deleteNotesheet(Integer id) {

		try {
			log.error("=======================>>>>>>>>>>>Delete NoteSheet ");
			Notesheet notesheet = notesheetRepository.getById(id);
			notesheet.setIs_active(false);
			notesheet.setIs_deleted(true);
			log.error("=======================>>>>>>>>>>>NoteSheet " + notesheet.getIs_active());
			log.error("=======================>>>>>>>>>>>NoteSheet " + notesheet.getIs_deleted());
			return ResponseHandler.generateResponse("Save FCFormC", HttpStatus.OK, "",
					notesheetRepository.save(notesheet));
		} catch (Exception ex) {
			log.error("=======================>>>>>>>>>>>" + ex.toString() + " " + ex.getStackTrace()[0]);
			throw new PariveshException("Error in deleting Notesheet with id " + id);
		}
	}

	public ResponseEntity<Object> saveNotesheet(Notesheet notesheet) {
		try {
			log.info("NoteSheet is " + notesheet);
			if (notesheet.getId() != 0) {
				Notesheet notesheet2 = notesheetRepository.findById(notesheet.getId())
						.orElseThrow(() -> new ProjectNotFoundException("Notesheet does not exist with Id"));
				notesheet.setId(notesheet2.getId());
			}

			return ResponseHandler.generateResponse("Save Notesheet", HttpStatus.OK, "",
					notesheetRepository.save(notesheet));
		} catch (Exception ex) {
			log.error("=======================>>>>>>>>>>>" + ex.toString() + " " + ex.getStackTrace()[0]);
			throw new PariveshException("Error in saving Notesheet " + notesheet.getId());
		}
	}

}
