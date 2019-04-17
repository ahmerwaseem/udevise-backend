package com.udevise.web.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.udevise.web.domain.enums.QuestionnaireType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Document
@JsonIgnoreProperties(ignoreUnknown = true)
public class Questionnaire {

  @Id
  @Indexed
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String id;
  private QuestionnaireType type;
  @JsonIgnore
  private String creatorId;
  @NotBlank
  private String title;
  private String description;
  @NotEmpty
  private List<Question> questions;
  @JsonFormat(pattern="MM-dd-yyyy HH:mm:ss")
  private Date beginDateTime;
  @JsonFormat(pattern="MM-dd-yyyy HH:mm:ss")
  private Date endDateTime;
  private List<Response> responses;
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  @JsonFormat(pattern="MM-dd-yyyy HH:mm:ss")
  private Date createTime;

  
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

  public QuestionnaireType getType() {
    return type;
  }

  public void setType(QuestionnaireType type) {
    this.type = type;
  }

  public List<Response> getResponses() {
    return responses;
  }

  public void setResponses(List<Response> responses) {
    this.responses = responses;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
}
