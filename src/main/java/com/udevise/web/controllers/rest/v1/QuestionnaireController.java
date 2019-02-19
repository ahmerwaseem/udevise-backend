package com.udevise.web.controllers.rest.v1;

import com.udevise.web.domain.Questionnaire;
import com.udevise.web.services.QuestionnaireServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/questionnaire")
public class QuestionnaireController {

  @Autowired
  QuestionnaireServiceImpl questionnaireService;

  @PostMapping
  public ResponseEntity<Questionnaire> createQuestionnaire(@RequestBody @Valid Questionnaire questionnaire, Principal principal) {
    return ResponseEntity.status(HttpStatus.CREATED).body(questionnaireService.save(questionnaire));
  }

  @GetMapping(path = "/{id}")
  public Questionnaire getQuestionnaire(@PathVariable String id) {
    return questionnaireService.findById(id);

  }
}
