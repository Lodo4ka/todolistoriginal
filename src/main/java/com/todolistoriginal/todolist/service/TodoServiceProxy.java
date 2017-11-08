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
                    return proxyMethod(proxy, method, args);
                } else {
                    return method.invoke(todoService, args);
                }
            }

            private boolean needProxy(final Method method) {
                return Stream.of(method.getAnnotations()).filter(annotation->{
                    return annotation.getClass().equals(Transactional.class);
                }).findAny().isPresent();
            }

            private Object proxyMethod(final Object proxy, final Method method, final Object[] args){
                TodoServiceImpl.class.getDeclaredField("entityManager");
            }
        });
    }
}
