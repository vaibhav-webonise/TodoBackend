package com.webonise.todoapp.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.webonise.todoapp.model.User;
import com.webonise.todoapp.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/getUser/{username}")
	public Optional<User> getCredentials(@PathVariable String username) {
		return userService.getUser(username);
	}
	
	@RequestMapping("/getUsers")
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
	@PostMapping("/addUser")
	public Boolean addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
}
