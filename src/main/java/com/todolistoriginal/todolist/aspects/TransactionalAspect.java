package com.todolistoriginal.todolist.aspects;


import com.todolistoriginal.todolist.service.utils.TransactionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;

@Aspect
public class TransactionalAspect {

    @Autowired
    private TransactionUtils transactionUtils;

    @Pointcut("execution" +
            "(@com.todolistoriginal.todolist.service.annotation.Transactional * com.todolistoriginal.todolist.service.*(..))")
    public void annotatedByTransactional() {

    }

    @Around("annotatedByTransactional()")
    public Object around(ProceedingJoinPoint joinPoint){


        return transactionUtils.performInsideTransaction(entityManager -> {

            try {
                Object target = joinPoint.getTarget();
                Field entityManagerField = target.getClass().getDeclaredField("entityManager");
                entityManagerField.setAccessible(true);
                entityManagerField.set(target, entityManager);
                Object result = joinPoint.proceed();

                entityManagerField.set(target, null);
                entityManagerField.setAccessible(false);

                return result;
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });
    }
}
