package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.DraftFormData;
import com.backend.service.DraftFormDataService;

@RestController
@RequestMapping("/draftFormData")
public class DraftFormDataController {

	@Autowired
	private DraftFormDataService draftFormDataService;

	@PostMapping("/save")
	public ResponseEntity<Object> saveDraftFormData(@RequestBody DraftFormData formData) throws PariveshException {

		return draftFormDataService.saveDraftFormData(formData);
	}

	@PostMapping("/getFormDataForCompare")
	public ResponseEntity<Object> getFormDataForCompare(@RequestParam Integer formId,@RequestParam Integer
	applicationId) {

		return draftFormDataService.getFormDataForCompare(formId,applicationId);
	}
	@PostMapping("/getFormDataForCompareWithStep")
	public ResponseEntity<Object> getFormDataForCompareWithStep(@RequestParam Integer formId,@RequestParam Integer
			applicationId,@RequestParam Integer stepId) {

		return draftFormDataService.getFormDataForCompareWithStep(formId,applicationId,stepId);
	}

	@PostMapping("/getCurrentVersion")
	public ResponseEntity<Object> getDraftFormDataCurrentVersion(@RequestParam Integer stepId,
																 @RequestParam Integer formId,
																 @RequestParam(required=false) Integer applicationdId) {

		return draftFormDataService.getDraftFormDataCurrentVersion(formId,applicationdId,stepId);
	}

}
