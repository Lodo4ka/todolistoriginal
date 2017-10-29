package com.todolistoriginal.todolist.repository;

import com.todolistoriginal.todolist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>{


    @Query("select u from User where name = :name")
    User findByName(@Param("name") String name);
}
