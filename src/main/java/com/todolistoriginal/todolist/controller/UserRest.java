package com.todolistoriginal.todolist.controller;


import com.todolistoriginal.todolist.entity.User;
import com.todolistoriginal.todolist.repository.UserRepository;
import com.todolistoriginal.todolist.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/")
public class UserRest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody User user){
            User createdUser = userService.create(user);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);

    }

    @RequestMapping(path = "/authentication", method = RequestMethod.POST)
    public ResponseEntity<String> authentication(@RequestBody User user) {
        User authenticationUser = userService.findUserByLoginAndPassword(user.getLogin(), user.getPassword());
        return new ResponseEntity(authenticationUser, HttpStatus.OK);
    }

    private String createToken(User user) {
        Date createdTime = Calendar.getInstance().getTime();
        TokenPayload payload = new TokenPayload(user.getId(), user.getLogin(), createdTime);

        String token = tokenManager.createToken(payload);

        return token;
    }


}
