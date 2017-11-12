package com.todolistoriginal.todolist.restcontroller.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AuthenticationAspect {

    @Pointcut("execution(@com.todolistoriginal.todolist.restcontroller.aspect.Authenticational * * (..))")
    public void annotatedByAuthentication(){

    }

    @Around("annotatedByAuthentication()")
    private Object around(ProceedingJoinPoint joinPoint){
        Object target = joinPoint.getTarget();

    }
}
