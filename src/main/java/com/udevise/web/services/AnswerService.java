package com.udevise.web.services;

import com.udevise.web.domain.Answer;
import com.udevise.web.domain.Question;

import java.util.List;
import java.util.Optional;

public interface AnswerService {
  Answer save(Answer answer);
  List<Answer> findAllByQuestionId(String id);
  List<Answer> findAllByQuestionnaireId(String id);
  boolean isAnswerAllowedForQuestion(Answer answer);
  boolean isAnswerAllowedForQuestion(Answer answer, String questionId);
  boolean isAnswerAllowedForQuestion (Answer answer, Optional<Question> question);
  boolean isAnswerListAllFromOneQuestionnaire(List<Answer> answerList);


}
