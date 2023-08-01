package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.service.EcImplementationStatusService;

@RestController
@RequestMapping("/ecImplementationStatus")
public class EcImplementationStatusController {

	@Autowired
	EcImplementationStatusService ecImplementationStatusService;

	@PostMapping("/delete")
	public ResponseEntity<Object> deleteEcImplementationStatus(@RequestParam(value = "id") Integer id) {

		ResponseEntity<Object> status = ecImplementationStatusService.deleteEcImplementationStatus(id);
		return status;

	}

}
