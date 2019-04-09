package com.udevise.web.services;

import com.udevise.web.domain.model.Questionnaire;
import com.udevise.web.domain.requests.ResponseRequest;
import com.udevise.web.domain.model.User;

import java.util.List;

public interface QuestionnaireService {
  Questionnaire saveNewQuestionnaire(Questionnaire questionnaire);
  Questionnaire getQuestionnaireForResponses(String id);
  List<Questionnaire> getQuestionnairesByUser(User user);
  void saveQuestionnaireResponse(ResponseRequest request, User user);
  void saveQuestionnaireResponse(ResponseRequest request);
  //Questionnaire getQuestionnaireForResponses(String id);
 // QuestionnaireResults getQuestionnairesByUser(String id);
//  List<QuestionnaireResults> getQuestionnaireByCreatorId(String creatorId);
//  List<QuestionnaireResults> getQuestionnaireByCreatorEmail(String creatorId);

}
