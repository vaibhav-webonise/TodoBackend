package com.webonise.todoapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.webonise.todoapp.model.User;

public interface UserRepository extends JpaRepository<User, String>{
}
