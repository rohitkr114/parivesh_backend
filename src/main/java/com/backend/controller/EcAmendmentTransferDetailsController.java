package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.service.EcAmendmentTransferDetailsService;

@RestController
@RequestMapping("/ecAmendmentTransferDetails")
public class EcAmendmentTransferDetailsController {

	@Autowired
	EcAmendmentTransferDetailsService ecAmendmentTransferDetailsService;

	@PostMapping("/delete")
	public ResponseEntity<Object> deleteECProjectActivityDetails(@RequestParam(value = "id") Integer id) {

		ResponseEntity<Object> status = ecAmendmentTransferDetailsService.deleteEcAmendmentTransferDetails(id);
		return status;

	}

}
