package com.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.User;
import com.backend.model.UserEmailChangeLog;
import com.backend.repository.postgres.UserEmailChangeLogRepository;
import com.backend.repository.postgres.UserRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserEmailChangeLogService {

	@Autowired
	private UserEmailChangeLogRepository userEmailChangeLogRepository;

	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<Object> updateEmail(UserPrincipal principal,String currentEmail, String newEmail) {
		try {
			User user = userRepository.findById(principal.getId())
					.orElseThrow(() -> new ProjectNotFoundException("user not found for id " + principal.getId()));

			if (user.getEmailId().equalsIgnoreCase(currentEmail)) {

				Integer count = userRepository.getEmailCount(newEmail);
				if (count == 0) {
					UserEmailChangeLog response = new UserEmailChangeLog();
					response.setNewEmail(newEmail.toLowerCase());
					response.setOldEmail(user.getEmailId());

					user.setEmail(newEmail.toLowerCase());
					user.setEmailId(newEmail.toLowerCase());
					userRepository.save(user);

					UserEmailChangeLog changeLog = userEmailChangeLogRepository.save(response);

					return ResponseHandler.generateResponse("email change successful", HttpStatus.OK, "", changeLog);
				} else {
					return ResponseHandler.generateResponse("user already exists for email:" + newEmail, HttpStatus.CONFLICT,
							"", null);
				}
			} else {
				return ResponseHandler.generateResponse("current email does not match with email of user",
						HttpStatus.NOT_FOUND, "", null);
			}
		} catch (Exception e) {
			log.error("encountered exception", e);
			throw new PariveshException("error in updating email", e);
		}
	}

}
