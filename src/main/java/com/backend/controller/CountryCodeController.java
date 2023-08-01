package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.CountryCode;
import com.backend.service.CountryCodeService;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/kya/")
public class CountryCodeController {
	
	@Autowired
	CountryCodeService countryCodeService;
	
	@GetMapping("/getcountrycodes")
	public ResponseEntity<Object> getStates() {
		ResponseEntity<Object> codes=countryCodeService.getAllCountryCode();
		return codes;
	}

}
