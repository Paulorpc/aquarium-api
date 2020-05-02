package com.paulorpc.aquarium.api.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.paulorpc.aquarium.api.response.Response;
import com.paulorpc.aquarium.api.services.AquarioService;

@RestController
@RequestMapping("/api/aquario")
@CrossOrigin("*")
@SessionAttributes("aquario")
public class AquarioController {
	
	Logger log = LoggerFactory.getLogger(AquarioController.class);
	
	@Autowired
	private AquarioService aquarioService;
	
	
	@GetMapping
	public ResponseEntity<Response<List<Aquario>>> buscarTodos() {		
		log.info("Requisição para buscar todos aquários - buscarTodos()");
		Response<List<Aquario>> response = new Response<>();
		List<Aquario> aquarios = aquarioService.buscarTodos();
		response.setData(aquarios);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value="/ativos")
	public ResponseEntity<Response<List<Aquario>>> buscarTodosAtivos() {
		log.info("Requisição para buscar todos aquários ativos - buscarTodosAtivos()");
		Response<List<Aquario>> response = new Response<>();
		List<Aquario> aquarios = aquarioService.buscarTodosAtivos();
		response.setData(aquarios);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Response<AquarioDto>> cadastrarAquario(@Validated(AquarioDto.Cadastrar.class) @RequestBody AquarioDto aquarioDto, BindingResult result) {
		log.info("Requisição para cadastrar um novo aquário - cadastrarAquario()");
		Response<AquarioDto> response = new Response<>();
		
		if(result.hasErrors()) {			
			response.setIssuesFromResultErrors(result);
			return ResponseEntity.badRequest().body(response);
		}
		
		if(!aquarioDto.getStatus().get().booleanValue())
				response.getIssues().add("O campo 'status' foi alterado para true pelo sistema, pois o método POST sempre considera true o 'status' de um novo registro.");
		
		
		Aquario novoAquario = aquarioService.cadastrarAquario(converteDtoParaObjeto(aquarioDto));		
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoAquario.getId()).toUri();
		response.setData(converteObjetoParaDto(novoAquario));		
        return ResponseEntity.created(uri).body(response);
	}

	
	@PutMapping
	public ResponseEntity<Response<AquarioDto>> alterarAquario(@Validated(AquarioDto.Alterar.class) @RequestBody AquarioDto aquarioDto, BindingResult result) {
		log.info("Requisição para alterar um aquaário existente - alterarAquario()");
		Response<AquarioDto> response = new Response<>();
		if(result.hasErrors()) {
			response.setIssuesFromResultErrors(result);
			return ResponseEntity.badRequest().body(response);
		}	
		
		Optional<Aquario> aquarioOpt = aquarioService.alterarAquario(aquarioDto);
		
		if(!aquarioOpt.isPresent()) {
			response.getIssues().add("Não foi possível localizar o aquário, id: " + aquarioDto.getId());
			return ResponseEntity.badRequest().body(response);
		}
			
		response.setData(converteObjetoParaDto(aquarioOpt.get()));
		return ResponseEntity.ok(response);
	}
	
	
	/***
	 * Método de deleção de aquários.
	 * Obs: Feito método utilizando estilo funcional para aprendizado.
	 * @param id
	 * @return
	 */
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Response<AquarioDto>> deletarAquario(@PathVariable int id) {
		log.info("Requisição para deletar um aquário - deletarAquario()");
		Response<AquarioDto> response = new Response<>();
		
		/*
		Optional<Aquario> aquario = aquarioService.deletarAquario(id);
		if(!aquario.isPresent())
			return ResponseEntity.notFound().build();
			
		//response.setData(ConverteAquarioToAquarioDto(aquario.get()));
		//return ResponseEntity.ok(response);
		*/
		
		return aquarioService
					.deletarAquario(id)
					.map(aquario -> {
						response.setData(converteObjetoParaDto(aquario));
						return ResponseEntity.ok(response);
					})
					.orElse(ResponseEntity.notFound().build());
	
	}
	
	
	/************* CONVERSORES OBJETO/DTO *************/ 
	
	
	/***
	 * Converte objeto Aquario para AquarioDTO
	 * @param dto
	 * @return Aquario
	 */
	private static Aquario converteDtoParaObjeto(AquarioDto dto) {		
		return converteDtoParaObjeto(dto, new Aquario());
	}
	
	/***
	 * Converte objeto Aquario para AquarioDTO
	 * @param dto
	 * @return Aquario
	 */
	public static Aquario converteDtoParaObjeto(AquarioDto dto, Aquario aquario) {	
		dto.getId().ifPresent(v->aquario.setId(v));
		dto.getNome().ifPresent(v->aquario.setNome(v));
		dto.getDtInicio().ifPresent(v->aquario.setDtInicio(v));
		dto.getDtFinal().ifPresent(v->aquario.setDtFinal(v));
		dto.getTipoAgua().ifPresent(v->aquario.setTipoAgua(v));
		dto.getTamanho().ifPresent(v->aquario.setTamanho(v));
		dto.getVolume().ifPresent(v->aquario.setVolume(v));
		dto.getIluminacao().ifPresent(v->aquario.setIluminacao(v));
		dto.getFiltragem().ifPresent(v->aquario.setFiltragem(v));
		dto.getSistemaCO2().ifPresent(v->aquario.setSistemaCO2(v));
		dto.getDosagem().ifPresent(v->aquario.setDosagem(v));
		dto.getSubstrato().ifPresent(v->aquario.setSubstrato(v));
		dto.getObservacao().ifPresent(v->aquario.setObservacao(v));
		dto.getStatus().ifPresent(v->aquario.setStatus(v));
		dto.getIdTipoAquario().ifPresent(v->aquario.setIdTipoAquario(v));
		return aquario;
	}
	
	
	/***
	 * Converte objeto Aquario para AquarioDTO
	 * @param aquario
	 * @return aquarioDto
	 */
	private static AquarioDto converteObjetoParaDto(Aquario aquario) {
		AquarioDto dto = new AquarioDto();
		dto.setId(Optional.ofNullable(aquario.getId()));
		dto.setNome(Optional.ofNullable(aquario.getNome()));
		dto.setDtInicio(Optional.ofNullable(aquario.getDtInicio()));
		dto.setDtFinal(Optional.ofNullable(aquario.getDtFinal()));
		dto.setTipoAgua(Optional.ofNullable(aquario.getTipoAgua()));
		dto.setTamanho(Optional.ofNullable(aquario.getTamanho()));
		dto.setVolume(Optional.ofNullable(aquario.getVolume()));
		dto.setIluminacao(Optional.ofNullable(aquario.getIluminacao()));
		dto.setFiltragem(Optional.ofNullable(aquario.getFiltragem()));
		dto.setSistemaCO2(Optional.ofNullable(aquario.getSistemaCO2()));
		dto.setDosagem(Optional.ofNullable(aquario.getDosagem()));
		dto.setSubstrato(Optional.ofNullable(aquario.getSubstrato()));
		dto.setObservacao(Optional.ofNullable(aquario.getObservacao()));
		dto.setStatus(Optional.ofNullable(aquario.getStatus()));
		dto.setIdTipoAquario(Optional.ofNullable(aquario.getIdTipoAquario()));		
		return dto;
	}
}
