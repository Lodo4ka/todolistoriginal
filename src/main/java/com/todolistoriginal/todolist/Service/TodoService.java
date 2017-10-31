package com.todolistoriginal.todolist.Service;


import com.todolistoriginal.todolist.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodoService {

    Todo create(Long userId, Todo todo);

    Todo update(Long userId, Todo todo);

    List<Todo> getList(Long userId);

    void remove(Todo todo);
}
