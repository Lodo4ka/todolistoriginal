package com.todolistoriginal.todolist.repository;

import com.todolistoriginal.todolist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long>{

    User findByName(String name);

}
