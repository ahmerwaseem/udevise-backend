package com.udevise.web.controllers.rest.v1;

import com.udevise.web.domain.Answer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

  @PostMapping
  public ResponseEntity submitAnswers(List<Answer> answerList) {
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
