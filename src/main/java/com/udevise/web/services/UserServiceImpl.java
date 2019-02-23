package com.udevise.web.services;

import com.udevise.web.domain.User;
import com.udevise.web.exceptions.NotFoundException;
import com.udevise.web.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository repository) {
    this.userRepository = repository;
  }

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public User findById(String id) {
    Optional<User> user = userRepository.getUserById(id);
    if (!user.isPresent()) {
      throw new NotFoundException("User not found with id: " + id);
    }
    return user.get();
  }

  @Override
  public User findUserByEmail(String email) {
    Optional<User> user = userRepository.getUserByEmailAddress(email);
    if (!user.isPresent()) {
      throw new NotFoundException("User not found with email: " + email);
    }
    return user.get();
  }


}
