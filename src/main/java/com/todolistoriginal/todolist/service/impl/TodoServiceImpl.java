package com.todolistoriginal.todolist.service.impl;

import com.todolistoriginal.todolist.service.TodoService;
import com.todolistoriginal.todolist.entity.Todo;
import com.todolistoriginal.todolist.entity.User;
import com.todolistoriginal.todolist.repository.TodoRepository;
import com.todolistoriginal.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagServiceImpl tagService;

    @Autowired
    private EntityManager entityManager;


    @Override
    @Transactional
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

    private void authorizationForRemove(final Long userID, final Long todoID){

    }

    private void validateForCreate(final Long userId, final Todo todo){

    }

    private void validateForRemove(final Long userId, Long todoId){

    }

    @Override
    @Transactional(readOnly = true)
    public Todo get(final Long userId) {
        authorizationForGet(userId);
        validateForGet(userId);
        Todo byUserId = todoRepository.findByUserId(userId);
        return byUserId;
    }

    @Override
    @Transactional
    public List<Todo> getList() {
        return todoRepository.findAll();
    }



//    @Override
//    public List<Todo> getList(Long userId) {
//        entityManager.createQuery("select todo " + " from Todo todo " + "join todo.user user "
//                + "where user.id = :userId")
//                .setParameter("userID", userId);
//        return null;
//    }

    private void validateForGet(Long userID){

    }

    private void authorizationForGet(Long userId){


    }

    @Override
    @Transactional
    public void remove(final Long todoID) {
//        validateForRemove(userId, todoId);
//        authorizationForRemove(userId, todoId);
        Todo todo = todoRepository.findById(todoID);
        todo.removeAllTags();
        todo.removeUser();
        todoRepository.delete(todo);
    }
}
