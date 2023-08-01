package com.backend.parivesh.nic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.client.UserRegistrationAPI;
import com.backend.model.Employee;
import com.backend.model.Organisation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserRegistrationService {

	@Autowired
	private UserRegistrationAPI userRegistrationAPI;
	
	public void UserRegistration(Employee employee, Organisation organisation, String password) {
		UserRegistration registration = new UserRegistration(employee, organisation, password);
		ResponseEntity<Object> response = userRegistrationAPI.saveUserRegistration(registration);
		System.out.println(response.toString());
		
		log.info(response.toString());
	}
}
