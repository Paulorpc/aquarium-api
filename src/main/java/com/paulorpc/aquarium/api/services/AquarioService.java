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

  private final Logger log = LoggerFactory.getLogger(AquarioService.class);

  @Autowired
  private AquarioRepository aquarioRep;

  /***
   * Busca aquarios pelo id
   * 
   * @return List<Aquario>
   */
  public Optional<Aquario> buscarAquario(int id) {
    log.info("Buscando aquário. Id: {} ", id);
    return aquarioRep.findByIdAndStatusIsTrue(id);
  }

  /***
   * Busca todos aquarios cadastrados
   * 
   * @return List<Aquario>
   */
  public List<Aquario> buscarTodos() {
    log.info("Buscando todos aquários.");
    return aquarioRep.findAll();

  }

  /***
   * Busca todos aquarios ativos (não excluídos pelo usuário)
   * 
   * @return List<Aquario>
   */
  public List<Aquario> buscarTodosAtivos() {
    log.info("Buscando todos aquários ativos.");
    return aquarioRep.findByStatusIsTrue();
  }

  /***
   * Cadastra novo aquário
   * 
   * @param aquario
   * @return Aquario
   */
  public Aquario cadastrarAquario(Aquario aquario) {
    log.info("Cadastrando um novo aquário. Aquário: {}", aquario.toString());
    aquario.setStatus(true);
    return aquarioRep.save(aquario);
  }

  /***
   * Atualiza cadastro de aquário
   * 
   * @param aquario
   * @return Aquario
   */
  // TODO corrigir o parametro para int
  public Optional<Aquario> alterarAquario(AquarioDto aquario) {
    log.info("Alterando um aquário. Aquario: {}", aquario.toString());
    Optional<Aquario> aquarioOpt =
        aquario.getId().flatMap(id -> aquarioRep.findByIdAndStatusIsTrue(id));
    if (aquarioOpt.isPresent()) {
      Aquario aquarioUpd = AquarioController.converteDtoParaObjeto(aquario, aquarioOpt.get());
      aquarioOpt = Optional.of(aquarioRep.save(aquarioUpd));
    }
    return aquarioOpt;
  }

  /***
   * Deleta um aquario cadastrado. Status é alterado para FALSE.
   * 
   * @param id
   * @return Optional<Aquario>
   */
  public Optional<Aquario> deletarAquario(int id) {
    log.info("Deletando um aquário. Id: {}", id);
    return aquarioRep.findByIdAndStatusIsTrue(id).map(a -> {
      a.setDtFinal(new Date());
      a.setStatus(false);
      return aquarioRep.save(a);
    });
  }

}
