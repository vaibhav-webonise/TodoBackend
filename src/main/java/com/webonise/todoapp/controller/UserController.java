package com.webonise.todoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.webonise.todoapp.Exception.InvalidCredentialsByUser;
import com.webonise.todoapp.Exception.UserNotExistsException;
import com.webonise.todoapp.dao.UserRepository;
import com.webonise.todoapp.model.AuthenticationRequest;
import com.webonise.todoapp.model.AuthenticationResponse;
import com.webonise.todoapp.model.UserData;
import com.webonise.todoapp.service.impl.UserDetailsServiceImpl;
import com.webonise.todoapp.service.impl.UserServiceImpl;
import com.webonise.todoapp.util.JwtUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	private AuthenticationManager auth;

	@Autowired
	private JwtUtil jwtutiltoken;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/signUp")
	public ResponseEntity<?> signUp(@RequestBody UserData userData) {
		return userServiceImpl.addUser(userData);
	}

	@PostMapping("/logIn")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {

		if (!userRepository.existsById(authenticationRequest.getUsername())) {
			throw new UserNotExistsException();
		}
		try {
			auth.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
					authenticationRequest.getPassword()));
			final UserDetails userDetails = userDetailsServiceImpl
					.loadUserByUsername(authenticationRequest.getUsername());
			final String jwt = jwtutiltoken.generateToken(userDetails);
			return ResponseEntity.ok(new AuthenticationResponse(jwt));
		} catch (BadCredentialsException e) {
			throw new InvalidCredentialsByUser();
		}
	}
}
