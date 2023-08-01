package com.backend.service.ClientCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.exceptions.PariveshException;
import com.backend.model.ClientCode.ClientCode;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.ClientCode.ClientCodeRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientCodeService {
	
	@Autowired
	private ClientCodeRepository clientCodeRepository;
	
	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository; 
	
	public ResponseEntity<Object> getClientCodeData(Integer id){
		try {
		Integer state_code= proponentApplicationRepository.getStateCodeById(id);
		ClientCode clientCode= clientCodeRepository.getClientCodeByStateCode(state_code);
		
		return ResponseHandler.generateResponse("getting Client Code data", HttpStatus.OK, "", clientCode);
		} catch(Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Getting client code data for Id- " + id, e);
		}
		
		
		
	}
	

}
