package com.webonise.todoapp.service.impl;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.webonise.todoapp.dao.UserRepository;
import com.webonise.todoapp.model.UserData;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserData> userData = userRepository.findById(username);
		String usern=userData.get().getUsername();
		String pass=userData.get().getPassword();
		return new User(usern, pass,
				new ArrayList<>());
	}
}
