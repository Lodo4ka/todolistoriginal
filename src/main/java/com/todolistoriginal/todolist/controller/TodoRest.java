package com.todolistoriginal.todolist.controller;


import com.todolistoriginal.todolist.Service.impl.TodoServiceImpl;
import com.todolistoriginal.todolist.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class TodoRest {

    @Autowired
    private TodoServiceImpl todoService;

    private Long userId;

    @RequestMapping(path = "/todo", method = RequestMethod.POST)
    public ResponseEntity<Todo> create(HttpServletRequest request, @RequestBody Todo todo) {
        Todo result = todoService.create(userId, todo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/todo/{id}", method = RequestMethod.GET)
    public ResponseEntity<Todo> get(HttpServletRequest request, @PathVariable Long id) {
        Todo result = todoService.get(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/todo", method = RequestMethod.PUT)
    public ResponseEntity<Todo> update(HttpServletRequest request, @RequestBody Todo todo){
        Todo result = todoService.update(todo.getId(), todo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

