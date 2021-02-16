package com.paulorpc.aquarium.api.services;

import com.paulorpc.aquarium.api.entities.Biota;
import java.util.List;
import java.util.Optional;

public interface BiotaService {

  /***
   * Busca seres vivos pelo id
   *
   * @return List<Biota>
   */
  Optional<Biota> buscar(Long id);

  /***
   * Busca todos seres vivos cadastrados
   *
   * @return List<Biota>
   */
  List<Biota> buscarTodos();

  /***
   * Cadastra novo ser vivo
   *
   * @param biota
   * @return Biota
   * @throws Exception
   */
  Biota persistir(Biota biota) throws Exception;

  /***
   * Atualiza cadastro de aquário
   *
   * @param biota
   * @return Biota
   * @throws Exception
   */
  // TODO corrigir o parametro para int
  Optional<Biota> alterar(Biota biota) throws Exception;

  /***
   * Deleta um Biota cadastrado. Status é alterado para FALSE.
   *
   * @param id
   * @return Optional<Biota>
   */
  Optional<Biota> deletar(Long id);
}
