package com.udevise.web.domain.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnswerRequest {

  private List<String> answer;

  private String questionId;

  public String getQuestionId() {
    return questionId;
  }

  public void setQuestionId(String questionId) {
    this.questionId = questionId;
  }

  public List<String> getAnswer() {
    return answer;
  }

  public void setAnswer(List<String> answer) {
    this.answer = answer;
  }
}
