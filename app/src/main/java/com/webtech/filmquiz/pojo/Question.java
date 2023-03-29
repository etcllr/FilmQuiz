package com.webtech.filmquiz.pojo;

import java.io.Serializable;

public class Question implements Serializable {
    private Integer id;
    private String question;
    private boolean answer;

    public Question(Integer id, String question, boolean answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
