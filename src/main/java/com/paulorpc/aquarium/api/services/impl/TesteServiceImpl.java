package com.paulorpc.aquarium.api.services.impl;

import com.paulorpc.aquarium.api.entities.Aquario;
import com.paulorpc.aquarium.api.entities.Parametro;
import com.paulorpc.aquarium.api.entities.Teste;
import com.paulorpc.aquarium.api.exceptions.NotFoundException;
import com.paulorpc.aquarium.api.repositories.TesteRepository;
import com.paulorpc.aquarium.api.services.AquarioService;
import com.paulorpc.aquarium.api.services.ParametroService;
import com.paulorpc.aquarium.api.services.TesteService;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TesteServiceImpl implements TesteService {

  @Autowired TesteRepository testeRepo;

  @Autowired AquarioService aquarioService;

  @Autowired ParametroService parametroService;

  @Override
  public Optional<Teste> buscar(Long id) {
    log.info("Buscando teste: {} ", id);
    return testeRepo.findById(id);
  }

  @Override
  public List<Teste> buscarTodosDoAquario(Long idAquario) {
    log.info("Buscando todos testes do aquário: {} ", idAquario);
    return testeRepo.findAllFromAquario(idAquario);
  }

  @Override
  public List<Teste> buscarTodosDoAquario(
      Long idAquario, LocalDateTime dtInicio, LocalDateTime dtFim) {
    log.info(
        "Buscando todos testes do aquário no período: {} : {} - {}", idAquario, dtInicio, dtFim);
    return testeRepo.findAllFromAquario(idAquario);
  }

  @Override
  public List<Teste> buscarTodosDoAquarioEParametro(Long idAquario, Long idParametro) {
    log.info("Buscando todos testes do aquário ({}) e parâmetro ({})", idAquario, idParametro);
    return testeRepo.findAllFromAquarioEParametro(idAquario, idParametro);
  }

  @Override
  public List<Teste> buscarTodosDoAquarioEParametro(
      Long idAquario, Long idParametro, LocalDateTime dtInicio, LocalDateTime dtFim) {
    log.info(
        "Buscando todos testes do aquário ({}) e parâmetro ({}) no período {} - {}",
        idAquario,
        idParametro,
        dtInicio,
        dtFim);
    return testeRepo.findAllFromAquarioEParametroPorPeriodo(
        idAquario, idParametro, dtInicio, dtFim);
  }

  @Override
  public Teste persistir(Teste teste) throws Exception {
    log.info("Persistindo um teste: {}", teste.toString());

    try {
      Optional<Aquario> aquario = aquarioService.buscar(teste.getAquario().getId());
      Optional<Parametro> parametro = parametroService.buscar(teste.getParametro().getId());

      if (aquario.isEmpty() || parametro.isEmpty()) {
        throw new NotFoundException();
      }
    } catch (Exception e) {
      throw new NotFoundException("Não foi possível localizar o Aquario ou Parâmetro do Teste.");
    }

    return testeRepo.save(teste);
  }

  @Override
  public List<Teste> persistirTodos(List<Teste> testes) throws Exception {
    log.info("Persistindo uma lista de testes");

    HashSet<Long> setAquario = new HashSet<>();
    HashSet<Long> setParametro = new HashSet<>();

    try {
      for (Teste t : testes) {
        setAquario.add(t.getAquario().getId());
        setParametro.add(t.getParametro().getId());
      }

      for (Long id : setAquario) {
        aquarioService.buscar(id).orElseThrow(() -> new NotFoundException());
      }
      for (Long id : setParametro) {
        parametroService.buscar(id).orElseThrow(() -> new Exception());
      }

    } catch (Exception e) {
      throw new NotFoundException(
          "Não foi possível localizar o Aquario ou Parâmetro do Teste: " + e.getMessage());
    }

    return testeRepo.saveAll(testes);
  }

  @Override
  public Teste alterar(Teste teste) throws Exception {
    log.info("Alterando um teste: {}", teste.toString());

    Teste testeDb =
        buscar(teste.getId())
            .orElseThrow(
                () ->
                    new NotFoundException(
                        "Não foi possível localizar o teste. Id: " + teste.getId()));

    teste.setId(testeDb.getId());
    teste.setDtCadastro(testeDb.getDtCadastro());

    return persistir(teste);
  }

  @Override
  public Teste deletar(Long id) throws Exception {
    log.info("Deletando um teste. Id: {}", id);

    Teste teste =
        buscar(id)
            .orElseThrow(
                () -> new NotFoundException("Não foi possível localizar o teste. Id: " + id));

    testeRepo.delete(teste);
    return teste;
  }

  @Override
  public void deletarTodos(List<Teste> testes) {
    log.info("Deletando todos os testes da lista.");
    testeRepo.deleteAll(testes);
  }

  @Override
  public int deletarTodosDoAquarioEParametro(Long idAquario, Long idParametro) throws Exception {
    log.info("Deletando todos os testes do aquário {} e parâmetro {}.", idAquario, idParametro);

    aquarioService
        .buscar(idAquario)
        .orElseThrow(
            () -> new NotFoundException("Não foi possível localizar o aquário. Id: " + idAquario));

    parametroService
        .buscar(idParametro)
        .orElseThrow(
            () ->
                new NotFoundException(
                    "Não foi possível localizar o parâmetro. Id: " + idParametro));

    List<Teste> testes = buscarTodosDoAquarioEParametro(idAquario, idParametro);
    int qtde = testes.size();

    testeRepo.deleteAll(testes);

    return qtde;
  }
}
