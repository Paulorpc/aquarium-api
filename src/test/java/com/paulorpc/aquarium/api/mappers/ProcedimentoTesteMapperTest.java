package com.paulorpc.aquarium.api.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.paulorpc.aquarium.api.dtos.ProcedimentoTesteDto;
import com.paulorpc.aquarium.api.entities.ProcedimentoTeste;
import com.paulorpc.aquarium.api.factories.ParametroFactory;
import com.paulorpc.aquarium.api.factories.ProcedimentoTesteFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProcedimentoTesteMapperTest {

  @Autowired private ProcedimentoTesteMapper mapper;

  @Test
  void should_convert_from_procedimentoTeste_to_ProcedimentoTestedto() {
    ProcedimentoTeste entidade = ProcedimentoTesteFactory.INSTANCE.seed();
    entidade.setParametro(ParametroFactory.INSTANCE.seed());

    ProcedimentoTesteDto dto = mapper.toProcedimentoTesteDto(entidade);

    assertNotNull(dto.getNroEtapa());
    assertNotNull(dto.getProcedimento());
    assertNotNull(dto.getDtCadastro());
    assertNotNull(dto.getDtAtualizacao());

    assertEquals(entidade.getNroEtapa(), dto.getNroEtapa());
    assertEquals(entidade.getProcedimento(), dto.getProcedimento());
    assertEquals(entidade.getDtCadastro(), dto.getDtCadastro());
    assertEquals(entidade.getDtAtualizacao(), dto.getDtAtualizacao());
  }

  @Test
  void should_convert_from_procedimentoTesteDto_to_ProcedimentoTeste() {
    ProcedimentoTesteDto dto = ProcedimentoTesteFactory.INSTANCE.seedDto();
    ProcedimentoTeste entidade = mapper.toProcedimentoTeste(dto);

    assertNotNull(entidade.getNroEtapa());
    assertNotNull(entidade.getProcedimento());
    assertNotNull(entidade.getDtCadastro());
    assertNotNull(entidade.getDtAtualizacao());

    assertEquals(dto.getNroEtapa(), entidade.getNroEtapa());
    assertEquals(dto.getProcedimento(), entidade.getProcedimento());
    assertEquals(dto.getDtCadastro(), entidade.getDtCadastro());
    assertEquals(dto.getDtAtualizacao(), entidade.getDtAtualizacao());
  }
}
