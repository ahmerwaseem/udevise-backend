package com.udevise.web.domain.model;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class Answer {

  public Answer() {
  }

  public Answer(boolean correct, User user, String answer) {
    this.correct = correct;
    this.user = user;
    this.answer = answer;
  }

  public Answer(User user, String answer) {
    this.user = user;
    this.answer = answer;
  }

  public Answer(String answer) {
    this.answer = answer;
  }

  private boolean correct;

  @DBRef
  private User user;

  private String answer;

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
}
