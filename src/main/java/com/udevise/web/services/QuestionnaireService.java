package com.udevise.web.services;

import com.udevise.web.domain.Questionnaire;
import com.udevise.web.domain.QuestionnaireResults;

public interface QuestionnaireService {
  Questionnaire save(Questionnaire questionnaire);
  Questionnaire findById(String id);
  QuestionnaireResults getQuestionnaireResults(String id);

}
