package com.tutorial.SpringTutorial.openai;


public class Choice {
    private Integer index;
    private Message message;
    private Boolean logprobs;
    private String finishReason;

    public Integer getIndex() {
        return index;
    }
    public void setIndex(Integer index) {
        this.index = index;
    }
    public Message getMessage() {
        return message;
    }
    public void setMessage(Message message) {
        this.message = message;
    }
    public Boolean getLogprobs() {
        return logprobs;
    }
    public void setLogprobs(Boolean logprobs) {
        this.logprobs = logprobs;
    }
    public String getFinishReason() {
        return finishReason;
    }
    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }
}
