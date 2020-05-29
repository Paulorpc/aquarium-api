package com.paulorpc.aquarium.api.exceptions;

public class NotFoundException extends ApiDefaultException {

  private static final long serialVersionUID = 1L;
  
  public NotFoundException() {
    super("Requisição não localizada"); 
  }
  
  public NotFoundException(String message) {
    super(message);
  }

}
