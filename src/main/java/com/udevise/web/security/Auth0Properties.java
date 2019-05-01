package com.udevise.web.security;

public class Auth0Properties {
  String apiAudience;
  String issuer;
  String userClaim;

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

  public String getUserClaim() {
    return userClaim;
  }

  public void setUserClaim(String userClaim) {
    this.userClaim = userClaim;
  }
}
