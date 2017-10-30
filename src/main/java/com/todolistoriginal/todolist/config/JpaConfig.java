package com.todolistoriginal.todolist.config;


import com.todolistoriginal.todolist.TodolistApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@Configuration
@EntityScan(basePackageClasses = {
        TodolistApplication.class,
        Jsr310JpaConverters.class
})
public class JpaConfig {
}
