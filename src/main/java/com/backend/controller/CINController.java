package com.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.cin.GstServiceStub;
import com.backend.cin.GstServiceStub.GetCINInfo;
import com.backend.cin.GstServiceStub.GetCINInfoE;
import com.backend.response.ResponseHandler;

@RestController
@RequestMapping("/cin")
public class CINController {

	@PostMapping("/getInfo")
	public ResponseEntity<Object> saveClearanceAction(@RequestParam String cin_no) {

		try {
			GstServiceStub gstObj = new GstServiceStub();

			GetCINInfoE cinObj = new GetCINInfoE();
			GetCINInfo cinStr = new GetCINInfo();
			cinStr.setArg0(cin_no);

			/*
			 * Setting the CIN to the object
			 */
			cinObj.setGetCINInfo(cinStr);
			

			return ResponseHandler.generateResponse("CIN Details", HttpStatus.OK, " Success ", gstObj.getCINInfo(cinObj));

		} catch (Exception e) {
			return ResponseHandler.generateResponse("CIN Details", HttpStatus.EXPECTATION_FAILED, " Exception ",
					e.getMessage());
		}
	}
}
