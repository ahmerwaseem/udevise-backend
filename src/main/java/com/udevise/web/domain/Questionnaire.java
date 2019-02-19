package com.udevise.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document
public class Questionnaire {

  @Id
  @Indexed
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String id;
  @JsonIgnore
  private String creatorId;
  private boolean isAnonymous;
  private String title;
  private String description;
  private List<Question> questions;
  private Instant beginDateTime;
  private Instant endDateTime;
  private List<String> usersAllowedByEmail;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(String creatorId) {
    this.creatorId = creatorId;
  }

  public boolean getAnonymous() {
    return isAnonymous;
  }

  public void setAnonymous(boolean anonymous) {
    isAnonymous = anonymous;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }

  public Instant getBeginDateTime() {
    return beginDateTime;
  }

  public void setBeginDateTime(Instant beginDateTime) {
    this.beginDateTime = beginDateTime;
  }

  public Instant getEndDateTime() {
    return endDateTime;
  }

  public void setEndDateTime(Instant endDateTime) {
    this.endDateTime = endDateTime;
  }

  public List<String> getUsersAllowedByEmail() {
    return usersAllowedByEmail;
  }

  public void setUsersAllowedByEmail(List<String> usersAllowedByEmail) {
    this.usersAllowedByEmail = usersAllowedByEmail;
  }
}
