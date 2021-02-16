package com.paulorpc.aquarium.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SystemInfo {

  private final String nome = "Aquarium API";
  private final String versao = "1.0.0";
  private final String dataAtualizacao = "13/01/2020 21:08";

  public String getNome() {
    return nome;
  }

  public String getVersao() {
    return versao;
  }

  public String getDataAtualizacao() {
    return dataAtualizacao;
  }

  @JsonIgnore
  public String getSystemInfo() {
    return nome + "\n" + versao + "\n" + dataAtualizacao;
  }
}
