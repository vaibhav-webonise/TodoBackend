package com.webonise.todoapp.service;

import org.springframework.http.ResponseEntity;
import com.webonise.todoapp.model.AuthenticationRequest;
import com.webonise.todoapp.model.UserData;

public interface UserService {
  /*
   * This service method adds user in the database
   * @param userData object
   * @return responseEntity object with the http ok status and success message
   */
  public ResponseEntity<Object> registerUser(UserData userData);

  /*
   * This service method authenticates the user
   * @param authentication object
   * @return responseEntity object with the http ok status and JWT token
   */
  public ResponseEntity<Object> logInUser(AuthenticationRequest authenticationRequest);
}
