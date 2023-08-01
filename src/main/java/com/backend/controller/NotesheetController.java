package com.backend.controller;

import com.backend.dto.UserPrincipal;
import com.backend.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.Notesheet;
import com.backend.service.NotesheetService;




@RestController
@RequestMapping("/notesheet/")
public class NotesheetController {

	@Autowired
	private NotesheetService notesheetService;
	
	@PostMapping("save")
	public ResponseEntity<Object> saveNotesheet(@RequestBody Notesheet notesheet) {
		return notesheetService.saveNotesheet(notesheet);
	}
	
	@PostMapping("get")
	public ResponseEntity<Object> getNotesheet(@RequestParam(required = false)Integer application_id, @CurrentUser UserPrincipal principal){
		
		return notesheetService.getNotesheet(application_id,principal.getId());
	}
	
	@PostMapping("delete")
	public ResponseEntity<Object> deleteNotesheet(@RequestParam Integer id) {
		return notesheetService.deleteNotesheet(id);
	}

}
