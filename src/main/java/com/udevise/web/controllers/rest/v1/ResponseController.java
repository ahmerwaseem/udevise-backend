package com.udevise.web.controllers.rest.v1;

import com.udevise.web.domain.Response;
import com.udevise.web.services.ResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/answer")
public class ResponseController {

  private ResponseService responseService;

  public ResponseController(ResponseService responseService) {
    this.responseService = responseService;
  }

  @PostMapping
  public ResponseEntity submitAnswers(@RequestBody Response response, Principal principal) {
    responseService.save(response);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
