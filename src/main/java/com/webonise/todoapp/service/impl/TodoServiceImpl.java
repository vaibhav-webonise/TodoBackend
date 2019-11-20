package com.webonise.todoapp.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.webonise.todoapp.Exception.TodoNotExistByGivenIdException;
import com.webonise.todoapp.Exception.TodosNotExistException;
import com.webonise.todoapp.dao.TodoRepository;
import com.webonise.todoapp.model.Todo;
import com.webonise.todoapp.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {

  @Autowired
  private TodoRepository todoRepository;
  private Logger log = (Logger) LoggerFactory.getLogger(TodoService.class);
  private static final int COUNT_ZERO = 0;

  public List<Todo> getTodos() {
    if (todoRepository.count() == COUNT_ZERO) {
      throw new TodosNotExistException();
    }
    return todoRepository.findAll();
  }

  public Todo saveTodo(Todo todo) {
    Todo savedTodo = todoRepository.save(todo);
    log.info("New todo added:{}", savedTodo.toString());
    return savedTodo;
  }

  public ResponseEntity<?> deleteTodo(int id) {
    if (todoRepository.existsById(id)) {
      int rowsDeleted = todoRepository.deleteTodoById(id);
      if (rowsDeleted > COUNT_ZERO) {
        log.info("{} todo deleted with id {}", rowsDeleted, id);
        return ResponseEntity.ok("Todo Successfully deleted with id" + id);
      }
    }
    throw new TodoNotExistByGivenIdException();
  }

  public Todo updateTodo(Todo todo) {
    Todo updatedTodo = todoRepository.save(todo);
    log.info("todo updated:{}", updatedTodo.toString());
    return updatedTodo;
  }
}
