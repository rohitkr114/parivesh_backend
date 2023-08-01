package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.District;
import com.backend.service.DistrictService;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/kyc/")
public class DistrictController {

	@Autowired
	DistrictService districtService;
	
	@GetMapping("/getdistricts")
	public ResponseEntity<Object> getDistricts() {
		ResponseEntity<Object> districtList= districtService.getAllDistricts();
		return districtList;
	}
	
	@GetMapping("/getdistrictbyCode")
	public ResponseEntity<Object> getSubactivityById(@RequestParam Integer state_code) {
		return (districtService.getDistrictByCode(state_code));
	}
	
	@PostMapping("/addDistrict")
	public ResponseEntity<Object> addDistricts(@RequestBody List<District> districts,@RequestParam Integer state_id){
		return districtService.addDistricts(districts,state_id);
	}
	
	@PostMapping("/deleteDistricts")
	public ResponseEntity<Object> deleteDistricts(@RequestParam Integer districtId){
		return districtService.deleteDistricts(districtId);
	}
}
