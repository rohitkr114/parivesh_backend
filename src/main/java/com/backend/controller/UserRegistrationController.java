package com.backend.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.exceptions.PariveshException;
import com.backend.model.Organisation;
import com.backend.model.ProjectProponentEntity;
import com.backend.model.ProjectProponentGovernment;
import com.backend.model.ProjectProponentIndividual;
import com.backend.model.ProjectProponentOther;
import com.backend.model.User;
import com.backend.model.VerificationToken;
import com.backend.repository.postgres.GenCodeMasterRepository;
import com.backend.response.ResponseHandler;
import com.backend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

//@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserRegistrationController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private GenCodeMasterRepository genCodeMasterRepository;

	@GetMapping("/registrationTypes")
	public ResponseEntity<Object> getRegistrationTypes() {
		return ResponseHandler.generateResponse("Registration types", HttpStatus.OK, "",
				genCodeMasterRepository.findAllByParentGrp("REGISTRATION_TYPE"));

	}

	@GetMapping("/entityTypes")
	public ResponseEntity<Object> getEntityTypes() {
		return ResponseHandler.generateResponse("Registration types", HttpStatus.OK, "", userService.getEntityTypes());

	}

	@GetMapping("/duplicatePan")
	public ResponseEntity<Object> duplicatePan(@RequestParam("pan") String pan,
			@RequestParam("entityType") String type) {
		return ResponseHandler.generateResponse("Check Duplicate PAN", HttpStatus.OK, "",
				userService.duplicatePan(pan, type));

	}

	@GetMapping("/duplicateCIN")
	public ResponseEntity<Object> duplicateCIN(@RequestParam("CIN") String CIN,
			@RequestParam("entityType") String type) {
		return ResponseHandler.generateResponse("Check Duplicate CIN", HttpStatus.OK, "",
				userService.duplicateCIN(CIN, type));

	}

	@GetMapping("/duplicateEmail")
	public ResponseEntity<Object> duplicateEmail(@RequestParam("email") @Valid @Email String email,
			@RequestParam("entityType") String type) {
		return ResponseHandler.generateResponse("Check Duplicate Email", HttpStatus.OK, "",
				userService.duplicateEmail(email, type));

	}
	


	@GetMapping("/duplicateEntity")
	public ResponseEntity<Object> duplicateEntity(@RequestParam("entity") String entity,
			@RequestParam(value = "state", required = false) String state) {
		return ResponseHandler.generateResponse("Check Duplicate Entity", HttpStatus.OK, "",
				userService.duplicateEntity(entity, state));

	}

	@GetMapping("/verifyEmail")
	public ResponseEntity<Object> verifyEmail(@RequestParam("token") String token) {
		VerificationToken verificationToken = userService.getVerificationToken(token);
		if (verificationToken == null) {
			logger.info("In if case when verification token is null");
			return ResponseHandler.generateResponse("Verify Email", HttpStatus.EXPECTATION_FAILED, "Exception",
					"Token is Invalid");
		}
		User user = verificationToken.getUser();
		Calendar cal = Calendar.getInstance();
		if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			logger.info("In if verifyEmail for Token Expiry Case");
			return ResponseHandler.generateResponse("Verify Email", HttpStatus.EXPECTATION_FAILED, "Exception",
					"Token has been expires");
		} else {
			logger.info("In else verifyEmail for enabling active field");
			user.setIs_active(true);
			user.setIsActive(true);
			userService.saveRegisteredUser(user);
		}
		return ResponseHandler.generateResponse("Verify Email", HttpStatus.OK, "", user);

	}

	@GetMapping("/verifyToken")
	public ResponseEntity<Object> verifyToken(@RequestParam("token") String token) {
		VerificationToken verificationToken = userService.getVerificationToken(token);
		try {
			if (verificationToken == null) {
				logger.info("In if case when verification token is null");
				return ResponseHandler.generateResponse("Verify Token", HttpStatus.OK, "Exception", "Token is Invalid");
			}
			Calendar cal = Calendar.getInstance();
			if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
				logger.info("In if verifyToken in case of invalid and expired token");
				return ResponseHandler.generateResponse("Verify Token", HttpStatus.OK, "Exception",
						"Token has been expired");
			} else {
				logger.info("In else verifyToken in case of valid and active Token");
				return ResponseHandler.generateResponse("Verify Token", HttpStatus.OK, "Exception",
						"Token not expired");
			}
		} catch (Exception ex) {
			log.error("=======================>>>>>>>>>>>" + ex.toString() + " " + ex.getStackTrace()[0]);
			throw new PariveshException("Error in Verify token -" + token, ex);
		}
	}

	@PostMapping("/registerUser")
	public ResponseEntity<Object> registerUser(@RequestBody @Valid ProjectProponentIndividual individual,
			@RequestParam("entityType") String type, HttpServletRequest request) {
		return userService.addUserRegistration(individual, type, request);
	}

	@PostMapping(value = "/registerEntity")
	public ResponseEntity<Object> registerUser(@RequestBody @Valid ProjectProponentEntity proponent,
			@RequestParam("entityType") String type, HttpServletRequest request) throws IOException {
		return userService.addEntityRegistration(proponent, type, request);
	}

	@PostMapping(value = "/registerOthers")
	public ResponseEntity<Object> registerOthers(@RequestBody @Valid ProjectProponentOther proponent,
			@RequestParam(value = "entityType") String type, HttpServletRequest request) throws IOException {
		return userService.addOtherRegistration(proponent, type, request);
	}

	@PostMapping(value = "/registerGovernment")
	public ResponseEntity<Object> registerGovernment(@RequestBody @Valid ProjectProponentGovernment proponent,
			@RequestParam("entityType") String type, HttpServletRequest request) throws IOException {
		return userService.addGovernmentRegistration(proponent, type, request);
	}
	
	@PostMapping(value="/resendMail")
	public ResponseEntity<Object> resendMail(@RequestParam String email) {
		return userService.resendMail(email);
	}

	@PostMapping(value="/resendMail/V2")
	public ResponseEntity<Object> resendMailV2(@RequestBody String email) {
		return userService.resendMail(email);
	}
	
	@PostMapping(value = "/migrateEmail")
	public ResponseEntity<Object> migrateMails(@RequestBody ArrayList<String> emails) {
		return userService.migrateEmails(emails);
	}
	
	@GetMapping(value = "/searchOrganization")
	public List<com.backend.model.Organisation> searchOrganization(@RequestParam String name) {
		return userService.searchOrganization(name);
	}
	

}
