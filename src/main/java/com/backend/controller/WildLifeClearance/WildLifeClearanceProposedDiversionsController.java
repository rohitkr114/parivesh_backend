package com.backend.controller.WildLifeClearance;

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
import com.backend.model.WildLifeClearance.WildLifeClearanceProposedDiversions;
import com.backend.service.ForestClearancePatchKmlsService;
import com.backend.service.ForestClearanceProposedDiversionsService;
import com.backend.service.WildLifeService.WildLifeClearanceProposedDiversionsService;

@RestController
@RequestMapping("/wildLifeClearanceProposedDiversionDetails/")
public class WildLifeClearanceProposedDiversionsController {

	@Autowired
	WildLifeClearanceProposedDiversionsService wildLifeClearanceProposedDiversionsService;

	@PostMapping("/save")
	public ResponseEntity<Object> saveWildLifeKmlsDetails(@RequestParam(name = "wl_id", required = false) Integer wl_id,
			@RequestBody List<WildLifeClearanceProposedDiversions> widlifeClearanceProposedDiversions) 
	{
		ResponseEntity<Object> status = null;
		if (wl_id != null)
			status = wildLifeClearanceProposedDiversionsService.saveWLProposedDiversionsDetails(wl_id,
					widlifeClearanceProposedDiversions);
		return status;
	}

	@GetMapping("/list")
	public ResponseEntity<Object> getWildLifeKmlsDetails(@RequestParam(name = "wl_id", required = false) Integer wl_id) {
		
			return wildLifeClearanceProposedDiversionsService.getWLPropesdDiversionData(wl_id);
	}

	@PostMapping("/delete")
	public ResponseEntity<Object> deleteProposedDiversionData(@RequestParam(value = "id") Integer id) {
		return wildLifeClearanceProposedDiversionsService.deleteWildLifeClearancePropesdDiversionData(id);
	}
	
	
	@PostMapping("/intersection/delete")
	public ResponseEntity<Object> deleteIntersection(@RequestParam (value = "wlProposedDiversionsDetails_id") Integer id){
		return wildLifeClearanceProposedDiversionsService.deleteIntersection(id);
	}

}
