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
   * Busca todos procedimentos de testes. O parâmetroId é opcional, caso tenha sido passado diferente de vazio, então irá filtrar apenas os testes do parâmetro desejado.
   *
   * @return List<ProcedimentoTeste>
   */
  List<ProcedimentoTeste> buscarTodos(Optional<Long> parametroId);

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
   * @param parametroId
   * @return Integer qtde registros deletados
   */
  Integer deletarTodosDoParametro(Long parametroId) throws Exception;
}
