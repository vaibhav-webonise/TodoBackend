package com.webonise.todoapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.webonise.todoapp.Exception.UserExistsException;
import com.webonise.todoapp.dao.UserRepository;
import com.webonise.todoapp.model.UserData;
import com.webonise.todoapp.service.TodoService;
import com.webonise.todoapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder bcryptPasswordEncoder;
  private Logger log = (Logger) LoggerFactory.getLogger(TodoService.class);

  public ResponseEntity<?> addUser(UserData userData) {
    if (!userRepository.existsById(userData.getUsername())) {
      UserData newUser = new UserData();
      newUser.setUsername(userData.getUsername());
      newUser.setPassword(bcryptPasswordEncoder.encode(userData.getPassword()));
      userRepository.save(newUser);
      log.info("new user is added:{}", newUser.toString());
      return ResponseEntity.ok("User successfully registered");
    }
    throw new UserExistsException();
  }
}
