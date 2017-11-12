package com.todolistoriginal.todolist.service;

import com.todolistoriginal.todolist.service.annotation.Transactional;
import com.todolistoriginal.todolist.service.impl.TodoServiceImpl;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.stream.Stream;

@Component("todoServiceProxy")
public class TodoServiceProxy {
    public TodoService createTransactionalProxy(TodoServiceImpl todoService) {
        return (TodoService) Proxy.newProxyInstance(TodoServiceImpl.class.getClassLoader(), new Class<?>[]{
                TodoService.class}, new InvocationHandler() {
            @Override
            public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
                if (needProxy(method)) {

                } else {
                    return method.invoke(todoService, args);
                }
            }

            private boolean needProxy(final Method method) {

                try {
                    Method todoServiceMethod = todoService.getClass().getMethod(method.getName(),
                            method.getParameterTypes());
                    return Stream.of(todoServiceMethod.getAnnotations()).filter(annotation->{
                        return annotation.annotationType().equals(Transactional.class);
                    }).findAny().isPresent();
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }



            }

        });
    }
}
