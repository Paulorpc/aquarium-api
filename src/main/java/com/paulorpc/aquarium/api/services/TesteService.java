package com.paulorpc.aquarium.api.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.paulorpc.aquarium.api.entities.Teste;

public interface TesteService {
  
  /***
   * Busca teste pelo id
   *
   * @return Optional<Teste>
   */
  Optional<Teste> buscar(Long id);
  
  /***
   * Busca todos testes cadastrados
   *
   * @return List<Teste>
   */
  List<Teste> buscarTodos();
  
  /***
   * Busca todos testes de um determinado aquário.
   *
   * @return List<Teste>
   */
  List<Teste> buscarTodosDoAquario(Long idAquario);
  
  /***
   * Busca todos os testes em um determinado aquário em um período desejado. Se o parametroId for diferente de null, então irá filtrar apenas os testes do parâmetro informado.
   *
   * @return List<Teste>
   */
  List<Teste> buscarTodosDoAquarioPorPeriodo(Long aquarioId, LocalDate inicio, LocalDate fim, Long parametroId);
  
  /***
   * Cadastra novo teste
   *
   * @param teste
   * @return Teste
   * @throws Exception
   */
  Teste persistir(Teste Teste) throws Exception;

  /***
   * Persiti uma lista de novos testes
   * @param testes
   * @return List<Teste>
   * @throws Exception
   */
  List<Teste> persistirTodos(List<Teste> testes) throws Exception;

  /***
   * Atualiza cadastro de teste
   *
   * @param Teste
   * @return Teste
   * @throws Exception
   */
  Teste alterar(Teste Teste) throws Exception;

  /***
   * Deleta um teste cadastrado pelo id.
   *
   * @param id
   * @return Optional<Teste>
   */
  Teste deletar(Long id) throws Exception;

}
