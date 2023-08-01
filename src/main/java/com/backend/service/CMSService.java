package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.model.CMS;
import com.backend.repository.postgres.CMSRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CMSService {

	@Autowired
	private CMSRepository cmsRepository;

	public ResponseEntity<Object> updateCMS(CMS cms) {
		try {
			log.info("INFO ------------ updateCMS WITH argument CMS ---->"+cms+"- SUCCESS");
			return ResponseHandler.generateResponse("Update CMS By Abbr", HttpStatus.OK, "", cmsRepository.save(cms));
		} catch (Exception ex) {
			log.info("ERROR ------------ EXPECTATION_FAILED: updateCMS WITH argument CMS---->"+cms+" - FAILURE");
			return ResponseHandler.generateResponse("Update CMS By Abbr", HttpStatus.EXPECTATION_FAILED, "",
					ex.getMessage());
		}

	}

	public ResponseEntity<Object> getCMSByAbbr(String abbr) {

		try {
			CMS cmsTemp = cmsRepository.findByAbbr(abbr);
			log.info("INFO ------------ getCMSByAbbr WITH argument Abbr ---->"+abbr+"- SUCCESS");
			return ResponseHandler.generateResponse("Get CMS By Abbr", HttpStatus.OK, "", cmsTemp);
		} catch (Exception ex) {
			log.info("ERROR ------------ EXPECTATION_FAILED: getCMSByAbbr WITH argument Abbr ---->"+abbr+"- FAILURE");
			return ResponseHandler.generateResponse("Get CMS By Abbr", HttpStatus.EXPECTATION_FAILED, "",
					ex.getMessage());
		}

	}

	public ResponseEntity<Object> listCMS() {
		try {
			log.info("INFO ------------ listCMS : GET ALL - SUCCESS");
			return ResponseHandler.generateResponse("List CMS", HttpStatus.OK, "", cmsRepository.findAll());
		} catch (Exception ex) {
			log.info("ERROR ------------ EXPECTATION_FAILED: listCMS : GET ALL - FAILURE");
			return ResponseHandler.generateResponse("List CMS", HttpStatus.EXPECTATION_FAILED, "", ex.getMessage());
		}
	}

}
