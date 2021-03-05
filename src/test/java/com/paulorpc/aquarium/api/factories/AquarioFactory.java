package com.paulorpc.aquarium.api.factories;

import com.github.javafaker.Faker;
import com.paulorpc.aquarium.api.dtos.AquarioDto;
import com.paulorpc.aquarium.api.entities.Aquario;
import java.time.LocalDate;

public class AquarioFactory implements BaseSeeder<Aquario, AquarioDto> {

  public static AquarioFactory INSTANCE = new AquarioFactory();

  Faker fake = new Faker();

  @Override
  public Aquario seed() {
    return Aquario.builder()
        .nome("Aquario " + fake.superhero().name())
        .dtInicio(LocalDate.now().minusDays(30))
        .tamanho(
            fake.number().randomNumber(2, false)
                + " x "
                + fake.number().randomNumber(2, false)
                + " x "
                + fake.number().randomNumber(2, false))
        .iluminacao("Iluminação " + fake.lorem().fixedString(15))
        .observacao(fake.lorem().sentence(6))
        .sistemaCO2(fake.superhero().power())
        .substrato("Substrato " + fake.food().dish())
        .status(true)
        .tipoAgua("salgada")
        .volume(200)
        .build();
  }

  @Override
  public AquarioDto seedDto() {
    return AquarioDto.builder()
        .nome("Aquario " + fake.superhero().name())
        .dtInicio(LocalDate.now().minusDays(30))
        .tamanho(
            fake.number().randomNumber(2, false)
                + " x "
                + fake.number().randomNumber(2, false)
                + " x "
                + fake.number().randomNumber(2, false))
        .iluminacao("Iluminação " + fake.lorem().fixedString(15))
        .observacao(fake.lorem().sentence(6))
        .sistemaCO2(fake.superhero().power())
        .substrato("Substrato " + fake.food().dish())
        .status(true)
        .tipoAgua("salgada")
        .volume(200)
        .build();
  }
}
