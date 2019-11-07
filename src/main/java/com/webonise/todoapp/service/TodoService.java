package com.webonise.todoapp.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webonise.todoapp.Exception.TodoNotExistByGivenIdException;
import com.webonise.todoapp.Exception.TodosNotExistException;
import com.webonise.todoapp.dao.TodoRepository;
import com.webonise.todoapp.model.Todo;

@Service
public class TodoService {
	@Autowired
	private TodoRepository todoRepository;
	private Logger log = (Logger) LoggerFactory.getLogger(TodoService.class);

	public List<Todo> getTodos() {
		if (todoRepository.count() == 0) {
			throw new TodosNotExistException();
		}
		return todoRepository.findAll();
	}

	public Todo saveTodo(Todo todo) {
		todoRepository.save(todo);
		log.info("New todo added:{}", todo.toString());
		return todo;
	}

	public Boolean deleteTodo(int id) {
		if (!todoRepository.existsById(id)) {
			throw new TodoNotExistByGivenIdException();
		}
		todoRepository.deleteById(id);
		log.info("todo deleted with id:{}", id);
		return true;
	}

	public Boolean updateTodo(Todo todo) {
		todoRepository.save(todo);
		log.info("todo updated:{}", todo.toString());
		return true;
	}
}
