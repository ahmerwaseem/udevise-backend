package com.udevise.web.security;

public class Auth0Properties {
  String apiAudience;
  String issuer;

  public String getApiAudience() {
    return apiAudience;
  }

  public void setApiAudience(String apiAudience) {
    this.apiAudience = apiAudience;
  }

  public String getIssuer() {
    return issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }
}
