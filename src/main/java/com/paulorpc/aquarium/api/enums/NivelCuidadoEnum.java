package com.paulorpc.aquarium.api.enums;

import java.util.Arrays;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum NivelCuidadoEnum {
  
  iniciante("Iniciante"), intermediario("Intermediário"), avancado("Avançado");

  private String descricao;

  NivelCuidadoEnum(String descricao) {
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
  public static NivelCuidadoEnum setValue(String value) {
    Optional<NivelCuidadoEnum> tipo = Arrays.stream(NivelCuidadoEnum.values())
        .filter(t -> t.toString().equalsIgnoreCase(value)).findAny();

    return tipo.orElse(null);
  }

}
