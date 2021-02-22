package com.paulorpc.aquarium.api.enums;

public enum AvaliacaoEnum {
  UM(1),
  DOIS(2),
  TRES(3),
  QUATRO(4),
  CINCO(5);

  private Integer avaliacao;

  AvaliacaoEnum(int avaliacao) {
    this.avaliacao = avaliacao;
  }

  public int rating() {
    return avaliacao.intValue();
  }
  ;
}
