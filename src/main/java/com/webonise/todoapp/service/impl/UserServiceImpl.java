package com.webonise.todoapp.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.webonise.todoapp.Exception.FailedToSaveEntityException;
import com.webonise.todoapp.Exception.InvalidCredentialsException;
import com.webonise.todoapp.Exception.UserExistsException;
import com.webonise.todoapp.Exception.UserNotExistsException;
import com.webonise.todoapp.dao.UserRepository;
import com.webonise.todoapp.model.AuthenticationRequest;
import com.webonise.todoapp.model.AuthenticationResponse;
import com.webonise.todoapp.model.UserData;
import com.webonise.todoapp.service.TodoService;
import com.webonise.todoapp.service.UserService;
import com.webonise.todoapp.util.JwtUtil;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder bcryptPasswordEncoder;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private JwtUtil jwtUtilToken;
  @Autowired
  private UserDetailsServiceImpl userDetailsServiceImpl;
  private Logger log = (Logger) LoggerFactory.getLogger(TodoService.class);

  @Override
  public ResponseEntity<Object> registerUser(UserData userData) {
    if (!userRepository.existsById(userData.getUsername())) {
      UserData newUser = new UserData();
      newUser.setUsername(userData.getUsername());
      newUser.setPassword(bcryptPasswordEncoder.encode(userData.getPassword()));
      Optional<UserData> savedUser = Optional.ofNullable(userRepository.save(newUser));
      if (savedUser.isPresent()) {
        log.info("new user is added:{}", savedUser.toString());
        return ResponseEntity.ok("User successfully registered");
      } else {
        throw new FailedToSaveEntityException("There is an issue to register user");
      }
    } else {
      throw new UserExistsException("User already exists with given username");
    }
  }

  @Override
  public ResponseEntity<Object> logInUser(AuthenticationRequest authenticationRequest) {
    if (!userRepository.existsById(authenticationRequest.getUsername())) {
      throw new UserNotExistsException("User not exists with given username, you need to sign up");
    } else {
      try {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
            authenticationRequest.getPassword()));
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(authenticationRequest.getUsername());
        String jwt = jwtUtilToken.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
      } catch (BadCredentialsException e) {
        throw new InvalidCredentialsException("Invalid password");
      }
    }
  }
}
