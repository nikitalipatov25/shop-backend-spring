package com.nikitalipatov.handmadeshop.core.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long answerId;

    private String userName;

    private String text;

    private String date;

    public Answer() {
    }

    public Answer(String userName, String text, String date) {
        this.userName = userName;
        this.text = text;
        this.date = date;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
