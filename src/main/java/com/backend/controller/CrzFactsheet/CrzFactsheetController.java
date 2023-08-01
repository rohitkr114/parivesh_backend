package com.backend.controller.CrzFactsheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.CrzFactsheet.CrzFactsheet;
import com.backend.service.CrzFactsheet.CrzFactsheetService;

@RestController
@RequestMapping("/crzFactsheet")
public class CrzFactsheetController {

	@Autowired
	CrzFactsheetService crzFactsheetService;
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveFactsheet(@RequestBody CrzFactsheet crzFactsheet, @RequestParam Integer id) throws PariveshException {
		
		return crzFactsheetService.saveFactsheet(crzFactsheet, id);
	}
	
	@GetMapping("/get")
	public ResponseEntity<Object> getFactsheet(@RequestParam Integer id) throws PariveshException  {
		
		return crzFactsheetService.getFactsheet(id);
	}
}
