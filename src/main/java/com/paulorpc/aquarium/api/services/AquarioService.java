package com.paulorpc.aquarium.api.services;

import com.paulorpc.aquarium.api.entities.Aquario;
import java.util.List;
import java.util.Optional;

public interface AquarioService {

  /***
   * Busca aquarios pelo id
   *
   * @return List<Aquario>
   */
  Optional<Aquario> buscar(Long id);

  /***
   * Busca todos aquarios cadastrados
   *
   * @return List<Aquario>
   */
  List<Aquario> buscarTodos();

  /***
   * Cadastra novo aquário
   *
   * @param aquario
   * @return Aquario
   * @throws Exception
   */
  Aquario persistir(Aquario aquario) throws Exception;

  /***
   * Atualiza cadastro de aquário
   *
   * @param aquario
   * @return Aquario
   * @throws Exception
   */
  Aquario alterar(Aquario aquario) throws Exception;

  /***
   * Deleta um aquario cadastrado. Status é alterado para FALSE.
   *
   * @param id
   * @return Optional<Aquario>
   */
  Aquario deletar(Long id) throws Exception;
}
