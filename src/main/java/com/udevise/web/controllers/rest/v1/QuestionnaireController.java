package com.udevise.web.controllers.rest.v1;

import com.udevise.web.Utilities.UserUtils;
import com.udevise.web.domain.enums.QuestionType;
import com.udevise.web.domain.enums.QuestionnaireType;
import com.udevise.web.domain.model.Questionnaire;
import com.udevise.web.domain.model.User;
import com.udevise.web.services.QuestionnaireServiceImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/questionnaire")
public class QuestionnaireController {

  QuestionnaireServiceImpl questionnaireService;

  public QuestionnaireController(QuestionnaireServiceImpl questionnaireService) {
    this.questionnaireService = questionnaireService;
  }

  @PostMapping
  public ResponseEntity<Questionnaire> createQuestionnaire(@RequestBody @Valid Questionnaire questionnaire, Principal principal) {
    User user = UserUtils.getUserFromPrincipal(principal);
    questionnaire.setCreatorId(user.getId());
    return ResponseEntity.status(HttpStatus.CREATED).body(questionnaireService.saveNewQuestionnaire(questionnaire));
  }


  @GetMapping(path = "/survey/{id}")
  public Questionnaire getSurvey(@PathVariable String id) {
    return questionnaireService.getQuestionnaireForResponses(id, QuestionnaireType.SURVEY);

  }

  @GetMapping(path = "/quiz/{id}")
  public Questionnaire getQuiz(@PathVariable String id, Principal principal) {
    User user = UserUtils.getUserFromPrincipal(principal);
    return questionnaireService.getQuestionnaireForResponses(id, QuestionnaireType.QUIZ, user);

  }

  @GetMapping
  public List<Questionnaire> getQuestionnairesForUser(Principal principal) {
    User user = UserUtils.getUserFromPrincipal(principal);
    List<Questionnaire> questionnaireList = questionnaireService.getQuestionnairesByUser(user);
    Collections.sort(questionnaireList);
    return questionnaireList;

  }

  @GetMapping(path = "/{id}/detail")
  public Questionnaire getQuestionnaireById(@PathVariable String id, Principal principal) {
    User user = UserUtils.getUserFromPrincipal(principal);
    return questionnaireService.getQuestionnairesByIdAndCreatorId(id,user);

  }


}
