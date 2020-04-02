package com.paulorpc.aquarium.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping(value="/")
	public List<Aquario> buscarTodos() {
		return aquarioService.buscarTodos();
	}

}
