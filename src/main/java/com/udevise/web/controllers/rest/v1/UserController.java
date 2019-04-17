package com.udevise.web.controllers.rest.v1;

import com.udevise.web.Utilities.UserUtils;
import com.udevise.web.domain.responses.UserResponse;
import com.udevise.web.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/responses")
  public List<UserResponse> getListOfResponses(Principal principal){
    return userService.getResponsesUserSubmitted(UserUtils.getUserFromPrincipal(principal));
  }

}
