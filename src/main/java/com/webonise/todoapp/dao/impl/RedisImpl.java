package com.webonise.todoapp.dao.impl;

import java.util.Map;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.webonise.todoapp.dao.RedisRepository;
import com.webonise.todoapp.model.Todo;

@Repository
public class RedisImpl implements RedisRepository {

  public static final String KEY = "Todo";
  private RedisTemplate<String, Todo> redisTemplate;
  private HashOperations<String, Integer, Todo> hashOperations;

  public RedisImpl(RedisTemplate<String, Todo> redisTemplate) {
    this.redisTemplate = redisTemplate;
    hashOperations = redisTemplate.opsForHash();
  }

  @Override
  public Map<Integer, Todo> getAllItems(Pageable pageable) {
    return hashOperations.entries(KEY);
  }

  @Override
  public Todo getItem(int todoId) {
    return (Todo) hashOperations.get(KEY, todoId);
  }

  @Override
  public void addItem(Todo todo) {
    hashOperations.put(KEY, todo.getId(), todo);
  }

  @Override
  public void deleteItem(int todoId) {
    hashOperations.delete(KEY, todoId);
  }

  @Override
  public void updateItem(Todo todo) {
    addItem(todo);
  }
}
