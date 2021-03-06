package com.todolistoriginal.todolist.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "TODO")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;

    @Column(nullable = false)
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

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setTags(final Set<Tag> tags) {
        this.tags = tags;
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
        setUser(user, false);
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void removeAllTags() {
        getTags().stream().collect(Collectors.toList()).forEach(this::removeTag);
    }

    public void setTags(final Collection<Tag> tags) {
        this.removeAllTags();
        tags.forEach(this::addTag);
    }

    public void removeTag(Tag tag) {
        removeTag(tag, false);
    }

    public void removeTag(Tag tag, boolean otherSideRemoved) {
        this.getTags().remove(tag);
        if (otherSideRemoved) {
            return;
        }
        tag.removeTodo(this, true);
    }

    public boolean equals(Object o) {

        if (!(o instanceof Todo)) {
            return false;
        }

        Todo that = (Todo) o;

        if (!Objects.equals(this.getId(), that.getId())) {
            return false;
        }

        if (!Objects.equals(this.getName(), that.getName())) {
            return false;
        }

        if (!Objects.equals(this.getStartDate(), that.getStartDate())) {
            return false;
        }

        if (!Objects.equals(this.getEndDate(), that.getEndDate())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getName(), this.getStartDate(),
                this.getEndDate());
    }

    public void setUser(final User user, final boolean otherSideWasAffected) {
        this.user = user;
        if(otherSideWasAffected){
            return;
        }
        user.addTodo(this, true);
    }

    public void removeUser(){
        removeUser(false);
    }

    public void removeUser(final boolean otherSideRemoved) {
        User user = this.getUser();
        this.user = null;
        if(otherSideRemoved){
            return;
        }
        user.removeTodo(this, true);
    }
}

