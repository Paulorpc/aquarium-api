package com.paulorpc.aquarium.api.exceptions;

import com.paulorpc.aquarium.api.response.ResponseError;
import com.paulorpc.aquarium.api.util.Global;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/***
 * Classe de controle de Exceções do componente @Controller
 */
@ControllerAdvice
public class ApiExceptionHandler {

  private final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

  /***
   * Método de controle de Exceções para erros internos do servidor.
   */
  @ExceptionHandler({RuntimeException.class, NullPointerException.class, Exception.class})
  protected ResponseEntity<ResponseError> conflitHandlerException(Throwable e, WebRequest request)
      throws Exception {
    URI uri = Global.getUri();
    log.error(
        "Internal Server Erro. Message: "
            + Global.formataExceptionMessage(e)
            + ". Path: "
            + uri.getPath());
    e.printStackTrace();

    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    ResponseError responseError = new ResponseError(status, e, Global.getUri());
    return ResponseEntity.status(status).body(responseError);
  }

  /***
   * Método de controle de Exceções para erros de requisição do cliente, lançados com classes que
   * extendem ApiDefaultException.
   */
  @ExceptionHandler(ApiDefaultException.class)
  protected ResponseEntity<ResponseError> apiConflitHandlerException(
      Throwable e, WebRequest request) throws Exception {
    URI uri = Global.getUri();
    log.warn(
        "Requisição continha erros: "
            + Global.formataExceptionMessage(e)
            + ". Path: "
            + uri.getPath());

    HttpStatus status = HttpStatus.BAD_REQUEST;

    ResponseError responseError = new ResponseError(status, e, uri);
    return ResponseEntity.status(status).body(responseError);
  }
}
