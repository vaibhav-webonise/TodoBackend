package com.webonise.todoapp.service;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import com.webonise.todoapp.model.Todo;

public interface TodoService {
  /*
   * This service method retrieves all todo records available in the database
   * @return: list of todos
   */
  public List<Todo> getTodos();

  /*
   * This method will save the todo in the database
   * @param todo object
   * @return todo object
   */
  public Optional<Todo> saveTodo(Todo todo);

  /*
   * This method will delete the specific todo with given id
   * @param todo id
   * @return responseEntity object with http ok status and success message
   */
  public ResponseEntity<Object> deleteTodo(int id);

  /*
   * This method will update the existing todo in the database
   * @param todo object
   * @return updated todo object
   */
  public ResponseEntity<Object> updateTodoById(Todo todo);
}
