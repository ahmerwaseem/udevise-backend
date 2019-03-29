package com.udevise.web.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

public class QuestionnaireResults extends Questionnaire {

  private List<Response> responses;

  private List<Answer> answerList;

  public List<Answer> getAnswerList() {
    return answerList;
  }

  public void setAnswerList(List<Answer> answerList) {
    this.answerList = answerList;
  }

  public List<Response> getResponses() {
    return responses;
  }

  public void setResponses(List<Response> responses) {
    this.responses = responses;
  }
}
