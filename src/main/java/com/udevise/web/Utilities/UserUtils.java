package com.udevise.web.Utilities;

import com.udevise.web.domain.model.Questionnaire;
import com.udevise.web.domain.model.Response;
import com.udevise.web.domain.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class UserUtils {

  public static String getIdentityProvider(LinkedHashMap map) {
    if (map != null){
      ArrayList<LinkedHashMap> identities = (ArrayList)map.get("identities");
      if (identities != null && identities.size()>0) {
        LinkedHashMap identitiesMap = identities.get(0);
        if (identitiesMap != null) {
          return (String) identitiesMap.get("provider");
        }
      }
    }
    return null;
  }

  public static User getUserDetails(User user,LinkedHashMap map) {
    if (user == null) {
      user = new User();
    }
    if (map != null) {
      if (getIdentityProvider(map).equalsIgnoreCase("auth0")){
        getUserFullName(user, (LinkedHashMap)map.get("user_metadata"));
      } else {
        getUserFullName(user, map);
      }
      user.setEmailAddress((String)map.get("email"));
      user.setImageUrl((String)map.get("picture"));
      return user;
    }
    return null;
  }

  public static void getUserFullName(User user, LinkedHashMap map) {
    if(map != null) {
      user.setFirstName((String) map.get("given_name"));
      user.setLastName((String) map.get("family_name"));
    }
  }

  public static User getUserFromPrincipal(Principal principal) {
    UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
    if (token != null){
      return (User) token.getPrincipal();
    }
    return null;
  }

  public static boolean hasUserResponded(User user, String id) {
    if (user!= null) {
      if (user.getResponseMap() != null) {
        return user.getResponseMap().containsKey(id);
      }
    }
    return false;
  }
}
