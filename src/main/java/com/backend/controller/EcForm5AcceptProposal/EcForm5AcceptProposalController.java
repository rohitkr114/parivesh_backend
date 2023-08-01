package com.backend.controller.EcForm5AcceptProposal;

import javax.servlet.http.HttpServletRequest;

import com.backend.exceptions.PariveshException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.EcFrom5AcceptProposal.EcForm5AcceptProposals;
import com.backend.response.ResponseHandler;
import com.backend.service.EcForm5AcceptProposalsService;

@RestController
public class EcForm5AcceptProposalController {

	@Autowired
	private EcForm5AcceptProposalsService ecForm5AcceptProposalsService;

	@PostMapping("/saveEcForm5AcceptProposal")
	public ResponseEntity<Object> saveEcForm5AcceptProposal(@RequestBody EcForm5AcceptProposals ecForm5AcceptProposals,
			HttpServletRequest request) {

		return ResponseHandler.generateResponse("Save Ec Form 5 Accept Proposal Data ", HttpStatus.OK, "",
				ecForm5AcceptProposalsService.saveEcForm5AcceptProposal(ecForm5AcceptProposals, request));
	}

	@PostMapping("/getEcForm5AcceptProposal")
	public ResponseEntity<Object> getEcForm5AcceptProposal(@RequestParam Integer id) {

		return ecForm5AcceptProposalsService.getEcForm5AcceptProposal(id);
	}

	@PostMapping("/getEcForm5AcceptProposalList")
	public ResponseEntity<Object> getFormVByEcId(@RequestParam Integer ecId) throws PariveshException {
		return ecForm5AcceptProposalsService.getFormVByEcId(ecId);
	}

	@PostMapping("/deleteEcForm5AcceptProposal")
	public ResponseEntity<Object> deleteEcForm5Proposal(@RequestParam Integer id)throws PariveshException{
		return ecForm5AcceptProposalsService.deleteEcForm5Proposal(id);
	}

}
