package com.webonise.todoapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webonise.todoapp.Exception.PasswordDoesNotMatchException;
import com.webonise.todoapp.Exception.UserExistsException;
import com.webonise.todoapp.Exception.UserNotExistsException;
import com.webonise.todoapp.dao.UserRepository;
import com.webonise.todoapp.model.User;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	private Logger log = (Logger) LoggerFactory.getLogger(TodoService.class);

	public Boolean verifyUser(User user) {
		if (!userRepository.existsById(user.getUsername())) {
			throw new UserNotExistsException();
		} else {
			if (userRepository.getOne(user.getUsername()).getPassword().equals(user.getPassword())) {
				return true;
			}
		}
		throw new PasswordDoesNotMatchException();
	}

	public Boolean addUser(User user) {
		if (!userRepository.existsById(user.getUsername())) {
			userRepository.save(user);
			log.info("new user is added:{}", user.toString());
			return true;
		}
		throw new UserExistsException();
	}
}
