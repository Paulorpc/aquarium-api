package com.paulorpc.aquarium.api.controllers;

import com.paulorpc.aquarium.api.dtos.AquarioDto;
import com.paulorpc.aquarium.api.dtos.TipoAquarioDto;
import com.paulorpc.aquarium.api.entities.TipoAquario;
import com.paulorpc.aquarium.api.exceptions.NotFoundException;
import com.paulorpc.aquarium.api.response.Response;
import com.paulorpc.aquarium.api.response.ResponseError;
import com.paulorpc.aquarium.api.response.ResponseObj;
import com.paulorpc.aquarium.api.services.TipoAquarioService;
import com.paulorpc.aquarium.api.util.Global;
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

@RestController
@RequestMapping("/api/tipoAquario")
@CrossOrigin("*")
public class TipoAquarioController {

  private final Logger log = LoggerFactory.getLogger(TipoAquarioController.class);

  @Autowired private TipoAquarioService tipoAquarioService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<Response> buscar(@PathVariable Long id) throws Exception {
    log.info("Requisição para buscar tipo de aquário: {}", id);

    return tipoAquarioService
        .buscar(id)
        .map(
            v -> {
              Response response = new ResponseObj<>(Global.getUri(), converteObjetoParaDto(v));
              return ResponseEntity.ok(response);
            })
        .orElseThrow(
            () -> new NotFoundException("Não foi possível localizar o tipo de aquário. Id: " + id));
  }

  @GetMapping
  public ResponseEntity<Response> buscarTodos() {
    log.info("Requisição para buscar todos tipos de aquários");

    List<TipoAquario> tiposAquarios = tipoAquarioService.buscarTodos();
    List<TipoAquarioDto> tiposAquariosDto =
        tiposAquarios.stream()
            .map(
                v -> {
                  return converteObjetoParaDto(v);
                })
            .collect(Collectors.toList());

    Response response = new ResponseObj<>(Global.getUri(), tiposAquariosDto);
    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "/ativos")
  public ResponseEntity<Response> buscarTodosAtivos() {
    log.info("Requisição para buscar todos tipos de aquários ativos");

    List<TipoAquario> tiposAquarios = tipoAquarioService.buscarTodosAtivos();
    List<TipoAquarioDto> tiposAquariosDto =
        tiposAquarios.stream().map(v -> converteObjetoParaDto(v)).collect(Collectors.toList());

    Response response = new ResponseObj<>(Global.getUri(), tiposAquariosDto);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<Response> cadastrar(
      @Validated(TipoAquarioDto.Cadastrar.class) @RequestBody TipoAquarioDto tipoAquarioDto,
      BindingResult result) {
    log.info("Requisição para cadastrar um novo tipo de aquário");
    ResponseError responseError = new ResponseError(Global.getUri());

    tipoAquarioDto
        .getTipo()
        .flatMap(tipo -> tipoAquarioService.buscarPorTipo(tipo))
        .ifPresent(
            t -> {
              result.addError(
                  new ObjectError(
                      "id",
                      "Tipo de aquário já cadastrado. Não é possível cadastrar dois tipos de aquários com mesmo valor. Tipo: "
                          + t.getTipo()));
            });

    if (result.hasErrors()) {
      responseError.addMessagesFromResultErrors(result, log);
      return ResponseEntity.badRequest().body(responseError);
    }

    TipoAquario novoTipoAquario = new TipoAquario();
    novoTipoAquario = tipoAquarioService.persistir(converteDtoParaObjeto(tipoAquarioDto));
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(novoTipoAquario.getId())
            .toUri();

    Response response = new ResponseObj<>(uri, converteObjetoParaDto(novoTipoAquario));
    return ResponseEntity.created(uri).body(response);
  }

  @PutMapping
  public ResponseEntity<Response> alterar(
      @Validated(AquarioDto.Patch.class) @RequestBody TipoAquarioDto tipoAquarioDto,
      BindingResult result)
      throws Exception {
    log.info("Requisição para alterar um tipo de aquário existente: {}", tipoAquarioDto.getId());
    ResponseError responseError = new ResponseError(Global.getUri());

    if (result.hasErrors()) {
      responseError.addMessagesFromResultErrors(result, log);
      return ResponseEntity.badRequest().body(responseError);
    }

    TipoAquario tipoAquario =
        tipoAquarioDto
            .getId()
            .flatMap(id -> tipoAquarioService.buscar(id))
            .orElseThrow(
                () ->
                    new NotFoundException(
                        "Não foi possível localizar o tipo de quário. Id: "
                            + tipoAquarioDto.getId().get()));

    Optional<TipoAquario> tipoAquarioUpd =
        tipoAquarioService.alterar(converteDtoParaObjeto(tipoAquarioDto, tipoAquario));

    Response response =
        new ResponseObj<>(Global.getUri(), converteObjetoParaDto(tipoAquarioUpd.get()));
    return ResponseEntity.ok(response);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Response> deletar(@PathVariable Long id) throws Exception {
    log.info("Requisição para deletar um aquário: {}", id);

    return tipoAquarioService
        .deletar(id)
        .map(
            v -> {
              Response response = new ResponseObj<>(Global.getUri(), converteObjetoParaDto(v));
              return ResponseEntity.ok(response);
            })
        .orElseThrow(
            () -> new NotFoundException("Não foi possível localizar o tipo de aquário. Id: " + id));
  }

  /************* CONVERSORES OBJETO/DTO *************/

  /***
   * Converte objeto TipoAquario para TipoAquarioDTO
   *
   * @param dto
   * @return TipoAquario
   */
  public static TipoAquario converteDtoParaObjeto(TipoAquarioDto dto) {
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
  public static TipoAquarioDto converteObjetoParaDto(TipoAquario obj) {
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
