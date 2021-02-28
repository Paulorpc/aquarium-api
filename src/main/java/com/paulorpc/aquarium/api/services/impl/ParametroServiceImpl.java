package com.paulorpc.aquarium.api.services.impl;

import com.paulorpc.aquarium.api.entities.Parametro;
import com.paulorpc.aquarium.api.entities.ProcedimentoTeste;
import com.paulorpc.aquarium.api.exceptions.NotFoundException;
import com.paulorpc.aquarium.api.repositories.ParametroRepository;
import com.paulorpc.aquarium.api.repositories.ProcedimentoTesteRepository;
import com.paulorpc.aquarium.api.services.AquarioService;
import com.paulorpc.aquarium.api.services.ParametroService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParametroServiceImpl implements ParametroService {

  private final Logger log = LoggerFactory.getLogger(ParametroServiceImpl.class);

  @Autowired ParametroRepository parametroRep;

  @Autowired ProcedimentoTesteRepository procedimentosRep;

  @Autowired AquarioService aquarioService;

  @Override
  public Optional<Parametro> buscar(Long id) {
    log.info("Buscando um parâmetro: {} ", id);
    return parametroRep.findById(id);
  }

  @Override
  @Transactional
  public Optional<Parametro> buscarRetornandoProcedimentosTeste(Long id) {
    log.info("Buscando um parâmetro recuperando os procedimentosTeste: {} ", id);
    Optional<Parametro> parametro = parametroRep.findById(id);
    parametro.ifPresent(p -> Hibernate.initialize(p.getProcedimentosTeste()));
    return parametro;
  }

  @Override
  public List<Parametro> buscarTodos() {
    log.info("Buscando todos parâmetros");
    return parametroRep.findAll();
  }

  @Override
  public List<Parametro> buscarTodosRetornandoProcedimentosTeste() {
    log.info("Buscando todos parâmetros recuperando os procedimentosTeste");
    return parametroRep.findAllRetrieveProcedimentosTeste();
  }

  @Override
  public List<Parametro> buscarTodosDoAquario(Long idAquario) {
    log.info("Buscando todos parâmetros do aquário");
    return parametroRep.findAllFromAquario(idAquario);
  }

  @Override
  public List<Parametro> buscarTodosDoAquarioRetornandoProcedimentosTeste(Long idAquario) {
    log.info("Buscando todos parâmetros do aquário recuperando os procedimentosTeste");
    return parametroRep.findAllFromAquarioRetrieveProcedimentosTeste(idAquario);
  }

  @Override
  public Parametro persistir(Parametro parametro) throws Exception {
    log.info("Persistindo um novo parâmetro: {}", parametro.toString());

    Optional<Long> idAquario = Optional.ofNullable(parametro.getAquario().getId());
    if (idAquario.isPresent()) {
      parametro.setAquario(
          aquarioService
              .buscar(idAquario.get())
              .orElseThrow(
                  () ->
                      new NotFoundException(
                          "Não foi possível localizar aquario. Id: " + idAquario.get())));
    }
    return parametroRep.save(parametro);
  }

  @Override
  public List<Parametro> persistirTodos(List<Parametro> parametros) throws Exception {
    log.info("Persistindo todos novos parâmetros");
    return parametroRep.saveAll(parametros);
  }

  @Override
  public Parametro alterar(Parametro parametro) throws Exception {
    log.info("Alterando um parâmetro: {}", parametro.toString());

    Optional<Parametro> parametroOpt = parametroRep.findById(parametro.getId());
    if (parametroOpt.isPresent()) {
      parametro.setId(parametroOpt.get().getId());
      parametro.setDtCadastro(parametroOpt.get().getDtCadastro());
    } else {
      throw new NotFoundException(
          "Não foi possível localizar o parametro. Id: " + parametro.getId());
    }
    return persistir(parametro);
  }

  @Override
  public Parametro deletar(Long id) throws Exception {
    log.info("Deletando um parâmetro: {}", id);

    Parametro parametro =
        parametroRep
            .findByIdRetrieveProcedimentosTeste(id)
            .orElseThrow(
                () -> new NotFoundException("Não foi possível localizar o parâmetro. Id: " + id));

    List<ProcedimentoTeste> procedimentos = parametro.getProcedimentosTeste();

    procedimentosRep.deleteAll(parametro.getProcedimentosTeste());
    parametro.setProcedimentosTeste(null);
    parametroRep.delete(parametro);

    parametro.setProcedimentosTeste(procedimentos);
    return parametro;
  }

  @Override
  public Integer deletarTodosDoAquario(Long idAquario) throws Exception {
    log.info("Deletando todos parâmetros do aquário: {}", idAquario);

    List<Parametro> parametros =
        parametroRep.findAllFromAquarioRetrieveProcedimentosTeste(idAquario).stream()
            .distinct()
            .collect(Collectors.toList());

    parametros.forEach(
        p -> {
          procedimentosRep.deleteAll(p.getProcedimentosTeste());
          p.setProcedimentosTeste(null);
        });

    parametroRep.deleteAll(parametros);

    return parametros.size();
  }
}
