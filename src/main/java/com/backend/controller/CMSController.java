package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.CMS;
import com.backend.service.CMSService;

@RestController
@RequestMapping("/cms/")
public class CMSController {
	
	@Autowired
	CMSService cmsService;

	@PostMapping("update")
	public ResponseEntity<Object> updateCMS(@RequestBody CMS cms){
		return cmsService.updateCMS(cms);
	}
	
	@PostMapping("getByAbbr")
	public ResponseEntity<Object> getCMS(@RequestParam(name="abbr")String Abbr){
		return cmsService.getCMSByAbbr(Abbr);
	}
	
	@PostMapping("list")
	public ResponseEntity<Object> listCMS(){
		return cmsService.listCMS();
	}
	
}
