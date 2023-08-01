package com.backend.controller.EcFormVPart2;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.EcFormVPart2Model.EcFormVPart2;
import com.backend.model.EcFormVPart2Model.EcFormVPart2PublicHearingdetails;
import com.backend.model.EcFormVPart2Model.EcFormVPart2Undertaking;
import com.backend.model.EcFormVPart2Model.EcFormVpart2HearingIssues;
import com.backend.service.EcFormVPart2.EcFormVPart2Service;

@RestController
@RequestMapping("/ecFormVPart2")
public class EcFormVPart2Controller {

	@Autowired
	private EcFormVPart2Service ecFormVPart2Service;

	@PostMapping("/save")
	public ResponseEntity<Object> saveEcFormVPart2(@RequestBody EcFormVPart2 ecFormVPart2, @RequestParam Integer clearance_id)
			throws PariveshException {
		return ecFormVPart2Service.saveEcFormVPart2(ecFormVPart2, clearance_id);
	}

	@PostMapping("/get")
	public ResponseEntity<Object> getEcFormVPart2(@RequestParam Integer id) throws PariveshException {
		return ecFormVPart2Service.getEcFormVPart2(id);
	}
	
	@PostMapping("/saveUndertaking")
	public ResponseEntity<Object> saveUndertaking(@RequestParam Integer id,
			@RequestBody EcFormVPart2Undertaking ecFormVPart2Undertaking, HttpServletRequest request)
			throws PariveshException {
		return ecFormVPart2Service.saveUndertaking(id, ecFormVPart2Undertaking, request);
	}

	@PostMapping("/getByTorId")
	public ResponseEntity<Object> getFormFiveParttTwoByTorId(@RequestParam Integer torId) throws PariveshException {
		return ecFormVPart2Service.getFormFiveParttTwoByTorId(torId);
	}

}

//	@PostMapping("/savePublicHearingDetails")
//	public ResponseEntity<Object> savePublicHearingdetails(
//			@RequestBody List<EcFormVPart2PublicHearingdetails> ecFormVPart2PublicHearingdetails,
//			@RequestParam Integer ec_form_v_part2_id) throws PariveshException {
//		return ecFormVPart2Service.savePublicHearingdetails(ec_form_v_part2_id, ecFormVPart2PublicHearingdetails);
//	}
//
//	@PostMapping("/deletePublicHearingDetails")
//	public ResponseEntity<Object> deletePublicHearingdetails(@RequestParam Integer ph_id) throws PariveshException {
//		return ecFormVPart2Service.deletePublicHearingdetails(ph_id);
//	}
//
//	@PostMapping("/saveHearingIssues")
//	public ResponseEntity<Object> saveHearingIssues(
//			@RequestBody List<EcFormVpart2HearingIssues> ecFormVpart2HearingIssues,
//			@RequestParam Integer ec_form_v_part2_id) throws PariveshException {
//		return ecFormVPart2Service.saveHearingIssues(ec_form_v_part2_id, ecFormVpart2HearingIssues);
//	}
//
//	@PostMapping("/deleteHearingIssues")
//	public ResponseEntity<Object> deleteHearingIssues(@RequestParam Integer hi_id) throws PariveshException {
//		return ecFormVPart2Service.deleteHearingIssues(hi_id);
//	}


