package com.paulorpc.aquarium.api.services.impl;

import com.paulorpc.aquarium.api.entities.Aquario;
import com.paulorpc.aquarium.api.entities.Equipamento;
import com.paulorpc.aquarium.api.exceptions.NotFoundException;
import com.paulorpc.aquarium.api.repositories.EquipamentoRepository;
import com.paulorpc.aquarium.api.services.AquarioService;
import com.paulorpc.aquarium.api.services.EquipamentoService;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipamentoServiceImpl implements EquipamentoService {

  private final Logger log = LoggerFactory.getLogger(BiotaServiceImpl.class);

  @Autowired EquipamentoRepository equipamentoRep;

  @Autowired AquarioService aquarioService;

  @Override
  public Optional<Equipamento> buscar(Long id) {
    log.info("Buscando um equipamento: {} ", id);
    return equipamentoRep.findById(id);
  }

  @Override
  @Transactional
  public Optional<Equipamento> buscarRetornandoAquarios(Long id) {
    log.info("Buscando um equipamento: {} ", id);
    Optional<Equipamento> equipamento = equipamentoRep.findById(id);
    Hibernate.initialize(equipamento.get().getAquarios());
    return equipamento;
  }

  @Override
  public List<Equipamento> buscarTodos() {
    log.info("Buscando todos equipamentos.");
    return equipamentoRep.findAll();
  }

  @Override
  public List<Equipamento> buscarTodosRetornandoAquarios() {
    log.info("Buscando todos equipamentos.");
    return equipamentoRep.findAllRetrieveAquario();
  }

  @Override
  public List<Equipamento> buscarTodosDoAquario(Long idAquario) {
    log.info("Buscando todos equipamentos do aquário: {}", idAquario);
    return equipamentoRep.findAllByAquarioIdRetrieveAquario(idAquario);
  }

  @Override
  public Equipamento persistir(Equipamento equipamento) throws Exception {
    log.info("Persistindo um novo equipamento: {}", equipamento.toString());
    return equipamentoRep.save(equipamento);
  }

  @Override
  public List<Equipamento> persistirTodos(List<Equipamento> equipamentos) throws Exception {
    log.info("Persistindo todos novos equipamentos");
    return equipamentoRep.saveAll(equipamentos);
  }

  @Override
  public Equipamento persistir(Equipamento equipamento, Long aquarioId) throws Exception {
    log.info("Persistindo um equipamento: {}", equipamento.toString());
    Optional<Aquario> aquario = aquarioService.buscar(aquarioId);

    if (aquario.isEmpty())
      throw new NotFoundException("Não foi possível localizar o aquario. Id: " + aquarioId);

    aquario.get().setEquipamentos(Set.of(equipamento));
    equipamento.setAquarios(Set.of(aquario.get()));
    aquarioService.persistir(aquario.get());
    equipamentoRep.save(equipamento);

    return equipamento;
  }

  @Override
  public Equipamento alterar(Equipamento equipamento) throws Exception {
    log.info("Alterando um equipamento: {}", equipamento.toString());

    Equipamento equipamentoDb =
        equipamentoRep
            .findById(equipamento.getId())
            .orElseThrow(
                () ->
                    new NotFoundException(
                        "Não foi possível localizar o equipamento. Id: " + equipamento.getId()));

    equipamento.setDtCadastro(equipamentoDb.getDtCadastro());

    return persistir(equipamento);
  }

  @Override
  @Transactional
  public Equipamento deletar(Long id) throws Exception {
    log.info("Deletando um equipamento. Id: {}", id);

    return equipamentoRep
        .findById(id)
        .map(
            e -> {
              Hibernate.initialize(e.getAquarios());
              equipamentoRep.delete(e);
              return e;
            })
        .orElseThrow(
            () -> new NotFoundException("Não foi possível localizar o equipamento. Id: " + id));
  }
}
