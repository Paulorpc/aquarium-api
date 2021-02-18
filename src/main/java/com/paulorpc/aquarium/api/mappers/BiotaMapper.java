package com.paulorpc.aquarium.api.mappers;

import com.paulorpc.aquarium.api.dtos.BiotaDto;
import com.paulorpc.aquarium.api.entities.Biota;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(
    componentModel = "spring",
    uses = {TaxonomiaMapper.class})
public interface BiotaMapper {

  BiotaMapper INSTANCE = Mappers.getMapper(BiotaMapper.class);

  BiotaDto toBiotaDto(Biota biota);

  @Mapping(target = "deletado", ignore = true)
  Biota toBiota(BiotaDto biotaDto);
}
