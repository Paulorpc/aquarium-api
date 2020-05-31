package com.paulorpc.aquarium.api.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paulorpc.aquarium.api.entities.Aquario;
import com.paulorpc.aquarium.api.exceptions.NotFoundException;
import com.paulorpc.aquarium.api.repositories.AquarioRepository;
import com.paulorpc.aquarium.api.services.AquarioService;
import com.paulorpc.aquarium.api.services.TipoAquarioService;

@Service
public class AquarioServiceImpl implements AquarioService {

  private final Logger log = LoggerFactory.getLogger(AquarioServiceImpl.class);

  @Autowired
  private AquarioRepository aquarioRep;

  @Autowired
  private TipoAquarioService tipoAquarioService;

  @Override
  public Optional<Aquario> buscar(int id) {
    log.info("Buscando aquário. Id: {} ", id);
    return aquarioRep.findByIdAndStatusIsTrue(id);
  }

  @Override
  public List<Aquario> buscarTodos() {
    log.info("Buscando todos aquários.");
    return aquarioRep.findAll();

  }

  @Override
  public List<Aquario> buscarTodosAtivos() {
    log.info("Buscando todos aquários ativos.");
    return aquarioRep.findByStatusIsTrue();
  }

  @Override
  public Aquario persistir(Aquario aquario) throws Exception {
    log.info("Cadastrando um novo aquário. Aquário: {}", aquario.toString());

    Optional<Integer> idTipoAquario = Optional.ofNullable(aquario.getTipoAquario().getId());
    if (idTipoAquario.isPresent()) {
      tipoAquarioService.buscar(idTipoAquario.get()).orElseThrow(() -> new NotFoundException(
          "Não foi possível localizar o tipoAquario. Id: " + idTipoAquario.get()));
    }

    return aquarioRep.save(aquario);
  }

  @Override
  public Optional<Aquario> alterar(Aquario aquario) throws Exception {
    log.info("Alterando um aquário. Aquario: {}", aquario.toString());

    Optional<Aquario> aquarioOpt = aquarioRep.findById(aquario.getId());
    if (aquarioOpt.isPresent()) {
      aquarioOpt = Optional.of(persistir(aquario));
    }

    return aquarioOpt;
  }

  @Override
  public Optional<Aquario> deletar(int id) {
    log.info("Deletando um aquário. Id: {}", id);
    return aquarioRep.findByIdAndStatusIsTrue(id).map(a -> {
      a.setDtFinal(new Date());
      a.setStatus(false);
      return aquarioRep.save(a);
    });
  }

}
