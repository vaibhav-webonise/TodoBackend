package com.webonise.todoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.webonise.todoapp.model.User;
import com.webonise.todoapp.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/logIn")
	public Boolean logIn(@RequestBody User user) {
		return userService.verifyUser(user);
	}

	@PostMapping("/signIn")
	public Boolean signUp(@RequestBody User user) {
		return userService.addUser(user);
	}
}
