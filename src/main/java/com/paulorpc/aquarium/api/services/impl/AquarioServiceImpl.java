package com.paulorpc.aquarium.api.services.impl;

import com.paulorpc.aquarium.api.entities.Aquario;
import com.paulorpc.aquarium.api.exceptions.NotFoundException;
import com.paulorpc.aquarium.api.repositories.AquarioRepository;
import com.paulorpc.aquarium.api.services.AquarioService;
import com.paulorpc.aquarium.api.services.TipoAquarioService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AquarioServiceImpl implements AquarioService {

  private final Logger log = LoggerFactory.getLogger(AquarioServiceImpl.class);

  @Autowired private AquarioRepository aquarioRep;

  @Autowired private TipoAquarioService tipoAquarioService;

  @Override
  public Optional<Aquario> buscar(Long id) {
    log.info("Buscando aquário. Id: {} ", id);
    return aquarioRep.findByIdAndStatusIsTrue(id);
  }

  @Override
  public List<Aquario> buscarTodos() {
    log.info("Buscando todos aquários.");
    return aquarioRep.findAll();
  }

  @Override
  public Aquario persistir(Aquario aquario) throws Exception {
    log.info("Cadastrando um novo aquário. Aquário: {}", aquario.toString());

    Optional<Long> idTipoAquario = Optional.ofNullable(aquario.getTipoAquario().getId());
    if (idTipoAquario.isPresent()) {
      tipoAquarioService
          .buscar(idTipoAquario.get())
          .orElseThrow(
              () ->
                  new NotFoundException(
                      "Não foi possível localizar o tipoAquario. Id: " + idTipoAquario.get()));
    }

    return aquarioRep.save(aquario);
  }

  @Override
  public Aquario alterar(Aquario aquario) throws Exception {
    log.info("Alterando um aquário. Aquario: {}", aquario.toString());

    Optional<Aquario> aquarioOpt = aquarioRep.findById(aquario.getId());
    if (aquarioOpt.isPresent()) {
      aquario.setDtCadastro(aquarioOpt.get().getDtCadastro());
      aquarioOpt = Optional.of(persistir(aquario));
    } else {
      throw new NotFoundException("Não foi possível localizar o aquário. Id: " + aquario.getId());
    }

    return aquarioOpt.get();
  }

  @Override
  public Aquario deletar(Long id) throws NotFoundException {
    log.info("Deletando um aquário. Id: {}", id);
    Optional<Aquario> aquario = aquarioRep.findById(id);

    if (aquario.isPresent()) {
      aquarioRep.delete(aquario.get());
    } else {
      throw new NotFoundException("Não foi possível localizar o aquário. Id: ");
    }

    return aquario.get();
  }
}
