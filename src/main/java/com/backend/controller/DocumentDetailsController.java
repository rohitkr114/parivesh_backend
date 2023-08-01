package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.DocumentDetails;
import com.backend.service.DocumentDetailsService;

@RestController
@RequestMapping("/documentdetails/")
public class DocumentDetailsController {

	@Autowired
	DocumentDetailsService documentDetailsService; 
	
	@RequestMapping(value = "/addDocumentDetail", method = RequestMethod.POST)
	public ResponseEntity<Object> addDocumentDetails(@RequestBody DocumentDetails documentDetails) {
			return documentDetailsService.addDocumentDetails(documentDetails);
	}
	
	@RequestMapping(value = "/getDocumentDetail", method = RequestMethod.POST)
	public ResponseEntity<Object> addDocumentDetails(@RequestParam Integer id) {
			return documentDetailsService.getDocumentDetails(id);
	}
	
	@RequestMapping(value = "/deleteDocumentDetail", method = RequestMethod.POST)
	public ResponseEntity<Object> deleteDocumentDetails(@RequestParam(value = "Id",required = true) Integer id) {
			return documentDetailsService.deleteDocumentDetails(id);
	}
}
