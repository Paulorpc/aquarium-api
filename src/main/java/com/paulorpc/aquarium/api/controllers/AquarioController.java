package com.paulorpc.aquarium.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paulorpc.aquarium.api.entities.Aquario;
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
	public ResponseEntity<Aquario> cadastrarAquario(@RequestBody Aquario aquario) {
		Aquario novoAquario = aquarioService.persistirAquario(aquario);
		return ResponseEntity.ok(novoAquario);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deletarAquario(@PathVariable int id) {
		Optional<Aquario> aquario = aquarioService.deletarAquario(id);
		if(!aquario.isPresent())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().build();
	}

}
