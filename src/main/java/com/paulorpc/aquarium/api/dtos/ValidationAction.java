package com.paulorpc.aquarium.api.dtos;

/***
 * Diferentes tipos de validação para DTOs
 */
public interface ValidationAction {

  public interface Default {}

  public interface Post extends Default {}

  public interface Put extends Default {}

  public interface Patch extends Default {}
}
