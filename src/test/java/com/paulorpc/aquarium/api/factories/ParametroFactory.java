package com.paulorpc.aquarium.api.factories;

import com.paulorpc.aquarium.api.entities.Parametro;
import java.time.LocalDateTime;

public class ParametroFactory {

  public static Parametro seed() {
    LocalDateTime data = LocalDateTime.now();

    return Parametro.builder()
        .nome("Salinidade")
        .abreviacaoNome("d. esp.")
        .dtAtualizacao(data)
        .dtAtualizacao(data)
        .escalaInicio(1.009)
        .escalaFim(1.030)
        .unidadeMedida("?")
        .vlrIdealFim(1.025)
        .vlrIdealInicio(1.024)
        .build();
  }
}
