package com.webonise.todoapp.service.impl;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.webonise.todoapp.Exception.UserNotExistsException;
import com.webonise.todoapp.dao.UserRepository;
import com.webonise.todoapp.model.UserData;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    Optional<UserData> userData = userRepository.findById(username);
    if (userData.isPresent()) {
      return new User(userData.get().getUsername(), userData.get().getPassword(), new ArrayList<>());
    } else {
      throw new UserNotExistsException("User does not exist");
    }
  }
}
