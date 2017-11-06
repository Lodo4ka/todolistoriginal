package com.todolistoriginal.todolist.Service;


import com.todolistoriginal.todolist.entity.User;
import org.springframework.stereotype.Service;

public interface UserService {

    User create(User user);

    User findUserByLoginAndPassword(String login, String password);
}
