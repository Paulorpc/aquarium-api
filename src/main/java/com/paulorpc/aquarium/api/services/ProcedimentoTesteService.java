package com.paulorpc.aquarium.api.services;

import com.paulorpc.aquarium.api.entities.ProcedimentoTeste;
import java.util.List;
import java.util.Optional;

public interface ProcedimentoTesteService {

  /***
   * Busca um procedimento de teste pelo id
   *
   * @return Optional<ProcedimentoTeste>
   */
  Optional<ProcedimentoTeste> buscar(Long id);

  /***
   * Busca todos procedimentos de testes de um aquário ou parâmtro específico.
   * @param id
   * @param clazz Aquario.class ou Parametro.class
   * @return List<ProcedimentoTeste>
   */
  List<ProcedimentoTeste> buscarTodos(Long parametroId, Class<?> clazz);

  /***
   * Cadastra novo procedimento de teste
   *
   * @param procedimento
   * @return ProcedimentoTeste
   * @throws Exception
   */
  ProcedimentoTeste persistir(ProcedimentoTeste procedimento) throws Exception;

  /***
   * Atualiza cadastro de um procedimento de teste
   *
   * @param procedimento
   * @return ProcedimentoTeste
   * @throws Exception
   */
  ProcedimentoTeste alterar(ProcedimentoTeste procedimento) throws Exception;

  /***
   * Deleta um procedimento de teste cadastrado.
   *
   * @param id
   * @return ProcedimentoTeste
   */
  ProcedimentoTeste deletar(Long id) throws Exception;

  /***
   * Deleta todos procedimento de teste de um determinado parâmetro.
   *
   * @param idParametro
   * @return Integer qtde registros deletados
   */
  Integer deletarTodosDoParametro(Long idParametro) throws Exception;

  /***
   * Deleta todos procedimento de teste de um determinado aquário.
   *
   * @param idAquario
   * @return Integer qtde registros deletados
   */
  Integer deletarTodosDoAquario(Long idAquario) throws Exception;
}
