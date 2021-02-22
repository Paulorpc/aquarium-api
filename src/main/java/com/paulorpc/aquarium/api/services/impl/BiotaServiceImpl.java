package com.paulorpc.aquarium.api.services.impl;

import com.paulorpc.aquarium.api.entities.Biota;
import com.paulorpc.aquarium.api.exceptions.NotFoundException;
import com.paulorpc.aquarium.api.repositories.BiotaRepository;
import com.paulorpc.aquarium.api.services.BiotaService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiotaServiceImpl implements BiotaService {

  private final Logger log = LoggerFactory.getLogger(BiotaServiceImpl.class);

  @Autowired private BiotaRepository biotaRep;

  @Override
  public Optional<Biota> buscar(Long id) {
    log.info("Buscando seres vivos (biota). Id: {} ", id);
    return biotaRep.findById(id);
  }

  @Override
  public List<Biota> buscarTodos() {
    log.info("Buscando todos seres vivos.");
    return biotaRep.findAll();
  }

  @Override
  public List<Biota> buscarTodosDeletados() {
    log.info("Buscando todos seres vivos incluindo deletados.");
    return biotaRep.findAllDeletadoIsTrue();
  }

  @Override
  public Biota persistir(Biota biota) throws Exception {
    log.info("Persistindo um novo ser vivo. Biota: {}", biota.toString());
    return biotaRep.save(biota);
  }

  @Override
  public Biota alterar(Biota biota) throws Exception {
    log.info("Alterando um ser vivo. Biota: {}", biota.toString());

    Biota biotaDb =
        biotaRep
            .findById(biota.getId())
            .orElseThrow(
                () ->
                    new NotFoundException(
                        "Não foi possível localizar o ser vivo. Id: " + biota.getId()));

    biota.setUsuarioAtualizacao("usuario_sessao");
    biota.setUsuarioCadastro(biotaDb.getUsuarioCadastro());
    biota.setDtCadastro(biotaDb.getDtCadastro());

    return persistir(biota);
  }

  @Override
  public Biota deletar(Long id) throws Exception {
    log.info("Deletando um ser vivo. Id: {}", id);

    return biotaRep
        .findById(id)
        .map(
            b -> {
              biotaRep.delete(b);
              return b;
            })
        .orElseThrow(() -> new NotFoundException("Não foi possível localizar biota. Id: " + id));
  }
}
