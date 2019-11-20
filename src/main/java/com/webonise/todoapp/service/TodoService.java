package com.webonise.todoapp.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.webonise.todoapp.model.Todo;

public interface TodoService {
  /*
   * This service interface provides the following methods 
   * getTodos(): This will help to retrieve all todo records available in the database 
   * saveTodo(): This method will save the todo in the database 
   * deleteTodo(): This method will delete the specific todo with given id 
   * updateTodo(): This method will update the existing todo in the database
   */
  public List<Todo> getTodos();
  public Todo saveTodo(Todo todo);
  public ResponseEntity<?> deleteTodo(int id);
  public Todo updateTodo(Todo todo);
}
