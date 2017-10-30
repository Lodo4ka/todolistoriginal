package com.todolistoriginal.todolist.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TODO")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String comment;

    @Temporal(TemporalType.DATE)
    private LocalDateTime startDate;

    @Temporal(TemporalType.DATE)
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private PriorityType type;

    private Long weight;

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

    public String getComment() {
        return comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(final LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(final LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public PriorityType getType() {
        return type;
    }

    public void setType(final PriorityType type) {
        this.type = type;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(final Long weight) {
        this.weight = weight;
    }
}
