package com.webonise.todoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.webonise.todoapp.model.AuthenticationRequest;
import com.webonise.todoapp.model.UserData;
import com.webonise.todoapp.service.impl.UserServiceImpl;

@RestController
public class UserController {

  @Autowired
  private UserServiceImpl userServiceImpl;

  @PostMapping("/signup")
  public ResponseEntity<?> signUp(@RequestBody UserData userData) {
    return userServiceImpl.registerUser(userData);
  }

  @PostMapping("/login")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
    return userServiceImpl.logInUser(authenticationRequest);
  }
}
