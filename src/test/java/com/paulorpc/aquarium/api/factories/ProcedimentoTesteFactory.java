package com.paulorpc.aquarium.api.factories;

import com.paulorpc.aquarium.api.dtos.ProcedimentoTesteDto;
import com.paulorpc.aquarium.api.entities.ProcedimentoTeste;
import java.time.LocalDateTime;

public class ProcedimentoTesteFactory
    implements BaseSeeder<ProcedimentoTeste, ProcedimentoTesteDto> {

  public static ProcedimentoTesteFactory INSTANCE = new ProcedimentoTesteFactory();

  private static int qtde;
  private static int qtdeDto;
  LocalDateTime data = LocalDateTime.now();

  @Override
  public ProcedimentoTeste seed() {
    return ProcedimentoTeste.builder()
        .dtAtualizacao(data)
        .dtCadastro(data)
        .nroEtapa(++qtde)
        .procedimento("realizar procedimento " + qtde)
        .build();
  }

  @Override
  public ProcedimentoTesteDto seedDto() {
    return ProcedimentoTesteDto.builder()
        .dtAtualizacao(data)
        .dtCadastro(data)
        .nroEtapa(++qtdeDto)
        .procedimento("realizar procedimento dto " + qtdeDto)
        .build();
  }
}
