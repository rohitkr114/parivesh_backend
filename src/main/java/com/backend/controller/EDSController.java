package com.backend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.EDS;
import com.backend.model.EDS_Details;
import com.backend.model.Agenda.AgendaRemarks;
import com.backend.model.Withdrawal.WithdrawalRemarks;
import com.backend.service.EDSDetailsService;
import com.backend.service.EDSService;

@RestController
@RequestMapping("/eds")
public class EDSController {

	@Autowired
	EDSService edsService;
	
	@Autowired
	private EDSDetailsService edsDetailsService;
	
	@PostMapping("/addEDS")
	public ResponseEntity<Object> addEDS(@RequestBody EDS eds) {
		return edsService.addEDS(eds);
	}

	@PostMapping("/getEDS")
	public ResponseEntity<Object> getEDS(@RequestParam Integer Id) {
		return edsService.getEDS(Id);
	}
	@PostMapping("/getEDSDetails")
	public ResponseEntity<Object> getEDSDetails(@RequestParam Integer id) {
		return edsDetailsService.getEDSDetails(id);
	}
	
	@PostMapping("/saveEDSDetails")
	public ResponseEntity<Object> saveEDSDetails(@RequestBody EDS_Details edsDetails, HttpServletRequest request) {
		return edsDetailsService.saveEDSDetails(edsDetails, request);
	}
	
	@PostMapping("/saveAgendaRemarks")
	public ResponseEntity<Object> saveAgendaRemarks(@RequestBody AgendaRemarks agendaRemarks,@RequestParam(required = false) Integer clearanceId,HttpServletRequest request) {
		return edsDetailsService.saveAgendaRemarks(agendaRemarks, clearanceId,request);
	}
	
	@PostMapping("/saveWithdrawalRemarks")
	public ResponseEntity<Object> saveWithdrawalRemarks(@RequestBody WithdrawalRemarks withdrawalRemarks,@RequestParam(required = false) Integer clearanceId,HttpServletRequest request) {
		return edsDetailsService.saveWithdrawalRemarks(withdrawalRemarks, clearanceId,request);
	}
	
	@PostMapping("/getWithdrawalRemarks")
	public ResponseEntity<Object> getWithdrawalRemarks(@RequestParam Integer id,HttpServletRequest request) {
		return edsDetailsService.getWithdrawalRemarks(id,request);
	}
	
	
}
