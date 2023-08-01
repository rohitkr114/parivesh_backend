package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.backend.model.SectorEntity;
import com.backend.repository.postgres.SectorEntityRepository;
import com.backend.service.ActivitySubActivitySectorService;

@RestController
@RequestMapping("/sector/")
public class SectorEntityController {

	@Autowired
	SectorEntityRepository sectorEntityRepository;
	
	@Autowired
	ActivitySubActivitySectorService activitySubActivitySectorService;
	
	@RequestMapping(value = "/getSector", method = RequestMethod.POST)
	public ResponseEntity<Object> newActivity(@RequestParam Integer ActId,@RequestParam Integer SubActId) {
		return activitySubActivitySectorService.getSector(ActId, SubActId);
		
	}
	
	@RequestMapping(value = "/newsector", method = RequestMethod.POST)
	public String newActivity(@RequestBody SectorEntity sectorEntity) {
		sectorEntityRepository.save(sectorEntity);
		return "SUCCESS";
	}
}
