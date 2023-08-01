package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.ClearanceAction;
import com.backend.model.ClearanceFactSheet;
import com.backend.model.ClearanceHistory;
import com.backend.response.ResponseHandler;
import com.backend.service.ClearanceService;


@RestController
@RequestMapping("/clearance")
public class ClearanceController {

	@Autowired
	public ClearanceService clearanceService;

	@PostMapping("/saveAction")
	public ResponseEntity<Object> saveClearanceAction(@RequestBody ClearanceAction action,
			@RequestParam(value = "application_id", required = false) Integer application_id) {
		return ResponseHandler.generateResponse("Save Clearance Action", HttpStatus.OK, "",
				clearanceService.saveClearanceAction(action, application_id));
	}
	
	@PostMapping("/saveFactSheet")
	public ResponseEntity<Object> saveFactSheet(@RequestBody ClearanceFactSheet factSheet) {
		return ResponseHandler.generateResponse("Save Clearance Fact SHeet", HttpStatus.OK, "",
				clearanceService.saveClearanceFactSheet(factSheet));
	}

	@PostMapping("/saveHistory")
	public ResponseEntity<Object> saveClearanceHistory(@RequestBody ClearanceHistory history,
			@RequestParam(value = "proposal_id") Integer proposal_id,
			@RequestParam(value = "clearance_action_id") Long clearance_action_id) {
		return ResponseHandler.generateResponse("Save Clearance History", HttpStatus.OK, "",
				clearanceService.saveClearanceHistory(history, proposal_id,clearance_action_id));
	}

	@Transactional
	@PostMapping("/getHistory")
	public ResponseEntity<Object> getClearanceHistorys(
			@RequestParam(value = "proposal_id", required = false) Integer proposal_id,
			@RequestParam(value="proposal_no",required = false)String proposal_no) {
		return clearanceService.getClearanceHistories(proposal_id,proposal_no);
	}
	
	@Transactional
	@PostMapping("/getFactSheet")
	public ResponseEntity<Object> getClearanceFactSheet(
			@RequestParam(value = "proposal_no", required = false) String proposal_no) {
		return ResponseHandler.generateResponse("get Clearance fact Sheet", HttpStatus.OK, "",
				clearanceService.getClearanceFactSheet(proposal_no));
	}

	@PostMapping("/getActions")
	public ResponseEntity<Object> getClearanceActions(
			@RequestParam(value = "application_id", required = false) Integer application_id) {
		return ResponseHandler.generateResponse("get Clearance History", HttpStatus.OK, "",
				clearanceService.getClearanceActions(application_id));
	}

	@PostMapping("/deleteActions")
	public ResponseEntity<Object> deleteClearanceActions(
			@RequestParam(value = "clearance_action_id") Long clearance_action_id) {
		return ResponseHandler.generateResponse("get Clearance History", HttpStatus.OK, "",
				clearanceService.deleteClearanceAction(clearance_action_id));
	}

	@PostMapping("/deleteHistory")
	public ResponseEntity<Object> deleteClearanceHistory(
			@RequestParam(value = "clearance_history_id", required = false) Long clearance_history_id) {
		return ResponseHandler.generateResponse("get Clearance History", HttpStatus.OK, "",
				clearanceService.deleteClearanceHistory(clearance_history_id));
	}
	
	@PostMapping("/deleteFactSheet")
	public ResponseEntity<Object> deleteClearanceFactSheet(
			@RequestParam(value = "fact_id", required = false) Long fact_id) {
		return ResponseHandler.generateResponse("delete Clearance fact sheet", HttpStatus.OK, "",
				clearanceService.deleteFactSheet(fact_id));
	}
}
