package com.paulorpc.aquarium.api.enums;

import java.util.Arrays;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum NivelCuidadoEnum {
  
  iniciante(1, "Iniciante"),
  intermediario(2, "Intermediário"), 
  experiente(3, "Experiente");

  private int nivel;
  private String descricao;

  NivelCuidadoEnum(int nivel, String descricao) {
    this.descricao = descricao;
  }

  public int getNivel() {
    return nivel;
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
