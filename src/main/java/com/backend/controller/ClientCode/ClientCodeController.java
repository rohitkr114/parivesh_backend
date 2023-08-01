package com.backend.controller.ClientCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.service.ClientCode.ClientCodeService;

@RestController
@RequestMapping("/clientCode")
public class ClientCodeController {
	
	@Autowired
	private ClientCodeService clientCodeService;
	
	@PostMapping("/get")
	public ResponseEntity<Object> getClientCodeData(@RequestParam Integer id)throws PariveshException{
		
			return clientCodeService.getClientCodeData(id);
		
	} 
	
	
}
