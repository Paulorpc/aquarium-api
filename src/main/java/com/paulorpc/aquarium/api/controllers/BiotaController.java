package com.paulorpc.aquarium.api.controllers;

import com.paulorpc.aquarium.api.dtos.BiotaDto;
import com.paulorpc.aquarium.api.dtos.TaxonomiaDto;
import com.paulorpc.aquarium.api.entities.Biota;
import com.paulorpc.aquarium.api.exceptions.NotFoundException;
import com.paulorpc.aquarium.api.response.Response;
import com.paulorpc.aquarium.api.response.ResponseError;
import com.paulorpc.aquarium.api.response.ResponseObj;
import com.paulorpc.aquarium.api.services.BiotaService;
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
@RequestMapping(value = "/api/biota")
@CrossOrigin("*")
public class BiotaController {

  private final Logger log = LoggerFactory.getLogger(BiotaController.class);

  @Autowired private BiotaService biotaService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<Response> buscar(@PathVariable Long id) throws Exception {
    log.info("Requisição para buscar ser vivo. Id: " + id);

    return biotaService
        .buscar(id)
        .map(
            biota -> {
              Response response = new ResponseObj<>(Global.getUri(), converteObjetoParaDto(biota));
              return ResponseEntity.ok(response);
            })
        .orElseThrow(() -> new NotFoundException("Não foi possível localizar biota. Id: " + id));
  }

  @GetMapping
  public ResponseEntity<Response> buscarTodos() {
    log.info("Requisição para buscar todos seres vivos");

    List<Biota> biotas = biotaService.buscarTodos();
    List<BiotaDto> biotasDto =
        biotas.stream()
            .map(
                v -> {
                  return converteObjetoParaDto(v);
                })
            .collect(Collectors.toList());

    ResponseObj<List<BiotaDto>> response = new ResponseObj<>(Global.getUri(), biotasDto);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<Response> cadastrar(
      @Validated(BiotaDto.Cadastrar.class) @RequestBody BiotaDto biotaDto, BindingResult result)
      throws Exception {
    log.info("Requisição para cadastrar ser vivo");
    ResponseError responseError = new ResponseError(Global.getUri());

    if (result.hasErrors()) {
      responseError.addMessagesFromResultErrors(result, log);
      return ResponseEntity.badRequest().body(responseError);
    }

    Biota novoBiota = biotaService.persistir(converteDtoParaObjeto(biotaDto));
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(novoBiota.getId())
            .toUri();

    ResponseObj<BiotaDto> response = new ResponseObj<>(uri, converteObjetoParaDto(novoBiota));
    return ResponseEntity.created(uri).body(response);
  }

  @PutMapping
  public ResponseEntity<Response> alterar(
      @Validated(BiotaDto.Alterar.class) @RequestBody BiotaDto biotaDto, BindingResult result)
      throws Exception {
    log.info("Requisição para alterar ser vivo existente");
    ResponseError responseError = new ResponseError(Global.getUri());

    if (result.hasErrors()) {
      responseError.addMessagesFromResultErrors(result, log);
      return ResponseEntity.badRequest().body(responseError);
    }

    Biota biota =
        biotaDto
            .getId()
            .flatMap(id -> biotaService.buscar(id))
            .orElseThrow(
                () ->
                    new NotFoundException(
                        "Não foi possível localizar o ser vivo. Id: " + biotaDto.getId().get()));

    Optional<Biota> biotaUpd = biotaService.alterar(converteDtoParaObjeto(biotaDto, biota));

    ResponseObj<BiotaDto> response =
        new ResponseObj<>(Global.getUri(), converteObjetoParaDto(biotaUpd.get()));
    return ResponseEntity.ok(response);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Response> deletar(@PathVariable Long id) throws Exception {
    log.info("Requisição para deletar um ser vivo");

    return biotaService
        .deletar(id)
        .map(
            v -> {
              Response response = new ResponseObj<>(Global.getUri(), converteObjetoParaDto(v));
              return ResponseEntity.ok(response);
            })
        .orElseThrow(() -> new NotFoundException("Não foi possível localizar biota. Id: " + id));
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
    dto.getAvaliacao().ifPresent(v -> obj.setAvaliacao(v));

    dto.getTaxonomia()
        .ifPresent(
            taxonomia -> {
              taxonomia.getDominio().ifPresent(v -> obj.getTaxonomia().setDominio(v));
              taxonomia.getReino().ifPresent(v -> obj.getTaxonomia().setReino(v));
              taxonomia.getFilo().ifPresent(v -> obj.getTaxonomia().setFilo(v));
              taxonomia.getClasse().ifPresent(v -> obj.getTaxonomia().setClasse(v));
              taxonomia.getOrdem().ifPresent(v -> obj.getTaxonomia().setOrdem(v));
              taxonomia.getGenero().ifPresent(v -> obj.getTaxonomia().setGenero(v));
              taxonomia.getEspecie().ifPresent(v -> obj.getTaxonomia().setEspecie(v));
            });

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
    dto.setAvaliacao(Optional.ofNullable(obj.getAvaliacao()));
    dto.setDtCadastro(obj.getDtCadastro());
    dto.setDtAtualizacao(obj.getDtAtualizacao());
    dto.setUsuarioCadastro(obj.getUsuarioCadastro());
    dto.setUsuarioAtualizacao(obj.getUsuarioAtualizacao());

    TaxonomiaDto taxonomia = new TaxonomiaDto();
    taxonomia.setDominio(Optional.ofNullable(obj.getTaxonomia().getDominio()));
    taxonomia.setReino(Optional.ofNullable(obj.getTaxonomia().getReino()));
    taxonomia.setFilo(Optional.ofNullable(obj.getTaxonomia().getFilo()));
    taxonomia.setClasse(Optional.ofNullable(obj.getTaxonomia().getClasse()));
    taxonomia.setOrdem(Optional.ofNullable(obj.getTaxonomia().getOrdem()));
    taxonomia.setGenero(Optional.ofNullable(obj.getTaxonomia().getGenero()));
    taxonomia.setEspecie(Optional.ofNullable(obj.getTaxonomia().getEspecie()));
    dto.setTaxonomia(Optional.ofNullable(taxonomia));

    return dto;
  }
}
