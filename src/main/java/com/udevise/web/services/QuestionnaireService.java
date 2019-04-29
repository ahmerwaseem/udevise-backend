package com.udevise.web.services;

import com.udevise.web.domain.enums.QuestionType;
import com.udevise.web.domain.enums.QuestionnaireType;
import com.udevise.web.domain.model.Questionnaire;
import com.udevise.web.domain.requests.GradeRequest;
import com.udevise.web.domain.requests.ResponseRequest;
import com.udevise.web.domain.model.User;

import java.io.IOException;
import java.util.List;

public interface QuestionnaireService {
  Questionnaire saveNewQuestionnaire(Questionnaire questionnaire);
  Questionnaire getQuestionnaireForResponses(String id, QuestionnaireType type);
  Questionnaire getQuestionnaireForResponses(String id, QuestionnaireType type, User user);
  List<Questionnaire> getQuestionnairesByUser(User user);
  void saveQuestionnaireResponse(ResponseRequest request, User user);
  void saveQuestionnaireResponse(ResponseRequest request);
  Questionnaire getQuestionnairesByIdAndCreatorId(String id, User user);
  Questionnaire getResponseDetails(String questionnaireId, User user);
  Questionnaire getResponseDetails(String questionnaireId, User user, String responseId);
  byte[] getQuestionnaireResponseReport(String questionnaireId, User user) throws IOException;
  void submitGradeAndFeedback(GradeRequest gradeRequest, User user, String questionnaireId, String responseId);



    //Questionnaire getQuestionnaireForResponses(String id);
 // QuestionnaireResults getQuestionnairesByUser(String id);
//  List<QuestionnaireResults> getQuestionnaireByCreatorId(String creatorId);
//  List<QuestionnaireResults> getQuestionnaireByCreatorEmail(String creatorId);

}
