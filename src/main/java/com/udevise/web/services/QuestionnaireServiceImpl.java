package com.udevise.web.services;

import com.udevise.web.domain.Answer;
import com.udevise.web.domain.QuestionnaireResults;
import com.udevise.web.exceptions.NotFoundException;
import com.udevise.web.domain.Question;
import com.udevise.web.domain.Questionnaire;
import com.udevise.web.repositories.AnswerRepository;
import com.udevise.web.repositories.QuestionRepository;
import com.udevise.web.repositories.QuestionnaireRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

  private QuestionnaireRepository questionnaireRepository;
  private QuestionRepository questionRepository;
  private AnswerRepository answerRepository;

  public QuestionnaireServiceImpl(QuestionnaireRepository questionnaireRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
    this.questionnaireRepository = questionnaireRepository;
    this.questionRepository = questionRepository;
    this.answerRepository = answerRepository;
  }

  @Override
  public Questionnaire save(Questionnaire questionnaire) {
    ArrayList<Question> newList = new ArrayList<>();
    for (Question s : questionnaire.getQuestions()){
      newList.add(questionRepository.save(s));
    }
    questionnaire.setQuestions(newList);
    return questionnaireRepository.save(questionnaire);
  }

  @Override
  public Questionnaire findById(String id) {
    Optional<Questionnaire> questionnaire = questionnaireRepository.getQuestionnairesById(id);
    if (!questionnaire.isPresent()) {
      throw new NotFoundException("No Questionnaires found with Questionnaire id: " + id);
    }
    return questionnaire.get();
  }

  @Override
  public QuestionnaireResults getQuestionnaireResults(String id) {
    QuestionnaireResults questionnaire = (QuestionnaireResults)findById(id);
    Optional<List<Answer>> answerList = answerRepository.getAnswersByQuestionnaireId(id);
    questionnaire.setAnswerList(answerList.isPresent() ? answerList.get() : null);
    return questionnaire;
  }
}
