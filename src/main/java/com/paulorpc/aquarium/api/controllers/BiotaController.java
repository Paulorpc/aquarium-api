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
import com.paulorpc.aquarium.api.dtos.BiotaDto;
import com.paulorpc.aquarium.api.entities.Biota;
import com.paulorpc.aquarium.api.response.Response;
import com.paulorpc.aquarium.api.services.BiotaService;

@RestController
@RequestMapping(value = "/api/biota")
@CrossOrigin("*")
public class BiotaController {

  private final Logger log = LoggerFactory.getLogger(BiotaController.class);

  @Autowired
  private BiotaService biotaService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<Response<BiotaDto>> buscar(@PathVariable int id) {
    log.info("Requisição para buscar aquário - buscarBiota(). Id: " + id);
    Response<BiotaDto> response = new Response<>();

    return biotaService.buscarBiota(id).map(biota -> {
      response.setData(converteObjetoParaDto(biota));
      return ResponseEntity.ok(response);
    }).orElse(ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<Response<List<BiotaDto>>> buscarTodos() {
    log.info("Requisição para buscar todos aquários - buscarTodos()");
    Response<List<BiotaDto>> response = new Response<>();

    List<Biota> biotas = biotaService.buscarTodos();
    List<BiotaDto> biotasDto = biotas.stream().map(v -> {
      return converteObjetoParaDto(v);
    }).collect(Collectors.toList());

    response.setData(biotasDto);
    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "/ativos")
  public ResponseEntity<Response<List<BiotaDto>>> buscarTodosAtivos() {
    log.info("Requisição para buscar todos aquários ativos - buscarTodosAtivos()");
    Response<List<BiotaDto>> response = new Response<>();

    List<Biota> biotas = biotaService.buscarTodosAtivos();
    List<BiotaDto> biotasDto = biotas.stream().map(v -> {
      return converteObjetoParaDto(v);
    }).collect(Collectors.toList());

    response.setData(biotasDto);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<Response<BiotaDto>> cadastrar(
      @Validated(BiotaDto.Cadastrar.class) @RequestBody BiotaDto BiotaDto, BindingResult result) {
    log.info("Requisição para cadastrar um novo aquário - cadastrarBiota()");
    Response<BiotaDto> response = new Response<>();

    if (result.hasErrors()) {
      response.setIssuesFromResultErrors(result, log);
      return ResponseEntity.badRequest().body(response);
    }

    Biota novoBiota = biotaService.cadastrarBiota(converteDtoParaObjeto(BiotaDto));
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(novoBiota.getId()).toUri();
    response.setData(converteObjetoParaDto(novoBiota));
    return ResponseEntity.created(uri).body(response);
  }

  @PutMapping
  public ResponseEntity<Response<BiotaDto>> alterar(
      @Validated(BiotaDto.Alterar.class) @RequestBody BiotaDto BiotaDto, BindingResult result) {
    log.info("Requisição para alterar um aquaário existente - alterarBiota()");
    Response<BiotaDto> response = new Response<>();
    if (result.hasErrors()) {
      response.setIssuesFromResultErrors(result, log);
      return ResponseEntity.badRequest().body(response);
    }

    Optional<Biota> aquarioOpt = biotaService.alterarBiota(BiotaDto);

    if (!aquarioOpt.isPresent()) {
      response.getIssues().add("Não foi possível localizar o aquário, id: " + BiotaDto.getId());
      return ResponseEntity.notFound().build();
    }

    response.setData(converteObjetoParaDto(aquarioOpt.get()));
    return ResponseEntity.ok(response);
  }

  /***
   * Método de deleção de aquários. Obs: Feito método utilizando estilo funcional para aprendizado.
   * 
   * @param id
   * @return
   */
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Response<BiotaDto>> deletar(@PathVariable int id) {
    log.info("Requisição para deletar um aquário - deletarBiota()");
    Response<BiotaDto> response = new Response<>();

    return biotaService.deletarBiota(id).map(v -> {
      response.setData(converteObjetoParaDto(v));
      return ResponseEntity.ok(response);
    }).orElse(ResponseEntity.notFound().build());

  }

  /************* CONVERSORES OBJETO/DTO *************/

  /***
   * Converte objeto Biota para BiotaDto
   * 
   * @param dto
   * @return Biota
   */
  public static Biota converteDtoParaObjeto(BiotaDto dto) {
    return converteDtoParaObjeto(dto, new Biota());
  }

  /***
   * Converte objeto Biota para BiotaDto
   * 
   * @param dto
   * @return Biota
   */
  public static Biota converteDtoParaObjeto(BiotaDto dto, Biota obj) {
    dto.getId().ifPresent(v -> obj.setId(v));
    dto.getNomePopular().ifPresent(v -> obj.setNomePopular(v));
    dto.getNomeCientifico().ifPresent(v -> obj.setNomeCientifico(v));
    dto.getTipoAgua().ifPresent(v -> obj.setTipoAgua(v));
    dto.getNivelCuidado().ifPresent(v -> obj.setNivelCuidado(v));
    dto.getBloqueadoAlteracao().ifPresent(v -> obj.setBloqueadoAlteracao(v));   
    return obj;
  }

  /***
   * Converte objeto Biota para BiotaDto
   * 
   * @param obj
   * @return BiotaDto
   */
  private static BiotaDto converteObjetoParaDto(Biota obj) {
    BiotaDto dto = new BiotaDto();
    dto.setId(Optional.ofNullable(obj.getId()));
    dto.setNomePopular(Optional.ofNullable(obj.getNomePopular()));
    dto.setNomeCientifico(Optional.ofNullable(obj.getNomeCientifico()));
    dto.setTipoAgua(Optional.ofNullable(obj.getTipoAgua()));
    dto.setNivelCuidado(Optional.ofNullable(obj.getNivelCuidado()));
    dto.setBloqueadoAlteracao(Optional.ofNullable(obj.isBloqueadoAlteracao()));
    dto.setDtCadastro(obj.getDtCadastro());
    dto.setDtAtualizacao(obj.getDtAtualizacao());
    return dto;
  }


}
