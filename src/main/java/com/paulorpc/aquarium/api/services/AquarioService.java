package com.paulorpc.aquarium.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulorpc.aquarium.api.entities.Aquario;
import com.paulorpc.aquarium.api.repositories.AquarioRepository;

@Service
public class AquarioService {
	
	@Autowired
	private AquarioRepository aquarioRepository;
	
	public List<Aquario> buscarTodos() {
		return aquarioRepository.findAll(); 
		 
	}
	

}
