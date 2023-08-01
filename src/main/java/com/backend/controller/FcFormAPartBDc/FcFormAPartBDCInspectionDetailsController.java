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
import com.backend.model.FcFormAPartBDc.FcFormAPartBDCInspectionDetails;
import com.backend.response.ResponseHandler;
import com.backend.service.FcFormAPartBDc.FcFormAPartBDCInspectionDetailsService;

@RestController
@RequestMapping("/fcFormAPartBDCInspectionDetails")
public class FcFormAPartBDCInspectionDetailsController {

	@Autowired
	private FcFormAPartBDCInspectionDetailsService fcFormAPartBDCInspectionDetailsService;

	@PostMapping("/save")
	public ResponseEntity<Object> saveFcFormAPartBDC(
			@RequestBody FcFormAPartBDCInspectionDetails fcFormAPartBDCInspectionDetails,
			@RequestParam Integer fc_formA_DC_id) throws PariveshException {
		return ResponseHandler.generateResponse("Save Fc FormA PartB DC Inspection Details", HttpStatus.OK, "",
				fcFormAPartBDCInspectionDetailsService.savefcFormAPartBDC(fcFormAPartBDCInspectionDetails,
						fc_formA_DC_id));
	}

	@PostMapping("/get")
	public ResponseEntity<Object> getFcFormAPartBDC(@RequestParam("id") Integer id) throws PariveshException {
		return ResponseHandler.generateResponse("Get Fc FormA PartB DC Inspection Details", HttpStatus.OK, "",
				fcFormAPartBDCInspectionDetailsService.getfcFormAPartBDC(id));
	}

	@PostMapping("/delete")
	public ResponseEntity<Object> deleteFcFormAPartBDC(@RequestParam("id") Integer id) throws PariveshException {
		return ResponseHandler.generateResponse("Delete Fc FormA PartB DC Inspection Details", HttpStatus.OK, "",
				fcFormAPartBDCInspectionDetailsService.deletefcFormAPartBDC(id));
	}

}
