package com.udevise.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Question {
  @Id
  @Indexed
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String id;
  private QuestionType type;
  private String question;
  private List<String> answersAllowed;
  private List<Answer> answersGiven;
  @Indexed
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String questionnaireId;
  boolean required;

  public boolean isRequired() {
    return required;
  }

  public void setRequired(boolean required) {
    this.required = required;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public String getQuestionnaireId() {
    return questionnaireId;
  }

  public void setQuestionnaireId(String questionnaireId) {
    this.questionnaireId = questionnaireId;
  }

  public List<Answer> getAnswersGiven() {
    return answersGiven;
  }

  public void setAnswersGiven(List<Answer> answersGiven) {
    this.answersGiven = answersGiven;
  }
}
