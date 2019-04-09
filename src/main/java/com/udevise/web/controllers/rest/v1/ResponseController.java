package com.udevise.web.controllers.rest.v1;

import com.udevise.web.Utilities.UserUtils;
import com.udevise.web.domain.requests.ResponseRequest;
import com.udevise.web.services.QuestionnaireService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/response")
public class ResponseController {

  private QuestionnaireService questionnaireService;


  public ResponseController(QuestionnaireService questionnaireService) {
    this.questionnaireService = questionnaireService;
  }

  @PostMapping
  public ResponseEntity submitAnswers(@RequestBody ResponseRequest responseRequest, Principal principal) {

    questionnaireService.saveQuestionnaireResponse(responseRequest, UserUtils.getUserFromPrincipal(principal));
    return ResponseEntity.status(HttpStatus.OK).build();
  }

}
