package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.service.StateService;

@RestController
@RequestMapping("/village/")
public class VillageController {

	@Autowired
	StateService stateService;

	@PostMapping("getBySubDistrictCode")
	public ResponseEntity<Object> getProjectList(@RequestParam(name = "sub_district_code") int sub_district_code) {
		return stateService.getVillagesBySubDistrictCode(sub_district_code);
	}

}
