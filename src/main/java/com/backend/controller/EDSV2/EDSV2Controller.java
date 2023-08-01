package com.backend.controller.EDSV2;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.EDSV2.EDSFormV2;
import com.backend.model.EDSV2.EDSQueries;
import com.backend.security.CurrentUser;
import com.backend.service.EDSV2.EDSV2Service;

@RestController
@RequestMapping("/edsV2")
public class EDSV2Controller {
	
	@Autowired
	private EDSV2Service edsV2Service;
	
	@PostMapping("/saveEDS")
	public ResponseEntity<Object> saveEDSFormV2(@RequestBody EDSFormV2 edsFormV2, @RequestParam Integer clearance_id,
			@CurrentUser UserPrincipal principal,HttpServletRequest request) throws PariveshException{
					
		return edsV2Service.saveEDSFormV2(edsFormV2, clearance_id, principal,request);
	} 
	
	@PostMapping("/getEDS")
	public ResponseEntity<Object> getEdsFormV2(@RequestParam Integer id) throws PariveshException{
		
		return edsV2Service.getEdsFormV2(id);
	}
	
	@PostMapping("/deleteQuery")
	public ResponseEntity<Object> deleteEDSQueries(@RequestParam Integer id) throws PariveshException{
		
		return edsV2Service.deleteEDSQueries(id);
	}
	
	@PostMapping("/updateQuery")
	public ResponseEntity<Object> saveEDSQueries( @RequestBody EDSQueries query) throws PariveshException{
		
		return edsV2Service.updateEDSQueries(query);
	}
	
	@PostMapping("/getQueries")
	public ResponseEntity<Object> getEDSQueriesByHistoryId(@RequestParam Integer application_id ,@RequestParam(required= false) Integer eds_form_id) throws PariveshException{
		
		return edsV2Service.getEDSQueriesByHistoryId(application_id,eds_form_id);
	}
	
	
	@PostMapping("/getQueriesById")
	public ResponseEntity<Object> getEDSQueriesByHistoryId(@RequestParam(required= false) Integer eds_form_id) throws PariveshException{
		
		return edsV2Service.getEDSQueriesById(eds_form_id);
	}
	
	
	@PostMapping("/getQueriesByRole")
	public ResponseEntity<Object> getEdsQueriesByRole(@RequestParam Integer application_id,
			@CurrentUser UserPrincipal principal) throws PariveshException{
		
		return edsV2Service.getEdsQueriesByRole(application_id, principal);
	}

}
