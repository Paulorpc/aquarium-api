package com.paulorpc.aquarium.api.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import com.paulorpc.aquarium.api.dtos.EquipamentoDto;
import com.paulorpc.aquarium.api.entities.Equipamento;
import com.paulorpc.aquarium.api.enums.AvaliacaoEnum;
import com.paulorpc.aquarium.api.services.EquipamentoService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
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
public class EquipamentoControllerTestIT {

  @Autowired private MockMvc mvc;

  @Autowired private EquipamentoService equipamentoService;

  @Test
  public void should_get_one_equipamento_retrieving_aquarios() throws Exception {
    Equipamento equipamento = equipamentoService.buscarRetornandoAquarios(1L).get();
    this.mvc
        .perform(get("/api/equipamentos/1").contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.id", is(equipamento.getId().intValue())))
        .andExpect(jsonPath("$.data.nome", is(equipamento.getNome())))
        .andExpect(jsonPath("$.data.qtde", is(equipamento.getQtde())))
        .andExpect(jsonPath("$.data.tipo", is(equipamento.getTipo())))
        .andExpect(jsonPath("$.data.fabricante", is(equipamento.getFabricante())))
        .andExpect(jsonPath("$.data.modelo", is(equipamento.getModelo())))
        .andExpect(jsonPath("$.data.potencia", is(equipamento.getPotencia())))
        .andExpect(jsonPath("$.data.avaliacao", is(equipamento.getAvaliacao().name())))
        .andExpect(jsonPath("$.data.dtAquisicao", is(equipamento.getDtAquisicao().toString())))
        .andExpect(jsonPath("$.data.aquarios").isNotEmpty());
  }

  @Test
  public void should_get_all_equipamentos() throws Exception {
    this.mvc
        .perform(get("/api/equipamentos").contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data", hasSize(2)));
  }

  @Test
  public void should_get_all_equipamentos_from_aquarium() throws Exception {
    this.mvc
        .perform(get("/api/equipamentos/aquarios/2").contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data", hasSize(2)));
  }

  @Test
  @DirtiesContext
  public void should_post_one_equipamento() throws Exception {
    LocalDate today = LocalDateTime.now().toLocalDate();
    ObjectMapper objMapper = new ObjectMapper();
    objMapper.registerModule(new JavaTimeModule());

    EquipamentoDto equipamento =
        EquipamentoDto.builder()
            .avaliacao(AvaliacaoEnum.CINCO)
            .dtAquisicao(LocalDate.now().minusDays(30))
            .dtSubstituicao(LocalDate.now().plusDays(300))
            .fabricante("ATI")
            .nome("Lâmpadas T5")
            .modelo("Blue Plus")
            .observacao("Lâmpadadas")
            .potencia("200")
            .qtde(2)
            .tipo("Ilumincação")
            .build();

    String json = objMapper.writeValueAsString(equipamento);

    this.mvc
        .perform(
            post("/api/equipamentos").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.path", containsString("/api/equipamentos/")))
        .andExpect(jsonPath("$.data.id", greaterThan(2)))
        .andExpect(jsonPath("$.data.nome", is(equipamento.getNome())))
        .andExpect(jsonPath("$.data.fabricante", is(equipamento.getFabricante())))
        .andExpect(jsonPath("$.data.modelo", is(equipamento.getModelo())))
        .andExpect(jsonPath("$.data.observacao", is(equipamento.getObservacao())))
        .andExpect(jsonPath("$.data.potencia", is(equipamento.getPotencia())))
        .andExpect(jsonPath("$.data.tipo", is(equipamento.getTipo())))
        .andExpect(jsonPath("$.data.dtAtualizacao", containsString(today.toString())))
        .andExpect(jsonPath("$.data.dtCadastro", containsString(today.toString())))
        .andExpect(
            jsonPath("$.data.dtSubstituicao", is(equipamento.getDtSubstituicao().toString())));

    System.out.println("");
  }

  @Test
  @DirtiesContext
  public void should_not_post_equipamento_invalid_parameters() throws Exception {
    ObjectMapper objMapper = new ObjectMapper();
    objMapper.registerModule(new JavaTimeModule());

    EquipamentoDto equipamento =
        EquipamentoDto.builder()
            .dtAquisicao(LocalDate.now().plusDays(30))
            .dtSubstituicao(LocalDate.now())
            .potencia("0")
            .qtde(0)
            .vlrUnitario(new BigDecimal("0"))
            .build();

    String json = objMapper.writeValueAsString(equipamento);

    this.mvc
        .perform(
            post("/api/equipamentos").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.path", containsString("/api/equipamentos")))
        .andExpect(jsonPath("$..message", hasItem("Campo 'potencia' deve ser maior que zero.")))
        .andExpect(
            jsonPath(
                "$..message", hasItem("Campo 'dtSubstituicao' deve ser posterior a data atual.")))
        .andExpect(jsonPath("$..message", hasItem("Campo 'vlrUnitario' deve ser maior que zero.")))
        .andExpect(jsonPath("$..message", hasItem("Campo 'nome' é obrigatório.")))
        .andExpect(jsonPath("$..message", hasItem("Campo 'qtde' deve ser maior que zero.")))
        .andExpect(
            jsonPath(
                "$..message", hasItem("Campo 'dtAquisicao' não pode ser posterior a data atual.")));
  }

  @Test
  @DirtiesContext
  public void should_modify_one_equipamento() throws Exception {
    ObjectMapper objMapper = new ObjectMapper();
    objMapper.registerModule(new JavaTimeModule());

    Long equipamentoId = 1L;
    Optional<Equipamento> equipamentoDb = equipamentoService.buscar(equipamentoId);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedLocalDate = equipamentoDb.get().getDtCadastro().format(formatter);

    EquipamentoDto equipamento =
        EquipamentoDto.builder()
            .id(1L)
            .avaliacao(AvaliacaoEnum.CINCO)
            .dtAquisicao(LocalDate.now().minusDays(30))
            .dtSubstituicao(LocalDate.now().plusDays(300))
            .fabricante("ATI")
            .nome("Lâmpadas T5")
            .modelo("Blue Plus")
            .observacao("Lâmpadadas")
            .potencia("200")
            .qtde(2)
            .tipo("Ilumincação")
            .build();

    String json = objMapper.writeValueAsString(equipamento);

    this.mvc
        .perform(
            put("/api/equipamentos").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.path", containsString("/api/equipamentos/" + equipamentoId)))
        .andExpect(jsonPath("$.data.id", is(equipamentoId.intValue())))
        .andExpect(jsonPath("$.data.nome", is(equipamento.getNome())))
        .andExpect(jsonPath("$.data.dtCadastro", is(formattedLocalDate)))
        .andExpect(jsonPath("$.data.dtAtualizacao", containsString(LocalDate.now().toString())))
        .andExpect(
            jsonPath("$.data.dtSubstituicao", is(equipamento.getDtSubstituicao().toString())));
    ;
  }

  @Test
  @DirtiesContext
  public void should_not_modify_equipamento_missing_id() throws Exception {
    ObjectMapper objMapper = new ObjectMapper();
    objMapper.registerModule(new JavaTimeModule());

    EquipamentoDto equipamento =
        EquipamentoDto.builder()
            .avaliacao(AvaliacaoEnum.CINCO)
            .dtAquisicao(LocalDate.now().minusDays(30))
            .dtSubstituicao(LocalDate.now().plusDays(300))
            .build();

    String json = objMapper.writeValueAsString(equipamento);

    this.mvc
        .perform(
            put("/api/equipamentos").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.path", containsString("/api/equipamentos")))
        .andExpect(jsonPath("$..message", hasItem("Campo 'id' obrigatório para alteração.")));
  }

  @Test
  @DirtiesContext
  public void should_not_modify_equipamento_invalid_id() throws Exception {
    ObjectMapper objMapper = new ObjectMapper();
    objMapper.registerModule(new JavaTimeModule());

    Long equipamentoId = 99L;

    EquipamentoDto equipamento =
        EquipamentoDto.builder()
            .id(equipamentoId)
            .nome("nome")
            .qtde(1)
            .dtAquisicao(LocalDate.now().minusDays(30))
            .build();

    String json = objMapper.writeValueAsString(equipamento);

    this.mvc
        .perform(
            put("/api/equipamentos")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.path", containsString("/api/equipamentos")))
        .andExpect(content().string(containsString("NotFoundException")));
  }

  @Test
  @DirtiesContext
  public void should_delete_one_eaquipamento() throws Exception {
    Long equipamentoId = 1L;

    Optional<Equipamento> equipamento = equipamentoService.buscar(equipamentoId);
    assertTrue(equipamento.isPresent());

    this.mvc
        .perform(
            delete("/api/equipamentos/" + equipamentoId)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.id", is(equipamento.get().getId().intValue())));

    equipamento = equipamentoService.buscar(equipamentoId);
    assertFalse(equipamento.isPresent());
  }
}
