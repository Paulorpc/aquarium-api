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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.paulorpc.aquarium.api.dtos.AquarioDto;
import com.paulorpc.aquarium.api.entities.Aquario;
import com.paulorpc.aquarium.api.entities.TipoAquario;
import com.paulorpc.aquarium.api.response.Response;
import com.paulorpc.aquarium.api.services.AquarioService;
import com.paulorpc.aquarium.api.services.TipoAquarioService;
import com.paulorpc.aquarium.api.util.Global;

@RestController
@RequestMapping("/api/aquario")
@CrossOrigin("*")
@SessionAttributes("aquario")
public class AquarioController {

  private final Logger log = LoggerFactory.getLogger(AquarioController.class);
  private Global gb = new Global();

  @Autowired
  private AquarioService aquarioService;
  @Autowired
  private TipoAquarioService tipoAquarioService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<Response<AquarioDto>> buscarAquario(@PathVariable int id) {
    log.info("Requisição para buscar aquário - buscarAquario(). Id: " + id);
    Response<AquarioDto> response = new Response<>();

    return aquarioService.buscar(id).map(aquario -> {
      response.setData(converteObjetoParaDto(aquario));
      return ResponseEntity.ok(response);
    }).orElse(ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<Response<List<AquarioDto>>> buscarTodos() {
    log.info("Requisição para buscar todos aquários - buscarTodos()");
    Response<List<AquarioDto>> response = new Response<>();

    List<Aquario> aquarios = aquarioService.buscarTodos();
    List<AquarioDto> aquariosDto = aquarios.stream().map(a -> {
      return converteObjetoParaDto(a);
    }).collect(Collectors.toList());

    response.setData(aquariosDto);
    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "/ativos")
  public ResponseEntity<Response<List<AquarioDto>>> buscarTodosAtivos() {
    log.info("Requisição para buscar todos aquários ativos - buscarTodosAtivos()");
    Response<List<AquarioDto>> response = new Response<>();

    List<Aquario> aquarios = aquarioService.buscarTodosAtivos();
    List<AquarioDto> aquariosDto = aquarios.stream().map(a -> {
      return converteObjetoParaDto(a);
    }).collect(Collectors.toList());

    response.setData(aquariosDto);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<Response<AquarioDto>> cadastrarAquario(
      @Validated(AquarioDto.Cadastrar.class) @RequestBody AquarioDto aquarioDto,
      BindingResult result) {
    log.info("Requisição para cadastrar um novo aquário - cadastrarAquario()");
    Response<AquarioDto> response = new Response<>();
    String issueMsg = new String();

    if (result.hasErrors()) {
      response.setIssuesFromResultErrors(result, log);
      return ResponseEntity.badRequest().body(response);
    }

    Aquario novoAquario = new Aquario();
    Optional<TipoAquario> tipoAquario =
        aquarioDto.getIdTipoAquario().flatMap(id -> tipoAquarioService.buscar(id));

    if (tipoAquario.isPresent()) {
      novoAquario.setTipoAquario(tipoAquario.get());
    } else if (aquarioDto.getIdTipoAquario().isPresent()) {
      issueMsg = "O campo 'idTipoAquario' informado não foi localizado. IdTipoAquario: "
          + gb.nullToEmpty(aquarioDto.getIdTipoAquario().get());
      response.addIssue(issueMsg, log);
    }

    issueMsg =
        "O campo 'status' foi alterado para true pelo sistema, pois o método POST sempre considera true o 'status' de um novo registro.";
    aquarioDto.getStatus().orElse(response.addIssue(issueMsg, log));

    novoAquario = aquarioService.cadastrar(converteDtoParaObjeto(aquarioDto, novoAquario));
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(novoAquario.getId()).toUri();
    response.setData(converteObjetoParaDto(novoAquario));
    return ResponseEntity.created(uri).body(response);
  }

  @PutMapping
  public ResponseEntity<Response<AquarioDto>> alterarAquario(
      @Validated(AquarioDto.Alterar.class) @RequestBody AquarioDto aquarioDto,
      BindingResult result) {
    log.info("Requisição para alterar um aquaário existente - alterarAquario()");
    Response<AquarioDto> response = new Response<>();
    if (result.hasErrors()) {
      response.setIssuesFromResultErrors(result, log);
      return ResponseEntity.badRequest().body(response);
    }

    Optional<Aquario> aquarioOpt = aquarioService.alterar(aquarioDto);

    if (!aquarioOpt.isPresent()) {
      response.getIssues().add("Não foi possível localizar o aquário, id: " + aquarioDto.getId());
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
  public ResponseEntity<Response<AquarioDto>> deletarAquario(@PathVariable int id) {
    log.info("Requisição para deletar um aquário - deletarAquario()");
    Response<AquarioDto> response = new Response<>();

    /*
     * Optional<Aquario> aquario = aquarioService.deletarAquario(id); if(!aquario.isPresent())
     * return ResponseEntity.notFound().build();
     *
     * //response.setData(ConverteAquarioToAquarioDto(aquario.get())); //return
     * ResponseEntity.ok(response);
     */

    return aquarioService.deletar(id).map(aquario -> {
      response.setData(converteObjetoParaDto(aquario));
      return ResponseEntity.ok(response);
    }).orElse(ResponseEntity.notFound().build());

  }

  /************* CONVERSORES OBJETO/DTO *************/

  /***
   * Converte objeto Aquario para AquarioDTO
   *
   * @param dto
   * @return Aquario
   */
  public static Aquario converteDtoParaObjeto(AquarioDto dto, Aquario obj) {
    dto.getId().ifPresent(v -> obj.setId(v));
    dto.getNome().ifPresent(v -> obj.setNome(v));
    dto.getDtInicio().ifPresent(v -> obj.setDtInicio(v));
    dto.getDtFinal().ifPresent(v -> obj.setDtFinal(v));
    dto.getTipoAgua().ifPresent(v -> obj.setTipoAgua(v));
    dto.getTamanho().ifPresent(v -> obj.setTamanho(v));
    dto.getVolume().ifPresent(v -> obj.setVolume(v));
    dto.getIluminacao().ifPresent(v -> obj.setIluminacao(v));
    dto.getFiltragem().ifPresent(v -> obj.setFiltragem(v));
    dto.getSistemaCO2().ifPresent(v -> obj.setSistemaCO2(v));
    dto.getDosagem().ifPresent(v -> obj.setDosagem(v));
    dto.getSubstrato().ifPresent(v -> obj.setSubstrato(v));
    dto.getObservacao().ifPresent(v -> obj.setObservacao(v));
    dto.getStatus().ifPresent(v -> obj.setStatus(v));
    return obj;
  }

  /***
   * Converte objeto Aquario para AquarioDTO
   *
   * @param obj
   * @return aquarioDto
   */
  private static AquarioDto converteObjetoParaDto(Aquario obj) {
    AquarioDto dto = new AquarioDto();
    dto.setId(Optional.ofNullable(obj.getId()));
    dto.setNome(Optional.ofNullable(obj.getNome()));
    dto.setDtInicio(Optional.ofNullable(obj.getDtInicio()));
    dto.setDtFinal(Optional.ofNullable(obj.getDtFinal()));
    dto.setTipoAgua(Optional.ofNullable(obj.getTipoAgua()));
    dto.setTamanho(Optional.ofNullable(obj.getTamanho()));
    dto.setVolume(Optional.ofNullable(obj.getVolume()));
    dto.setIluminacao(Optional.ofNullable(obj.getIluminacao()));
    dto.setFiltragem(Optional.ofNullable(obj.getFiltragem()));
    dto.setSistemaCO2(Optional.ofNullable(obj.getSistemaCO2()));
    dto.setDosagem(Optional.ofNullable(obj.getDosagem()));
    dto.setSubstrato(Optional.ofNullable(obj.getSubstrato()));
    dto.setObservacao(Optional.ofNullable(obj.getObservacao()));
    dto.setStatus(Optional.ofNullable(obj.getStatus()));
    dto.setDtCadastro(obj.getDtCadastro());
    dto.setDtAtualizacao(obj.getDtAtualizacao());
    Optional.ofNullable(obj.getTipoAquario())
        .ifPresent(v -> dto.setIdTipoAquario(Optional.of(v.getId())));
    return dto;
  }
}
