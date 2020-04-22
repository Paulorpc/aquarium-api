package com.paulorpc.aquarium.api.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.paulorpc.aquarium.api.dtos.AquarioDto;
import com.paulorpc.aquarium.api.entities.Aquario;
import com.paulorpc.aquarium.api.response.Response;
import com.paulorpc.aquarium.api.services.AquarioService;

@RestController
@RequestMapping("/api/aquario")
@CrossOrigin("*")
public class AquarioController {
	
	@Autowired
	private AquarioService aquarioService;
	
	@GetMapping
	public ResponseEntity<List<Aquario>> buscarTodos() {
		List<Aquario> aquarios = aquarioService.buscarTodos();
		return ResponseEntity.ok(aquarios);
	}
	
	@GetMapping(value="/ativos")
	public ResponseEntity<List<Aquario>> buscarTodosAtivos() {
		List<Aquario> aquarios = aquarioService.buscarTodosAtivos();
		return ResponseEntity.ok(aquarios);
	}
	
	@PostMapping
	public ResponseEntity<Response<AquarioDto>> cadastrarAquario(@Valid @RequestBody AquarioDto aquarioDto, BindingResult result) {
		Response<AquarioDto> response = new Response<>();
		
		if(result.hasErrors()) {			
			response.setIssuesFromResultErrors(result);
			return ResponseEntity.badRequest().body(response);
		}
		
		if(!aquarioDto.getStatus())
			response.getIssues().add("O campo 'status' foi alterado para true pelo sistema, pois o método POST sempre considera true o 'status' de um novo registro.");
		
		Aquario novoAquario = aquarioService.persistirAquario(ConverteAquarioDtoToAquario(aquarioDto, result));		
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoAquario.getIdAquario()).toUri();
		response.setData(ConverteAquarioToAquarioDto(novoAquario));		
        return ResponseEntity.created(uri).body(response);
	}
	
	/***
	 * Método de deleção de aquários.
	 * Obs: Feito método utilizando estilo funcional para aprendizado.
	 * @param id
	 * @return
	 */
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Response<AquarioDto>> deletarAquario(@PathVariable int id) {
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
						response.setData(ConverteAquarioToAquarioDto(aquario));
						return ResponseEntity.ok(response);
					})
					.orElse(ResponseEntity.notFound().build());
	
	}

	
	

	/***
	 * Converte objeto Aquario para AquarioDTO
	 * @param dto
	 * @return Aquario
	 */
	private static Aquario ConverteAquarioDtoToAquario(AquarioDto dto, BindingResult result) {
		Aquario aquario = new Aquario();
		aquario.setNome(dto.getNome());
		aquario.setDtInicio(dto.getDtInicio());
		aquario.setDtFinal(dto.getDtFinal());
		aquario.setTipoAgua(dto.getTipoAgua());
		aquario.setTamanho(dto.getTamanho());
		aquario.setVolume(dto.getVolume());
		aquario.setIluminacao(dto.getIluminacao());
		aquario.setFiltragem(dto.getFiltragem());
		aquario.setSistemaCO2(dto.getSistemaCO2());
		aquario.setDosagem(dto.getDosagem());
		aquario.setSubstrato(dto.getSubstrato());
		aquario.setObservacao(dto.getObservacao());
		aquario.setStatus(dto.getStatus());
		aquario.setIdTipoAquario(dto.getIdTipoAquario());		
		return aquario;
	}
	
	
	/***
	 * Converte objeto AquarioDTO para Aquario
	 * @param aquario
	 * @return aquarioDto
	 */
	private static AquarioDto ConverteAquarioToAquarioDto(Aquario aquario) {
		AquarioDto dto = new AquarioDto();
		dto.setIdAquario(aquario.getIdAquario());
		dto.setNome(aquario.getNome());
		dto.setDtInicio(aquario.getDtInicio());
		dto.setDtFinal(aquario.getDtFinal());
		dto.setTipoAgua(aquario.getTipoAgua());
		dto.setTamanho(aquario.getTamanho());
		dto.setVolume(aquario.getVolume());
		dto.setIluminacao(aquario.getIluminacao());
		dto.setFiltragem(aquario.getFiltragem());
		dto.setSistemaCO2(aquario.getSistemaCO2());
		dto.setDosagem(aquario.getDosagem());
		dto.setSubstrato(aquario.getSubstrato());
		dto.setObservacao(aquario.getObservacao());
		dto.setStatus(aquario.getStatus());
		dto.setIdTipoAquario(aquario.getIdTipoAquario());
		return dto;
	}
}
