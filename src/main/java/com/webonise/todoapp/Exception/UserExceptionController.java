package com.webonise.todoapp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionController {
	@ExceptionHandler(value = UserExistsException.class)
	public ResponseEntity<Object> exception(UserExistsException exception) {
		return new ResponseEntity<>("Server response : User already exists with given username", HttpStatus.FOUND);
	}
	
	@ExceptionHandler(value = UserNotExistsException.class)
	public ResponseEntity<Object> exception(UserNotExistsException exception) {
		return new ResponseEntity<>("Server response : User not exists", HttpStatus.NOT_FOUND);
	}
}
