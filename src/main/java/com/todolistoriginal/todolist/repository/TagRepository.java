package com.todolistoriginal.todolist.repository;

import com.todolistoriginal.todolist.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long>{

    Tag findByName(String name);
}
