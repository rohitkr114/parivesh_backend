package com.backend.controller.FcFormAPartBDc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.FcFormAPartBDc.FcFormAPartBDC;
import com.backend.model.FcFormAPartBDc.FcFormAPartBDCUndertaking;
import com.backend.response.ResponseHandler;
import com.backend.service.FcFormAPartBDc.FcFormAPartBDCService;

@RestController
@RequestMapping("/fcFormAPartBDC")
public class FcFormAPartBDCController {

	@Autowired
	private FcFormAPartBDCService fcFormAPartBDCService;

	@PostMapping("/save")
	public ResponseEntity<Object> saveFcFormAPartBDC(@RequestBody FcFormAPartBDC fcFormAPartBDC,@RequestParam Integer clearanceId)
			throws PariveshException {
		return ResponseHandler.generateResponse("Save Fc FormA PartB DC", HttpStatus.OK, "",
				fcFormAPartBDCService.savefcFormAPartBDC(fcFormAPartBDC,clearanceId));
	}

	@PostMapping("/get")
	public ResponseEntity<Object> getFcFormAPartBDC(@RequestParam("id") Integer id) throws PariveshException {
		return ResponseHandler.generateResponse("Get Fc FormA PartB DC", HttpStatus.OK, "",
				fcFormAPartBDCService.getfcFormAPartBDC(id));
	}

	@PostMapping("/saveUndertaking")
	public ResponseEntity<Object> saveUndertaking(@RequestParam Integer fcFormAPartBDCId,
			@RequestBody FcFormAPartBDCUndertaking fcFormAPartBDCUndertaking) throws PariveshException {
		return fcFormAPartBDCService.saveUndertaking(fcFormAPartBDCUndertaking, fcFormAPartBDCId);
	}
	
	@PostMapping("/getInspectionReport")
	public ResponseEntity<Object> getInspectionReport(@RequestParam String proposal_no){
		return fcFormAPartBDCService.getSiteInspectionReport(proposal_no);
	}

}
