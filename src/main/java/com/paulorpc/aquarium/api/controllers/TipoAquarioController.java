package com.paulorpc.aquarium.api.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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

import com.paulorpc.aquarium.api.dtos.AquarioDto;
import com.paulorpc.aquarium.api.dtos.TipoAquarioDto;
import com.paulorpc.aquarium.api.entities.TipoAquario;
import com.paulorpc.aquarium.api.response.Response;
import com.paulorpc.aquarium.api.services.TipoAquarioService;

@RestController
@RequestMapping("/api/tipoAquario")
@CrossOrigin("*")
public class TipoAquarioController {

  private final Logger log = LoggerFactory.getLogger(TipoAquarioController.class);

  @Autowired
  private TipoAquarioService tipoAquarioService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<Response<TipoAquarioDto>> buscarTipoAquario(@PathVariable int id) {
    log.info("Requisição para buscar tipo de aquário - buscarTipoAquario(). Id: " + id);
    Response<TipoAquarioDto> response = new Response<>();

    return tipoAquarioService.buscarTipoAquario(id).map(t -> {
      response.setData(converteObjetoParaDto(t));
      return ResponseEntity.ok(response);
    }).orElse(ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<Response<List<TipoAquarioDto>>> buscarTodos() {
    log.info("Requisição para buscar todos tipos de aquários - buscarTodos()");
    Response<List<TipoAquarioDto>> response = new Response<>();

    List<TipoAquario> tiposAquarios = tipoAquarioService.buscarTodos();
    List<TipoAquarioDto> tiposAquariosDto = tiposAquarios.stream().map(v -> {
      return converteObjetoParaDto(v);
    }).collect(Collectors.toList());

    response.setData(tiposAquariosDto);
    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "/ativos")
  public ResponseEntity<Response<List<TipoAquarioDto>>> buscarTodosAtivos() {
    log.info("Requisição para buscar todos aquários ativos - buscarTodosAtivos()");
    Response<List<TipoAquarioDto>> response = new Response<>();

    List<TipoAquario> tiposAquarios = tipoAquarioService.buscarTodosAtivos();
    List<TipoAquarioDto> tiposAquariosDto =
        tiposAquarios.stream().map(v -> converteObjetoParaDto(v)).collect(Collectors.toList());

    response.setData(tiposAquariosDto);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<Response<TipoAquarioDto>> cadastrarTipoAquario(
      @Validated(TipoAquarioDto.Cadastrar.class) @RequestBody TipoAquarioDto tipoAquarioDto,
      BindingResult result) {
    log.info("Requisição para cadastrar um novo tipo de aquário - cadastrarTipoAquario()");
    Response<TipoAquarioDto> response = new Response<>();

    tipoAquarioDto.getTipo().flatMap(tipo -> tipoAquarioService.buscarTipoAquarioPorTipo(tipo))
        .ifPresent(t -> {
          result.addError(new ObjectError("id",
              "Tipo de aquário já cadastrado. Não é possível cadastrar dois tipos de aquários com mesmo valor. Tipo: "
                  + t.getTipo()));
        });

    if (result.hasErrors()) {
      response.setIssuesFromResultErrors(result, log);
      return ResponseEntity.badRequest().body(response);
    }

    TipoAquario novoTipoAquario = new TipoAquario();
    novoTipoAquario =
        tipoAquarioService.cadastrarTipoAquario(converteDtoParaObjeto(tipoAquarioDto));
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(novoTipoAquario.getId()).toUri();
    response.setData(converteObjetoParaDto(novoTipoAquario));
    return ResponseEntity.created(uri).body(response);
  }

  @PutMapping
  public ResponseEntity<Response<TipoAquarioDto>> alterarTipoAquario(
      @Validated(AquarioDto.Alterar.class) @RequestBody TipoAquarioDto tipoAquarioDto,
      BindingResult result) {
    log.info("Requisição para alterar um tipo de aquário existente - alterarTipoAquario()");
    Response<TipoAquarioDto> response = new Response<>();

    if (result.hasErrors()) {
      response.setIssuesFromResultErrors(result, log);
      return ResponseEntity.badRequest().body(response);
    }

    Optional<TipoAquario> tipoAquario =
        tipoAquarioService.buscarTipoAquario(tipoAquarioDto.getId().get());
    if (!tipoAquario.isPresent()) {
      response.addIssue(
          "Não foi possível localizar o tipo de aquário. Id: " + tipoAquarioDto.getId(), log);
      return ResponseEntity.notFound().build();
    }

    Optional<TipoAquario> tipoAquarioUpd = tipoAquarioService
        .alterarTipoAquario(converteDtoParaObjeto(tipoAquarioDto, tipoAquario.get()));
    tipoAquarioUpd.ifPresent(v -> response.setData(converteObjetoParaDto(v)));
    return ResponseEntity.ok(response);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Response<TipoAquarioDto>> deletarAquario(@PathVariable int id) {
    log.info("Requisição para deletar um aquário - deletarAquario()");
    Response<TipoAquarioDto> response = new Response<>();

    return tipoAquarioService.deletarTipoAquario(id).map(v -> {
      response.setData(converteObjetoParaDto(v));
      return ResponseEntity.ok(response);
    }).orElseGet(() -> {
      response.addIssue("Cadastro não localizado. Id: " + id, log);
      return ResponseEntity.notFound().build();
    });

  }

  /************* CONVERSORES OBJETO/DTO *************/

  /***
   * Converte objeto TipoAquario para TipoAquarioDTO
   * 
   * @param dto
   * @return TipoAquario
   */
  private static TipoAquario converteDtoParaObjeto(TipoAquarioDto dto) {
    return converteDtoParaObjeto(dto, new TipoAquario());
  }

  /***
   * Converte objeto TipoAquario para TipoAquarioDTO
   * 
   * @param dto
   * @return TipoAquario
   */
  public static TipoAquario converteDtoParaObjeto(TipoAquarioDto dto, TipoAquario obj) {
    dto.getId().ifPresent(v -> obj.setId(v));
    dto.getTipo().ifPresent(v -> obj.setTipo(v));
    dto.getTipoAgua().ifPresent(v -> obj.setTipoAgua(v));
    dto.getDescricao().ifPresent(v -> obj.setDescricao(v));
    return obj;
  }

  /***
   * Converte objeto TipoAquario para TipoAquarioDTO
   * 
   * @param obj
   * @return TipoAquarioDto
   */
  private static TipoAquarioDto converteObjetoParaDto(TipoAquario obj) {
    TipoAquarioDto dto = new TipoAquarioDto();
    dto.setId(Optional.ofNullable(obj.getId()));
    dto.setTipo(Optional.ofNullable(obj.getTipo()));
    dto.setTipoAgua(Optional.ofNullable(obj.getTipoAgua()));
    dto.setDescricao(Optional.ofNullable(obj.getDescricao()));
    dto.setDtCadastro(obj.getDtCadastro());
    dto.setDtAtualizacao(obj.getDtAtualizacao());
    return dto;
  }

}
