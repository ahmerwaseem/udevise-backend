package com.udevise.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
public class Answer {

  @Id
  @Indexed
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String id;
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private User user;
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String userId;
  private String answer;
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Instant submitTime;
  @Indexed
  private String questionId;
  @Indexed
  private String questionnaireId;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public Instant getSubmitTime() {
    return submitTime;
  }

  public void setSubmitTime(Instant submitTime) {
    this.submitTime = submitTime;
  }

  public String getQuestionId() {
    return questionId;
  }

  public void setQuestionId(String questionId) {
    this.questionId = questionId;
  }

  public String getQuestionnaireId() {
    return questionnaireId;
  }

  public void setQuestionnaireId(String questionnaireId) {
    this.questionnaireId = questionnaireId;
  }
}
