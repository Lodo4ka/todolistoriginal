package com.todolistoriginal.todolist.restcontroller;


import com.todolistoriginal.todolist.restcontroller.aspect.Authenticational;
import com.todolistoriginal.todolist.entity.Todo;
import com.todolistoriginal.todolist.service.impl.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class TodoRest {

    @Autowired
    private TodoServiceImpl todoService;

    private Long userId;

    @RequestMapping(path = "/todo", method = RequestMethod.POST)
    @Authenticational
    public ResponseEntity<Todo> create(@RequestBody Todo todo) {
        Todo result = todoService.create(userId, todo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/todo/{id}", method = RequestMethod.GET)
    @Authenticational
    public ResponseEntity<Todo> get( @PathVariable Long id) {
        Todo result = todoService.get(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/todo", method = RequestMethod.PUT)
    @Authenticational
    public ResponseEntity<Todo> update(@RequestBody Todo todo){
        Todo result = todoService.update(todo.getId(), todo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/todoList", method = RequestMethod.GET)
    @Authenticational
    public ResponseEntity<List<Todo>> getList(){
        List<Todo> resulst = todoService.getList();
        return new ResponseEntity<List<Todo>>(resulst, HttpStatus.OK);
    }

    @RequestMapping(path = "/todo/{id}", method = RequestMethod.DELETE)
    @Authenticational
    public void remove(@PathVariable Long todoId){
        todoService.remove(todoId);
    }

}

