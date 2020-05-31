package com.paulorpc.aquarium.api.response;

public class Error {

  private String code;
  private String message;
  
  public Error(String code, String message) {
    super();
    this.code = code;
    this.message = message;
  }
  
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "Error [code=" + code + ", message=" + message + "]";
  }
  
}
