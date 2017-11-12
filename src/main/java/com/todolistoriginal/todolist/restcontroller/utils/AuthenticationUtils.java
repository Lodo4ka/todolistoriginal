package com.todolistoriginal.todolist.restcontroller.utils;


import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Function;


public class AuthenticationUtils {


    public <R>ResponseEntity<R> performAfterAuthentication(HttpServletRequest request, Function<Long, ResponseEntity<R>> function){

        String token = request.getHeader("token");

        if()
    }

}
