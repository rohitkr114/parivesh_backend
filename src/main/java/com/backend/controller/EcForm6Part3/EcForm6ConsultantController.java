package com.backend.controller.EcForm6Part3;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.EcForm6Part3.EcForm6EiaConsultantDetails;
import com.backend.response.ResponseHandler;
import com.backend.service.EcForm6Part3.EcForm6EiaConsultantDetailsService;

@RestController
@RequestMapping("/ecForm6Part3")
public class EcForm6ConsultantController {

	@Autowired
	private EcForm6EiaConsultantDetailsService ecForm6EiaConsultantDetailsService;

	// [Method to save Eia Consultants Details]
	@PostMapping("/saveEiaConsultantDetails")
	public ResponseEntity<Object> saveEiaConsultantDetails(
			@RequestBody EcForm6EiaConsultantDetails ecForm6EiaConsultantDetails, @RequestParam("id") Integer ecId,
			HttpServletRequest request) throws PariveshException {
		//Save ecForm6EiaConsultantDetails Form 6
		return ResponseHandler.generateResponse("Consultant Details Saved Successfully", HttpStatus.OK, "",
				ecForm6EiaConsultantDetailsService.saveEiaConsultantDetails(ecForm6EiaConsultantDetails, ecId,
						request));
	}
		
		// [Method to get Consultant Details data by ID]
		@PostMapping("/getConsultantDetails")
		public ResponseEntity<Object> view(@RequestParam Integer ecId, HttpServletRequest request)
				throws PariveshException {
			return ResponseHandler.generateResponse("Success", HttpStatus.OK, "",
					ecForm6EiaConsultantDetailsService.getConsultantDetails(ecId));
		}
	
}
