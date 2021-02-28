package com.paulorpc.aquarium.api.factories;

import com.github.javafaker.Faker;
import com.paulorpc.aquarium.api.dtos.ParametroDto;
import com.paulorpc.aquarium.api.entities.Parametro;

public class ParametroFactory implements BaseSeeder<Parametro, ParametroDto> {

  public static ParametroFactory INSTANCE = new ParametroFactory();

  Faker faker = new Faker();
  private String name;
  private Double escalaInicio;
  private Double vlrIdealInicio;

  @Override
  public Parametro seed() {
    name = faker.funnyName().name();
    escalaInicio = faker.number().randomDouble(2, 600, 1200);
    vlrIdealInicio = faker.number().randomDouble(2, 600, 1200);

    return Parametro.builder()
        .nome(name)
        .abreviacaoNome(name.substring(0, 2))
        .escalaInicio(escalaInicio)
        .escalaFim(faker.number().randomDouble(2, escalaInicio.intValue() + 1, 1200))
        .unidadeMedida("?")
        .vlrIdealInicio(vlrIdealInicio)
        .vlrIdealFim(faker.number().randomDouble(2, vlrIdealInicio.intValue() + 1, 1200))
        .build();
  }

  public ParametroDto seedDto() {
    name = faker.funnyName().name();
    escalaInicio = faker.number().randomDouble(2, 600, 1200);
    vlrIdealInicio = faker.number().randomDouble(2, 600, 1200);

    return ParametroDto.builder()
        .nome(name)
        .abreviacaoNome(name.substring(0, 2))
        .escalaInicio(escalaInicio)
        .escalaFim(faker.number().randomDouble(2, escalaInicio.intValue() + 1, 1200))
        .unidadeMedida("?")
        .vlrIdealInicio(vlrIdealInicio)
        .vlrIdealFim(faker.number().randomDouble(2, vlrIdealInicio.intValue() + 1, 1200))
        .build();
  }
}
