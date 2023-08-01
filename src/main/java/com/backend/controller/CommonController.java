package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.service.RiverValleyProjectService;
import com.backend.exceptions.PariveshException;
import com.backend.response.ResponseHandler;
import com.backend.service.AirportProposalService;
import com.backend.service.IrrigationProjectCapacityVillageService;
import com.backend.service.MiningProposalsService;

@RestController
@RequestMapping("/common")
public class CommonController {

	@Autowired
	private MiningProposalsService miningProposalService;

	@Autowired
	private AirportProposalService airportProposalService;

	@Autowired
	private IrrigationProjectCapacityVillageService irrigationService;

	@Autowired
	private RiverValleyProjectService riverValleyProjectService;

	@GetMapping("/miningProposals")
	public ResponseEntity<Object> getMiningProposals(@RequestParam("cafId") Integer cafId) throws PariveshException {
		return miningProposalService.getMiningProposalsDetails(cafId);
	}

	@GetMapping("/airportProposals")
	public ResponseEntity<Object> getAirportProposal(@RequestParam("cafId") Integer cafId) throws PariveshException {
		return airportProposalService.getAirportProposalDetails(cafId);
	}

	@GetMapping("/irrigationProjectCapacityVillage")
	public ResponseEntity<Object> getIrrigationProjectCapacityVillage(@RequestParam("cafId") Integer cafId)
			throws PariveshException {
		return irrigationService.getIrrigationProjectCapacityVillageDetails(cafId);
	}

	@GetMapping("/riverValleyProject")
	public ResponseEntity<Object> getRiverValleyProjectDetails(@RequestParam("cafId") Integer cafId)
			throws PariveshException {
		return riverValleyProjectService.getRiverValleyProjectDetails(cafId);
	}
}
