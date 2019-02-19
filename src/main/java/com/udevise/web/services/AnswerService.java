package com.udevise.web.services;

import com.udevise.web.domain.Answer;

import java.util.List;

public interface AnswerService {
  Answer save(Answer answer);
  List<Answer> findAllByQuestionId(String id);
  List<Answer> findAllByQuestionnaireId(String id);
  boolean isAnswerAllowedForQuestion(Answer answer);
  boolean isAnswerAllowedForQuestion(Answer answer, String questionId);


}
