package com.paulorpc.aquarium.api.services.impl;

import com.paulorpc.aquarium.api.entities.Teste;
import com.paulorpc.aquarium.api.services.TesteService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TesteServiceImpl implements TesteService {

  @Override
  public Optional<Teste> buscar(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Teste> buscarTodos(Optional<Long> parametroId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Teste> buscarTodosDoAquario(Long idAquario, Optional<Long> parametroId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Teste> buscarTodosDoAquarioPorPeriodo(
      Long aquarioId, LocalDate inicio, LocalDate fim, Optional<Long> parametroId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Teste persistir(Teste Teste) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Teste> persistirTodos(List<Teste> testes) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Teste alterar(Teste Teste) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Teste deletar(Long id) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }
}
