package com.udevise.web.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document
public class User {

  public User() {
  }

  public User(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public User(String firstName, String lastName, String emailAddress, String imageUrl) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.emailAddress = emailAddress;
    this.imageUrl = imageUrl;
  }

  @Id
  @Indexed
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String id;
  private String firstName;
  private String lastName;
  @Indexed
  private String emailAddress;
  private String imageUrl;
  @JsonIgnore
  private Map<String,String> responseMap;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Map<String, String> getResponseMap() {
    return responseMap;
  }

  public void setResponseMap(Map<String, String> responseMap) {
    this.responseMap = responseMap;
  }
}
