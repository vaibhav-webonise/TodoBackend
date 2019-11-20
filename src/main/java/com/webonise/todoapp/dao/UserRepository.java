package com.webonise.todoapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.webonise.todoapp.model.UserData;

public interface UserRepository extends JpaRepository<UserData, String> {
}
