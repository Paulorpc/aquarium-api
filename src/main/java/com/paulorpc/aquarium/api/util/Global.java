package com.paulorpc.aquarium.api.util;

import com.paulorpc.aquarium.api.exceptions.ApiDefaultException;
import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Global {

  /***
   * Retorna vazio quando valor for nulo.
   *
   * @param obj
   * @return
   */
  public static String nullToEmpty(String obj) {
    return (obj == null) ? "" : obj.trim();
  }

  /***
   * Retorna o caminho do URI desconsidarando caminho do servidor. Ex: htt://www.domain.com.br/
   *
   * @param uri
   * @return
   */
  public static String getPath(URI uri) {
    return uri.normalize().getPath().toString();
  }

  /***
   * Retorna o URI do request atual. Ao invocar o método fora de contexto será lançacado exceção.
   *
   * @return
   */
  public static URI getUri() {
    return ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
  }

  /***
   * Retorna data e hora de agora
   *
   * @return
   */
  public static String getDataHora() {
    return DataUtil.getDataHora();
  }

  /***
   * Formata as mensagem de Exception com um padrão de retorno.
   *
   * @param exception
   * @return
   * @throws Exception
   */
  public static String formataExceptionMessage(Throwable exception) throws Exception {
    return ApiDefaultException.formataExceptionMessage(exception);
  }
}
