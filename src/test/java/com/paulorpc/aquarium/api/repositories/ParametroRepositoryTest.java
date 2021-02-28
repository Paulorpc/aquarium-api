package com.paulorpc.aquarium.api.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.paulorpc.aquarium.api.entities.Parametro;
import com.paulorpc.aquarium.api.entities.ProcedimentoTeste;
import com.paulorpc.aquarium.api.factories.ParametroFactory;
import com.paulorpc.aquarium.api.factories.ProcedimentoTesteFactory;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
public class ParametroRepositoryTest {

  @Autowired private ParametroRepository parametroRep;

  @Autowired private ProcedimentoTesteRepository procedimentosRep;

  @Test
  public void should_find_all_parametros() {
    List<Parametro> parametros = parametroRep.findAll();
    assertThat(parametros).hasSize(8);
  }

  @Test
  public void should_find_parametro_by_id() {
    Optional<Parametro> parametro = parametroRep.findById(1L);
    assertTrue(parametro.isPresent());
  }

  @Test
  public void should_find_all_parametros_from_aquario() {
    List<Parametro> parametros = parametroRep.findAllFromAquario(1L);
    assertThat(parametros).hasSize(5);
  }

  @Test
  public void should_find_all_parametros_from_aquario_retrieving_procedimentosTeste() {
    List<Parametro> parametros = parametroRep.findAllFromAquarioRetrieveProcedimentosTeste(1L);
    parametros = parametros.stream().distinct().collect(Collectors.toList());
    assertThat(parametros).hasSize(5);

    parametros = parametros.stream().filter(p -> p.getId().equals(2L)).collect(Collectors.toList());
    assertThat(parametros.get(0).getProcedimentosTeste()).hasSize(3);
  }

  @Test
  public void should_save() {
    try {
      Parametro parametro = ParametroFactory.INSTANCE.seed();
      List<ProcedimentoTeste> procedimentos = ProcedimentoTesteFactory.INSTANCE.seed(3);
      procedimentos.forEach(p -> parametro.addProcedimentoTeste(p));

      Parametro parametroSaved = parametroRep.save(parametro);
      procedimentosRep.saveAll(procedimentos);

      Optional<Parametro> parametroDb = parametroRep.findById(parametroSaved.getId());
      assertTrue(parametroDb.isPresent());
      assertEquals(parametroDb.get().getProcedimentosTeste().size(), procedimentos.size());
    } catch (DataIntegrityViolationException e) {
      Assertions.fail();
    }
  }

  @Test
  public void should_not_save_is_missing_atributes() {
    try {
      Parametro parametro = ParametroFactory.INSTANCE.seed();
      parametro.setNome(null);
      Parametro parametroSaved = parametroRep.save(parametro);
      Optional<Parametro> parametroDb = parametroRep.findById(parametroSaved.getId());
      assertTrue(parametroDb.isEmpty());
      Assertions.fail();
    } catch (DataIntegrityViolationException e) {
    }
  }

  @Test
  public void should_delete_one_parameter() {
    Optional<Parametro> parametro = parametroRep.findByIdRetrieveProcedimentosTeste(1L);
    parametro.ifPresent(
        p -> {
          procedimentosRep.deleteAll(p.getProcedimentosTeste());
          p.setProcedimentosTeste(null);
          parametroRep.delete(p);
        });

    Optional<Parametro> parametroDb = parametroRep.findById(1L);
    assertTrue(parametroDb.isEmpty());
  }

  @Test
  public void should_delete_all_from_aquario() {
    List<Parametro> parametros = parametroRep.findAllFromAquario(1L);
    Integer qtdeTuplasBef = parametros.size();

    parametros.forEach(p -> procedimentosRep.deleteAll(p.getProcedimentosTeste()));
    parametroRep.deleteAll(parametros);
    Integer qtdeTuplasAft = parametroRep.findAllFromAquario(1L).size();

    assertEquals(5, qtdeTuplasBef);
    assertEquals(0, qtdeTuplasAft);
  }
}
