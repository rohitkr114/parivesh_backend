package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.model.CountryCode;
import com.backend.repository.postgres.CountryCodeRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CountryCodeService {
	
	@Autowired
	CountryCodeRepository countryCodeRepository;

	public ResponseEntity<Object> getAllCountryCode(){
		/*
		try {
		return (countryCodeRepository.getAllCountryCodes());
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
		*/
		try {
			log.info("INFO ------------ getAllCountryCode RETRIEVED - SUCCESS");
			return ResponseHandler.generateResponse("Country Codes",HttpStatus.OK,"",countryCodeRepository.getAllCountryCodes());
		}
		catch(Exception ex) {
			log.info("ERROR ------------ BAD_REQUEST: getAllCountryCode NOT RETRIEVED - FAILURE");
			return ResponseHandler.generateResponse("Country Codes",HttpStatus.BAD_REQUEST,"Exception Occurred",ex.getMessage());	
		}
		
	}
}
