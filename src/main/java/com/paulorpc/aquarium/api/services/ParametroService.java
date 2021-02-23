package com.paulorpc.aquarium.api.services;

import java.util.List;
import java.util.Optional;
import com.paulorpc.aquarium.api.entities.Parametro;

public interface ParametroService {
  
  /***
   * Busca parametro pelo id
   *
   * @return Optional<Parametro>
   */
  Optional<Parametro> buscar(Long id);

  /***
   * Busca parâmetro pelo id e recuperar os procedimentos para realização do teste para este parâmetro.
   *
   * @return Optional<Parametro>
   */
  Optional<Parametro> buscarRetornandoProcedimentosTeste(Long id);

  /***
   * Busca todos parâmetros cadastrados
   *
   * @return List<Parametro>
   */
  List<Parametro> buscarTodos();

  /***
   * Busca todos parâmetros cadastrados recuperando todos os procedimentos para realização de testes para o parâmetro.
   *
   * @return List<Parametro>
   */
  List<Parametro> buscarTodosRetornandoProcedimentosTeste();

  /***
   * Busca todos parametros de um determinado aquário.
   *
   * @return List<Parametro>
   */
  List<Parametro> buscarTodosDoAquario(Long idAquario);

  /***
   * Cadastra novo parâmetro
   *
   * @param parametro
   * @return Parametro
   * @throws Exception
   */
  Parametro persistir(Parametro Parametro) throws Exception;

  /***
   * Persiti uma lista de novos parâmetros
   * @param parametros
   * @return List<Parametro>
   * @throws Exception
   */
  List<Parametro> persistirTodos(List<Parametro> parametros) throws Exception;

  /***
   * Cadastra novo parametro a um determinado aquário pelo id do aquário
   *
   * @param parametro
   * @return Parametro
   * @throws Exception
   */
  Parametro persistir(Parametro Parametro, Long aquarioId) throws Exception;

  /***
   * Atualiza cadastro de parâmetro
   *
   * @param Parametro
   * @return Parametro
   * @throws Exception
   */
  Parametro alterar(Parametro Parametro) throws Exception;

  /***
   * Deleta um parâmetro cadastrado pelo id.
   *
   * @param id
   * @return Optional<Parametro>
   */
  Parametro deletar(Long id) throws Exception;

}
