package com.udevise.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class Question {

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String id = UUID.randomUUID().toString();
  @NotNull
  private QuestionType type;
  @NotBlank
  private String question;
  private List<String> answersAllowed;
  boolean required;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public boolean isRequired() {
    return required;
  }

  public void setRequired(boolean required) {
    this.required = required;
  }

  public QuestionType getType() {
    return type;
  }

  public void setType(QuestionType type) {
    this.type = type;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public List<String> getAnswersAllowed() {
    return answersAllowed;
  }

  public void setAnswersAllowed(List<String> answersAllowed) {
    this.answersAllowed = answersAllowed;
  }

}
