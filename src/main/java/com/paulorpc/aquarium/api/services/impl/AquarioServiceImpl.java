package com.paulorpc.aquarium.api.services.impl;

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
import com.paulorpc.aquarium.api.services.AquarioService;

@Service
public class AquarioServiceImpl implements AquarioService {

  private final Logger log = LoggerFactory.getLogger(AquarioServiceImpl.class);

  @Autowired
  private AquarioRepository aquarioRep;

  public Optional<Aquario> buscar(int id) {
    log.info("Buscando aquário. Id: {} ", id);
    return aquarioRep.findByIdAndStatusIsTrue(id);
  }

  public List<Aquario> buscarTodos() {
    log.info("Buscando todos aquários.");
    return aquarioRep.findAll();

  }

  public List<Aquario> buscarTodosAtivos() {
    log.info("Buscando todos aquários ativos.");
    return aquarioRep.findByStatusIsTrue();
  }

  public Aquario persistir(Aquario aquario) {
    log.info("Cadastrando um novo aquário. Aquário: {}", aquario.toString());
    aquario.setStatus(true);
    return aquarioRep.save(aquario);
  }

  public Optional<Aquario> alterar(AquarioDto aquario) {
    log.info("Alterando um aquário. Aquario: {}", aquario.toString());
    Optional<Aquario> aquarioOpt =
        aquario.getId().flatMap(id -> aquarioRep.findByIdAndStatusIsTrue(id));
    if (aquarioOpt.isPresent()) {
      Aquario aquarioUpd = AquarioController.converteDtoParaObjeto(aquario, aquarioOpt.get());
      aquarioOpt = Optional.of(aquarioRep.save(aquarioUpd));
    }
    return aquarioOpt;
  }

  public Optional<Aquario> deletar(int id) {
    log.info("Deletando um aquário. Id: {}", id);
    return aquarioRep.findByIdAndStatusIsTrue(id).map(a -> {
      a.setDtFinal(new Date());
      a.setStatus(false);
      return aquarioRep.save(a);
    });
  }

}
