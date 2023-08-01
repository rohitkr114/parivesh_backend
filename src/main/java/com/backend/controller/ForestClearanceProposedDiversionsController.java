package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.ForestClearanceDivisionPatchDetails;
import com.backend.model.ForestClearancePatchKmls;
import com.backend.model.ForestClearanceProposedDiversions;
import com.backend.service.ForestClearancePatchKmlsService;
import com.backend.service.ForestClearanceProposedDiversionsService;

@RestController
@RequestMapping("/forestClearanceProposedDiversionDetails/")
public class ForestClearanceProposedDiversionsController {

	@Autowired
	ForestClearanceProposedDiversionsService forestClearanceProposedDiversionsService;

	@PostMapping("/save")
	public ResponseEntity<Object> saveForestKmlsDetails(@RequestParam(name = "fc_id", required = false) Integer fc_id,
			@RequestParam(name = "wl_id", required = false) Integer wl_id,
			@RequestBody List<ForestClearanceProposedDiversions> forestClearanceProposedDiversions) {
		ResponseEntity<Object> status;
		if (fc_id != null)
			status = forestClearanceProposedDiversionsService.saveForestProposedDiversionsDetails(fc_id,
					forestClearanceProposedDiversions);
		else
			status = forestClearanceProposedDiversionsService.saveWLProposedDiversionsDetails(wl_id,
					forestClearanceProposedDiversions);
		return status;
	}

	@GetMapping("/list")
	public ResponseEntity<Object> getForestKmlsDetails(@RequestParam(value = "fc_id", required = false) Integer fc_id,
			@RequestParam(name = "wl_id", required = false) Integer wl_id) {

		if (fc_id != null)
			return forestClearanceProposedDiversionsService.getForestClearancePropesdDiversionData(fc_id);
		else
			return forestClearanceProposedDiversionsService.getWLPropesdDiversionData(wl_id);
	}

	@PostMapping("/delete")
	public ResponseEntity<Object> deleteProposedDiversionData(@RequestParam(value = "id") Integer id) {
		return forestClearanceProposedDiversionsService.deleteForestClearancePropesdDiversionData(id);
	}
	
	
	@PostMapping("/intersection/delete")
	public ResponseEntity<Object> deleteIntersection(@RequestParam (value = "fcProposedDiversionsDetails_id") Integer id){
		return forestClearanceProposedDiversionsService.deleteIntersection(id);
	}
	/*@PostMapping("/save/FCDivisionPatchDetails")
	public ResponseEntity<Object> addFCDivisionPatchDetails(
			@RequestBody List<ForestClearanceDivisionPatchDetails> forestClearanceDivisionPatchDetails,
			@RequestParam Integer FCProposedDiversionid) {
		return forestClearanceProposedDiversionsService.addFCDivisionPatchDetails(forestClearanceDivisionPatchDetails,
				FCProposedDiversionid);
	}*/
}
