package com.udevise.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Document
public class Questionnaire {

  @Id
  @Indexed
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String id;
  @JsonIgnore
  private String creatorId;
  private Boolean isAnonymous;
  @NotBlank
  private String title;
  private String description;
  private List<Response> responses;
  private List<Question> questions;
  private Date beginDateTime;
  private Date endDateTime;
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

  public Boolean getAnonymous() {
    return isAnonymous;
  }

  public void setAnonymous(Boolean anonymous) {
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

  public Date getBeginDateTime() {
    return beginDateTime;
  }

  public void setBeginDateTime(Date beginDateTime) {
    this.beginDateTime = beginDateTime;
  }

  public Date getEndDateTime() {
    return endDateTime;
  }

  public void setEndDateTime(Date endDateTime) {
    this.endDateTime = endDateTime;
  }

  public List<String> getUsersAllowedByEmail() {
    return usersAllowedByEmail;
  }

  public void setUsersAllowedByEmail(List<String> usersAllowedByEmail) {
    this.usersAllowedByEmail = usersAllowedByEmail;
  }

  public List<Response> getResponses() {
    return responses;
  }

  public void setResponses(List<Response> responses) {
    this.responses = responses;
  }
}
