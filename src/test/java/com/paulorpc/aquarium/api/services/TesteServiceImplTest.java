package com.paulorpc.aquarium.api.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.paulorpc.aquarium.api.entities.Aquario;
import com.paulorpc.aquarium.api.entities.Parametro;
import com.paulorpc.aquarium.api.entities.Teste;
import com.paulorpc.aquarium.api.exceptions.NotFoundException;
import com.paulorpc.aquarium.api.factories.AquarioFactory;
import com.paulorpc.aquarium.api.factories.ParametroFactory;
import com.paulorpc.aquarium.api.factories.TesteFactory;
import com.paulorpc.aquarium.api.repositories.TesteRepository;
import com.paulorpc.aquarium.api.services.impl.TesteServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(
    classes = {
      AquarioService.class,
      ParametroService.class,
      TesteRepository.class,
      TesteServiceImpl.class
    })
@AutoConfigureMockMvc
public class TesteServiceImplTest {

  @Autowired private TesteServiceImpl testeService;

  @MockBean private AquarioService aquarioService;

  @MockBean private ParametroService parametroService;

  @MockBean private TesteRepository testeRepo;

  private void createMocksRepository(List<Teste> testes) {
    testes.forEach(
        t -> {
          long i = 1;
          t.setId(i++);
        });
    when(testeRepo.save(testes.get(0))).thenReturn(testes.get(0));
    when(testeRepo.saveAll(testes)).thenReturn(testes);
  }

  private void createMocksService(Aquario a, Parametro p) {
    when(aquarioService.buscar(1L)).thenReturn(Optional.of(a));
    when(parametroService.buscar(1L)).thenReturn(Optional.of(p));
  }

  private void createMocksService() {
    Aquario a = AquarioFactory.INSTANCE.seed();
    a.setId(1L);

    Parametro p = ParametroFactory.INSTANCE.seed();
    p.setId(1L);

    createMocksService(a, p);
  }

  @Test
  void should_not_persist_test_childs_not_persisted() throws Exception {
    Teste teste = TesteFactory.INSTANCE.seed();
    teste.getAquario().setId(1L);
    teste.getParametro().setId(null);

    createMocksService(teste.getAquario(), teste.getParametro());

    Assertions.assertThrows(
        NotFoundException.class,
        () -> {
          testeService.persistir(teste);
        });
  }

  @Test
  @DirtiesContext
  void should_persist_test() throws Exception {
    Teste teste = TesteFactory.INSTANCE.seed();

    createMocksRepository(List.of(teste));
    createMocksService();

    Optional<Aquario> aquario = aquarioService.buscar(1L);
    Optional<Parametro> parametro = parametroService.buscar(1L);

    teste.setParametro(parametro.get());
    teste.setAquario(aquario.get());

    teste = testeService.persistir(teste);
    assertNotNull(teste);
    assertThat(teste.getId()).isGreaterThan(0);
  }

  @Test
  @DirtiesContext
  void should_persist_tests_list() throws Exception {
    createMocksService();

    List<Teste> testes = TesteFactory.INSTANCE.seed(8);
    Optional<Aquario> aquario = aquarioService.buscar(1L);
    Optional<Parametro> parametro = parametroService.buscar(1L);

    testes.forEach(
        t -> {
          t.setParametro(parametro.get());
          t.setAquario(aquario.get());
        });

    testeService.persistirTodos(testes);
  }

  @Test
  void should_not_persist_tests_list_childs_not_persisted() throws Exception {
    createMocksService();

    List<Teste> testes = TesteFactory.INSTANCE.seed(8);
    Optional<Aquario> aquario = aquarioService.buscar(1L);
    Parametro parametro = ParametroFactory.INSTANCE.seed();

    testes.forEach(
        t -> {
          t.setParametro(parametro);
          t.setAquario(aquario.get());
        });

    Assertions.assertThrows(
        NotFoundException.class,
        () -> {
          testeService.persistirTodos(testes);
        });
  }

  @Test
  void should_delete_all_from_aquario_and_parametro() throws Exception {
    createMocksService();
    testeService.deletarTodosDoAquarioEParametro(1L, 1L);
  }
}
