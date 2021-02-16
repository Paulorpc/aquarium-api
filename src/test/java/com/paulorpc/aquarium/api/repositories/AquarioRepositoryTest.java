package com.paulorpc.aquarium.api.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.paulorpc.aquarium.api.entities.Aquario;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
public class AquarioRepositoryTest {

  @Autowired private AquarioRepository aquarioRepository;

  @Test
  public void should_findAll() {
    List<Aquario> list = aquarioRepository.findAll();
    assertThat(list).hasSize(3);
  }

  @Test
  public void should_findById() {
    Optional<Aquario> aquario = aquarioRepository.findById(1L);
    assertTrue(aquario.isPresent());
  }

  @Test
  public void should_findByIdAndStatusIsTrue() {
    Optional<Aquario> aquario = aquarioRepository.findByIdAndStatusIsTrue(1L);
    assertTrue(aquario.isPresent());
  }

  @Test
  public void should_not_findByIdAndStatusIsTrue() {
    Optional<Aquario> aquario = aquarioRepository.findByIdAndStatusIsTrue(3L);
    assertFalse(aquario.isPresent());
  }

  @Test
  public void should_findByStatusIsTrue() {
    List<Aquario> list = aquarioRepository.findByStatusIsTrue();
    assertThat(list).hasSize(2);
  }

  @Test
  public void should_save() {
    LocalDateTime data = LocalDateTime.now();
    Aquario aquarioNovo = new Aquario();
    aquarioNovo.setNome("aquario");
    aquarioNovo.setDtCadastro(data);
    aquarioNovo.setDtInicio(data.toLocalDate());
    aquarioNovo.setDtFinal(data.toLocalDate());

    try {
      Aquario aquario = aquarioRepository.save(aquarioNovo);
      assertThat(aquario).isNotNull();
    } catch (DataIntegrityViolationException e) {
      Assertions.fail();
    }
  }

  @Test
  public void should_not_save_is_missing_atributes() {
    Aquario aquarioNovo = new Aquario();
    try {
      aquarioRepository.save(aquarioNovo);
      Assertions.fail();
    } catch (DataIntegrityViolationException e) {
    }
  }
}
