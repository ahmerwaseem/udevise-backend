package com.udevise.web.services;

import com.udevise.web.Utilities.StringUtils;
import com.udevise.web.Utilities.UserUtils;
import com.udevise.web.domain.enums.QuestionnaireType;
import com.udevise.web.domain.model.*;
import com.udevise.web.domain.requests.AnswerRequest;
import com.udevise.web.domain.requests.ResponseRequest;
import com.udevise.web.domain.enums.QuestionType;
import com.udevise.web.domain.responses.ResponseDetail;
import com.udevise.web.exceptions.DataMismatchException;
import com.udevise.web.exceptions.NotFoundException;
import com.udevise.web.repositories.QuestionnaireRepository;
import com.udevise.web.repositories.UserRepository;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.*;

import static com.udevise.web.Utilities.UserUtils.hasUserResponded;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

  private QuestionnaireRepository questionnaireRepository;
  private MongoTemplate mongoTemplate;
  private UserRepository userRepository;

  public QuestionnaireServiceImpl(QuestionnaireRepository questionnaireRepository, MongoTemplate mongoTemplate, UserRepository userRepository) {
    this.questionnaireRepository = questionnaireRepository;
    this.mongoTemplate = mongoTemplate;
    this.userRepository = userRepository;
  }

  @Override
  public Questionnaire saveNewQuestionnaire(Questionnaire questionnaire) {
    for (Question s : questionnaire.getQuestions()){
      s.setId(UUID.randomUUID().toString());
      if (s.getType() == QuestionType.TEXT || s.getType() == QuestionType.TEXTAREA){
        s.setAnswersAllowed(null);
        s.setCorrectAnswer(null);
      }
    }
    questionnaire.setCreateTime(Date.from(Instant.now()));
    return questionnaireRepository.save(questionnaire);
  }

  public Questionnaire getQuestionnaireForResponses(String id, QuestionnaireType type) {
    return getQuestionnaireForResponses(id,type, null);
  }


    // @Override
  public Questionnaire getQuestionnaireForResponses(String id, QuestionnaireType type, User user) {
    Query q = new Query();
    ObjectId objectId;
    try{
      objectId = new ObjectId(id);
    } catch (IllegalArgumentException e){
      throw new NotFoundException();
    }
    q.addCriteria(Criteria.where("_id").is(objectId))
      .addCriteria(Criteria.where("type").is(type))
      .fields()
      .include("title")
      .include("description")
      .include("type")
      .include("questions.id")
      .include("questions.question")
      .include("questions.type")
      .include("questions.answersAllowed");

    Questionnaire questionnaire = mongoTemplate.findOne(q,Questionnaire.class);
    if (questionnaire == null){
      throw new NotFoundException();
    }

    if (hasUserResponded(user,id)){
      throw new DataMismatchException("Looks like you have already responded!");
    }

      return questionnaire;
  }

  public List<Questionnaire> getQuestionnairesByUser(User user) {
    Optional<List<Questionnaire>> questionnaireList = questionnaireRepository.findByCreatorId(user.getId());
    if (!questionnaireList.isPresent()){
      throw new NotFoundException();
    }
    return questionnaireList.get();

  }

  public Questionnaire getQuestionnairesByIdAndCreatorId(String id, User user) {
    Optional<Questionnaire> questionnaire = questionnaireRepository.findByIdAndCreatorId(id,user.getId());
    if (!questionnaire.isPresent()){
      throw new NotFoundException();
    }
    return questionnaire.get();
  }

  public void saveQuestionnaireResponse(ResponseRequest request, User user) {
    Optional<Questionnaire> questionnaire = questionnaireRepository.getQuestionnairesById(request.getQuestionnaireId());
    if (!questionnaire.isPresent()){
      throw new DataMismatchException("Looks like you took a wrong turn!");
    }

    if (questionnaire.isPresent() & questionnaire.get().getType() == QuestionnaireType.QUIZ && user == null){
      throw new DataMismatchException("Looks like you took a wrong turn!");
    }

    if (questionnaire.get().getType()==QuestionnaireType.SURVEY){
      user = null; //I dont want to save the user information for surveys
    }

    if (questionnaire.get().getType()==QuestionnaireType.QUIZ
      && UserUtils.hasUserResponded(user, request.getQuestionnaireId())){
      throw new DataMismatchException("We appreciate the enthusiasm but it looks like you already responded!");
    }

    List<Question> questionList= questionnaire.get().getQuestions();

    String responseTrackingId = UUID.randomUUID().toString();

    for (AnswerRequest answerRequest : request.getAnswers()){
      for (Question question : questionList) {
        if (answerRequest.getQuestionId().equalsIgnoreCase(question.getId())){
          if (question.getAnswersGiven() == null){
            question.setAnswersGiven(new ArrayList<>());
          }

          Boolean isCorrectAnswer = isCorrectAnswer(questionnaire, answerRequest, question);
          Answer answer = new Answer(user,answerRequest.getAnswer(),responseTrackingId, isCorrectAnswer);
          question.getAnswersGiven().add(answer);
        }
      }
    }

    List<Response> responseList = questionnaire.get().getResponses();
    if (responseList == null){
      responseList = new ArrayList<>();
    }
    responseList.add(new Response(user,responseTrackingId));
    questionnaire.get().setResponses(responseList);
    if (user != null){
      Map<String,String> responseMap = user.getResponseMap();
      if (user.getResponseMap()==null){
        responseMap = new HashMap<>();
      }
      responseMap.put(questionnaire.get().getId(),responseTrackingId);
      user.setResponseMap(responseMap);
      userRepository.save(user);
    }

    questionnaireRepository.save(questionnaire.get());

  }

  public Boolean isCorrectAnswer(Optional<Questionnaire> questionnaire, AnswerRequest answerRequest, Question question) {
    Boolean isCorrectAnswer = null;
    if (questionnaire.get().getType() == QuestionnaireType.QUIZ
      && question.getCorrectAnswer() != null) {
      return question.getCorrectAnswer().equalsIgnoreCase(answerRequest.getAnswer());
    }
    return isCorrectAnswer;
  }

  public void saveQuestionnaireResponse(ResponseRequest request) {
    saveQuestionnaireResponse(request,null);
  }

  public Questionnaire getResponseDetails(String questionnaireId, User user) {
    return getResponseDetails(questionnaireId,user,null);
  }

  public Questionnaire getResponseDetails(String questionnaireId, User user, String responseId) {
    Query q = new Query();
    ObjectId objectId;
    try{
      objectId = new ObjectId(questionnaireId);
    } catch (IllegalArgumentException e){
      throw new NotFoundException();
    }
    q.addCriteria(Criteria.where("_id").is(objectId));

    Optional<Questionnaire> questionnaire = questionnaireRepository.getQuestionnairesById(questionnaireId);
    if (!questionnaire.isPresent()){
      throw new NotFoundException();
    }

    List<Response> responses = questionnaire.get().getResponses();
    if (responses == null) {
      throw new NotFoundException();
    }
    if (responses != null) {
      Optional<Response> userResponse = null;
      if (responseId != null){
        userResponse = responses.stream().filter(o -> responseId.equals(o.getResponseId())).findFirst();
      } else {
        userResponse = responses.stream().filter(o -> user.getId().equals(o.getUser().getId())).findFirst();
      }
      if (userResponse.isPresent()) {
        setQuestionnnaireDetailsForUser(questionnaire.get(), userResponse.get());
      } else {
        throw new NotFoundException();
      }

    }
    return questionnaire.get();


  }

  public void setQuestionnnaireDetailsForUser(Questionnaire questionnaire, Response userResponse) {
    if (userResponse != null){
      ResponseDetail responseDetail = new ResponseDetail();
      responseDetail.setTimeSubmitted(userResponse.getSubmitTime());
      List<Question> questionList = questionnaire.getQuestions();
      for (Question question : questionList){

        if (question.getAnswersGiven() !=null){
          Optional<Answer> answerOptional = question.getAnswersGiven()
            .stream()
            .filter(item -> userResponse.getResponseId().equals(item.getResponseId())).findFirst();
          Answer answerGiven;
          if (answerOptional.isPresent()) {
            answerGiven = answerOptional.get();
            if (answerGiven.getUser() != null) {
              answerGiven.getUser().setResponseMap(null);
            }
            List<Answer> answers = new ArrayList<>();
            answers.add(answerGiven);
            question.setAnswersGiven(answers);
          }
        } else {
          question.setAnswersGiven(null);
        }
      }
    }

    questionnaire.setResponses(null);
    questionnaire.setCreatorId(null);
    questionnaire.setCreateTime(null);
    questionnaire.setBeginDateTime(null);
    questionnaire.setEndDateTime(null);
    questionnaire.setUsersAllowedByEmail(null);
  }

  public byte[] getQuestionnaireResponseReport(String id, User user) throws IOException{

    Questionnaire questionnaire = getQuestionnairesByIdAndCreatorId(id,user);
    File tmpFile = File.createTempFile("temp", ".tmp");
    FileWriter writer = new FileWriter(tmpFile);
    HashMap<String,StringBuilder> responseMap = new HashMap<>();
    HashMap<String,User> userMap = new HashMap<>();

    for (Response response : questionnaire.getResponses()){
      userMap.put(response.getResponseId(),response.getUser());
    }


    writer.append(StringUtils.TAB);
    for (Question question : questionnaire.getQuestions()){
      writer.append(question.getQuestion()).append(StringUtils.TAB);
      if (questionnaire.getType()==QuestionnaireType.QUIZ) {
        writer.append(StringUtils.replaceIfNull(question.getCorrectAnswer(), "")).append(StringUtils.TAB);
      }

      for (Answer answer: question.getAnswersGiven()){
        StringBuilder userResponseStr = new StringBuilder();
        if (responseMap.get(answer.getResponseId())!=null){
          userResponseStr = responseMap.get(answer.getResponseId());
        }
        userResponseStr.append(StringUtils.replaceIfNull(answer.getAnswer(),"")).append(StringUtils.TAB);
        if (questionnaire.getType()==QuestionnaireType.QUIZ) {
          userResponseStr.append(StringUtils.replaceIfNull(question.getCorrectAnswer(),"")).append(StringUtils.TAB);
        }
        responseMap.put(answer.getResponseId(),userResponseStr);
      }
    }



    writer.append(StringUtils.NEWLINE);
    Iterator iterator = responseMap.entrySet().iterator();
    while (iterator.hasNext()) {
        Map.Entry entry = (Map.Entry) iterator.next();
        StringBuilder strToWrite = (StringBuilder) entry.getValue();
        String userEmail = null;
        if (userMap.get(entry.getKey()) != null){
          userEmail = userMap.get(entry.getKey()).getEmailAddress();
        }
        if (userEmail == null || userEmail.isEmpty()) {
          userEmail = "Anonymous";
        }
        strToWrite.insert(0, userEmail + StringUtils.TAB);
        writer.append(strToWrite.toString()).append(StringUtils.NEWLINE);

    }

    writer.close();
    return IOUtils.toByteArray(tmpFile.toURI());
  }

}
