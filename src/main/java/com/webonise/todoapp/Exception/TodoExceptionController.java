package com.webonise.todoapp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TodoExceptionController {
	@ExceptionHandler(value = TodosNotExistException.class)
	public ResponseEntity<Object> exception(TodosNotExistException exception) {
		return new ResponseEntity<>("Server response : No todos exist", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = TodoNotExistByGivenIdException.class)
	public ResponseEntity<Object> exception(TodoNotExistByGivenIdException exception) {
		return new ResponseEntity<>("Server response : No todo exists with given id", HttpStatus.NOT_FOUND);
	}
}
