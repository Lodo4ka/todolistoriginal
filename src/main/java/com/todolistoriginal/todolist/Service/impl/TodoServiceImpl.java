package com.todolistoriginal.todolist.Service.impl;

import com.todolistoriginal.todolist.Service.TodoService;
import com.todolistoriginal.todolist.entity.Todo;
import com.todolistoriginal.todolist.entity.User;
import com.todolistoriginal.todolist.repository.TodoRepository;
import com.todolistoriginal.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TodoServiceImpl implements TodoService{

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagServiceImpl tagService;


    @Override
    public Todo create(final Long userId, final Todo todo) {

        Todo newTodo = new Todo();

        User findOneUser = userRepository.findOne(userId);
        newTodo.setUser(findOneUser);

        Todo saveTodo = todoRepository.save(newTodo);

        tagService.findOrCreate(todo.getTags()).forEach(todo::addTag);

        return saveTodo;
    }

    @Override
    @Transactional
    public Todo update(Long userId, final Todo todo) {

        Todo findOne = todoRepository.findOne(userId);
        findOne.setComment(todo.getComment());
        findOne.setEndDate(todo.getEndDate());
        findOne.setStartDate(todo.getStartDate());
        findOne.setLogin(todo.getLogin());
        findOne.setTags(todo.getTags());
        findOne.setType(todo.getType());
        findOne.setWeight(todo.getWeight());
        findOne.setWeight(todo.getWeight());

        return findOne;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Todo> getList(final Long userId) {
        return todoRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public void remove(final Todo todo) {
        todoRepository.delete(todo);
    }

}
