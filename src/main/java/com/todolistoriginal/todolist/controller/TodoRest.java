package com.todolistoriginal.todolist.controller;


import com.todolistoriginal.todolist.service.impl.TodoServiceImpl;
import com.todolistoriginal.todolist.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/")
public class TodoRest {

    @Autowired
    private TodoServiceImpl todoService;

    private Long userId;

    @RequestMapping(path = "/todo", method = RequestMethod.POST)
    public ResponseEntity<Todo> create(@RequestBody Todo todo) {
        Todo result = todoService.create(userId, todo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/todo/{id}", method = RequestMethod.GET)
    public ResponseEntity<Todo> get( @PathVariable Long id) {
        Todo result = todoService.get(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/todo", method = RequestMethod.PUT)
    public ResponseEntity<Todo> update(@RequestBody Todo todo){
        Todo result = todoService.update(todo.getId(), todo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/todoList", method = RequestMethod.GET)
    public ResponseEntity<List<Todo>> getList(){
        List<Todo> resulst = todoService.getList();
        return new ResponseEntity<List<Todo>>(resulst, HttpStatus.OK);
    }

    @RequestMapping(path = "/todo/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long todoId){
        todoService.remove(todoId);
    }

}

