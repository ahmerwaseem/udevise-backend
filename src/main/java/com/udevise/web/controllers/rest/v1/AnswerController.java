package com.udevise.web.controllers.rest.v1;

import com.udevise.web.domain.Answer;
import com.udevise.web.domain.Response;
import com.udevise.web.services.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/answer")
public class AnswerController {

  private AnswerService answerService;

  public AnswerController(AnswerService answerService) {
    this.answerService = answerService;
  }

  @PostMapping
  public ResponseEntity submitAnswers(@RequestBody Response response, Principal principal) {
    answerService.saveAnswerResponse(response);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }




}
