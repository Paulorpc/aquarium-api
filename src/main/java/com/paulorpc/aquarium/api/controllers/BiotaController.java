package com.paulorpc.aquarium.api.controllers;

import com.paulorpc.aquarium.api.dtos.BiotaDto;
import com.paulorpc.aquarium.api.entities.Biota;
import com.paulorpc.aquarium.api.exceptions.NotFoundException;
import com.paulorpc.aquarium.api.mappers.BiotaMapper;
import com.paulorpc.aquarium.api.response.Response;
import com.paulorpc.aquarium.api.response.ResponseError;
import com.paulorpc.aquarium.api.response.ResponseObj;
import com.paulorpc.aquarium.api.services.BiotaService;
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
@RequestMapping(value = "/api/biotas")
@CrossOrigin("*")
public class BiotaController {

  private final Logger log = LoggerFactory.getLogger(BiotaController.class);

  @Autowired private BiotaService biotaService;

  @Autowired private BiotaMapper mapper;

  @GetMapping(value = "/{id}")
  public ResponseEntity<Response> buscar(@PathVariable Long id) throws Exception {
    log.info("Requisição para buscar ser vivo: {}", id);

    return biotaService
        .buscar(id)
        .map(
            b -> {
              Response response = new ResponseObj<>(Global.getUri(), mapper.toBiotaDto(b));
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
                b -> {
                  return mapper.toBiotaDto(b);
                })
            .collect(Collectors.toList());

    ResponseObj<List<BiotaDto>> response = new ResponseObj<>(Global.getUri(), biotasDto);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<Response> cadastrar(
      @Validated(BiotaDto.Post.class) @RequestBody BiotaDto biotaDto, BindingResult result)
      throws Exception {
    log.info("Requisição para cadastrar ser vivo");
    ResponseError responseError = new ResponseError(Global.getUri());

    if (result.hasErrors()) {
      responseError.addMessagesFromResultErrors(result, log);
      return ResponseEntity.badRequest().body(responseError);
    }

    Biota novoBiota = biotaService.persistir(mapper.toBiota(biotaDto));
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(novoBiota.getId())
            .toUri();

    ResponseObj<BiotaDto> response = new ResponseObj<>(uri, mapper.toBiotaDto(novoBiota));
    return ResponseEntity.created(uri).body(response);
  }

  @PutMapping
  public ResponseEntity<Response> alterar(
      @Validated(BiotaDto.Put.class) @RequestBody BiotaDto biotaDto, BindingResult result)
      throws Exception {
    log.info("Requisição para alterar ser vivo existente");
    ResponseError responseError = new ResponseError(Global.getUri());

    if (result.hasErrors()) {
      responseError.addMessagesFromResultErrors(result, log);
      return ResponseEntity.badRequest().body(responseError);
    }

    Biota biotaUpd = biotaService.alterar(mapper.toBiota(biotaDto));
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(biotaUpd.getId())
            .toUri();

    ResponseObj<BiotaDto> response = new ResponseObj<>(uri, mapper.toBiotaDto(biotaUpd));
    return ResponseEntity.ok(response);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Response> deletar(@PathVariable Long id) throws Exception {
    log.info("Requisição para deletar um ser vivo");

    Biota biota = biotaService.deletar(id);
    Response response = new ResponseObj<>(Global.getUri(), mapper.toBiotaDto(biota));

    return ResponseEntity.ok(response);
  }
}
