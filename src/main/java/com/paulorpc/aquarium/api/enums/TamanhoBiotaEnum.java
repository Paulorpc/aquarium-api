package com.paulorpc.aquarium.api.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import java.util.Optional;

public enum TamanhoBiotaEnum {
  P("Pequeno"),
  M("Médio"),
  G("Grande");

  private String descricao;

  TamanhoBiotaEnum(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }

  /***
   * Método com propriedade JsonCreator para forçar caseinsensitive na desserialização JSON.
   *
   * @param value
   * @return
   */
  @JsonCreator
  public static TamanhoBiotaEnum setValue(String value) {
    Optional<TamanhoBiotaEnum> tipo =
        Arrays.stream(TamanhoBiotaEnum.values())
            .filter(t -> t.toString().equalsIgnoreCase(value))
            .findAny();

    return tipo.orElse(null);
  }
}
