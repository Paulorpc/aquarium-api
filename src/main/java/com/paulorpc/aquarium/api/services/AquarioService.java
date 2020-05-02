package com.paulorpc.aquarium.api.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulorpc.aquarium.api.controllers.AquarioController;
import com.paulorpc.aquarium.api.dtos.AquarioDto;
import com.paulorpc.aquarium.api.entities.Aquario;
import com.paulorpc.aquarium.api.repositories.AquarioRepository;

@Service
public class AquarioService {
	
	Logger log = LoggerFactory.getLogger(AquarioService.class);
	
	@Autowired
	private AquarioRepository aquarioRepository;
	
	/***
	 * Busca todos aquarios cadastrados
	 * @return List<Aquario>
	 */
	public List<Aquario> buscarTodos() {
		log.info("Buscando todos aquários.");
		return aquarioRepository.findAll(); 
		 
	}
	
	/***
	 * Busca todos aquarios ativos (não excluídos pelo usuário)
	 * @return List<Aquario>
	 */
	public List<Aquario> buscarTodosAtivos() {
		log.info("Buscando todos aquários ativos.");
		return aquarioRepository.findByStatusIsTrue(); 
	}
	
	
	/***
	 * Cadastra novo aquário
	 * @param aquario
	 * @return Aquario
	 */
	public Aquario cadastrarAquario(Aquario aquario) {
		log.info("Cadastrando um novo aquário.");
		aquario.setStatus(true);
		return aquarioRepository.save(aquario);
	}
	
	
	/***
	 * Atualiza cadastro de aquário
	 * @param aquario
	 * @return Aquario
	 */
	public Optional<Aquario> alterarAquario(AquarioDto aquario) {
		log.info("Alterando um aquário.");
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
		log.info("Deletando um aquário.");
		return aquarioRepository
					.findByIdAndStatusIsTrue(id)
					.map(a -> {						
							a.setDtFinal(new Date());
							a.setStatus(false);
							return aquarioRepository.save(a);
					});
	}

}
