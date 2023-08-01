package com.backend.controller.EcSVReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.EcSVReport.EcSiteVisitCommittee;
import com.backend.model.EcSVReport.EcSiteVisitReport;
import com.backend.response.ResponseHandler;
import com.backend.service.EcSVReport.EcSiteVisitReportService;

@RestController
public class EcSiteVisitReportController {

	@Autowired
	private EcSiteVisitReportService ecSiteVisitReportService;

	@PostMapping("/saveEcSVReport")
	public ResponseEntity<Object> saveEcSVReport(@RequestBody EcSiteVisitReport ecSiteVisitForm,
			@RequestParam(required = false) Integer caf_id) throws PariveshException {
		return ResponseHandler.generateResponse("Save Ec Site Visit Form", HttpStatus.OK, "",
				ecSiteVisitReportService.saveEcSVReport(ecSiteVisitForm, caf_id));
	}

	@PostMapping("/getEcSVReport")
	public ResponseEntity<Object> getEcSVReport(@RequestParam("ecSVRId") Integer ecSVRid) throws PariveshException {
		return ResponseHandler.generateResponse("Get Ec Site Visit Form", HttpStatus.OK, "",
				ecSiteVisitReportService.getEcSVReport(ecSVRid));
	}

	@PostMapping("/saveEcSVCommittee")
	public ResponseEntity<Object> saveEcSVCommittee(@RequestBody EcSiteVisitCommittee ecSVRCommittee)
			throws PariveshException {
		return ResponseHandler.generateResponse("Save EcSVRCommittee", HttpStatus.OK, "",
				ecSiteVisitReportService.saveEcSVCommittee(ecSVRCommittee));
	}

	@PostMapping("/getEcSVCommittee")
	public ResponseEntity<Object> getEcSVCommittee(@RequestParam("id") Integer id) {

		return ResponseHandler.generateResponse("Get EcSVRCommittee", HttpStatus.OK, "",
				ecSiteVisitReportService.getEcSVCommittee(id));
	}

	@PostMapping("/deleteEcSVCommittee")
	public ResponseEntity<Object> deleteEcSVCommittee(@RequestParam("id") Integer id) {

		return ResponseHandler.generateResponse("Delete EcSVRCommittee", HttpStatus.OK, "",
				ecSiteVisitReportService.deleteEcSVCommittee(id));
	}

}
