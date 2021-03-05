package com.paulorpc.aquarium.api.factories;

import com.github.javafaker.Faker;
import com.paulorpc.aquarium.api.dtos.TesteDto;
import com.paulorpc.aquarium.api.entities.Aquario;
import com.paulorpc.aquarium.api.entities.Parametro;
import com.paulorpc.aquarium.api.entities.Teste;

public class TesteFactory implements BaseSeeder<Teste, TesteDto> {

  public static TesteFactory INSTANCE = new TesteFactory();

  Faker fake = new Faker();

  @Override
  public Teste seed() {
    Parametro parametro = ParametroFactory.INSTANCE.seed();
    Aquario aquario = AquarioFactory.INSTANCE.seed();

    Teste teste =
        Teste.builder()
            .observacao(fake.lorem().sentence())
            .unidadeMedida("ppm")
            .vlrResultado(fake.number().randomDouble(2, 0, 1000))
            .parametro(parametro)
            .aquario(aquario)
            .build();

    parametro.addHistoricoTeste(teste);
    return teste;
  }

  @Override
  public TesteDto seedDto() {
    // TODO Auto-generated method stub
    return null;
  }
}
