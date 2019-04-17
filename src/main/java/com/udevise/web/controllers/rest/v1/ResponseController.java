package com.udevise.web.controllers.rest.v1;

import com.udevise.web.Utilities.UserUtils;
import com.udevise.web.domain.model.Questionnaire;
import com.udevise.web.domain.model.User;
import com.udevise.web.domain.requests.ResponseRequest;
import com.udevise.web.services.QuestionnaireService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/response")
public class ResponseController {

  private QuestionnaireService questionnaireService;

  public ResponseController(QuestionnaireService questionnaireService) {
    this.questionnaireService = questionnaireService;
  }

  @PostMapping
  public ResponseEntity submit(@RequestBody ResponseRequest responseRequest, Principal principal) {
    questionnaireService.saveQuestionnaireResponse(responseRequest, UserUtils.getUserFromPrincipal(principal));
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("/detail/{questionnaireId}")
  public Questionnaire getResponseDetailsForCurrentUser(@PathVariable String questionnaireId, Principal principal) {
    return questionnaireService.getResponseDetails(questionnaireId,UserUtils.getUserFromPrincipal(principal));
  }

  @GetMapping("/detail/{questionnaireId}/{responseId}")
  public Questionnaire getResponseDetailForQuestionnaireTaker(@PathVariable String questionnaireId, @PathVariable String responseId, Principal principal) {
    return questionnaireService.getResponseDetails(questionnaireId,UserUtils.getUserFromPrincipal(principal),responseId);
  }

}
