package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.ActivityActionMapping;
import com.backend.service.ActivityActionMappingService;

@RestController
@RequestMapping("/activity/action/")
public class ActivityActionMappingController {

	@Autowired
	private ActivityActionMappingService actionMappingService;
	
	@PostMapping("save")
	public ResponseEntity<Object> saveActivityMapping(@RequestBody ActivityActionMapping activityActionMapping){
		return actionMappingService.saveActivityMapping(activityActionMapping);
	}
	
	@PostMapping("getById")
	public ResponseEntity<Object> getActivityMapping(@RequestParam Integer id){
		return actionMappingService.getActivityMappingById(id);
	}
	@PostMapping("getAllByClearenceId")
	public ResponseEntity<Object> getActivityMappingByClearenceId(@RequestParam Integer clearenceId){
		return actionMappingService.getActivityMappingByClearenceId(clearenceId);
	}
	
	@PostMapping("getAll")
	public ResponseEntity<Object> getAll(){
		return actionMappingService.getAllActivityMapping();
	}
	
}
