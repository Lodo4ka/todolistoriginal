package com.todolistoriginal.todolist.repository;

import com.todolistoriginal.todolist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long>{


    List<User> findByLoginAndPassword(String login, String password);
}
