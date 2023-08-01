package com.backend.controller.EcFormV;

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
import com.backend.model.EcFormVModel.EcFormV;
import com.backend.model.EcFormVModel.EcFormVUndertaking;
import com.backend.model.FcFormAPartBDc.FcFormAPartBDC;
import com.backend.model.FcFormAPartBDc.FcFormAPartBDCUndertaking;
import com.backend.response.ResponseHandler;
import com.backend.service.EcFormV.EcFormVService;

@RestController
@RequestMapping("/ecFormV")
public class EcFormVController {

	@Autowired
	private EcFormVService ecFormFiveService;

	@PostMapping("/save")
	public ResponseEntity<Object> saveEcFormV(@RequestBody EcFormV ecFormV, @RequestParam Integer caf_id,
			HttpServletRequest request) throws PariveshException {
		return ResponseHandler.generateResponse("Save Ec Form V", HttpStatus.OK, "",
				ecFormFiveService.saveEcFormV(ecFormV, caf_id, request));
	}

	@PostMapping("/get")
	public ResponseEntity<Object> getEcFormV(@RequestParam("ecFormVId") Integer ecFormVId) throws PariveshException {
		return ResponseHandler.generateResponse("Get Ec Form V", HttpStatus.OK, "",
				ecFormFiveService.getEcFormV(ecFormVId));
	}

	@PostMapping("/saveUndertaking")
	public ResponseEntity<Object> saveUndertaking(@RequestParam Integer ecFormVId,
			@RequestBody EcFormVUndertaking ecFormVUndertaking,@RequestParam(required = false)Boolean is_submit,HttpServletRequest request) throws PariveshException {
		return ecFormFiveService.saveUndertaking(ecFormVUndertaking, ecFormVId,is_submit,request);
	}
}
