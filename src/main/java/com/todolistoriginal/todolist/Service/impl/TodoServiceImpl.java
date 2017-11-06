package com.todolistoriginal.todolist.Service.impl;

import com.todolistoriginal.todolist.Service.TodoService;
import com.todolistoriginal.todolist.entity.Todo;
import com.todolistoriginal.todolist.entity.User;
import com.todolistoriginal.todolist.repository.TodoRepository;
import com.todolistoriginal.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagServiceImpl tagService;




    @Override
    public Todo create(final Long userId, final Todo todo) {

        validateForCreate(userId, todo);

        Todo newTodo = new Todo();

        User findOneUser = userRepository.findOne(userId);
        newTodo.setUser(findOneUser);

        Todo saveTodo = todoRepository.save(newTodo);

        tagService.findOrCreate(todo.getTags()).forEach(todo::addTag);

        return saveTodo;
    }

    @Override
    @Transactional
    public Todo update(final  Long userId, final Todo todo) {

        validateForUpdate(userId, todo);
        authorizationForUpdate(userId, todo);

        Todo findOne = todoRepository.findOne(userId);
        findOne.setComment(todo.getComment());
        findOne.setEndDate(todo.getEndDate());
        findOne.setStartDate(todo.getStartDate());
        findOne.setLogin(todo.getLogin());
        findOne.setType(todo.getType());
        findOne.setWeight(todo.getWeight());
        findOne.setWeight(todo.getWeight());
        findOne.setTags(todo.getTags());

        return findOne;
    }

    private void validateForUpdate(final Long userId, final Todo todo){

    }

    private void authorizationForUpdate(final Long userID, final Todo todo){

    }

    private void validateForCreate(final Long userId, final Todo todo){

    }

    @Override
    @Transactional(readOnly = true)
    public Todo get(final Long userId) {
        authorizationForGet(userId);
        validateForGet(userId);
        Todo byUserId = todoRepository.findByUserId(userId);
        return byUserId;
    }

    private void validateForGet(Long userID){

    }

    private void authorizationForGet(Long userId){


    }

    @Override
    @Transactional
    public void remove(final Todo todo) {
        todoRepository.delete(todo);
    }

}
