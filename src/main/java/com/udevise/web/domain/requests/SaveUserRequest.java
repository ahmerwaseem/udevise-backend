package com.udevise.web.domain.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SaveUserRequest {
  @NotEmpty
  private String email;
  private String nickname;
  private String picture;
  private String family_name;
  private String given_name;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getFamily_name() {
    return family_name;
  }

  public void setFamily_name(String family_name) {
    this.family_name = family_name;
  }

  public String getGiven_name() {
    return given_name;
  }

  public void setGiven_name(String given_name) {
    this.given_name = given_name;
  }
}
