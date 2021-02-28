package com.paulorpc.aquarium.api.controllers;

import com.paulorpc.aquarium.api.dtos.DeleteTotalDto;
import com.paulorpc.aquarium.api.dtos.ParametroDto;
import com.paulorpc.aquarium.api.entities.Parametro;
import com.paulorpc.aquarium.api.mappers.ParametroMapper;
import com.paulorpc.aquarium.api.response.Response;
import com.paulorpc.aquarium.api.response.ResponseError;
import com.paulorpc.aquarium.api.response.ResponseObj;
import com.paulorpc.aquarium.api.services.ParametroService;
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
@CrossOrigin
@RequestMapping("/api/parametros")
public class ParametroController {

  private final Logger log = LoggerFactory.getLogger(ParametroController.class);

  @Autowired ParametroService parametroService;

  @Autowired ParametroMapper mapper;

  @GetMapping(value = "/{id}")
  public ResponseEntity<Response> buscar(@PathVariable Long id) {
    log.info("Requisição para buscar parametro: {}", id);

    return parametroService
        .buscarRetornandoProcedimentosTeste(id)
        .map(
            p -> {
              Response response = new ResponseObj<>(Global.getUri(), mapper.toParametroDto(p));
              return ResponseEntity.ok(response);
            })
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<Response> buscarTodos() {
    log.info("Requisição para buscar todos parâmetros");

    List<Parametro> parametros =
        parametroService.buscarTodosRetornandoProcedimentosTeste().stream()
            .distinct()
            .collect(Collectors.toList());
    List<ParametroDto> parametrosDto =
        parametros.stream().map(p -> mapper.toParametroDto(p)).collect(Collectors.toList());

    Response response = new ResponseObj<>(Global.getUri(), parametrosDto);
    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "/aquarios/{idAquario}")
  public ResponseEntity<Response> buscarTodosPeloAquario(@PathVariable Long idAquario) {
    log.info("Requisição para buscar todos parâmetros de um determinado aquário: " + idAquario);

    List<Parametro> parametros = parametroService.buscarTodosDoAquario(idAquario);
    List<ParametroDto> parametrosDto =
        parametros.stream().map(p -> mapper.toParametroDto(p)).collect(Collectors.toList());

    Response response = new ResponseObj<>(Global.getUri(), parametrosDto);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<Response> cadastrar(
      @Validated(ParametroDto.Post.class) @RequestBody ParametroDto parametroDto,
      BindingResult result)
      throws Exception {
    log.info("Requisição para cadastrar um novo parâmetro");
    ResponseError responseError = new ResponseError(Global.getUri());

    if (result.hasErrors()) {
      responseError.addMessagesFromResultErrors(result, log);
      return ResponseEntity.badRequest().body(responseError);
    }

    Parametro parametroNovo = parametroService.persistir(mapper.toParametro(parametroDto));
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(parametroNovo.getId())
            .toUri();

    Response response = new ResponseObj<>(uri, mapper.toParametroDto(parametroNovo));
    return ResponseEntity.created(uri).body(response);
  }

  @PutMapping
  public ResponseEntity<Response> alterar(
      @Validated(ParametroDto.Put.class) @RequestBody ParametroDto parametroDto,
      BindingResult result)
      throws Exception {
    log.info("Requisição para alterar um parâmetro existente: {}", parametroDto.getId());
    ResponseError responseError = new ResponseError(Global.getUri());

    if (result.hasErrors()) {
      responseError.addMessagesFromResultErrors(result, log);
      return ResponseEntity.badRequest().body(responseError);
    }

    Parametro parametroUpd = parametroService.alterar(mapper.toParametro(parametroDto));
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(parametroDto.getId())
            .toUri();

    Response response = new ResponseObj<>(uri, mapper.toParametroDto(parametroUpd));
    return ResponseEntity.ok(response);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Response> deletar(@PathVariable Long id) throws Exception {
    log.info("Requisição para deletar parâmetro: {}", id);

    Parametro parametro = parametroService.deletar(id);
    Response response = new ResponseObj<>(Global.getUri(), mapper.toParametroDto(parametro));

    return ResponseEntity.ok(response);
  }

  @DeleteMapping(value = "/aquarios/{idAquario}")
  public ResponseEntity<Response> deletarTodosDoAquario(@PathVariable Long idAquario)
      throws Exception {
    log.info("Requisição para deletar todos parâmetros de uma aquário: {}", idAquario);

    Integer qtde = parametroService.deletarTodosDoAquario(idAquario);
    Response response = new ResponseObj<>(Global.getUri(), new DeleteTotalDto(qtde));

    return ResponseEntity.ok(response);
  }
}
