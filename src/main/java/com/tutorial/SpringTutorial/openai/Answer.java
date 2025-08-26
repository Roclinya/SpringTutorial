package com.tutorial.SpringTutorial.openai;

public class Answer {
    private Integer statusCode;
    private String content;
    public Answer(int statusCode, String content) {
        this.statusCode = statusCode;
        this.content = content;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
