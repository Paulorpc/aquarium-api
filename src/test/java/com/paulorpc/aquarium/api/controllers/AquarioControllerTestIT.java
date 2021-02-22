package com.paulorpc.aquarium.api.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
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
import com.paulorpc.aquarium.api.dtos.AquarioDto;
import com.paulorpc.aquarium.api.entities.Aquario;
import com.paulorpc.aquarium.api.services.AquarioService;
import com.paulorpc.aquarium.api.services.EquipamentoService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

/***
 * A anotação @SpringBootTest carrega todo contenxto da aplicação através do(s) arquivo(s) com a
 * anotação @SpringBoot. Sendo assim, é possível realizar o teste de integração (IT) em todos os
 * layers, inclusive o banco de dados h2 configurado no contexto de TEST
 * (src/test/resources/application.properties). No entanto, isso causa um dependência do dos dados
 * inseridos e qualquer alteração resultará em quebra dos testes de integração. O mesmo pode ocorrer
 * com os testes dos repositories, portanto também deve ser considerado mockar o banco de dados.
 *
 * MockMvcResultMatcher.content() não retorna mais por default como UTF-8, portanto, é necessário aficionar o .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AquarioControllerTestIT {

  @Autowired private MockMvc mvc;

  @Autowired private AquarioService aquarioService;

  @Autowired private EquipamentoService equipamentoService;

  @Test
  public void should_get_one_aquarium() throws Exception {
    LocalDate today = LocalDate.now();
    this.mvc
        .perform(get("/api/aquarios/1").contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.id", is(1)))
        .andExpect(jsonPath("$.data.nome", is("aquario 1")))
        .andExpect(jsonPath("$.data.dtInicio", containsString(today.toString())))
        .andExpect(jsonPath("$.data.status", is(true)))
        .andExpect(jsonPath("$.data.dtCadastro", containsString(today.toString())))
        .andExpect(jsonPath("$.data.idTipoAquario", is(1)))
        .andExpect(jsonPath("$.data.dtAtualizacao", containsString(today.toString())));
  }

  @Test
  public void should_get_all_aquariums() throws Exception {
    this.mvc
        .perform(get("/api/aquarios").contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data", Matchers.hasSize(3)))
        .andExpect(jsonPath("$.data[0].id").exists())
        .andExpect(jsonPath("$.data[0].nome").exists())
        .andExpect(jsonPath("$.data[0].dtInicio").exists())
        .andExpect(jsonPath("$.data[0].status").exists())
        .andExpect(jsonPath("$.data[0].dtCadastro").exists())
        .andExpect(jsonPath("$.data[0].dtAtualizacao").exists());
  }

  @Test
  @DirtiesContext
  public void should_post_one_aquarium() throws Exception {
    LocalDate today = LocalDate.now();
    ObjectMapper objMapper = new ObjectMapper();
    objMapper.registerModule(new JavaTimeModule());

    AquarioDto aquario =
        AquarioDto.builder()
            .nome("Aquario de teste")
            .dtInicio(LocalDate.now().minusDays(30))
            .idTipoAquario(2L)
            .status(true)
            .tipoAgua("salgada")
            .volume(200)
            .build();

    String json = objMapper.writeValueAsString(aquario);

    this.mvc
        .perform(post("/api/aquarios").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.path", containsString("/api/aquarios/")))
        .andExpect(jsonPath("$.data.id", greaterThan(3)))
        .andExpect(jsonPath("$.data.nome", is("Aquario de teste")))
        .andExpect(jsonPath("$.data.dtCadastro", containsString(today.toString())))
        .andExpect(jsonPath("$.data.dtAtualizacao", containsString(today.toString())));
  }

  @Test
  public void should_not_post_aquarium_invalid_parameters() throws Exception {
    ObjectMapper objMapper = new ObjectMapper();
    objMapper.registerModule(new JavaTimeModule());

    AquarioDto aquario =
        AquarioDto.builder()
            .dtCadastro(LocalDateTime.now())
            .dtInicio(LocalDate.now().minusDays(30))
            .dtAtualizacao(LocalDateTime.now())
            .idTipoAquario(1L)
            .status(true)
            .tipoAgua("salgada é top")
            .volume(200)
            .build();

    String json = objMapper.writeValueAsString(aquario);

    this.mvc
        .perform(post("/api/aquarios").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.path", containsString("/api/aquarios")))
        .andExpect(jsonPath("$..message", hasItem("Campo 'dtAtualizacao' deve ser nulo.")))
        .andExpect(jsonPath("$..message", hasItem("Campo 'dtCadastro' deve ser nulo.")));
  }

  @Test
  public void should_not_post_aquarium_invalid_tipoAquario() throws Exception {
    ObjectMapper objMapper = new ObjectMapper();
    objMapper.registerModule(new JavaTimeModule());

    AquarioDto aquario =
        AquarioDto.builder()
            .nome("Aquario de teste")
            .dtInicio(LocalDate.now().minusDays(30))
            .idTipoAquario(5L)
            .status(true)
            .tipoAgua("salgada")
            .volume(200)
            .build();

    String json = objMapper.writeValueAsString(aquario);

    this.mvc
        .perform(
            post("/api/aquarios")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.path", containsString("/api/aquarios")))
        .andExpect(content().string(containsString("NotFoundException")));
  }

  @Test
  @DirtiesContext
  public void should_modify_one_aquarium() throws Exception {
    ObjectMapper objMapper = new ObjectMapper();
    objMapper.registerModule(new JavaTimeModule());

    Long aquarioId = 1L;
    Optional<Aquario> aquarioDb = aquarioService.buscar(aquarioId);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedLocalDate = aquarioDb.get().getDtCadastro().format(formatter);

    AquarioDto aquario =
        AquarioDto.builder()
            .id(aquarioId)
            .nome("Aquario modificado")
            .dtInicio(LocalDate.now().minusDays(30))
            .idTipoAquario(2L)
            .status(false)
            .tipoAgua("doce")
            .volume(1000)
            .build();

    String json = objMapper.writeValueAsString(aquario);

    this.mvc
        .perform(put("/api/aquarios").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.path", containsString("/api/aquarios/" + aquarioId)))
        .andExpect(jsonPath("$.data.id", is(aquarioId.intValue())))
        .andExpect(jsonPath("$.data.nome", is("Aquario modificado")))
        .andExpect(jsonPath("$.data.dtCadastro", is(formattedLocalDate)))
        .andExpect(jsonPath("$.data.dtAtualizacao", containsString(LocalDate.now().toString())));
  }

  @Test
  @DirtiesContext
  public void should_not_modify_aquarium_missing_id() throws Exception {
    ObjectMapper objMapper = new ObjectMapper();
    objMapper.registerModule(new JavaTimeModule());

    AquarioDto aquario =
        AquarioDto.builder()
            .nome("Aquario modificado")
            .dtInicio(LocalDate.now().minusDays(30))
            .idTipoAquario(2L)
            .status(false)
            .tipoAgua("doce")
            .volume(1000)
            .build();

    String json = objMapper.writeValueAsString(aquario);

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
  public void should_not_modify_aquarium_invalid_id() throws Exception {
    ObjectMapper objMapper = new ObjectMapper();
    objMapper.registerModule(new JavaTimeModule());

    Long aquarioId = 99L;

    AquarioDto aquario =
        AquarioDto.builder()
            .id(aquarioId)
            .nome("Aquario modificado")
            .dtInicio(LocalDate.now().minusDays(30))
            .idTipoAquario(2L)
            .status(false)
            .tipoAgua("doce")
            .volume(1000)
            .build();

    String json = objMapper.writeValueAsString(aquario);

    this.mvc
        .perform(
            put("/api/aquarios")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.path", containsString("/api/aquarios")))
        .andExpect(content().string(containsString("NotFoundException")));
  }

  @Test
  @DirtiesContext
  public void should_delete_one_aquarium() throws Exception {
    Long aquarioId = 1L;

    Optional<Aquario> aquario = aquarioService.buscar(aquarioId);
    assertTrue(aquario.isPresent());

    this.mvc
        .perform(delete("/api/aquarios/" + aquarioId).contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.id", is(aquario.get().getId().intValue())));

    aquario = aquarioService.buscar(aquarioId);
    assertTrue(aquario.isEmpty());
  }
}
