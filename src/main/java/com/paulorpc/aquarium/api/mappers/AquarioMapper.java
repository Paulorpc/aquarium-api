package com.paulorpc.aquarium.api.mappers;

import com.paulorpc.aquarium.api.dtos.AquarioDto;
import com.paulorpc.aquarium.api.entities.Aquario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AquarioMapper {

  AquarioMapper INSTANCE = Mappers.getMapper(AquarioMapper.class);

  @Mapping(target = "foto", ignore = true)
  @Mapping(source = "tipoAquario.id", target = "idTipoAquario")
  AquarioDto toAquarioDto(Aquario aquario);

  @Mapping(target = "equipamentos", ignore = true)
  @InheritInverseConfiguration
  Aquario toAquario(AquarioDto aquarioDto);
}
