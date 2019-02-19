package com.udevise.web.domain;

import java.util.List;

public class QuestionnaireResults extends Questionnaire {

  private List<Answer> answerList;

  public List<Answer> getAnswerList() {
    return answerList;
  }

  public void setAnswerList(List<Answer> answerList) {
    this.answerList = answerList;
  }

}
