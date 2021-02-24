package com.paulorpc.aquarium.api.factories;

import com.paulorpc.aquarium.api.entities.ProcedimentoTeste;
import java.time.LocalDateTime;

public class ProcedimentoTesteFactory {

  private static int n;

  public static ProcedimentoTeste seed() {
    LocalDateTime data = LocalDateTime.now();

    return ProcedimentoTeste.builder()
        .dtAtualizacao(data)
        .dtCadastro(data)
        .nroEtapa(++n)
        .procedimento("realizar procedimento " + n)
        .build();
  }
}
