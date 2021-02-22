package com.paulorpc.aquarium.api.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.paulorpc.aquarium.api.dtos.AquarioDto;
import com.paulorpc.aquarium.api.dtos.EquipamentoDto;
import com.paulorpc.aquarium.api.entities.Aquario;
import com.paulorpc.aquarium.api.entities.Equipamento;
import com.paulorpc.aquarium.api.enums.AvaliacaoEnum;
import java.time.LocalDateTime;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EquipamentoMapperTest {

  @Autowired private EquipamentoMapper mapper;

  @Test
  void should_convert_from_equipamento_to_equipamentoDto() {
    Set<Aquario> aquarios = Set.of(Aquario.builder().id(1L).build());
    Equipamento equipamento =
        Equipamento.builder()
            .avaliacao(AvaliacaoEnum.CINCO)
            .dtAquisicao(LocalDateTime.now().toLocalDate().minusDays(30))
            .nome("skimmer")
            .aquarios(aquarios)
            .build();

    EquipamentoDto equipamentoDto = mapper.toEquipamentoDto(equipamento);

    assertNotNull(equipamentoDto.getNome());
    assertNotNull(equipamentoDto.getAvaliacao());
    assertNotNull(equipamentoDto.getDtAquisicao());
    assertNotNull(equipamentoDto.getAquarios());

    assertEquals(equipamento.getNome(), equipamentoDto.getNome());
    assertEquals(equipamento.getAvaliacao(), equipamentoDto.getAvaliacao());
    assertEquals(equipamento.getDtAquisicao(), equipamentoDto.getDtAquisicao());
    assertTrue(
        equipamentoDto.getAquarios().stream()
            .map(AquarioDto::getId)
            .findFirst()
            .equals(aquarios.stream().map(Aquario::getId).findFirst()));
  }

  @Test
  void should_convert_from_equipamentoDto_to_equipamento() {
    Set<AquarioDto> aquarios = Set.of(AquarioDto.builder().id(1L).build());
    EquipamentoDto equipamentoDto =
        EquipamentoDto.builder()
            .avaliacao(AvaliacaoEnum.CINCO)
            .dtAquisicao(LocalDateTime.now().toLocalDate().minusDays(30))
            .nome("skimmer")
            .aquarios(aquarios)
            .build();

    Equipamento equipamento = mapper.toEquipamento(equipamentoDto);

    assertNotNull(equipamento.getNome());
    assertNotNull(equipamento.getAvaliacao());
    assertNotNull(equipamento.getDtAquisicao());
    assertNotNull(equipamento.getAquarios());

    assertEquals(equipamentoDto.getNome(), equipamento.getNome());
    assertEquals(equipamentoDto.getAvaliacao(), equipamento.getAvaliacao());
    assertEquals(equipamentoDto.getDtAquisicao(), equipamento.getDtAquisicao());
    assertTrue(
        equipamentoDto.getAquarios().stream()
            .map(AquarioDto::getId)
            .findFirst()
            .equals(equipamento.getAquarios().stream().map(Aquario::getId).findFirst()));
  }
}
