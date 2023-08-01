package com.backend.controller.EcForm6Part2;

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
import com.backend.model.EcForm6Part2.EcForm6Productdetails;
import com.backend.response.ResponseHandler;
import com.backend.service.EcForm6Part2.EcForm6Service;

@RestController
@RequestMapping("/ecForm6Part2")
public class EcForm6ProductDetailsController {

	@Autowired
	private EcForm6Service ecForm6Service;

	@PostMapping("/saveEcForm6Productdetails")
	public ResponseEntity<Object> saveEcForm6(@RequestParam("ec_id") Integer ec_id,@RequestBody EcForm6Productdetails ecForm6Productdetails,	HttpServletRequest request) throws PariveshException {	
		//Save Ec Form 6 Implementation and Product Details
		return ResponseHandler.generateResponse("Implementation & Product Details Saved Successfully", HttpStatus.OK, "",
				ecForm6Service.saveEcForm6(ecForm6Productdetails, ec_id, request));

	}
	
	// [Method to get Product Details data by ID]
	@PostMapping("/getProductDetails")
	public ResponseEntity<Object> view(@RequestParam Integer ecId, HttpServletRequest request)
			throws PariveshException {
		return ResponseHandler.generateResponse("Success", HttpStatus.OK, "",
				ecForm6Service.getProductDetails(ecId));
	}

	@PostMapping("/deleteEcForm6ProductDetails")
	public ResponseEntity<Object> deleteEcForm6Obtained(@RequestParam Integer id) throws PariveshException {
		
		return ResponseHandler.generateResponse("Success", HttpStatus.OK, "", ecForm6Service.deleteProductDetails(id));
	}
}