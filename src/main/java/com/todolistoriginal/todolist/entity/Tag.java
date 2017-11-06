package com.todolistoriginal.todolist.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
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

    public void removeTodo(Todo todo){
        removeTodo(todo, false);
    }

    public void removeTodo(Todo todo, boolean otherSideWasAffected){
        this.getTodoList().remove(todo);
        if(otherSideWasAffected){
            return;
        }
        todo.removeTag(this, true);
    }

    public boolean equals(Object o) {

        if(!(o instanceof Tag)){
            return false;
        }

        Tag that = (Tag) o;

        if(!Objects.equals(this.getId(), that.getId())){
            return false;
        }

        if(!Objects.equals(this.getName(), that.getName())){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.getId(), this.getName());
    }
}
