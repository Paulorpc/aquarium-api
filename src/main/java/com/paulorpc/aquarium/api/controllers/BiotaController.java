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
    log.info("Requisição para buscar ser vivo. Id: " + id);
    Response<BiotaDto> response = new Response<>();

    return biotaService.buscar(id).map(biota -> {
      response.setData(converteObjetoParaDto(biota));
      return ResponseEntity.ok(response);
    }).orElse(ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<Response<List<BiotaDto>>> buscarTodos() {
    log.info("Requisição para buscar todos seres vivos");
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
    log.info("Requisição para buscar todos seres vivos ativos");
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
    log.info("Requisição para cadastrar ser vivo");
    Response<BiotaDto> response = new Response<>();

    if (result.hasErrors()) {
      response.setIssuesFromResultErrors(result, log);
      return ResponseEntity.badRequest().body(response);
    }

    Biota novoBiota = biotaService.cadastrar(converteDtoParaObjeto(BiotaDto));
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(novoBiota.getId()).toUri();
    response.setData(converteObjetoParaDto(novoBiota));
    return ResponseEntity.created(uri).body(response);
  }

  @PutMapping
  public ResponseEntity<Response<BiotaDto>> alterar(
      @Validated(BiotaDto.Alterar.class) @RequestBody BiotaDto BiotaDto, BindingResult result) {
    log.info("Requisição para alterar ser vivo existente");
    Response<BiotaDto> response = new Response<>();
    if (result.hasErrors()) {
      response.setIssuesFromResultErrors(result, log);
      return ResponseEntity.badRequest().body(response);
    }

    Optional<Biota> aquarioOpt = biotaService.alterar(BiotaDto);

    if (!aquarioOpt.isPresent()) {
      response.getIssues().add("Não foi possível localizar biota. Id: " + BiotaDto.getId());
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
    log.info("Requisição para deletar um ser vivo");
    Response<BiotaDto> response = new Response<>();

    return biotaService.deletar(id).map(v -> {
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
    dto.getReefSafe().ifPresent(v -> obj.setReefSafe(v));
    dto.getVolumeMinAquario().ifPresent(v -> obj.setVolumeMinAquario(v));
    dto.getAlimentacao().ifPresent(v -> obj.setAlimentacao(v));
    dto.getHabitat().ifPresent(v -> obj.setHabitat(v));
    dto.getRegiao().ifPresent(v -> obj.setRegiao(v));
    dto.getTamanho().ifPresent(v -> obj.setTamanho(v));
    dto.getRiscoExtincao().ifPresent(v -> obj.setRiscoExtincao(v));
    dto.getInfoAdicional().ifPresent(v -> obj.setInfoAdicional(v));
    dto.getTaxonomia().ifPresent(v -> obj.setTaxonomia(v));
    dto.getAvaliacao().ifPresent(v -> obj.setAvaliacao(v));
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
    dto.setReefSafe(Optional.ofNullable(obj.isReefSafe()));
    dto.setVolumeMinAquario(Optional.ofNullable(obj.getVolumeMinAquario()));
    dto.setAlimentacao(Optional.ofNullable(obj.getAlimentacao()));
    dto.setHabitat(Optional.ofNullable(obj.getHabitat()));
    dto.setRegiao(Optional.ofNullable(obj.getRegiao()));
    dto.setTamanho(Optional.ofNullable(obj.getTamanho()));
    dto.setRiscoExtincao(Optional.ofNullable(obj.getRiscoExtincao()));
    dto.setInfoAdicional(Optional.ofNullable(obj.getInfoAdicional()));
    dto.setTaxonomia(Optional.ofNullable(obj.getTaxonomia()));
    dto.setAvaliacao(Optional.ofNullable(obj.getAvaliacao()));
    dto.setDtCadastro(obj.getDtCadastro());
    dto.setDtAtualizacao(obj.getDtAtualizacao());
    dto.setUsuarioCadastro(obj.getUsuarioCadastro());
    dto.setUsuarioAtualizacao(obj.getUsuarioAtualizacao());
    return dto;
  }

}
