package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.EcProdTransportDetails;
import com.backend.response.ResponseHandler;
import com.backend.service.EcProdTransportDetailsService;

@RestController
@RequestMapping("/ecProdTransportDetails")
public class EcProdTransportDetailsController {

	@Autowired
	EcProdTransportDetailsService ecProdTransportDetailsService;

	@PostMapping("/save")
	public ResponseEntity<Object> saveProdTransportDetails(@RequestParam("id") Integer id,
			@RequestBody List<EcProdTransportDetails> ecProdTransportDetails) {

		return ResponseHandler.generateResponse("Save EC Project Activity Data", HttpStatus.OK, "",
				ecProdTransportDetailsService.saveEcProdTransportDetails(id, ecProdTransportDetails));

	}

	@PostMapping("/delete")
	public ResponseEntity<Object> deleteECProjectActivityDetails(@RequestParam(value = "id") Integer id) {

		ResponseEntity<Object> status = ecProdTransportDetailsService.deleteEcProdTransportDetails(id);
		return status;

	}

}
