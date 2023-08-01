package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.UserPrincipal;
import com.backend.model.CAFKMLPlots;
import com.backend.model.CafKML;
import com.backend.model.CafLocationOfKml;
import com.backend.model.CafOthers;
import com.backend.model.CafProjectActivityCost;
import com.backend.model.CommonFormDetail;
import com.backend.security.CurrentUser;
import com.backend.service.CommonFormDetailService;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/caf")
public class CommonFormDetailController {

	@Autowired
	private CommonFormDetailService commonFormDetailService;
	
	@PostMapping("/saveCafDetails")
	public ResponseEntity<Object> saveCafDetails(@RequestParam("project_id") Integer id,@RequestBody CommonFormDetail commonFormDetail, @CurrentUser UserPrincipal currentuser) {
		return commonFormDetailService.saveProjectDetails(id,commonFormDetail,currentuser.getId());
	}
	
	@PostMapping("/saveCafLocationKml")
	public ResponseEntity<Object> saveCafLocationKml(@RequestParam("caf_id") Integer id,@RequestBody CafLocationOfKml cafLocationOfKml) {
		return commonFormDetailService.saveCafLocationDetails(id,cafLocationOfKml);
	}
	
	@PostMapping("/saveCafprojectActivityCost")
	public ResponseEntity<Object> saveCafprojectActivityCost(@RequestParam("caf_id") Integer id,@RequestBody CafProjectActivityCost cafProjectActivityCost) {
		return commonFormDetailService.saveCafProjectActivityCost(id,cafProjectActivityCost);
	}
	
	@PostMapping("/saveCafOthers")
	public ResponseEntity<Object> saveCafOthers(@RequestParam("caf_id") Integer id,@RequestBody CafOthers cafOthers) {
		return commonFormDetailService.saveCafOthers(id,cafOthers);
	}
	
	@PostMapping("/saveCafKML")
	public ResponseEntity<Object> saveCafKML(@RequestParam("caf_id") Integer id,@RequestBody List<CafKML> cafKML) {
		return commonFormDetailService.saveCafKML(id, cafKML);
	}
	
	@PostMapping("/deleteCafKML")
	public ResponseEntity<Object> deleteCafKML(@RequestParam("caf_kml_id") Integer id) {
		return commonFormDetailService.deleteCafKML(id);
	}
	
	@PostMapping("/getCafKML")
	public ResponseEntity<Object> getCafKML(@RequestParam("id") Integer id) {
		return commonFormDetailService.getCafKML(id);
	}
	
	@PostMapping("/getCafKMLbyCAFId")
	public ResponseEntity<Object> getCafKMLbyCAFID(@RequestParam("caf_id") Integer id) {
		return commonFormDetailService.getCafKMLbyCAFId(id);
	}
	
	@PostMapping("/saveCafKMLPlots")
	public ResponseEntity<Object> saveCafKMLPlots(@RequestParam("caf_kml_id") Integer id,@RequestBody CAFKMLPlots cafKMLPlots) {
		return commonFormDetailService.saveCafKMLPlot(id, cafKMLPlots);
	}
	
	@PostMapping("/getCafKMLPlots")
	public ResponseEntity<Object> getCafKMLPlots(@RequestParam("id") Integer id) {
		return commonFormDetailService.getCafKMLPlot(id);
	}
	
	@PostMapping("/getCafDetails")
	public ResponseEntity<Object> getCafDetails(@RequestParam(name ="project_id", required = false) Integer project_id, @RequestParam(name = "caf_id", required = false) Integer caf_id) {
		if(project_id != null) {
			return commonFormDetailService.getCafDetailsByProjectId(project_id);
		} else {			
			return commonFormDetailService.getCafDetails(caf_id);
		}
	}
	
	@PostMapping("/getFormFillingEligibilty")
	public ResponseEntity<Object> getFilledStatus(@RequestParam(name ="caf_id") Integer caf_id, @RequestParam(name = "clearance_id") Integer clearance_id) {
		return commonFormDetailService.getFilledStatus(caf_id, clearance_id);
	}

	@PostMapping("/copyCafDetails")
	public ResponseEntity<Object> copyCafDetails(@RequestParam("caf_id") Integer id, @RequestParam(required = false) String amendment_form) {
		return commonFormDetailService.copyCafDetails(id, amendment_form);
	}

}
