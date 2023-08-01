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

import com.backend.model.ForestClearancePatchKmls;
import com.backend.service.ForestClearancePatchKmlsService;

@RestController
@RequestMapping("/forestClearanceKmlsDetails/")
public class ForestClearancePatchKmlsController {

	@Autowired
	ForestClearancePatchKmlsService forestClearancePatchKmlsService;
	
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveForestKmlsDetails(@RequestParam(value = "fc_id") Integer id, @RequestBody List<ForestClearancePatchKmls> forestClearancePatchKmlsList) {
		
		ResponseEntity<Object> status = forestClearancePatchKmlsService.saveForestPatchKmlsDetails(id ,forestClearancePatchKmlsList);
		return status;
	}
	
	@GetMapping("/list")
	public ResponseEntity<Object> getForestKmlsDetails(@RequestParam(value = "fc_id") Integer id){
		
		return forestClearancePatchKmlsService.getForestKmlsData(id);
	}
	
	@PostMapping("delete")
	public ResponseEntity<Object> getForestKmlsDetails(@RequestBody List<Integer> fc_kml_Id){
		
		return forestClearancePatchKmlsService.deleteForestPatchKmlsDetails(fc_kml_Id);
	}
	
	
}
