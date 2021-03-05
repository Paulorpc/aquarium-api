package com.paulorpc.aquarium.api.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.paulorpc.aquarium.api.entities.Teste;
import com.paulorpc.aquarium.api.factories.TesteFactory;
import com.paulorpc.aquarium.api.util.DataUtil;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
public class TesteRepositoryTest {

  @Autowired private TesteRepository testesRepo;

  @Autowired private AquarioRepository aquarioRepo;

  @Autowired private ParametroRepository parametroRepo;

  @Test
  public void should_find_all_testes() {
    List<Teste> testes = testesRepo.findAll();
    assertThat(testes).hasSize(15);
  }

  @Test
  public void should_find_teste_by_id() {
    Optional<Teste> parametro = testesRepo.findById(1L);
    assertTrue(parametro.isPresent());
  }

  @Test
  public void should_find_all_testes_from_aquario() {
    List<Teste> testes = testesRepo.findAllFromAquario(1L);
    assertThat(testes).hasSize(12);
  }

  @Test
  public void should_find_all_testes_from_aquario_no_periodo() {
    LocalDateTime fromDate = DataUtil.getLocalDateTime(2021, 01, 1);
    LocalDateTime toDate = DataUtil.getLocalDateTime(2021, 01, 7);
    List<Teste> testes = testesRepo.findAllFromAquario(1L, fromDate, toDate);
    assertThat(testes).hasSize(8);
  }

  @Test
  public void should_find_all_testes_from_aquario_e_parametro() {
    List<Teste> testes = testesRepo.findAllFromAquarioEParametro(1L, 1L);
    assertThat(testes).hasSize(6);
  }

  @Test
  public void should_find_all_testes_from_aquario_e_parametro_no_periodo() {
    LocalDateTime fromDate = DataUtil.getLocalDateTime(2021, 01, 1);
    LocalDateTime toDate = DataUtil.getLocalDateTime(2021, 01, 7);
    List<Teste> testes =
        testesRepo.findAllFromAquarioEParametroPorPeriodo(1L, 1L, fromDate, toDate);
    assertThat(testes).hasSize(5);
  }

  @Test
  public void should_save_one_test() {
    try {
      Teste teste = TesteFactory.INSTANCE.seed();
      teste.setAquario(aquarioRepo.findById(1L).get());
      teste.setParametro(parametroRepo.findById(1L).get());
      Teste testeSaved = testesRepo.save(teste);

      Optional<Teste> testeDb = testesRepo.findById(testeSaved.getId());
      assertTrue(testeDb.isPresent());
      assertNotNull(testeDb.get().getAquario());
      assertNotNull(testeDb.get().getParametro());
      assertEquals(teste.getAquario().getNome(), testeDb.get().getAquario().getNome());
      assertEquals(teste.getParametro().getNome(), testeDb.get().getParametro().getNome());
    } catch (DataIntegrityViolationException e) {
      Assertions.fail();
    }
  }

  @Test
  public void should_not_save_is_missing_atributes() {
    try {
      Teste teste = TesteFactory.INSTANCE.seed();
      teste.setParametro(null);
      teste.setAquario(null);
      testesRepo.save(teste);
      Assertions.fail();
    } catch (DataIntegrityViolationException e) {
    }
  }

  @Test
  public void should_delete_one_parameter() {
    Optional<Teste> teste = testesRepo.findById(1L);
    teste.ifPresent(
        t -> {
          testesRepo.delete(t);
        });

    Optional<Teste> testeDb = testesRepo.findById(1L);
    assertTrue(testeDb.isEmpty());
  }
}
