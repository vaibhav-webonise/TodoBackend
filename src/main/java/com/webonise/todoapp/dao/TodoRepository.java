package com.webonise.todoapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.webonise.todoapp.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
}
