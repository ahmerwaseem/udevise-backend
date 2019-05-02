package com.udevise.web.Utilities.mappers;

import com.udevise.web.domain.model.User;
import com.udevise.web.domain.requests.SaveUserRequest;

public class UserMapper {

  public static User  mapSaveUserRequestToUser(SaveUserRequest saveUserRequest){
    User user = null;
    if (saveUserRequest != null){
      user = new User();
      if ((saveUserRequest.getGiven_name() != null && !saveUserRequest.getGiven_name().isEmpty())
      || (saveUserRequest.getFamily_name() != null && !saveUserRequest.getFamily_name().isEmpty() ) ){
        user.setFirstName(saveUserRequest.getGiven_name());
        user.setLastName(saveUserRequest.getFamily_name());
      } else {
        user.setFirstName(saveUserRequest.getNickname());
      }

      user.setEmailAddress(saveUserRequest.getEmail());
      user.setImageUrl(saveUserRequest.getPicture());
    }

    return user;
  }
}
