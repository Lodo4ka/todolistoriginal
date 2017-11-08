package com.todolistoriginal.todolist.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;

    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Todo> todoList = new HashSet<>();

    public Set<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(final Set<Todo> todoList) {
        this.todoList = todoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void addTodo(Todo todo, boolean otherSidehasBeenAlreadySet){
        getTodoList().add(todo);
        if(otherSidehasBeenAlreadySet){
            return;
        }

        todo.setUser(this, true);
    }

    public void removeTodo(final Todo todo){
        removeTodo(todo, false);
    }

    public void removeTodo(Todo todo, boolean otherSideWasRemoved){
        this.getTodoList().remove(todo);
        if(otherSideWasRemoved){
            return;
        }

        todo.removeUser( true);
    }
}
