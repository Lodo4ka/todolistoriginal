package com.todolistoriginal.todolist.Service;


import com.todolistoriginal.todolist.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TodoService {

    Todo create(Long userId, Todo todo);

    Todo update(Long userId, Todo todo);

    Todo get(Long userId);

    void remove(Todo todo);
}
