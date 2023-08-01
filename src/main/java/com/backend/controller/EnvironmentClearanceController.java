package com.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.EcConsultant;
import com.backend.model.EcEnclosureDetail;
import com.backend.model.EcOthersDetail;
import com.backend.model.EcProductDetail;
import com.backend.model.EcProjectDetail;
import com.backend.model.EcSectorFormDetails;
import com.backend.model.EnvironmentClearence;
import com.backend.response.ResponseHandler;
import com.backend.service.EnvironmentClearanceService;

@RestController
@RequestMapping("/ec")
public class EnvironmentClearanceController {

	@Autowired
	private EnvironmentClearanceService environmentClearanceService;

	@PostMapping("/saveForm")
	public ResponseEntity<Object> saveEnvironmentClearence(@RequestParam("caf_id") Integer caf_id,
			@RequestBody EnvironmentClearence environmentClearence, HttpServletRequest request,@RequestParam(required = false)  Integer clearance_id) {
		return ResponseHandler.generateResponse("Save Environment Clearance form", HttpStatus.OK, "",
				environmentClearanceService.saveEnvironmentClearance(caf_id, environmentClearence, request, clearance_id));
	}

	@PostMapping("/saveProductDetail")
	public ResponseEntity<Object> saveProductDetail(@RequestParam("ec_id") Integer ec_id,
			@RequestBody EcProductDetail ecProductDetail) {
		return ResponseHandler.generateResponse("Save Environment Clearance form", HttpStatus.OK, "",
				environmentClearanceService.saveProductDetail(ec_id, ecProductDetail));
	}

	@PostMapping("/saveProjectDetail")
	public ResponseEntity<Object> saveProjectDetail(@RequestParam("ec_id") Integer ec_id,
			@RequestBody EcProjectDetail ecProjectDetail) {
		return ResponseHandler.generateResponse("Save Environment Clearance form", HttpStatus.OK, "",
				environmentClearanceService.saveProjectDetail(ec_id, ecProjectDetail));
	}

	@PostMapping("/saveOthersDetail")
	public ResponseEntity<Object> saveOthersDetail(@RequestParam("ec_id") Integer ec_id,
			@RequestBody EcOthersDetail ecOthersDetail,@RequestParam(required = false) Boolean is_submit ,HttpServletRequest request) {
		return ResponseHandler.generateResponse("Save Environment Clearance form", HttpStatus.OK, "",
				environmentClearanceService.saveOthersDetail(ec_id, ecOthersDetail,is_submit,request));
	}

	@PostMapping("/saveEnclosureDetail")
	public ResponseEntity<Object> saveEnclosureDetail(@RequestParam("ec_id") Integer ec_id,
			@RequestBody EcEnclosureDetail ecEnclosureDetail) {
		return ResponseHandler.generateResponse("Save Environment Clearance form", HttpStatus.OK, "",
				environmentClearanceService.saveEnclosureDetail(ec_id, ecEnclosureDetail));
	}
	
	@PostMapping("/saveConsultantDetail")
	public ResponseEntity<Object> saveConsultantDetail(@RequestParam("ec_id") Integer ec_id,
			@RequestBody EcConsultant ecConsultant) {
		return ResponseHandler.generateResponse("Save Environment Clearance form", HttpStatus.OK, "",
				environmentClearanceService.saveConsultantDetail(ec_id, ecConsultant));
	}

	@PostMapping("/getForm")
	public ResponseEntity<Object> getForm(@RequestParam("ec_id") Integer ec_id) {
		return ResponseHandler.generateResponse("Save Environment Clearance form", HttpStatus.OK, "",
				environmentClearanceService.getEnvironmentClearence(ec_id));
	}

	@PostMapping("/getEcSectorForms")
	public ResponseEntity<Object> getECSectorForm() {
		return environmentClearanceService.getSectorForm();
	}

	@PostMapping("/getEcSectorFormById")
	public ResponseEntity<Object> getECSectorFormById(@RequestParam("SectorFormId") Integer sectorFormId) {
		return environmentClearanceService.getSectorFormbyId(sectorFormId);
	}

	@PostMapping("/saveEcSectorForm")
	public ResponseEntity<Object> saveECSectorForm(@RequestBody List<EcSectorFormDetails> ecSectorForm,
			@RequestParam(name = "actId", required = false) Integer ActId) {
		return environmentClearanceService.saveSectorForm(ecSectorForm, ActId);
	}

	@PostMapping("/deleteEcSectorForms")
	public ResponseEntity<Object> deleteECSectorForm(@RequestBody List<EcSectorFormDetails> ecSectorForm) {
		return environmentClearanceService.deleteSectorForm(ecSectorForm);
	}

	@PostMapping("/updateSectorForm")
	public ResponseEntity<Object> deleteECSectorForm(
			@RequestParam(name = "sectorFormId", required = true) Integer SectorFormId,
			@RequestParam(name = "activityId", required = false) Integer actid,
			@RequestParam(name = "formName", required = false) String formName) {
		return environmentClearanceService.updateSectorForm(SectorFormId, actid, formName);
	}
	
	@PostMapping("/getTORStatus")
	public ResponseEntity<Object> getTORStatus(
			@RequestParam(name = "proposalId", required = true) Integer proposalId) {
		return environmentClearanceService.getTORStatus(proposalId);
	}
	
}
