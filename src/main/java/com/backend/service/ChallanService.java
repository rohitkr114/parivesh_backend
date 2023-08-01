package com.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.client.ChallanAPI;
import com.backend.model.PaymentCompletionDTO;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChallanService {

	@Autowired
	private ChallanAPI challanAPI;
	
	public ResponseEntity<Object> insertChallan(PaymentCompletionDTO paymentCompletionDTO){
		try {
			log.info("INFO ------------ insertChallan");
			return ResponseHandler.generateResponse("Insert Challan", HttpStatus.OK, "",
					challanAPI.insertChallan(paymentCompletionDTO));
		} catch (Exception ex) {
			log.info("INFO ------------ insertChallan--Failure");
			return ResponseHandler.generateResponse("Insert Challan", HttpStatus.INTERNAL_SERVER_ERROR, "Exception Occurred",
					ex.getMessage());
		}
	}
}
