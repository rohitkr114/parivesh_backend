package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.service.ScenarioService;

@RestController
@RequestMapping("/scenario/")
public class ScenarioController {

	@Autowired
	ScenarioService scenarioService;
	
	@PostMapping("getScenarios")
	public ResponseEntity<Object> getCMS(@RequestParam(name="area")Double area){
		return scenarioService.getScenario(area);
	}
}
