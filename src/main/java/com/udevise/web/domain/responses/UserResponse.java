package com.udevise.web.domain.responses;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserResponse {

  private String questionnaireTitle;
  private String questionnaireId;
  private String responseId;
  @JsonFormat(pattern="MM-dd-yyyy HH:mm:ss")
  private Date timeCompleted;

  public String getQuestionnaireTitle() {
    return questionnaireTitle;
  }

  public void setQuestionnaireTitle(String questionnaireTitle) {
    this.questionnaireTitle = questionnaireTitle;
  }

  public String getQuestionnaireId() {
    return questionnaireId;
  }

  public void setQuestionnaireId(String questionnaireId) {
    this.questionnaireId = questionnaireId;
  }

  public String getResponseId() {
    return responseId;
  }

  public void setResponseId(String responseId) {
    this.responseId = responseId;
  }

  public Date getTimeCompleted() {
    return timeCompleted;
  }

  public void setTimeCompleted(Date timeCompleted) {
    this.timeCompleted = timeCompleted;
  }
}
