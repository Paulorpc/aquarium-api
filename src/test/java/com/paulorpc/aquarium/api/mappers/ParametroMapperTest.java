package com.paulorpc.aquarium.api.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.paulorpc.aquarium.api.dtos.ParametroDto;
import com.paulorpc.aquarium.api.entities.Parametro;
import com.paulorpc.aquarium.api.factories.ParametroFactory;
import com.paulorpc.aquarium.api.factories.ProcedimentoTesteFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ParametroMapperTest {

  @Autowired private ParametroMapper mapper;

  @Test
  void should_convert_from_procedimentoTeste_to_ProcedimentoTestedto() {
    Parametro entidade = ParametroFactory.INSTANCE.seed();
    entidade.setProcedimentosTeste(ProcedimentoTesteFactory.INSTANCE.seed(3));
    entidade.getProcedimentosTeste().forEach(p -> p.setParametro(entidade));

    ParametroDto dto = mapper.toParametroDto(entidade);

    assertNotNull(dto.getNome());
    assertNotNull(dto.getAbreviacaoNome());
    assertNotNull(dto.getEscalaInicio());
    assertNotNull(dto.getEscalaFim());
    assertNotNull(dto.getVlrIdealInicio());
    assertNotNull(dto.getVlrIdealFim());
    assertNotNull(dto.getProcedimentosTeste());

    assertEquals(entidade.getProcedimentosTeste().size(), dto.getProcedimentosTeste().size());
    assertEquals(
        entidade.getProcedimentosTeste().get(0).getProcedimento(),
        dto.getProcedimentosTeste().get(0).getProcedimento());
  }

  @Test
  void should_convert_from_procedimentoTesteDto_to_ProcedimentoTeste() {
    ParametroDto dto = ParametroFactory.INSTANCE.seedDto();
    dto.setProcedimentosTeste(ProcedimentoTesteFactory.INSTANCE.seedDto(3));

    Parametro entidade = mapper.toParametro(dto);

    assertNotNull(entidade.getNome());
    assertNotNull(entidade.getAbreviacaoNome());
    assertNotNull(entidade.getEscalaInicio());
    assertNotNull(entidade.getEscalaFim());
    assertNotNull(entidade.getVlrIdealInicio());
    assertNotNull(entidade.getVlrIdealFim());
    assertNotNull(entidade.getProcedimentosTeste());

    assertEquals(dto.getProcedimentosTeste().size(), entidade.getProcedimentosTeste().size());
    assertEquals(
        dto.getProcedimentosTeste().get(0).getProcedimento(),
        entidade.getProcedimentosTeste().get(0).getProcedimento());
  }
}
