package com.udevise.web.services;

import com.udevise.web.domain.*;
import com.udevise.web.exceptions.NotFoundException;
import com.udevise.web.repositories.AnswerRepository;
import com.udevise.web.repositories.QuestionRepository;
import com.udevise.web.repositories.QuestionnaireRepository;
import com.udevise.web.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

  private QuestionnaireRepository questionnaireRepository;
  private QuestionRepository questionRepository;
  private AnswerRepository answerRepository;
  private UserRepository userRepository;

  public QuestionnaireServiceImpl(
    QuestionnaireRepository questionnaireRepository,
    QuestionRepository questionRepository,
    AnswerRepository answerRepository,
    UserRepository userRepository) {

    this.questionnaireRepository = questionnaireRepository;
    this.questionRepository = questionRepository;
    this.answerRepository = answerRepository;
    this.userRepository = userRepository;
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
    questionnaire.setAnswerList(answerRepository.getAnswersByQuestionnaireId(questionnaire.getId()).get());
    return questionnaire;
  }

  @Override
  public List<QuestionnaireResults> getQuestionnaireByCreatorId(String creatorId) {
    List<QuestionnaireResults> questionnaireResultsList = new ArrayList<>();
    Optional<List<Questionnaire>> questionnaireList = questionnaireRepository.findByCreatorId(creatorId);
    if (questionnaireList.isPresent()){
      for (Questionnaire questionnaire : questionnaireList.get()){
        questionnaireResultsList.add(getQuestionnaireResults(questionnaire.getId()));
      }
    }
    return questionnaireResultsList;
  }

  @Override
  public List<QuestionnaireResults> getQuestionnaireByCreatorEmail(String email) {
    Optional<User> user = userRepository.getUserByEmailAddress(email);
    if (!user.isPresent()) {
      throw new NotFoundException();
    }

    return getQuestionnaireByCreatorId(user.get().getId());
  }

}
