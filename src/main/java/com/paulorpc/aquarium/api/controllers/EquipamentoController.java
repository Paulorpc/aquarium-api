package com.paulorpc.aquarium.api.controllers;

import com.paulorpc.aquarium.api.dtos.EquipamentoDto;
import com.paulorpc.aquarium.api.entities.Equipamento;
import com.paulorpc.aquarium.api.mappers.EquipamentoMapper;
import com.paulorpc.aquarium.api.response.Response;
import com.paulorpc.aquarium.api.response.ResponseError;
import com.paulorpc.aquarium.api.response.ResponseObj;
import com.paulorpc.aquarium.api.services.EquipamentoService;
import com.paulorpc.aquarium.api.util.Global;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/equipamentos")
@CrossOrigin("*")
public class EquipamentoController {

  private final Logger log = LoggerFactory.getLogger(EquipamentoController.class);

  @Autowired private EquipamentoService equipamentoService;

  @Autowired private EquipamentoMapper mapper;

  @GetMapping(value = "/{id}")
  public ResponseEntity<Response> buscar(@PathVariable Long id) {
    log.info("Requisição para buscar equipamento: {}", id);

    return equipamentoService
        .buscarRetornandoAquarios(id)
        .map(
            e -> {
              Response response = new ResponseObj<>(Global.getUri(), mapper.toEquipamentoDto(e));
              return ResponseEntity.ok(response);
            })
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<Response> buscarTodos() {
    log.info("Requisição para buscar todos equipamentos");

    List<Equipamento> equipamentos = equipamentoService.buscarTodos();
    List<EquipamentoDto> equipamentosDto =
        equipamentos.stream()
            .map(e -> mapper.toEquipamentoDtoIgnoreAquarios(e))
            .collect(Collectors.toList());

    Response response = new ResponseObj<>(Global.getUri(), equipamentosDto);
    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "/aquarios/{idAquario}")
  public ResponseEntity<Response> buscarTodosPeloAquario(@PathVariable Long idAquario) {
    log.info("Requisição para buscar equipamento de um determinado aquário: " + idAquario);

    List<Equipamento> equipamentos = equipamentoService.buscarTodosDoAquario(idAquario);
    List<EquipamentoDto> equipamentosDto =
        equipamentos.stream().map(e -> mapper.toEquipamentoDto(e)).collect(Collectors.toList());

    Response response = new ResponseObj<>(Global.getUri(), equipamentosDto);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<Response> cadastrar(
      @Validated(EquipamentoDto.Post.class) @RequestBody EquipamentoDto equipamentoDto,
      BindingResult result)
      throws Exception {
    log.info("Requisição para cadastrar um novo equipamento");
    ResponseError responseError = new ResponseError(Global.getUri());

    if (result.hasErrors()) {
      responseError.addMessagesFromResultErrors(result, log);
      return ResponseEntity.badRequest().body(responseError);
    }

    Equipamento equipamentoNovo =
        equipamentoService.persistir(mapper.toEquipamento(equipamentoDto));
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(equipamentoNovo.getId())
            .toUri();

    Response response =
        new ResponseObj<>(uri, mapper.toEquipamentoDtoIgnoreAquarios(equipamentoNovo));
    return ResponseEntity.created(uri).body(response);
  }

  @PostMapping(value = "/aquarios/{aquarioId}")
  public ResponseEntity<Response> cadastrarNoAquario(
      @Validated(EquipamentoDto.Post.class) @RequestBody EquipamentoDto equipamentoDto,
      @PathVariable Long aquarioId,
      BindingResult result)
      throws Exception {
    log.info("Requisição para cadastrar um novo equipamento em um aquário: {}", aquarioId);
    ResponseError responseError = new ResponseError(Global.getUri());

    if (result.hasErrors()) {
      responseError.addMessagesFromResultErrors(result, log);
      return ResponseEntity.badRequest().body(responseError);
    }

    Equipamento equipamentoNovo =
        equipamentoService.persistir(mapper.toEquipamento(equipamentoDto), aquarioId);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(equipamentoNovo.getId())
            .toUri();

    Response response = new ResponseObj<>(uri, mapper.toEquipamentoDto(equipamentoNovo));
    return ResponseEntity.created(uri).body(response);
  }

  @PutMapping
  public ResponseEntity<Response> alterar(
      @Validated(EquipamentoDto.Put.class) @RequestBody EquipamentoDto equipamentoDto,
      BindingResult result)
      throws Exception {
    log.info("Requisição para alterar um equipamento existente: {}", equipamentoDto.getId());
    ResponseError responseError = new ResponseError(Global.getUri());

    if (result.hasErrors()) {
      responseError.addMessagesFromResultErrors(result, log);
      return ResponseEntity.badRequest().body(responseError);
    }

    Equipamento aquarioUpd = equipamentoService.alterar(mapper.toEquipamento(equipamentoDto));
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(aquarioUpd.getId())
            .toUri();

    Response response = new ResponseObj<>(uri, mapper.toEquipamentoDto(aquarioUpd));
    return ResponseEntity.ok(response);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Response> deletar(@PathVariable Long id) throws Exception {
    log.info("Requisição para deletar um equipamento: {}", id);

    Equipamento aquario = equipamentoService.deletar(id);
    Response response = new ResponseObj<>(Global.getUri(), mapper.toEquipamentoDto(aquario));

    return ResponseEntity.ok(response);
  }
}
