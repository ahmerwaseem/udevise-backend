package com.udevise.web.domain.requests;

public class GradeRequest {

  private Double score;

  private String feedback;

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }

  public String getFeedback() {
    return feedback;
  }

  public void setFeedback(String feedback) {
    this.feedback = feedback;
  }
}
