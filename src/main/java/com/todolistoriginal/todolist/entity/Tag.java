package com.todolistoriginal.todolist.entity;


import javax.persistence.*;

@Entity
@Table(name = "TAG")
public class Tag {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

    private String name;



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
}