package com.udevise.web.controllers.rest.v1;

import com.udevise.web.Utilities.UserUtils;
import com.udevise.web.domain.Questionnaire;
import com.udevise.web.domain.QuestionnaireResults;
import com.udevise.web.domain.User;
import com.udevise.web.services.QuestionnaireServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    User user = UserUtils.getUserFromPrincipal((UsernamePasswordAuthenticationToken) principal);
    questionnaire.setCreatorId(user.getId());
    return ResponseEntity.status(HttpStatus.CREATED).body(questionnaireService.save(questionnaire));
  }


  @GetMapping(path = "/{id}")
  public Questionnaire getQuestionnaire(@PathVariable String id) {
    Questionnaire questionnaire = questionnaireService.getQuestionnaireForRespondent(id);
    return questionnaire;

  }

  @GetMapping(path = "/all")
  public List<QuestionnaireResults> getCurrentUsersQuestionnaire(Principal principal) {
    User user = UserUtils.getUserFromPrincipal((UsernamePasswordAuthenticationToken) principal);
    List<QuestionnaireResults> questionnaire = questionnaireService.getQuestionnaireResults(user);
    return questionnaire;

  }


}
