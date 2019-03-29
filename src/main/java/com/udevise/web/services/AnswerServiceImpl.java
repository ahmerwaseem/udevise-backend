package com.udevise.web.services;

import com.udevise.web.domain.Question;
import com.udevise.web.domain.Questionnaire;
import com.udevise.web.domain.Response;
import com.udevise.web.exceptions.DataMismatchException;
import com.udevise.web.exceptions.NotFoundException;
import com.udevise.web.domain.Answer;
import com.udevise.web.repositories.AnswerRepository;
import com.udevise.web.repositories.QuestionRepository;
import com.udevise.web.repositories.QuestionnaireRepository;
import com.udevise.web.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {

  private AnswerRepository answerRepository;
  private QuestionRepository questionRepository;
  private QuestionnaireRepository questionnaireRepository;
  private UserRepository userRepository;

  public AnswerServiceImpl(AnswerRepository answerRepository, QuestionRepository questionRepository, QuestionnaireRepository questionnaireRepository, UserRepository userRepository) {
    this.answerRepository = answerRepository;
    this.questionRepository = questionRepository;
    this.questionnaireRepository = questionnaireRepository;
    this.userRepository = userRepository;
  }


  @Override
  public Answer save(Answer answer) {
    return answerRepository.save(answer);
  }

  public void save(List<Answer> answerList) {
    for (Answer answer : answerList){
      save(answer);
    }
  }

  @Override
  public void saveAnswerResponse(Response response){

    if (!isQuestionIdValidForQuestionnaire(response)){
      throw new DataMismatchException();
    }
    save(response.getAnswers());

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
//
//  @Override
//  public boolean isAnswerAllowedForQuestion (Answer answer) {
//    Optional<Question> question = questionRepository.findById(answer.getQuestionId());
//    return isAnswerAllowedForQuestion(answer,question);
//  }
//
//  @Override
//  public boolean isAnswerAllowedForQuestion (Answer answer, String questionId) {
//    Optional<Question> question = questionRepository.findById(questionId);
//    return isAnswerAllowedForQuestion(answer,question);
//  }
//
//  @Override
//  public boolean isAnswerAllowedForQuestion (Answer answer, Optional<Question> question) {
//    if (question.isPresent()) {
//      if (question.get().getAnswersAllowed()==null || question.get().getAnswersAllowed().isEmpty()) {
//        return true;
//      } else {
//        if (answer.getAnswer() != null) {
//          return question.get().getAnswersAllowed().contains(answer.getAnswer().trim());
//        }
//      }
//    } else throw new DataMismatchException("Invalid request made");
//    return false;
//  }
//
    @Override
    public boolean isAnswerListAllFromOneQuestionnaire(Response response) {
      String questionnaireId = null;
      if (response.getAnswers() != null && response.getAnswers().size()>0){
        questionnaireId = response.getQuestionnaireId();

        if (questionnaireId == null) return false;

        Optional<Questionnaire> questionnaire = questionnaireRepository.getQuestionnairesById(questionnaireId);

        if (questionnaire.isPresent()) {

          for (Answer answer : response.getAnswers()) {
            if (answer.getQuestionId() != questionnaireId)
              return false;
          }
        }
      }
      return true;
    }

    @Override
    public boolean isQuestionIdValidForQuestionnaire(Response response){
      String questionnaireId = null;
      if (response.getAnswers() != null && response.getAnswers().size()>0){
        questionnaireId = response.getQuestionnaireId();

        if (questionnaireId == null) return false;

        Optional<Questionnaire> questionnaire = questionnaireRepository.getQuestionnairesById(questionnaireId);

        if (questionnaire.isPresent()) {

          List<Question> questionList = questionnaire.get().getQuestions();

          HashMap<String,String> map = new HashMap<>();
          for (Question question : questionList) {
            map.put(question.getId(),question.getQuestion());
          }

          for (Answer answer : response.getAnswers()) {
            if (map.get(answer.getQuestionId())==null){
              return false;
            } else {
              setQuestionDataInAnswer(questionnaire, map, answer);
            }

          }

          saveResponse(response, questionnaire);
        }
      }
      return true;
    }

  public void saveResponse(Response response, Optional<Questionnaire> questionnaire) {
    List<Response> responseList = questionnaire.get().getResponses();
    responseList.add(response);
    questionnaire.get().setResponses(responseList);
    questionnaireRepository.save(questionnaire.get());
  }

  public void setQuestionDataInAnswer(Optional<Questionnaire> questionnaire, HashMap<String, String> map, Answer answer) {
    answer.setQuestionnaireId(questionnaire.get().getId());
    answer.setQuestion(map.get(answer.getQuestionId()));

  }
}
