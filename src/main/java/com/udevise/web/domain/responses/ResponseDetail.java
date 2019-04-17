package com.udevise.web.domain.responses;

import com.udevise.web.domain.model.Question;
import com.udevise.web.domain.model.User;

import java.util.Date;
import java.util.List;

public class ResponseDetail{
  private User user;
  private String questionnaireTitle;
  private String description;
  private List<Question> questionsList;
  private Date timeSubmitted;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getQuestionnaireTitle() {
    return questionnaireTitle;
  }

  public void setQuestionnaireTitle(String questionnaireTitle) {
    this.questionnaireTitle = questionnaireTitle;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Question> getQuestionsList() {
    return questionsList;
  }

  public void setQuestionsList(List<Question> questionsList) {
    this.questionsList = questionsList;
  }

  public Date getTimeSubmitted() {
    return timeSubmitted;
  }

  public void setTimeSubmitted(Date timeSubmitted) {
    this.timeSubmitted = timeSubmitted;
  }
}
