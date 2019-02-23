package com.udevise.web.services;

import com.udevise.web.domain.Question;
import com.udevise.web.exceptions.DataMismatchException;
import com.udevise.web.exceptions.NotFoundException;
import com.udevise.web.domain.Answer;
import com.udevise.web.repositories.AnswerRepository;
import com.udevise.web.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {

  private AnswerRepository answerRepository;
  private QuestionRepository questionRepository;

  @Autowired
  public AnswerServiceImpl(AnswerRepository answerRepository, QuestionRepository questionRepository) {
    this.answerRepository = answerRepository;
    this.questionRepository = questionRepository;
  }

  @Override
  public Answer save(Answer answer) {
    return answerRepository.save(answer);
  }

  @Override
  public List<Answer> findAllByQuestionId(String id) {
    Optional<List<Answer>> answerIterable = answerRepository.findAllByQuestionId(id);
    if (!answerIterable.isPresent()) {
      throw new NotFoundException("No Answers found with Question id: " + id);
    }
    return answerIterable.get();
  }

  @Override
  public List<Answer> findAllByQuestionnaireId(String id) {
    Optional<List<Answer>> answerIterable = answerRepository.getAnswersByQuestionnaireId(id);
    if (!answerIterable.isPresent()) {
      throw new NotFoundException("No Answers found with Questionnaire id: " + id);
    }
    return answerIterable.get();
  }

  @Override
  public boolean isAnswerAllowedForQuestion (Answer answer) {
    Optional<Question> question = questionRepository.findById(answer.getQuestionId());
    return isAnswerAllowedForQuestion(answer,question);
  }

  @Override
  public boolean isAnswerAllowedForQuestion (Answer answer, String questionId) {
    Optional<Question> question = questionRepository.findById(questionId);
    return isAnswerAllowedForQuestion(answer,question);
  }

  @Override
  public boolean isAnswerAllowedForQuestion (Answer answer, Optional<Question> question) {
    if (question.isPresent()) {
      if (question.get().getAnswersAllowed()==null || question.get().getAnswersAllowed().isEmpty()) {
        return true;
      } else {
        if (answer.getAnswer() != null) {
          return question.get().getAnswersAllowed().contains(answer.getAnswer().trim());
        }
      }
    } else throw new DataMismatchException("Invalid request made");
    return false;
  }

  @Override
  public boolean isAnswerListAllFromOneQuestionnaire(List<Answer> answerList) {
    String questionnaireId = null;
    if (answerList != null && answerList.size()>0){
      questionnaireId = answerList.get(0).getQuestionnaireId();
      if (questionnaireId == null)
        return false;
    }

    for (Answer answer : answerList) {
      if (answer.getQuestionnaireId() != questionnaireId)
        return false;
    }

    return true;
  }
}
