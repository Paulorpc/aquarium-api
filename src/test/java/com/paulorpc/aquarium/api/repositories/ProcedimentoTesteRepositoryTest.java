package com.paulorpc.aquarium.api.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.paulorpc.aquarium.api.entities.Parametro;
import com.paulorpc.aquarium.api.entities.ProcedimentoTeste;
import com.paulorpc.aquarium.api.factories.ParametroFactory;
import com.paulorpc.aquarium.api.factories.ProcedimentoTesteFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
public class ProcedimentoTesteRepositoryTest {

  @Autowired private ProcedimentoTesteRepository procedimentoRep;

  @Autowired private ParametroRepository parametroRep;

  @Test
  public void should_find_all_procedimentos() {
    List<ProcedimentoTeste> procedimentos = procedimentoRep.findAll();
    assertThat(procedimentos).hasSize(5);
  }

  @Test
  public void should_find_procedimento_by_id() {
    Optional<ProcedimentoTeste> procedimento = procedimentoRep.findById(1L);
    assertTrue(procedimento.isPresent());
  }

  @Test
  public void should_find_all_procedimentos_from_parametro() {
    List<ProcedimentoTeste> procedimentos = procedimentoRep.findAllByParametroId(2L);
    assertThat(procedimentos).hasSize(3);
  }

  @Test
  public void should_save() {
    try {
      List<ProcedimentoTeste> procedimentos = new ArrayList<>();
      Parametro parametro = parametroRep.save(ParametroFactory.seed());

      procedimentos.addAll(
          List.of(
              ProcedimentoTesteFactory.seed(),
              ProcedimentoTesteFactory.seed(),
              ProcedimentoTesteFactory.seed()));
      procedimentos.forEach(p -> p.setParametro(parametro));

      List<ProcedimentoTeste> procedimentosDb = procedimentoRep.saveAll(procedimentos);
      assertThat(procedimentosDb).isNotNull();
      assertEquals(procedimentos.size(), procedimentosDb.size());
    } catch (DataIntegrityViolationException e) {
      Assertions.fail();
    }
  }

  @Test
  public void should_not_save_is_missing_atributes() {
    try {
      List<ProcedimentoTeste> procedimentos = new ArrayList<>();

      procedimentos.addAll(
          List.of(
              ProcedimentoTesteFactory.seed(),
              ProcedimentoTesteFactory.seed(),
              ProcedimentoTesteFactory.seed()));

      List<ProcedimentoTeste> procedimentosDb = procedimentoRep.saveAll(procedimentos);
      assertThat(procedimentosDb).isNotNull();
      assertEquals(procedimentos.size(), procedimentosDb.size());
      Assertions.fail();
    } catch (DataIntegrityViolationException e) {
    }
  }

  @Test
  public void should_delete_all_from_parametro() {
    List<ProcedimentoTeste> procedimentos = procedimentoRep.findAllByParametroId(1L);
    Integer qtdeTuplasBef = procedimentos.size();
    procedimentoRep.deleteAll(procedimentos);
    Integer qtdeTuplasAft = procedimentoRep.findAllByParametroId(1L).size();

    assertEquals(2, qtdeTuplasBef);
    assertEquals(0, qtdeTuplasAft);
  }
}
