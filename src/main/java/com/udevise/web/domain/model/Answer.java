package com.udevise.web.domain.model;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class Answer {

  public Answer(User user, String answer, String responseId, Boolean correct) {
    this.user = user;
    this.answer = answer;
    this.responseId = responseId;
    this.correct = correct;
  }


  private Boolean correct;

  @DBRef
  private User user;

  private String answer;

  private String responseId;

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Boolean getCorrect() {
    return correct;
  }

  public void setCorrect(Boolean correct) {
    this.correct = correct;
  }

  public String getResponseId() {
    return responseId;
  }

  public void setResponseId(String responseId) {
    this.responseId = responseId;
  }
}
