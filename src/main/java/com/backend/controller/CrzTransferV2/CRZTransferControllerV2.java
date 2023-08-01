package com.backend.controller.CrzTransferV2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.CrzTransferV2.CRZTransferEnclosureDetailsV2;
import com.backend.model.CrzTransferV2.CRZTransferProposalDetailsV2;
import com.backend.model.CrzTransferV2.CRZTransferUndertakingV2;
import com.backend.service.CrzTransferV2.CRZTransferServiceV2;

@RestController
@RequestMapping("/crzTransferV2")
public class CRZTransferControllerV2 {
	
	@Autowired
	private CRZTransferServiceV2 crzTransferServiceV2;
	
	@PostMapping("/saveProposalDetails")
	public ResponseEntity<Object> saveCrzTransferProposalDetails(@RequestParam Integer cafId, @RequestParam Integer clearanceId, @RequestParam Integer crzId,
			@RequestBody CRZTransferProposalDetailsV2 crzTransferProposalDetailsV2) throws PariveshException{
		
		return crzTransferServiceV2.saveCrzTransferProposalDetails(cafId, clearanceId, crzId, crzTransferProposalDetailsV2);
	}
	
	@PostMapping("/getForm")
	public ResponseEntity<Object> getCrzTransferForm(@RequestParam Integer id){
		
		return crzTransferServiceV2.getCrzTransferForm(id);
	}
	
	@PostMapping("/saveEnclosureDetails")
	public ResponseEntity<Object> saveCrzEnclosureDetails(@RequestParam Integer crzTransferId, @RequestBody CRZTransferEnclosureDetailsV2 crzTransferEnclosureDetailsV2){
		
		return crzTransferServiceV2.saveCrzEnclosureDetails(crzTransferId, crzTransferEnclosureDetailsV2);
	}
	
	@PostMapping("/saveUndertaking")
	public ResponseEntity<Object> saveUndertaking(@RequestParam Integer crzTransferId, @RequestBody CRZTransferUndertakingV2 crzTransferUndertakingV2){
		
		return crzTransferServiceV2.saveUndertaking(crzTransferId, crzTransferUndertakingV2);
	}

}
