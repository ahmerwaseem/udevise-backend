package com.udevise.web.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "questionnaire")
public class QuestionnaireResults extends Questionnaire {

  private List<Response> responses;

  public List<Response> getResponses() {
    return responses;
  }

  public void setResponses(List<Response> responses) {
    this.responses = responses;
  }
}
