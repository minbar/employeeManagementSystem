package com.mindaugasbar.memployeemanagement.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Comment {
    protected enum Type {PROBLEM, QUESTION, INFORMATIONAL_MESSAGE}

    @Id
    @GeneratedValue
    private int id;

    private Type type;

    private String message;

    private Date datePublished;

    public Comment(Type type, String message, Date datePublished) {
        this.type = type;
        this.message = message;
        this.datePublished = datePublished;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }
}
