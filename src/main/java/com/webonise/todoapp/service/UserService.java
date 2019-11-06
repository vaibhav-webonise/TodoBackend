package com.webonise.todoapp.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webonise.todoapp.Exception.TodosNotExistException;
import com.webonise.todoapp.Exception.UserExistsException;
import com.webonise.todoapp.Exception.UserNotExistsException;
import com.webonise.todoapp.dao.UserRepository;
import com.webonise.todoapp.model.User;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	Logger log = (Logger) LoggerFactory.getLogger(TodoService.class);

	public Optional<User> getUser(String username) {
		if (!userRepository.existsById(username)) {
			throw new UserNotExistsException();
		}
		return userRepository.findById(username);
	}

	public List<User> getUsers() {
		if (userRepository.count() == 0) {
			throw new TodosNotExistException();
		}
		return userRepository.findAll();
	}

	public Boolean addUser(User user) {
		if (!userRepository.existsById(user.getUsername())) {
			userRepository.save(user);
			log.info("new user is added::" + user.toString());
			return true;
		}
		throw new UserExistsException();
	}
}
