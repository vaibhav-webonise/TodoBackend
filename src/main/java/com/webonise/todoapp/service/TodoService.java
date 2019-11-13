package com.webonise.todoapp.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.webonise.todoapp.model.Todo;

public interface TodoService {
	public List<Todo> getTodos();
	public Todo saveTodo(Todo todo);
	public ResponseEntity<?> deleteTodo(int id);
	public Todo updateTodo(Todo todo);
}
