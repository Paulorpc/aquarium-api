package com.paulorpc.aquarium.api.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.paulorpc.aquarium.api.dtos.BiotaDto;
import com.paulorpc.aquarium.api.dtos.TaxonomiaDto;
import com.paulorpc.aquarium.api.entities.Biota;
import com.paulorpc.aquarium.api.entities.Taxonomia;
import com.paulorpc.aquarium.api.enums.NivelCuidadoEnum;
import com.paulorpc.aquarium.api.enums.RiscoExtincaoEnum;
import com.paulorpc.aquarium.api.enums.TamanhoBiotaEnum;
import com.paulorpc.aquarium.api.enums.TipoAguaEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BiotaMapperTest {

  @Autowired BiotaMapper mapper;

  @Test
  void should_convert_from_biota_to_biotaDto() {
    Biota biota =
        Biota.builder()
            .alimentacao("alimentacao")
            .avaliacao(5.0)
            .habitat("habitat")
            .infoAdicional("info adicional")
            .nivelCuidado(NivelCuidadoEnum.avancado)
            .nomePopular("Dory")
            .reefSafe(true)
            .regiao("Regiao")
            .riscoExtincao(RiscoExtincaoEnum.DD)
            .tamanho(TamanhoBiotaEnum.G)
            .tipoAgua(TipoAguaEnum.salgada)
            .volumeMinAquario(200.0)
            .taxonomia(
                Taxonomia.builder()
                    .classe("classe")
                    .dominio("dominio")
                    .especie("especie")
                    .familia("familia")
                    .filo("filo")
                    .genero("genero")
                    .ordem("ordem")
                    .reino("reino")
                    .build())
            .build();

    BiotaDto biotaDto = mapper.toBiotaDto(biota);

    assertNotNull(biotaDto.getAlimentacao());
    assertNotNull(biotaDto.getAvaliacao());
    assertNotNull(biotaDto.getHabitat());
    assertNotNull(biotaDto.getInfoAdicional());
    assertNotNull(biotaDto.getNivelCuidado());
    assertNotNull(biotaDto.getNomePopular());
    assertNotNull(biotaDto.isReefSafe());
    assertNotNull(biotaDto.getRegiao());
    assertNotNull(biotaDto.getRiscoExtincao());
    assertNotNull(biotaDto.getTamanho());
    assertNotNull(biotaDto.getTipoAgua());
    assertNotNull(biotaDto.getVolumeMinAquario());
    assertNotNull(biotaDto.getTaxonomia());

    assertEquals(biota.getAlimentacao(), biotaDto.getAlimentacao());
    assertEquals(biota.getAvaliacao(), biotaDto.getAvaliacao());
    assertEquals(biota.getHabitat(), biotaDto.getHabitat());
    assertEquals(biota.getInfoAdicional(), biotaDto.getInfoAdicional());
    assertEquals(biota.getNivelCuidado(), biotaDto.getNivelCuidado());
    assertEquals(biota.getNomePopular(), biotaDto.getNomePopular());
    assertEquals(biota.isReefSafe(), biotaDto.isReefSafe());
    assertEquals(biota.getRegiao(), biotaDto.getRegiao());
    assertEquals(biota.getRiscoExtincao(), biotaDto.getRiscoExtincao());
    assertEquals(biota.getTamanho(), biotaDto.getTamanho());
    assertEquals(biota.getTipoAgua(), biotaDto.getTipoAgua());
    assertEquals(biota.getVolumeMinAquario(), biotaDto.getVolumeMinAquario());

    assertEquals(biota.getTaxonomia().getClasse(), biotaDto.getTaxonomia().getClasse());
    assertEquals(biota.getTaxonomia().getDominio(), biotaDto.getTaxonomia().getDominio());
    assertEquals(biota.getTaxonomia().getEspecie(), biotaDto.getTaxonomia().getEspecie());
    assertEquals(biota.getTaxonomia().getFamilia(), biotaDto.getTaxonomia().getFamilia());
    assertEquals(biota.getTaxonomia().getFilo(), biotaDto.getTaxonomia().getFilo());
    assertEquals(biota.getTaxonomia().getGenero(), biotaDto.getTaxonomia().getGenero());
    assertEquals(biota.getTaxonomia().getOrdem(), biotaDto.getTaxonomia().getOrdem());
    assertEquals(biota.getTaxonomia().getReino(), biotaDto.getTaxonomia().getReino());
  }

  @Test
  void should_convert_from_biotaDto_to_biota() {
    BiotaDto biotaDto =
        BiotaDto.builder()
            .id(1L)
            .alimentacao("alimentacao")
            .avaliacao(5.0)
            .habitat("habitat")
            .infoAdicional("info adicional")
            .nivelCuidado(NivelCuidadoEnum.avancado)
            .nomePopular("Dory")
            .reefSafe(true)
            .regiao("Regiao")
            .riscoExtincao(RiscoExtincaoEnum.DD)
            .tamanho(TamanhoBiotaEnum.G)
            .tipoAgua(TipoAguaEnum.salgada)
            .volumeMinAquario(200.0)
            .taxonomia(
                TaxonomiaDto.builder()
                    .classe("classe")
                    .dominio("dominio")
                    .especie("especie")
                    .familia("familia")
                    .filo("filo")
                    .genero("genero")
                    .ordem("ordem")
                    .reino("reino")
                    .build())
            .build();

    Biota biota = mapper.toBiota(biotaDto);

    assertNotNull(biota.getAlimentacao());
    assertNotNull(biota.getAvaliacao());
    assertNotNull(biota.getHabitat());
    assertNotNull(biota.getInfoAdicional());
    assertNotNull(biota.getNivelCuidado());
    assertNotNull(biota.getNomePopular());
    assertNotNull(biota.isReefSafe());
    assertNotNull(biota.getRegiao());
    assertNotNull(biota.getRiscoExtincao());
    assertNotNull(biota.getTamanho());
    assertNotNull(biota.getTipoAgua());
    assertNotNull(biota.getVolumeMinAquario());
    assertNotNull(biota.getTaxonomia());

    assertEquals(biota.getAlimentacao(), biotaDto.getAlimentacao());
    assertEquals(biota.getAvaliacao(), biotaDto.getAvaliacao());
    assertEquals(biota.getHabitat(), biotaDto.getHabitat());
    assertEquals(biota.getInfoAdicional(), biotaDto.getInfoAdicional());
    assertEquals(biota.getNivelCuidado(), biotaDto.getNivelCuidado());
    assertEquals(biota.getNomePopular(), biotaDto.getNomePopular());
    assertEquals(biota.isReefSafe(), biotaDto.isReefSafe());
    assertEquals(biota.getRegiao(), biotaDto.getRegiao());
    assertEquals(biota.getRiscoExtincao(), biotaDto.getRiscoExtincao());
    assertEquals(biota.getTamanho(), biotaDto.getTamanho());
    assertEquals(biota.getTipoAgua(), biotaDto.getTipoAgua());
    assertEquals(biota.getVolumeMinAquario(), biotaDto.getVolumeMinAquario());

    assertEquals(biota.getTaxonomia().getClasse(), biotaDto.getTaxonomia().getClasse());
    assertEquals(biota.getTaxonomia().getDominio(), biotaDto.getTaxonomia().getDominio());
    assertEquals(biota.getTaxonomia().getEspecie(), biotaDto.getTaxonomia().getEspecie());
    assertEquals(biota.getTaxonomia().getFamilia(), biotaDto.getTaxonomia().getFamilia());
    assertEquals(biota.getTaxonomia().getFilo(), biotaDto.getTaxonomia().getFilo());
    assertEquals(biota.getTaxonomia().getGenero(), biotaDto.getTaxonomia().getGenero());
    assertEquals(biota.getTaxonomia().getOrdem(), biotaDto.getTaxonomia().getOrdem());
    assertEquals(biota.getTaxonomia().getReino(), biotaDto.getTaxonomia().getReino());
  }
}
