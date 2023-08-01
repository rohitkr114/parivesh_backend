package com.backend.controller.EcForm9;

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
import com.backend.model.EcForm9.EcForm9Basicdetails;
import com.backend.model.EcForm9.EcForm9Undertaking;
import com.backend.response.ResponseHandler;
import com.backend.service.EcForm9.EcForm9Service;

@RestController
@RequestMapping("/ecForm9")
public class EcForm9Controller {

	@Autowired
	private EcForm9Service ecForm9Service;

	/*@PostMapping("/save")
	public ResponseEntity<Object> saveEcForm9(@RequestBody EcForm9Basicdetails ecForm9, @RequestParam Integer caf_id,
			HttpServletRequest request) throws PariveshException {
		return ResponseHandler.generateResponse("Save Ec Form V", HttpStatus.OK, "",
				ecForm9Service.saveEcFormV(ecForm9, caf_id, request));
	}*/

	/*@PostMapping("/get")
	public ResponseEntity<Object> getEcForm9(@RequestParam("ecForm9Id") Integer ecForm9Id) throws PariveshException {
		return ResponseHandler.generateResponse("Get Ec Form V", HttpStatus.OK, "",
				ecForm9Service.getEcFormV(ecForm9Id));
	}*/

	/*@PostMapping("/saveUndertaking")
	public ResponseEntity<Object> saveUndertaking(@RequestParam Integer ecForm9Id,
			@RequestBody EcForm9Undertaking ecForm9Undertaking,@RequestParam(required=false)Boolean is_submit,HttpServletRequest request) throws PariveshException {
		return ecForm9Service.saveUndertaking(ecForm9Undertaking, ecForm9Id, is_submit,request);
	}*/

}
