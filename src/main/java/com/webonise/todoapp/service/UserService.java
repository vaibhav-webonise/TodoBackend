package com.webonise.todoapp.service;

import org.springframework.http.ResponseEntity;
import com.webonise.todoapp.model.UserData;

public interface UserService {
  /*
   * This service interface provides following method that will add user in the database
   * And returns responseEntity object
   */
  public ResponseEntity<?> addUser(UserData userData);
}
