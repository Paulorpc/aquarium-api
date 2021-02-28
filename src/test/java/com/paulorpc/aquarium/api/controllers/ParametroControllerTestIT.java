package com.paulorpc.aquarium.api.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
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
import com.paulorpc.aquarium.api.dtos.ParametroDto;
import com.paulorpc.aquarium.api.entities.Parametro;
import com.paulorpc.aquarium.api.entities.ProcedimentoTeste;
import com.paulorpc.aquarium.api.factories.ParametroFactory;
import com.paulorpc.aquarium.api.services.ParametroService;
import com.paulorpc.aquarium.api.services.ProcedimentoTesteService;
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
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ParametroControllerTestIT {

  @Autowired private MockMvc mvc;

  @Autowired private ParametroService parametroService;

  @Autowired private ProcedimentoTesteService procedimentosService;

  @Test
  public void should_get_one_parametro() throws Exception {
    LocalDate today = LocalDate.now();
    this.mvc
        .perform(get("/api/parametros/1").contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.id", is(1)))
        .andExpect(jsonPath("$.data.nome", is("Cálcio")))
        .andExpect(jsonPath("$.data.abreviacaoNome", is("ca")))
        .andExpect(jsonPath("$.data.escalaInicio", is(300.00)))
        .andExpect(jsonPath("$.data.escalaFim", is(500.00)))
        .andExpect(jsonPath("$.data.vlrIdealInicio", is(400.00)))
        .andExpect(jsonPath("$.data.vlrIdealFim", is(420.00)))
        .andExpect(jsonPath("$.data.aquarioId", is(1)))
        .andExpect(jsonPath("$.data.unidadeMedida", is("ppm")))
        .andExpect(jsonPath("$.data.procedimentosTeste").isArray())
        .andExpect(jsonPath("$.data.dtCadastro", containsString(today.toString())))
        .andExpect(jsonPath("$.data.dtAtualizacao", containsString(today.toString())));
  }

  @Test
  public void should_get_all_parametros() throws Exception {
    this.mvc
        .perform(get("/api/parametros").contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data").isArray())
        .andExpect(jsonPath("$.data", Matchers.hasSize(8)));
  }

  @Test
  @DirtiesContext
  public void should_post_one_parametro() throws Exception {
    LocalDate today = LocalDate.now();
    ObjectMapper objMapper = new ObjectMapper();
    objMapper.registerModule(new JavaTimeModule());

    ParametroDto parametro = ParametroFactory.INSTANCE.seedDto();
    parametro.setProcedimentosTeste(null);
    parametro.setAquarioId(1L);
    String json = objMapper.writeValueAsString(parametro);

    this.mvc
        .perform(
            post("/api/parametros").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.path", containsString("/api/parametros/")))
        .andExpect(jsonPath("$.data.id", greaterThan(3)))
        .andExpect(jsonPath("$.data.nome", is(parametro.getNome())))
        .andExpect(jsonPath("$.data.abreviacaoNome", is(parametro.getAbreviacaoNome())))
        .andExpect(jsonPath("$.data.escalaInicio", is(parametro.getEscalaInicio())))
        .andExpect(jsonPath("$.data.escalaFim", is(parametro.getEscalaFim())))
        .andExpect(jsonPath("$.data.vlrIdealInicio", is(parametro.getVlrIdealInicio())))
        .andExpect(jsonPath("$.data.vlrIdealFim", is(parametro.getVlrIdealFim())))
        .andExpect(jsonPath("$.data.aquarioId", is(parametro.getAquarioId().intValue())))
        .andExpect(jsonPath("$.data.unidadeMedida", is(parametro.getUnidadeMedida())))
        .andExpect(jsonPath("$.data.dtCadastro", containsString(today.toString())))
        .andExpect(jsonPath("$.data.dtAtualizacao", containsString(today.toString())));
  }

  @Test
  @DirtiesContext
  public void should_not_post_parametro_aquario_notfound() throws Exception {
    ObjectMapper objMapper = new ObjectMapper();
    objMapper.registerModule(new JavaTimeModule());

    ParametroDto parametro = ParametroFactory.INSTANCE.seedDto();
    parametro.setProcedimentosTeste(null);
    parametro.setAquarioId(99L);
    String json = objMapper.writeValueAsString(parametro);

    this.mvc
        .perform(
            post("/api/parametros").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.path", containsString("/api/parametros")))
        .andExpect(content().string(containsStringIgnoringCase("NotFoundException")));
  }

  @Test
  public void should_not_post_parametro_invalid_parameters() throws Exception {
    LocalDateTime today = LocalDateTime.now();
    ObjectMapper objMapper = new ObjectMapper();
    objMapper.registerModule(new JavaTimeModule());

    ParametroDto parametro = ParametroFactory.INSTANCE.seedDto();
    parametro.setDtAtualizacao(today);
    parametro.setDtCadastro(today);
    String json = objMapper.writeValueAsString(parametro);

    this.mvc
        .perform(
            post("/api/parametros").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.path", containsString("/api/parametros")))
        .andExpect(jsonPath("$..message", hasItem("Campo 'aquarioId' obrigatório.")))
        .andExpect(jsonPath("$..message", hasItem("Campo 'procedimentosTeste' deve ser nulo.")))
        .andExpect(jsonPath("$..message", hasItem("Campo 'dtAtualizacao' deve ser nulo.")))
        .andExpect(jsonPath("$..message", hasItem("Campo 'dtCadastro' deve ser nulo.")));
  }

  @Test
  @DirtiesContext
  public void should_modify_one_parametro() throws Exception {
    ObjectMapper objMapper = new ObjectMapper();
    objMapper.registerModule(new JavaTimeModule());

    Long idParametro = 1L;
    Optional<Parametro> parametroDb = parametroService.buscar(idParametro);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedLocalDate = parametroDb.get().getDtCadastro().format(formatter);

    ParametroDto parametro = ParametroFactory.INSTANCE.seedDto();
    parametro.setProcedimentosTeste(null);
    parametro.setId(idParametro);
    parametro.setAquarioId(2L);
    String json = objMapper.writeValueAsString(parametro);

    this.mvc
        .perform(put("/api/parametros").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.path", containsString("/api/parametros/" + idParametro)))
        .andExpect(jsonPath("$.data.id", is(idParametro.intValue())))
        .andExpect(jsonPath("$.data.nome", is(parametro.getNome())))
        .andExpect(jsonPath("$.data.aquarioId", is(2)))
        .andExpect(jsonPath("$.data.dtCadastro", is(formattedLocalDate)))
        .andExpect(jsonPath("$.data.dtAtualizacao", containsString(LocalDate.now().toString())));
  }

  @Test
  @DirtiesContext
  public void should_not_modify_parametro_invalid_parameters() throws Exception {
    LocalDateTime today = LocalDateTime.now();
    ObjectMapper objMapper = new ObjectMapper();
    objMapper.registerModule(new JavaTimeModule());

    ParametroDto parametro = ParametroFactory.INSTANCE.seedDto();
    parametro.setDtCadastro(today);
    parametro.setDtAtualizacao(today);
    String json = objMapper.writeValueAsString(parametro);

    this.mvc
        .perform(
            put("/api/parametros")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.path", containsString("/api/parametros")))
        .andExpect(content().string(containsString("'id' obrigatório")))
        .andExpect(content().string(containsString("'dtCadastro' deve ser nulo")))
        .andExpect(content().string(containsString("'aquarioId' obrigatório")))
        .andExpect(content().string(containsString("'procedimentosTeste' deve ser nulo")))
        .andExpect(content().string(containsString("'dtAtualizacao' deve ser nulo")));
  }

  @Test
  @DirtiesContext
  public void should_not_modify_parametro_invalid_id() throws Exception {
    ObjectMapper objMapper = new ObjectMapper();
    objMapper.registerModule(new JavaTimeModule());

    ParametroDto parametro = ParametroFactory.INSTANCE.seedDto();
    parametro.setId(99L);
    parametro.setAquarioId(1L);
    parametro.setProcedimentosTeste(null);
    String json = objMapper.writeValueAsString(parametro);

    this.mvc
        .perform(put("/api/parametros").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.path", containsString("/api/parametros")))
        .andExpect(content().string(containsString("NotFoundException")));
  }

  @Test
  @DirtiesContext
  public void should_delete_one_parametro() throws Exception {
    Long parametroId = 1L;

    Optional<Parametro> parametro = parametroService.buscar(parametroId);
    assertTrue(parametro.isPresent());

    this.mvc
        .perform(
            delete("/api/parametros/" + parametroId).contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.id", is(parametro.get().getId().intValue())))
        .andExpect(jsonPath("$.data.procedimentosTeste").isArray());

    parametro = parametroService.buscar(parametroId);
    assertTrue(parametro.isEmpty());
  }

  @Test
  @DirtiesContext
  public void should_delete_all_parametros_from_aquario() throws Exception {
    Long idAquario = 1L;
    Long idParametro = 2L;

    List<Parametro> parametros =
        parametroService.buscarTodosDoAquarioRetornandoProcedimentosTeste(idAquario);

    parametros = parametros.stream().distinct().collect(Collectors.toList());
    assertThat(parametros).hasSize(5);
    parametros =
        parametros.stream().filter(p -> p.getId().equals(idParametro)).collect(Collectors.toList());
    assertThat(parametros.get(0).getProcedimentosTeste()).hasSize(3);

    this.mvc
        .perform(
            delete("/api/parametros/aquarios/" + idAquario)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.total", is(5)));

    parametros = parametroService.buscarTodosDoAquarioRetornandoProcedimentosTeste(idAquario);
    parametros = parametros.stream().distinct().collect(Collectors.toList());
    assertThat(parametros).isEmpty();

    List<ProcedimentoTeste> procedimentos =
        procedimentosService.buscarTodos(Optional.of(idParametro));
    assertThat(procedimentos).isEmpty();
  }
}
