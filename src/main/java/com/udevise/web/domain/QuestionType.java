package com.udevise.web.domain;

public enum QuestionType {

  MULTIPLE_CHOICE("Multiple Choice"),
  TRUE_FALSE ("True/False"),
  TEXT_BOX("Text Box"),
  CHECK_BOX("Check Box");

  private String displayName;

  QuestionType(String displayName) {
    this.displayName = displayName;
  }

}
