package com.paulorpc.aquarium.api.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.paulorpc.aquarium.api.dtos.BiotaDto;
import com.paulorpc.aquarium.api.dtos.TaxonomiaDto;
import com.paulorpc.aquarium.api.entities.Biota;
import com.paulorpc.aquarium.api.enums.NivelCuidadoEnum;
import com.paulorpc.aquarium.api.enums.RiscoExtincaoEnum;
import com.paulorpc.aquarium.api.enums.TamanhoBiotaEnum;
import com.paulorpc.aquarium.api.enums.TipoAguaEnum;
import com.paulorpc.aquarium.api.services.BiotaService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BiotaControllerIT {

  @Autowired private MockMvc mvc;

  @Autowired private BiotaService biotaService;

  @Test
  public void should_get_one_biota() throws Exception {
    this.mvc
        .perform(get("/api/biotas/1").contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.id", is(1)))
        .andExpect(jsonPath("$.data.nomePopular", is("Tang Amarelo e Cirurgião Amarelo")))
        .andExpect(jsonPath("$.data.nomeCientifico", containsString("Zebrasoma flavescens")))
        .andExpect(jsonPath("$.data.tipoAgua", is("salgada")))
        .andExpect(jsonPath("$.data.nivelCuidado", is("iniciante")))
        .andExpect(jsonPath("$.data.volumeMinAquario", is(180.00)))
        .andExpect(jsonPath("$.data.alimentacao", is("herbívoro")))
        .andExpect(jsonPath("$.data.habitat", is("Águas tropicais ricas em coral")))
        .andExpect(
            jsonPath(
                "$.data.regiao",
                is(
                    "Oceano Pacífico: Ryukyu, Mariana, Marshall, Marcus, ilhas havaianas. Foi relatado na costa da Flórida no Atlântico Centro-Oeste")))
        .andExpect(jsonPath("$.data.tamanho", is("G")))
        .andExpect(jsonPath("$.data.riscoExtincao", is("LC")))
        .andExpect(jsonPath("$.data.usuarioCadastro", is("sistema")))
        .andExpect(jsonPath("$.data.usuarioAtualizacao", is("sistema")));
  }

  @Test
  public void should_get_all_biotas() throws Exception {
    this.mvc
        .perform(get("/api/biotas").contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data", Matchers.hasSize(2)))
        .andExpect(jsonPath("$.data[0].nomePopular").exists())
        .andExpect(jsonPath("$.data[0].nomeCientifico").exists())
        .andExpect(jsonPath("$.data[0].tamanho").exists())
        .andExpect(jsonPath("$.data[0].riscoExtincao").exists());
  }

  @Test
  @DirtiesContext
  public void should_post_one_biota() throws Exception {
    LocalDate today = LocalDate.now();
    ObjectMapper objMapper = new ObjectMapper();
    objMapper.registerModule(new JavaTimeModule());

    BiotaDto biota =
        BiotaDto.builder()
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

    String json = objMapper.writeValueAsString(biota);

    this.mvc
        .perform(post("/api/biotas").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.path", containsString("/api/biotas/")))
        .andExpect(jsonPath("$.data.id", greaterThan(2)))
        .andExpect(jsonPath("$.data.nomePopular", is("Dory")))
        .andExpect(jsonPath("$.data.dtCadastro", containsString(today.toString())))
        .andExpect(jsonPath("$.data.dtAtualizacao", containsString(today.toString())));
  }

  @Test
  public void should_not_post_biota_invalid_parameters() throws Exception {
    LocalDateTime today = LocalDateTime.now();
    ObjectMapper objMapper = new ObjectMapper();
    objMapper.registerModule(new JavaTimeModule());

    BiotaDto aquario =
        BiotaDto.builder().nomePopular("nome").dtCadastro(today).dtAtualizacao(today).build();

    String json = objMapper.writeValueAsString(aquario);

    this.mvc
        .perform(post("/api/biotas").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.path", containsString("/api/biotas")))
        .andExpect(jsonPath("$..message", hasItem("Campo 'dtAtualizacao' deve ser nulo.")))
        .andExpect(jsonPath("$..message", hasItem("Campo 'dtCadastro' deve ser nulo.")))
        .andExpect(jsonPath("$..message", hasItem("Campo 'tipoAgua' é obrigatório.")));
  }

  @Test
  @DirtiesContext
  public void should_modify_one_biota() throws Exception {
    ObjectMapper objMapper = new ObjectMapper();
    objMapper.registerModule(new JavaTimeModule());

    Long biotaId = 1L;
    Optional<Biota> biotaDb = biotaService.buscar(biotaId);
    assertThat(biotaDb.get().getTaxonomia().getOrdem()).isNull();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedLocalDate = biotaDb.get().getDtCadastro().format(formatter);

    BiotaDto biota =
        BiotaDto.builder()
            .id(biotaId)
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

    String json = objMapper.writeValueAsString(biota);

    this.mvc
        .perform(put("/api/biotas").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.path", containsString("/api/biotas/" + biotaId)))
        .andExpect(jsonPath("$.data.id", is(biotaId.intValue())))
        .andExpect(jsonPath("$.data.nomePopular", is(biota.getNomePopular())))
        .andExpect(jsonPath("$.data.dtCadastro", is(formattedLocalDate)))
        .andExpect(jsonPath("$.data.taxonomia.ordem", is(biota.getTaxonomia().getOrdem())))
        .andExpect(jsonPath("$.data.dtAtualizacao", containsString(LocalDate.now().toString())));
  }

  @Test
  @DirtiesContext
  public void should_not_modify_biota_missing_id() throws Exception {
    ObjectMapper objMapper = new ObjectMapper();
    objMapper.registerModule(new JavaTimeModule());

    BiotaDto biota = BiotaDto.builder().nomePopular("Dory").avaliacao(5.0).build();

    String json = objMapper.writeValueAsString(biota);

    this.mvc
        .perform(
            put("/api/aquarios")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.path", containsString("/api/aquarios")))
        .andExpect(content().string(containsString("'id' obrigatório")));
  }

  @Test
  @DirtiesContext
  public void should_not_modify_biota_invalid_id() throws Exception {
    ObjectMapper objMapper = new ObjectMapper();
    objMapper.registerModule(new JavaTimeModule());

    Long biotaId = 99L;

    BiotaDto biota =
        BiotaDto.builder()
            .id(biotaId)
            .tipoAgua(TipoAguaEnum.salgada)
            .nomePopular("Dory")
            .avaliacao(5.0)
            .build();

    String json = objMapper.writeValueAsString(biota);

    this.mvc
        .perform(put("/api/biotas").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.path", containsString("/api/biotas")))
        .andExpect(content().string(containsString("NotFoundException")));
  }

  @Test
  @DirtiesContext
  public void should_delete_one_biota() throws Exception {
    Long biotaId = 1L;

    Optional<Biota> biota = biotaService.buscar(biotaId);
    assertTrue(biota.isPresent());

    this.mvc
        .perform(delete("/api/biotas/" + biotaId).contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.id", is(biota.get().getId().intValue())));

    biota = biotaService.buscar(biotaId);
    assertTrue(biota.isEmpty());

    List<Biota> deletados =
        biotaService.buscarTodosDeletados().stream()
            .filter(b -> b.getId() == biotaId)
            .collect(Collectors.toList());
    assertEquals(1, deletados.size());
  }
}
