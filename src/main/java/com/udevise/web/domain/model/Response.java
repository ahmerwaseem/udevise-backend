package com.udevise.web.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class Response {

  public Response(User user, String responseId) {
    this.user = user;
    this.responseId = responseId;
  }

  private String responseId;

  @DBRef
  private User user;

  @JsonFormat(pattern="MM-dd-yyyy HH:mm:ss")
  private Date submitTime = Date.from(Instant.now());

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Date getSubmitTime() {
    return submitTime;
  }

  public void setSubmitTime(Date submitTime) {
    this.submitTime = submitTime;
  }

  public String getResponseId() {
    return responseId;
  }

  public void setResponseId(String responseId) {
    this.responseId = responseId;
  }
}
