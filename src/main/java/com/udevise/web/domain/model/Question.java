package com.udevise.web.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.udevise.web.domain.enums.QuestionType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class Question {

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String id;
  @NotNull
  private QuestionType type;
  @NotBlank
  private String question;
  private List<String> answersAllowed;
  private List<Answer> answersGiven;
  private String correctAnswer;
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

  public String getCorrectAnswer() {
    return correctAnswer;
  }

  public void setCorrectAnswer(String correctAnswer) {
    this.correctAnswer = correctAnswer;
  }

  public List<Answer> getAnswersGiven() {
    return answersGiven;
  }

  public void setAnswersGiven(List<Answer> answersGiven) {
    this.answersGiven = answersGiven;
  }
}
