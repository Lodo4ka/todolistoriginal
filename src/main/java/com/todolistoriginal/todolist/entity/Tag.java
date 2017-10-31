package com.todolistoriginal.todolist.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TAG")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Todo> todoList = new HashSet<>();

    public Tag(final String name) {
        this.name = name;
    }


    public void addTodo(Todo todo){
        addTodo(todo, false);
    }

    public void addTodo(Todo todo, boolean otherSideHasBeenAlreadySet){
        getTodoList().add(todo);
        if(otherSideHasBeenAlreadySet){
            return;
        }

        todo.addTag(this, true);
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(final Set<Todo> todoList) {
        this.todoList = todoList;
    }
}
