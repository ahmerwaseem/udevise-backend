package com.udevise.web.controllers.rest.v1;

import com.udevise.web.Utilities.UserUtils;
import com.udevise.web.domain.model.Questionnaire;
import com.udevise.web.domain.model.User;
import com.udevise.web.services.QuestionnaireServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/questionnaire")
public class QuestionnaireController {

  @Autowired
  QuestionnaireServiceImpl questionnaireService;

  @PostMapping
  public ResponseEntity<Questionnaire> createQuestionnaire(@RequestBody @Valid Questionnaire questionnaire, Principal principal) {
    User user = UserUtils.getUserFromPrincipal(principal);
    questionnaire.setCreatorId(user.getId());
    return ResponseEntity.status(HttpStatus.CREATED).body(questionnaireService.saveNewQuestionnaire(questionnaire));
  }


  @GetMapping(path = "/{id}")
  public Questionnaire getQuestionnaire(@PathVariable String id) {
    Questionnaire questionnaire = questionnaireService.getQuestionnaireForResponses(id);
    return questionnaire;

  }

  @GetMapping
  public List<Questionnaire> getCurrentUsersCreatedQuestionnaires(Principal principal) {
    User user = UserUtils.getUserFromPrincipal(principal);
    return questionnaireService.getQuestionnairesByUser(user);

  }


}
