package com.udevise.web.services;

import com.udevise.web.domain.model.User;

public interface UserService {
  User save(User user);
  User findById(String id);
  User findUserByEmail(String email);
}
