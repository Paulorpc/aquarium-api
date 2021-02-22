package com.paulorpc.aquarium.api.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import java.util.Optional;

public enum NivelAgressividadeEnum {
  NA(1, "Não Agressivo"),
  SA(2, "Semi-Agressivo"),
  AG(3, "Agressivo");

  private int nivel;
  private String descricao;

  NivelAgressividadeEnum(int nivel, String descricao) {
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
  public static NivelAgressividadeEnum setValue(String value) {
    Optional<NivelAgressividadeEnum> tipo =
        Arrays.stream(NivelAgressividadeEnum.values())
            .filter(t -> t.toString().equalsIgnoreCase(value))
            .findAny();

    return tipo.orElse(null);
  }
}
