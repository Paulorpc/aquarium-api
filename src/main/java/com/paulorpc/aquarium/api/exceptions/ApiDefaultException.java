package com.paulorpc.aquarium.api.exceptions;

import java.util.Optional;

public class ApiDefaultException extends Exception {

  private static final long serialVersionUID = 1L;

  private String message;

  public ApiDefaultException() {}

  public ApiDefaultException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }

  /***
   * Formata as mensagem de Exception com um padrão de retorno.
   *
   * @param exception
   * @return
   * @throws Exception
   */
  public static String formataExceptionMessage(Throwable exception) throws Exception {

    Optional<Throwable> exceptionOpt = Optional.ofNullable(exception);

    return exceptionOpt
        .map(
            e -> {
              String messageBase = e.getClass().getSimpleName();
              String message = messageBase + ": No message available.";

              if (e.getMessage() != null) {
                message = messageBase + ". " + e.getMessage() + ".";
              }
              return message;
            })
        .orElseThrow(() -> new Exception("Impossível formatar Exception null"));
  }
}
