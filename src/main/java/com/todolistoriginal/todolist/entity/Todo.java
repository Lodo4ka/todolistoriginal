package com.todolistoriginal.todolist.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TODO")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String comment;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private PriorityType type;

    private Long weight;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;


    @ManyToMany
    @JoinTable(name = "TODO_TAG", joinColumns = @JoinColumn(name = "TODO_ID"), inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    private Set<Tag> tags = new HashSet<>();


    public void addTag(Tag tag) {
        addTag(tag, false);
    }

    public void addTag(Tag tag, boolean otherSideHasBeenAlreadySet) {
        getTags().add(tag);
        if (otherSideHasBeenAlreadySet) {
            return;
        }
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

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(final Set<Tag> tags) {
        this.tags = tags;
    }
}

