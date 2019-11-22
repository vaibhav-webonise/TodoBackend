package com.webonise.todoapp.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.webonise.todoapp.model.Todo;
import com.webonise.todoapp.service.impl.TodoServiceImpl;

@RestController
public class TodoController {
  @Autowired
  private TodoServiceImpl todoService;

  @RequestMapping("/todos/{pageNo}")
  public List<Todo> getTodos(@PathVariable int pageNo, @Value("${todo.page.size}") int pageSize) {
    return todoService.getTodos(pageNo, pageSize);
  }

  @PostMapping("/todos")
  public Optional<Todo> addTodo(@RequestBody Todo todo) {
    return todoService.saveTodo(todo);
  }

  @DeleteMapping("/todos/{id}")
  public ResponseEntity<?> deleteTodo(@PathVariable int id) {
    return todoService.deleteTodo(id);
  }

  @PutMapping("/todos")
  public ResponseEntity<Object> updateTodo(@RequestBody Todo todo) {
    return todoService.updateTodoById(todo);
  }
}
