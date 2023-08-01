package com.backend.controller;


import com.backend.exceptions.PariveshException;
import com.backend.service.FCStateCertificateHeaderService;
import com.backend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fc/stateCertificate")
public class FCStateCertificateController {

	@Autowired
	FCStateCertificateHeaderService fcStateCertificateHeaderService;

	@RequestMapping(value = "/getHeader", method = RequestMethod.POST)
	public ResponseEntity<Object> getHeader(
			@RequestParam(value = "state_code") Integer state_code) throws PariveshException {

		return fcStateCertificateHeaderService.getHeader(state_code);
	}

	
	@RequestMapping(value = "/saveHeader", method = RequestMethod.POST)
	public ResponseEntity<Object> saveHeader(@RequestBody FCStateCertificateHeader fcStateCertificateHeader) throws PariveshException{

		return fcStateCertificateHeaderService.saveHeader(fcStateCertificateHeader);
	}

	@RequestMapping(value="/deleteHeader", method=RequestMethod.POST)
	public ResponseEntity<Object> deleteHeader(@RequestParam Integer id) throws PariveshException{
		return fcStateCertificateHeaderService.deleteHeader(id);
	}

}
