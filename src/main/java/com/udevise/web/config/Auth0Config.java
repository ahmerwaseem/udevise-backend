package com.udevise.web.config;

import com.udevise.web.security.Auth0Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Auth0Config {

  @Value(value = "${auth0.apiAudience}")
  String apiAudience;
  @Value(value = "${auth0.issuer}")
  String issuer;

  @Bean
  public Auth0Properties getAuth0Properties() {
    Auth0Properties auth0Properties = new Auth0Properties();
    auth0Properties.setApiAudience(apiAudience);
    auth0Properties.setIssuer(issuer);
    return auth0Properties;
  }

}
