package com.backend.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.ChangePassword;
import com.backend.dto.JwtRequest;
import com.backend.dto.JwtResponse;
import com.backend.response.ResponseHandler;
import com.backend.security.JwtTokenUtil;
import com.backend.security.JwtUserDetailsService;
import com.backend.service.UserService;

import io.jsonwebtoken.impl.DefaultClaims;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/user")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@PostMapping(value = "/authenticate")
	@Operation(summary = "Non-Secured endpoint")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {

		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		} catch (BadCredentialsException ex) {
//			loginService.IncrementFailedLoginAttempt(authenticationRequest.getUsername());
			throw new BadCredentialsException("Incorrect username or password");
		} catch (LockedException ex) {
			// sendUnlockAccountMail(authenticationRequest.getUsername());
			throw new BadCredentialsException(
					"User Account is Locked, this can be due to multiple wrong login attempts. Link is send to your email address use it to unlock your account.");
		}

//		user user = loginService.GetUserByName(authenticationRequest.getUsername()).orElseThrow(() -> new UserNotFoundException("User not found with email:"+authenticationRequest.getUsername()));
//		if(user.isIs_enabled()) {

		// Reset Login Failed attempts
//			loginService.ResetFailedLoginAttempt(authenticationRequest.getUsername());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token, new SimpleDateFormat("yyyy MM dd HH:mm:ss")
				.format(new Date(new Date().getTime() + JwtTokenUtil.JWT_TOKEN_VALIDITY))));
//		} else {
//			throw new UserNotFoundException("User account is not activated. Please activate the account first before login with credentials");
//		}

	}

//	@GetMapping(value = "/refreshtoken")
//	@Operation(summary = "Non-Secured endpoint")
	public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
		// From the HttpRequest get the claims
		DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");

		Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
		String token = jwtTokenUtil.doGenerateToken(expectedMap, expectedMap.get("sub").toString());
		return ResponseEntity.ok(new JwtResponse(token, new SimpleDateFormat("yyyy MM dd HH:mm:ss")
				.format(new Date(new Date().getTime() + JwtTokenUtil.JWT_TOKEN_VALIDITY))));
	}

	@PostMapping("/changepassword")
	public ResponseEntity<Object> ChangeUserPassword(@RequestBody ChangePassword changeRequest) {
		return userService.ChangeUserPassword(changeRequest);
	}

	public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
		Map<String, Object> expectedMap = new HashMap<String, Object>();
		for (Entry<String, Object> entry : claims.entrySet()) {
			expectedMap.put(entry.getKey(), entry.getValue());
		}
		return expectedMap;
	}

	private void authenticate(String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}
}
