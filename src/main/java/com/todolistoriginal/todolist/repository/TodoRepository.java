package com.todolistoriginal.todolist.repository;

import com.todolistoriginal.todolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long>{

    List<Todo> findByUserId(Long userId);
}
