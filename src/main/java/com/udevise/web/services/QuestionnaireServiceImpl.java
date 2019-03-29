package com.udevise.web.services;

import com.udevise.web.domain.*;
import com.udevise.web.exceptions.NotFoundException;
import com.udevise.web.repositories.AnswerRepository;
import com.udevise.web.repositories.QuestionRepository;
import com.udevise.web.repositories.QuestionnaireRepository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

  private QuestionnaireRepository questionnaireRepository;
  private QuestionRepository questionRepository;
  private MongoTemplate mongoTemplate;
  private UserService userService;
  private AnswerRepository answerRepository;

  public QuestionnaireServiceImpl(QuestionnaireRepository questionnaireRepository, QuestionRepository questionRepository, MongoTemplate mongoTemplate, UserService userService, AnswerRepository answerRepository) {
    this.questionnaireRepository = questionnaireRepository;
    this.questionRepository = questionRepository;
    this.mongoTemplate = mongoTemplate;
    this.userService = userService;
    this.answerRepository = answerRepository;
  }

  @Override
  public Questionnaire save(Questionnaire questionnaire) {
    ArrayList<Question> newList = new ArrayList<>();
    for (Question s : questionnaire.getQuestions()){
      if (s.getType() == QuestionType.TEXT){
        s.setAnswersAllowed(null);
      }
      newList.add(questionRepository.save(s));
    }
    questionnaire.setQuestions(newList);
    return questionnaireRepository.save(questionnaire);
  }

 // @Override
  public Questionnaire getQuestionnaireForRespondent(String id) {
    Query q = new Query();
    q.addCriteria(Criteria.where("_id").is(new ObjectId(id)))
      .fields()
      .include("title").include("questions").include("description");

    Questionnaire questionnaire = mongoTemplate.findOne(q,Questionnaire.class);
    if (questionnaire!=null){
      questionnaire.setResponses(null);
    } else{
      throw new NotFoundException("Looks like there's nothing here!");
    }

    return questionnaire;
  }

  public List<QuestionnaireResults> getQuestionnaireResults(User user) {
    Query q = new Query();
    q.addCriteria(Criteria.where("creatorId").is(user.getId()));
    List<QuestionnaireResults> questionnaire = mongoTemplate.find(q,QuestionnaireResults.class);
    for (QuestionnaireResults results : questionnaire){
      Optional<List<Answer>> answers = answerRepository.getAnswersByQuestionnaireId(results.getId());
      if (answers.isPresent()) {
        results.setAnswerList(answers.get());
      }
    }

    return questionnaire;
  }

  public Object yo(User user) {
    Query q = new Query();
    q.addCriteria(Criteria.where("creatorId").is(user.getId()));
    List<QuestionnaireResults> questionnaire = mongoTemplate.find(q,QuestionnaireResults.class);

    return questionnaire;
  }


//  @Override
//  public QuestionnaireResults getQuestionnaireResults(String id) {
//    QuestionnaireResults questionnaire = (QuestionnaireResults)getQuestionnaireForRespondent(id);
//    questionnaire.setAnswerList(answerRepository.getAnswersByQuestionnaireId(questionnaire.getId()).get());
//    return questionnaire;
//  }

//  @Override
//  public List<QuestionnaireResults> getQuestionnaireByCreatorId(String creatorId) {
//    List<QuestionnaireResults> questionnaireResultsList = new ArrayList<>();
//    Optional<List<Questionnaire>> questionnaireList = questionnaireRepository.findByCreatorId(creatorId);
//    if (questionnaireList.isPresent()){
//      for (Questionnaire questionnaire : questionnaireList.get()){
//        questionnaireResultsList.add(getQuestionnaireResults(questionnaire.getId()));
//      }
//    }
//    return questionnaireResultsList;
//  }
//
//  @Override
//  public List<QuestionnaireResults> getQuestionnaireByCreatorEmail(String email) {
//    Optional<User> user = userRepository.getUserByEmailAddress(email);
//    if (!user.isPresent()) {
//      throw new NotFoundException();
//    }
//
//    return getQuestionnaireByCreatorId(user.get().getId());
//  }

}
