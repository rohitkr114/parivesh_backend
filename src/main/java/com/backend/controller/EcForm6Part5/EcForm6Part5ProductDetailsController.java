package com.backend.controller.EcForm6Part5;

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
import com.backend.model.EcForm6Part5.EcForm6Undertaking;
import com.backend.response.ResponseHandler;
import com.backend.service.EcForm6Part5.EcForm6Part5Service;

@RestController
@RequestMapping("/ecForm6Part5")
public class EcForm6Part5ProductDetailsController {

	@Autowired
	private EcForm6Part5Service ecForm6Part5Service;

//EcForm6Undertaking ecForm6Undertaking, Integer ec_basic_id,Integer ecId,
	//HttpServletRequest request
	@PostMapping("/saveEcForm6Undertaking")
	public ResponseEntity<Object> saveEcForm7Undertaking(@RequestBody EcForm6Undertaking ecForm6Undertaking,
			@RequestParam Integer ec_id,@RequestParam(required = false)Boolean is_submit,HttpServletRequest request)
			throws PariveshException {
		//return ecForm6Part5Service.saveUndertaking(ecForm6Undertaking, ec_basic_id, ecId, request);
		//return ecForm6Part5Service.saveUndertaking(ecForm6Undertaking,ec_id, request);
		return ResponseHandler.generateResponse("Undertaking Saved Successfully", HttpStatus.OK, "",
				ecForm6Part5Service.saveUndertaking(ecForm6Undertaking, ec_id,is_submit,request));
	}
	
	// view
	//EcForm6Undertaking
	@PostMapping("/getUndertaking")
	public ResponseEntity<Object>  view(@RequestParam Integer ec_basic_id) throws PariveshException {
		//return ecForm6Part5Service.saveUndertaking(ecForm6Undertaking, ec_id, request);
		return ResponseHandler.generateResponse("Success", HttpStatus.OK, "",
				ecForm6Part5Service.getUndertaking(ec_basic_id));
		
		//return ecForm6Part5Service.getUndertaking(ec_basic_id);
	}
	
	
	
	

}
