package com.udevise.web.services;

import com.udevise.web.domain.model.Questionnaire;
import com.udevise.web.domain.model.User;
import com.udevise.web.domain.responses.UserResponse;
import com.udevise.web.exceptions.NotFoundException;
import com.udevise.web.repositories.QuestionnaireRepository;
import com.udevise.web.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private QuestionnaireRepository questionnaireRepository;

  public UserServiceImpl(UserRepository userRepository, QuestionnaireRepository questionnaireRepository) {
    this.userRepository = userRepository;
    this.questionnaireRepository = questionnaireRepository;
  }

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public User findById(String id) {
    Optional<User> user = userRepository.getUserById(id);
    if (!user.isPresent()) {
      return null;
    }
    return user.get();
  }

  @Override
  public User findUserByEmail(String email) {
    Optional<User> user = userRepository.getUserByEmailAddress(email);
    if (!user.isPresent()) {
      return null;
    }
    return user.get();
  }

  public List<UserResponse> getResponsesUserSubmitted(User user) {
    List<UserResponse> responses = new ArrayList<>();
    if (user != null){
      if (user.getResponseMap() != null){
        Iterator iterator = user.getResponseMap().entrySet().iterator();
        while (iterator.hasNext()){
          Map.Entry<String,String> entry = (Map.Entry)iterator.next();
          UserResponse userResponse = new UserResponse();
          userResponse.setQuestionnaireId(entry.getKey());
          userResponse.setResponseId(entry.getValue());
          Optional<Questionnaire> questionnaire = questionnaireRepository.findById(entry.getKey());
          if (questionnaire.isPresent()){
            userResponse.setQuestionnaireTitle(questionnaire.get().getTitle());
            userResponse.setTimeCompleted(
              questionnaire.get().getResponses()
                .stream()
                .filter(item -> userResponse.getResponseId().equals(item.getResponseId()))
                .findFirst().get().getSubmitTime());
            responses.add(userResponse);
          } else {
            continue;
          }
        }

      }
      return responses;
    } else {
      throw new NotFoundException();
    }
  }


}
