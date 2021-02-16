package com.paulorpc.aquarium.api.mappers;

import com.paulorpc.aquarium.api.dtos.AquarioDto;
import com.paulorpc.aquarium.api.entities.Aquario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AquarioMapper {

  AquarioMapper INSTANCE = Mappers.getMapper(AquarioMapper.class);

  @Mapping(target = "foto", ignore = true)
  @Mapping(source = "tipoAquario.id", target = "idTipoAquario")
  AquarioDto toAquarioDto(Aquario aquario);

  @Mapping(source = "idTipoAquario", target = "tipoAquario.id")
  Aquario toAquario(AquarioDto aquarioDto);
}
