package com.paulorpc.aquarium.api.response;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.springframework.validation.BindingResult;

public class Response<T> {

  private T data;
  private ArrayList<String> issues;

  public Response() {
    issues = new ArrayList<>();
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public ArrayList<String> getIssues() {
    return issues;
  }

  public void setIssues(ArrayList<String> errors) {
    issues = errors;
  }

  /***
   * Adiciona uma informação a lista de informações (issues).
   * 
   * @param issue
   */
  public boolean addIssue(String issue, Logger log) {
    try {
      log.warn("Requisição continha erros: {}", issue);
      getIssues().add(issue);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /***
   * Adiciona os erros da validação para a lista de informações (issues) retornadas ao client. Esse
   * método irá fazer o logging do tipo WARN.
   * 
   * @param result resulado da validação
   * @param Logger Instancia para log de erros
   */
  public void setIssuesFromResultErrors(BindingResult result, Logger log) {
    result.getAllErrors().forEach(e -> getIssues().add(e.getDefaultMessage()));
    log.warn("Requisição continha erros: " + getIssues().toString());
  }

  /***
   * Adiciona os erros da validação para a lista de informações (issues) retornadas ao client.
   * 
   * @param result resulado da validação
   */
  public void setIssuesFromResultErrors(BindingResult result) {
    result.getAllErrors().forEach(e -> getIssues().add(e.getDefaultMessage()));
  }

}
