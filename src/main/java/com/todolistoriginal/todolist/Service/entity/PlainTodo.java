package com.todolistoriginal.todolist.Service.entity;

import com.todolistoriginal.todolist.entity.PriorityType;
import com.todolistoriginal.todolist.entity.Tag;
import com.todolistoriginal.todolist.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class PlainTodo {


    private Long id;


    private String name;


    private String comment;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private PriorityType type;

    private Long weight;

    private User user;

    @ManyToMany
    @JoinTable(name = "TODO_TAG", joinColumns = @JoinColumn(name = "TODO_ID"), inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    private Set<Tag> tags = new HashSet<>();
}
