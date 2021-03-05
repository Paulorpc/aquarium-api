package com.paulorpc.aquarium.api.services;

import com.paulorpc.aquarium.api.entities.Teste;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TesteService {

  /***
   * Busca teste pelo id
   *
   * @return Optional<Teste>
   */
  Optional<Teste> buscar(Long id);

  /***
   * Buscar todo histórico de testes de um aquário
   *
   * @param idAquario
   * @return List<Teste>
   */
  List<Teste> buscarTodosDoAquario(Long idAquario);

  /***
   * Buscar todo histórico de testes de um aquário sendo possível especificar um período de pesquisa.
   *
   * @param idAquario
   * @return List<Teste>
   */
  List<Teste> buscarTodosDoAquario(Long idAquario, LocalDateTime dtInicio, LocalDateTime dtFim);

  /***
   * Busca todos testes de um determinado aquário e parâmetro específico.
   *
   * @return List<Teste>
   */
  List<Teste> buscarTodosDoAquarioEParametro(Long idAquario, Long idParametro);

  /***
   * Busca todos testes de um determinado aquário e parâmetro específico sendo possível especificar um período de pesquisa.
   *
   * @return List<Teste>
   */
  List<Teste> buscarTodosDoAquarioEParametro(
      Long idAquario, Long idParametro, LocalDateTime dtInicio, LocalDateTime dtFim);

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

  /***
   * Deleta todos testes da lista.
   *
   * @param testes
   */
  void deletarTodos(List<Teste> testes);

  /***
   * Deleta todos testes de um aquário e parâmetro específicos.
   *
   * @param int qtde de registros deletados
   */
  int deletarTodosDoAquarioEParametro(Long idAquario, Long idParametro) throws Exception;
}
