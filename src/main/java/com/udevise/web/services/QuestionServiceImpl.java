package com.udevise.web.services;

import com.udevise.web.domain.Question;
import com.udevise.web.domain.QuestionType;
import com.udevise.web.exceptions.NotFoundException;
import com.udevise.web.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

  private QuestionRepository questionRepository;

  @Autowired
  public QuestionServiceImpl(QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
  }

  @Override
  public Question findById(String id) {
    Optional<Question> question = questionRepository.findById(id);
    if (!question.isPresent()){
      throw new NotFoundException("No Questions found with Question id: " + id);
    }
    return question.get();
  }

  @Override
  public Question save(Question question) {
    if (question != null){
      if (question.getType() == QuestionType.TEXT) {
        question.setAnswersAllowed(null);
      }
    }
    return questionRepository.save(question);
  }

}
