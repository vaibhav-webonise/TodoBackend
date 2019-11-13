package com.webonise.todoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.webonise.todoapp.model.UserData;
import com.webonise.todoapp.service.impl.UserServiceImpl;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	@Autowired
	private UserServiceImpl userService;

	@PostMapping("/signUp")
	public ResponseEntity<?> signUp(@RequestBody UserData userData) {
		return userService.addUser(userData);
	}
}
