package com.webonise.todoapp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionController {

	@ExceptionHandler(value = UserExistsException.class)
	public ResponseEntity<Object> exception(UserExistsException exception) {
		return new ResponseEntity<>("User already exists with given username", HttpStatus.FOUND);
	}

	@ExceptionHandler(value = UserNotExistsException.class)
	public ResponseEntity<Object> exception(UserNotExistsException exception) {
		return new ResponseEntity<>("User not exists, You need to sign up", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = PasswordDoesNotMatchException.class)
	public ResponseEntity<Object> exception(PasswordDoesNotMatchException exception) {
		return new ResponseEntity<>("Wrong password", HttpStatus.PRECONDITION_FAILED);
	}
}
