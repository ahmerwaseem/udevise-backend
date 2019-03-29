package com.udevise.web.services;

import com.udevise.web.domain.Questionnaire;
import com.udevise.web.domain.QuestionnaireResults;

import java.util.List;

public interface QuestionnaireService {
  Questionnaire save(Questionnaire questionnaire);
  Questionnaire getQuestionnaireForRespondent(String id);
  //Questionnaire getQuestionnaireForRespondent(String id);
 // QuestionnaireResults getQuestionnaireResults(String id);
//  List<QuestionnaireResults> getQuestionnaireByCreatorId(String creatorId);
//  List<QuestionnaireResults> getQuestionnaireByCreatorEmail(String creatorId);

}
