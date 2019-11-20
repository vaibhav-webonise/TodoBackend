package com.webonise.todoapp.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.webonise.todoapp.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
  @Transactional
  int deleteTodoById(int id);
}
