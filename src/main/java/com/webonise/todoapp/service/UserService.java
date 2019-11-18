package com.webonise.todoapp.service;

import org.springframework.http.ResponseEntity;
import com.webonise.todoapp.model.UserData;

public interface UserService {
  public ResponseEntity<?> addUser(UserData userData);
}
