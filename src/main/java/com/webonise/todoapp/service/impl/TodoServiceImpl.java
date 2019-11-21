package com.webonise.todoapp.service.impl;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.webonise.todoapp.Exception.FailedToSaveEntityException;
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
  private static final int EMPTY_LIST = 0;

  @Override
  public List<Todo> getTodos() {
    if (todoRepository.count() == EMPTY_LIST) {
      throw new TodosNotExistException("No todos exist");
    }
    return todoRepository.findAll();
  }

  @Override
  public Optional<Todo> saveTodo(Todo todo) {
    Optional<Todo> savedTodo = Optional.ofNullable(todoRepository.save(todo));
    if (savedTodo.isPresent()) {
      log.info("New todo added:{}", savedTodo.toString());
      return savedTodo;
    } else {
      throw new FailedToSaveEntityException("There is an issue while saving todo");
    }
  }

  @Override
  public ResponseEntity<Object> deleteTodo(int id) {
    if (todoRepository.existsById(id)) {
      if (todoRepository.deleteTodoById(id) > COUNT_ZERO) {
        log.info("todo deleted with id {}", id);
        return ResponseEntity.ok("Todo Successfully deleted with id" + id);
      }
    }
    throw new TodoNotExistByGivenIdException("todo not exist with given id");
  }

  @Override
  public ResponseEntity<Object> updateTodoById(Todo todo) {
    if (todoRepository.updateTodo(todo.getId(), todo.getDesc()) > COUNT_ZERO) {
      log.info("todo updated with id {}", todo.getId());
      return ResponseEntity.ok("Todo successfully updated");
    } else {
      throw new FailedToSaveEntityException("There is an issue while updating todo");
    }
  }
}
