package com.paulorpc.aquarium.api.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.paulorpc.aquarium.api.dtos.AquarioDto;
import com.paulorpc.aquarium.api.entities.Aquario;
import com.paulorpc.aquarium.api.entities.TipoAquario;
import com.paulorpc.aquarium.api.enums.TipoAguaEnum;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AquarioMapperTest {

  @Autowired private AquarioMapper mapper;

  @Test
  void should_convert_from_aquario_to_aquartioDto() {
    Aquario aquario =
        Aquario.builder()
            .id(1L)
            .nome("Aquario 1")
            .dtCadastro(LocalDateTime.now())
            .dtInicio(LocalDate.now().minusDays(30))
            .dtAtualizacao(LocalDateTime.now())
            .tipoAquario(
                TipoAquario.builder().id(2L).tipo("marinho").tipoAgua(TipoAguaEnum.salgada).build())
            .status(true)
            .tipoAgua("salgada")
            .volume(200)
            .build();

    AquarioDto aquarioDto = mapper.toAquarioDto(aquario);

    assertNotNull(aquarioDto.getNome());
    assertEquals(aquario.getNome(), aquarioDto.getNome());

    assertNotNull(aquarioDto.getIdTipoAquario());
    assertEquals(aquario.getTipoAquario().getId(), aquarioDto.getIdTipoAquario());

    assertNotNull(aquarioDto.getId());
    assertNotNull(aquarioDto.getNome());
    assertNotNull(aquarioDto.getDtCadastro());
    assertNotNull(aquarioDto.getDtInicio());
    assertNotNull(aquarioDto.getDtAtualizacao());
    assertNotNull(aquarioDto.getIdTipoAquario());
    assertNotNull(aquarioDto.getStatus());
    assertNotNull(aquarioDto.getTipoAgua());
    assertNotNull(aquarioDto.getVolume());

    assertEquals(aquario.getId(), aquarioDto.getId());
    assertEquals(aquario.getNome(), aquarioDto.getNome());
    assertEquals(aquario.getDtCadastro(), aquarioDto.getDtCadastro());
    assertEquals(aquario.getDtInicio(), aquarioDto.getDtInicio());
    assertEquals(aquario.getDtAtualizacao(), aquarioDto.getDtAtualizacao());
    assertEquals(aquario.getTipoAquario().getId(), aquarioDto.getIdTipoAquario());
    assertEquals(aquario.getStatus(), aquarioDto.getStatus());
    assertEquals(aquario.getTipoAgua(), aquarioDto.getTipoAgua());
  }

  @Test
  void should_convert_from_aquarioDto_to_aquartio() {
    AquarioDto aquarioDto =
        AquarioDto.builder()
            .id(1L)
            .nome("Aquario 1")
            .dtCadastro(LocalDateTime.now())
            .dtInicio(LocalDate.now().minusDays(30))
            .dtAtualizacao(LocalDateTime.now())
            .idTipoAquario(1L)
            .status(true)
            .tipoAgua("salgada")
            .volume(200)
            .build();

    Aquario aquario = mapper.toAquario(aquarioDto);

    assertNotNull(aquario.getId());
    assertNotNull(aquario.getNome());
    assertNotNull(aquario.getDtCadastro());
    assertNotNull(aquario.getDtInicio());
    assertNotNull(aquario.getDtAtualizacao());
    assertNotNull(aquario.getTipoAquario());
    assertNotNull(aquario.getStatus());
    assertNotNull(aquario.getTipoAgua());
    assertNotNull(aquario.getVolume());

    assertEquals(aquarioDto.getId(), aquario.getId());
    assertEquals(aquarioDto.getNome(), aquario.getNome());
    assertEquals(aquarioDto.getDtCadastro(), aquario.getDtCadastro());
    assertEquals(aquarioDto.getDtInicio(), aquario.getDtInicio());
    assertEquals(aquarioDto.getDtAtualizacao(), aquario.getDtAtualizacao());
    assertEquals(aquarioDto.getIdTipoAquario(), aquario.getTipoAquario().getId());
    assertEquals(aquarioDto.getStatus(), aquario.getStatus());
    assertEquals(aquarioDto.getTipoAgua(), aquario.getTipoAgua());
  }
}
