package com.ngjackson.vetero.models;

public class DeleteLocationRequest {

  private String zip;
  private Long userId;

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
}
