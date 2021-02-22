package com.paulorpc.aquarium.api.response;

import com.paulorpc.aquarium.api.util.Global;
import java.net.URI;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

public class ResponseError implements Response {

  private final String path;
  private ArrayList<Error> messages;

  public ResponseError(URI uri) {
    messages = new ArrayList<>();
    this.path = Global.getPath(uri);
  }

  public ResponseError(ArrayList<Error> messages, URI uri) {
    this(uri);
    this.messages = messages;
  }

  public ResponseError(HttpStatus status, Throwable e, URI uri) throws Exception {
    this(uri);

    if (status == HttpStatus.INTERNAL_SERVER_ERROR) {
      addMessageFromServerException(e);
    } else {
      addMessageFromClientException(e);
    }
  }

  @Override
  public String getPath() {
    return path;
  }

  public ArrayList<Error> getMessages() {
    return messages;
  }

  /***
   * Adiciona um erro na lista de erros.
   *
   * @param issue
   */
  private boolean addError(Error error) {
    try {
      getMessages().add(error);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /***
   * Adiciona um erro na lista de erros. Esse método irá fazer o logging do tipo WARN.
   *
   * @param issue
   */
  public boolean addMessage(Error error, Logger log) {
    try {
      log.warn("Requisição continha erros: {}", error.toString());
      return addError(error);
    } catch (Exception e) {
      return false;
    }
  }

  /***
   * Adiciona os erros da validação para a lista de erros retornadas ao client. Esse método irá
   * fazer o logging do tipo WARN.
   *
   * @param result resulado da validação
   * @param Logger Instancia para log de erros
   */
  public void addMessagesFromResultErrors(BindingResult result, Logger log) {
    result
        .getAllErrors()
        .forEach(e -> getMessages().add(new Error(e.getCode(), e.getDefaultMessage())));
    log.warn("Requisição continha erros: " + getMessages().toString());
  }

  /***
   * Adiciona os erros da validação para a lista de erros retornadas ao client.
   *
   * @param result resulado da validação
   */
  public void addMessagesFromResultErrors(BindingResult result) {
    result
        .getAllErrors()
        .forEach(e -> getMessages().add(new Error(e.getCode(), e.getDefaultMessage())));
  }

  /***
   * Adiciona um exception para a lista de erros retornadas ao client.
   *
   * @param e
   * @throws Exception
   */
  public void addMessageFromServerException(Throwable e) throws Exception {
    addError(new Error("I0001", Global.formataExceptionMessage(e)));
  }

  /***
   * Adiciona um exception para a lista de erros retornadas ao client.
   *
   * @param e
   * @throws Exception
   */
  public void addMessageFromClientException(Throwable e) throws Exception {
    addError(new Error("E0002", Global.formataExceptionMessage(e)));
  }

  /***
   * Adiciona um exception para a lista de erros retornadas ao client. Esse método irá fazer o
   * logging do tipo ERROR.
   *
   * @param e
   * @param log
   * @throws Exception
   */
  public void addMessageFromClientException(Throwable e, Logger log) throws Exception {
    addMessageFromClientException(e);
    log.error("Exception: " + getMessages().get(getMessages().size()));
  }
}
