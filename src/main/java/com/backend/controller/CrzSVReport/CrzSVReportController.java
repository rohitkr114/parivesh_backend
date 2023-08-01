package com.backend.controller.CrzSVReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.CrzSVReport;
import com.backend.model.CrzSiteVisitCommittee;
import com.backend.response.ResponseHandler;
import com.backend.service.CrzSVReport.CrzSVReportService;

@RestController
public class CrzSVReportController {

	@Autowired
	CrzSVReportService crzSVReportService;
	
	@PostMapping("/saveCrzSVReport")
	public ResponseEntity<Object> saveCrzSVReport(@RequestBody CrzSVReport crzSiteVisitForm,
			@RequestParam(required = false) Integer caf_id) throws PariveshException {
		return ResponseHandler.generateResponse("Save Crz Site Visit Form", HttpStatus.OK, "",
				crzSVReportService.saveCrzSVReport(crzSiteVisitForm, caf_id));
	}

	@PostMapping("/getCrzSVReport")
	public ResponseEntity<Object> getCrzSVReport(@RequestParam("crzSVRId") Integer crzSVRid) throws PariveshException {
		return ResponseHandler.generateResponse("Get Crz Site Visit Form", HttpStatus.OK, "",
				crzSVReportService.getCrzSVReport(crzSVRid));
	}

	@PostMapping("/saveCrzSVCommittee")
	public ResponseEntity<Object> saveCrzSVCommittee(@RequestBody CrzSiteVisitCommittee crzSVRCommittee)
			throws PariveshException {
		return ResponseHandler.generateResponse("Save CrzSVRCommittee", HttpStatus.OK, "",
				crzSVReportService.saveCrzSVCommittee(crzSVRCommittee));
	}

	@PostMapping("/getCrzSVCommittee")
	public ResponseEntity<Object> getCrzSVCommittee(@RequestParam("id") Integer id) {

		return ResponseHandler.generateResponse("Get CrzSVRCommittee", HttpStatus.OK, "",
				crzSVReportService.getCrzSVCommittee(id));
	}

	@PostMapping("/deleteCrzSVCommittee")
	public ResponseEntity<Object> deleteCrzSVCommittee(@RequestParam("id") Integer id) {

		return ResponseHandler.generateResponse("Delete CrzSVRCommittee", HttpStatus.OK, "",
				crzSVReportService.deleteCrzSVCommittee(id));
	}
}
