package com.todolistoriginal.todolist.service;


import com.todolistoriginal.todolist.entity.Todo;

import java.util.List;

public interface TodoService {

    Todo create(Long userId, Todo todo);

    Todo update(Long userId, Todo todo);

    Todo get(Long userId);

    List<Todo> getList();

    void remove(Long todoId);
}
