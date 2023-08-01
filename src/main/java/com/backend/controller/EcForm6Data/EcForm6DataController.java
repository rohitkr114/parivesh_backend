package com.backend.controller.EcForm6Data;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.response.ResponseHandler;
import com.backend.service.EcForm6DataService.EcForm6DataService;

@RestController
@RequestMapping("/ecForm6")
public class EcForm6DataController {

	@Autowired
	private EcForm6DataService ecForm6DataService;

	@Transactional
	@PostMapping("/getEcForm6Data")
	public ResponseEntity<Object> getEcForm6Data(@RequestParam Integer ecId)
			throws PariveshException {

		return ResponseHandler.generateResponse("Get Save Ec Form 6 Data", HttpStatus.OK, "",
				ecForm6DataService.getEcForm6Data(ecId));
	}
}