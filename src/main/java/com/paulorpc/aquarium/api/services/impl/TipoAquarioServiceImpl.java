package com.paulorpc.aquarium.api.services.impl;

import com.paulorpc.aquarium.api.entities.TipoAquario;
import com.paulorpc.aquarium.api.enums.TipoAguaEnum;
import com.paulorpc.aquarium.api.repositories.TipoAquarioRepository;
import com.paulorpc.aquarium.api.services.TipoAquarioService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoAquarioServiceImpl implements TipoAquarioService {

  private final Logger log = LoggerFactory.getLogger(TipoAquarioServiceImpl.class);

  @Autowired private TipoAquarioRepository tipoAquarioRepository;

  @Override
  public Optional<TipoAquario> buscarPorTipo(String tipo) {
    log.info("Buscando tipo aquário. Tipo: {} ", tipo);
    return tipoAquarioRepository.findByTipo(tipo);
  }

  @Override
  public Optional<TipoAquario> buscar(Long id) {
    log.info("Buscando tipo aquário. Id: {} ", id);
    return tipoAquarioRepository.findById(id);
  }

  @Override
  public List<TipoAquario> buscarPorTipoAgua(TipoAguaEnum tipoAgua) {
    log.info("Buscando tipos de aquário pelo tipo de água. TipoAgua: {} ", tipoAgua.getDescricao());
    return tipoAquarioRepository.findByTipoAgua(tipoAgua);
  }

  @Override
  public List<TipoAquario> buscarTodos() {
    log.info("Buscando todos tipos de aquários.");
    return tipoAquarioRepository.findAll();
  }

  @Override
  public List<TipoAquario> buscarTodosAtivos() {
    log.info("Buscando todos tipos de aquários ativos.");
    return tipoAquarioRepository.findByStatusIsTrue();
  }

  @Override
  public TipoAquario persistir(TipoAquario tipoAquario) {
    log.info("Persistindo tipoAquário. TipoAquário: {}", tipoAquario.toString());
    return tipoAquarioRepository.save(tipoAquario);
  }

  @Override
  public Optional<TipoAquario> alterar(TipoAquario tipoAquario) {
    log.info("Alterando um tipo de aquário. TipoAquario: {}", tipoAquario.toString());

    Optional<TipoAquario> tipoAquarioOpt = tipoAquarioRepository.findById(tipoAquario.getId());
    if (tipoAquarioOpt.isPresent()) {
      tipoAquarioOpt = Optional.of(persistir(tipoAquario));
    }

    return tipoAquarioOpt;
  }

  @Override
  public Optional<TipoAquario> deletar(Long id) {
    log.info("Deletando um tipo de aquário. Id: {}", id);
    Optional<TipoAquario> tipoAquario = tipoAquarioRepository.findById(id);
    tipoAquario.ifPresent(v -> tipoAquarioRepository.deleteById(id));
    return tipoAquario;
  }
}
