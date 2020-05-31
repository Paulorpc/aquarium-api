package com.paulorpc.aquarium.api.enums;

import java.util.Arrays;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoAguaEnum {

  doce("Doce"), salgada("Salgada");

  private String descricao;

  TipoAguaEnum(String descricao) {
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
  public static TipoAguaEnum setValue(String value) {
    Optional<TipoAguaEnum> tipo = Arrays.stream(TipoAguaEnum.values())
        .filter(t -> t.toString().equalsIgnoreCase(value)).findAny();

    return tipo.orElse(null);
  }
}
