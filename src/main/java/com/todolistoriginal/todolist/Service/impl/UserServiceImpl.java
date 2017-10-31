package com.todolistoriginal.todolist.Service.impl;

import com.todolistoriginal.todolist.Service.UserService;
import com.todolistoriginal.todolist.entity.User;
import com.todolistoriginal.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Override
    @Transactional
    public User create(final User user) {
        repository.save(user);

        User result = new User();
        result.setLogin(user.getLogin());
        result.setId(user.getId());
        return result;
    }

    @Override
    public User findUserByLoginAndPassword(final String login, final String password) {

        List<User> users = repository.findByLoginAndPassword(login, password);
        //That's check for first found user
        return users.size() == 1 ? users.get(0) : null;
    }
}
