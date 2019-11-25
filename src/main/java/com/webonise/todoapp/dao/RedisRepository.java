package com.webonise.todoapp.dao;

import java.util.Map;
import org.springframework.data.domain.Pageable;
import com.webonise.todoapp.model.Todo;

public interface RedisRepository {

  public Map<Integer, Todo> getAllItems(Pageable pageable);
  public Todo getItem(int itemId);
  public void addItem(Todo item);
  public void deleteItem(int id);
  public void updateItem(Todo item);
}
