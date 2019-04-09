package com.udevise.web.services;

import com.mongodb.client.MongoCollection;
import com.udevise.web.domain.model.*;
import com.udevise.web.domain.requests.AnswerRequest;
import com.udevise.web.domain.requests.ResponseRequest;
import com.udevise.web.domain.enums.QuestionType;
import com.udevise.web.exceptions.DataMismatchException;
import com.udevise.web.repositories.QuestionnaireRepository;
import com.udevise.web.repositories.ResponseRepository;
import org.bson.Document;
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
  public Questionnaire saveNewQuestionnaire(Questionnaire questionnaire) {
    for (Question s : questionnaire.getQuestions()){
      if (s.getType() == QuestionType.TEXT || s.getType() == QuestionType.TEXTAREA){
        s.setAnswersAllowed(null);
        s.setCorrectAnswer(null);
      }
    }
    return questionnaireRepository.save(questionnaire);
  }

 // @Override
  public Questionnaire getQuestionnaireForResponses(String id) {
    Query q = new Query();
    q.addCriteria(Criteria.where("_id").is(new ObjectId(id)))
      .fields()
      .include("title").include("questions").include("description");

    Questionnaire questionnaire = mongoTemplate.findOne(q,Questionnaire.class);

    return questionnaire;
  }

  public List<Questionnaire> getQuestionnairesByUser(User user) {
    return questionnaireRepository.findByCreatorId(user.getId()).get();
  }

  @Override
  public void saveQuestionnaireResponse(ResponseRequest request, User user) {
    Optional<Questionnaire> questionnaire = questionnaireRepository.getQuestionnairesById(request.getQuestionnaireId());
    if (!questionnaire.isPresent()){
      throw new DataMismatchException("Questionnaire Not Found");
    }

    List<Question> questionList= questionnaire.get().getQuestions();

    for (AnswerRequest answerRequest : request.getAnswers()){
      for (Question question : questionList) {
        if (answerRequest.getQuestionId().equalsIgnoreCase(question.getId())){
          if (question.getAnswersGiven() == null){
            question.setAnswersGiven(new ArrayList<>());
          }
          Answer answer = new Answer(user,answerRequest.getAnswer());
          question.getAnswersGiven().add(answer);
        }
      }
    }

    List<Response> responseList = questionnaire.get().getResponses();
    if (responseList == null){
      responseList = new ArrayList<>();
    }
    responseList.add(new Response(user));
    questionnaire.get().setResponses(responseList);

    questionnaireRepository.save(questionnaire.get());

  }

  @Override
  public void saveQuestionnaireResponse(ResponseRequest request) {
    saveQuestionnaireResponse(request,null);

  }

  public void test(){
    MongoCollection<Document> collection = mongoTemplate.getCollection("questionnaire");

  }

}
