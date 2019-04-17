package com.udevise.web.services;

import com.udevise.web.domain.model.User;
import com.udevise.web.domain.responses.UserResponse;

import java.util.List;

public interface UserService {
  User save(User user);

  User findById(String id);

  User findUserByEmail(String email);

  List<UserResponse> getResponsesUserSubmitted(User user);
}
