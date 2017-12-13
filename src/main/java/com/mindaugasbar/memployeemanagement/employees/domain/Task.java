package com.mindaugasbar.memployeemanagement.employees.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "ttask")
public class Task {
    protected enum Priority { LOW, MEDIUM, HIGH, CRITICAL}
    protected enum Status {TODO, IN_PROGRESS, IN_REVIEW, DONE}

    @Id
    @GeneratedValue
    private int Id;

    private Priority priority;

    private Status status;

    @Length(min = 3, max = 50)
    private String name;

    @Length(max = 1000)
    private String description;

    private Date createdDate = new Date();

    private Date startedWorkingOnIt;

    private Date completed;

    private int hoursWorked = 0;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getStartedWorkingOnIt() {
        return startedWorkingOnIt;
    }

    public void setStartedWorkingOnIt(Date startedWorkingOnIt) {
        this.startedWorkingOnIt = startedWorkingOnIt;
    }

    public Date getCompleted() {
        return completed;
    }

    public void setCompleted(Date completed) {
        this.completed = completed;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
