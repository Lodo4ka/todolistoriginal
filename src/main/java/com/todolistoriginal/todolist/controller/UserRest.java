package com.todolistoriginal.todolist.controller;


import com.todolistoriginal.todolist.entity.User;
import com.todolistoriginal.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserRest {


    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/{username}")
    public User userController(@PathVariable(value = "username") String username){
        User user = userRepository.findOne(1L);
        return user;
    }
}
