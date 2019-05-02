package com.udevise.web.controllers.rest.v1;

import com.udevise.web.Utilities.UserUtils;
import com.udevise.web.Utilities.mappers.UserMapper;
import com.udevise.web.domain.model.User;
import com.udevise.web.domain.requests.SaveUserRequest;
import com.udevise.web.domain.responses.UserResponse;
import com.udevise.web.services.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.LinkedHashMap;
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

  @PostMapping()
  public void saveUser(@Valid @RequestBody SaveUserRequest data){
    if (userService.findUserByEmail(data.getEmail())==null){
      userService.save(UserMapper.mapSaveUserRequestToUser(data));
    }
  }

}
