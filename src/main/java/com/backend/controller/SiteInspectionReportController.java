package com.backend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.SiteInspectionReport;
import com.backend.service.SiteInspectionReportService;

@RestController
@RequestMapping("/siteInspectionReport")
public class SiteInspectionReportController {

	@Autowired
	private SiteInspectionReportService siteInspectionReportService;

	@PostMapping("/getDetails")
	public ResponseEntity<Object> getDetails(@RequestParam Integer id) {
		return siteInspectionReportService.getDetails(id);
	}

	@PostMapping("/saveDetails")
	public ResponseEntity<Object> saveDetails(@RequestBody SiteInspectionReport siteInspectionReport,
			HttpServletRequest request) {
		return siteInspectionReportService.saveDetails(siteInspectionReport, request);
	}
}
