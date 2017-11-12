package com.todolistoriginal.todolist.service;


import com.todolistoriginal.todolist.entity.User;

public interface UserService {

    User create(User user);

    User findUserByLoginAndPassword(String login, String password);

}
