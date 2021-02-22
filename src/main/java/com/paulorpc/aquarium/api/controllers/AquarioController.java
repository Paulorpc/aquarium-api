package com.paulorpc.aquarium.api.controllers;

import com.paulorpc.aquarium.api.dtos.AquarioDto;
import com.paulorpc.aquarium.api.entities.Aquario;
import com.paulorpc.aquarium.api.mappers.AquarioMapper;
import com.paulorpc.aquarium.api.response.Response;
import com.paulorpc.aquarium.api.response.ResponseError;
import com.paulorpc.aquarium.api.response.ResponseObj;
import com.paulorpc.aquarium.api.services.AquarioService;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/aquarios")
@CrossOrigin("*")
@SessionAttributes("aquario")
public class AquarioController {

  private final Logger log = LoggerFactory.getLogger(AquarioController.class);

  @Autowired private AquarioService aquarioService;

  @Autowired private AquarioMapper mapper;

  @GetMapping(value = "/{id}")
  public ResponseEntity<Response> buscar(@PathVariable Long id) {
    log.info("Requisição para buscar aquário: {}", id);

    return aquarioService
        .buscar(id)
        .map(
            a -> {
              Response response = new ResponseObj<>(Global.getUri(), mapper.toAquarioDto(a));
              return ResponseEntity.ok(response);
            })
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<Response> buscarTodos() {
    log.info("Requisição para buscar todos aquários");

    List<Aquario> aquarios = aquarioService.buscarTodos();
    List<AquarioDto> aquariosDto =
        aquarios.stream().map(a -> mapper.toAquarioDto(a)).collect(Collectors.toList());

    Response response = new ResponseObj<>(Global.getUri(), aquariosDto);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<Response> cadastrar(
      @Validated(AquarioDto.Post.class) @RequestBody AquarioDto aquarioDto, BindingResult result)
      throws Exception {
    log.info("Requisição para cadastrar um novo aquário");
    ResponseError responseError = new ResponseError(Global.getUri());

    if (result.hasErrors()) {
      responseError.addMessagesFromResultErrors(result, log);
      return ResponseEntity.badRequest().body(responseError);
    }

    Aquario aquarioNovo = aquarioService.persistir(mapper.toAquario(aquarioDto));
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(aquarioNovo.getId())
            .toUri();

    Response response = new ResponseObj<>(uri, mapper.toAquarioDto(aquarioNovo));
    return ResponseEntity.created(uri).body(response);
  }

  @PutMapping
  public ResponseEntity<Response> alterar(
      @Validated(AquarioDto.Put.class) @RequestBody AquarioDto aquarioDto, BindingResult result)
      throws Exception {
    log.info("Requisição para alterar um aquaário existente");
    ResponseError responseError = new ResponseError(Global.getUri());

    if (result.hasErrors()) {
      responseError.addMessagesFromResultErrors(result, log);
      return ResponseEntity.badRequest().body(responseError);
    }

    Aquario aquarioUpd = aquarioService.alterar(mapper.toAquario(aquarioDto));
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(aquarioUpd.getId())
            .toUri();

    Response response = new ResponseObj<>(uri, mapper.toAquarioDto(aquarioUpd));
    return ResponseEntity.ok(response);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Response> deletar(@PathVariable Long id) throws Exception {
    log.info("Requisição para deletar um aquário");

    Aquario aquario = aquarioService.deletar(id);
    Response response = new ResponseObj<>(Global.getUri(), mapper.toAquarioDto(aquario));

    return ResponseEntity.ok(response);
  }
}
