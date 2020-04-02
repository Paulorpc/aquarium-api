package com.paulorpc.aquarium.api.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulorpc.aquarium.api.entities.Aquario;
import com.paulorpc.aquarium.api.repositories.AquarioRepository;

@Service
public class AquarioService {
	
	@Autowired
	private AquarioRepository aquarioRepository;
	
	/***
	 * Busca todos aquarios cadastrados
	 * @return List<Aquario>
	 */
	public List<Aquario> buscarTodos() {
		return aquarioRepository.findAll(); 
		 
	}
	
	/***
	 * Busca todos aquarios ativos (não excluídos pelo usuário)
	 * @return List<Aquario>
	 */
	public List<Aquario> buscarTodosAtivos() {
		return aquarioRepository.findByStatusIsTrue(); 
	}
	
	
	/***
	 * Cadastro um novo aquário
	 * @param aquario
	 * @return Aquario
	 */
	public Aquario persistirAquario(Aquario aquario) {
		return aquarioRepository.save(aquario);
	}
	
	
	/***
	 * Deleta um aquario cadastrado. Status é alterado para FALSE.
	 * @param id
	 * @return Optional<Aquario>
	 */
	public Optional<Aquario> deletarAquario(int id) {
		Optional<Aquario> aquario = aquarioRepository.findByIdAquarioAndStatusIsTrue(id);
		aquario.ifPresent(a -> {
									a.setDtFinal(new Date());
									a.setStatus(false);
									aquarioRepository.save(a);
								});
		return aquario;
	}
	

}
