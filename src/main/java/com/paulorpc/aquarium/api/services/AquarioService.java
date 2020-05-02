package com.paulorpc.aquarium.api.services;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulorpc.aquarium.api.controllers.AquarioController;
import com.paulorpc.aquarium.api.dtos.AquarioDto;
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
	 * Cadastra novo aquário
	 * @param aquario
	 * @return Aquario
	 */
	public Aquario cadastrarAquario(Aquario aquario) {
		aquario.setStatus(true);
		return aquarioRepository.save(aquario);
	}
	
	
	/***
	 * Atualiza cadastro de aquário
	 * @param aquario
	 * @return Aquario
	 */
	public Optional<Aquario> alterarAquario(AquarioDto aquario) {
		Optional<Aquario> aquarioOpt = aquarioRepository.findByIdAndStatusIsTrue(aquario.getId().get());
		if(aquarioOpt.isPresent()) {
			Aquario aquarioUpd = AquarioController.converteDtoParaObjeto(aquario, aquarioOpt.get());			
			aquarioOpt = Optional.of(aquarioRepository.save(aquarioUpd));		
		}			
		return aquarioOpt;
	}
	
	
	/***
	 * Deleta um aquario cadastrado. Status é alterado para FALSE.
	 * @param id
	 * @return Optional<Aquario>
	 */
	public Optional<Aquario> deletarAquario(int id) {
		return aquarioRepository
					.findByIdAndStatusIsTrue(id)
					.map(a -> {						
							a.setDtFinal(new Date());
							a.setStatus(false);
							return aquarioRepository.save(a);
					});
	}

}
