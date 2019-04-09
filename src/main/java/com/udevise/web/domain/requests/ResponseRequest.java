package com.udevise.web.domain.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseRequest {

  private List<AnswerRequest> answers;
  private String questionnaireId;

  public List<AnswerRequest> getAnswers() {
    return answers;
  }

  public void setAnswers(List<AnswerRequest> answers) {
    this.answers = answers;
  }

  public String getQuestionnaireId() {
    return questionnaireId;
  }

  public void setQuestionnaireId(String questionnaireId) {
    this.questionnaireId = questionnaireId;
  }
}
