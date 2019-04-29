package com.udevise.web.controllers.rest.v1;

import com.udevise.web.Utilities.UserUtils;
import com.udevise.web.domain.model.Questionnaire;
import com.udevise.web.domain.model.User;
import com.udevise.web.domain.requests.GradeRequest;
import com.udevise.web.domain.requests.ResponseRequest;
import com.udevise.web.services.QuestionnaireService;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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

  @GetMapping(path = "/{id}/report",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public @ResponseBody byte[] getFile(@PathVariable String id, Principal principal) throws IOException {
    return questionnaireService.getQuestionnaireResponseReport(id, UserUtils.getUserFromPrincipal(principal));
  }

  @PutMapping("/grade/{questionnaireId}/{responseId}")
  public ResponseEntity submitGradeAndFeedback(@RequestBody GradeRequest gradeRequest, @PathVariable String questionnaireId, @PathVariable String responseId, Principal principal) {
    questionnaireService.submitGradeAndFeedback(gradeRequest, UserUtils.getUserFromPrincipal(principal),questionnaireId,responseId);
    return ResponseEntity.status(HttpStatus.OK).build();

  }


}
