package com.udevise.web.services;

import com.udevise.web.domain.*;
import com.udevise.web.exceptions.NotFoundException;
import com.udevise.web.repositories.QuestionnaireRepository;
import com.udevise.web.repositories.ResponseRepository;
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
  private ResponseRepository responseRepository;
  private MongoTemplate mongoTemplate;

  public QuestionnaireServiceImpl(QuestionnaireRepository questionnaireRepository, ResponseRepository responseRepository, MongoTemplate mongoTemplate) {
    this.questionnaireRepository = questionnaireRepository;
    this.responseRepository = responseRepository;
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public Questionnaire save(Questionnaire questionnaire) {
    for (Question s : questionnaire.getQuestions()){
      if (s.getType() == QuestionType.TEXT){
        s.setAnswersAllowed(null);
      }
    }
    return questionnaireRepository.save(questionnaire);
  }

 // @Override
  public Questionnaire getQuestionnaireForRespondent(String id) {
    Query q = new Query();
    q.addCriteria(Criteria.where("_id").is(new ObjectId(id)))
      .fields()
      .include("title").include("questions").include("description");

    Questionnaire questionnaire = mongoTemplate.findOne(q,Questionnaire.class);

    return questionnaire;
  }

  public List<QuestionnaireResults> getQuestionnaireResults(User user) {
    Query q = new Query();
    q.addCriteria(Criteria.where("creatorId").is(user.getId()));
    List<QuestionnaireResults> questionnaires = mongoTemplate.find(q,QuestionnaireResults.class);

    for (QuestionnaireResults questionnaire: questionnaires ) {
      Optional<List<Response>> responseList = responseRepository.findAllByQuestionnaireId(questionnaire.getId());

      if (responseList.isPresent()) {
        questionnaire.setResponses(responseList.get());
      }
    }
    return questionnaires;
  }

}
