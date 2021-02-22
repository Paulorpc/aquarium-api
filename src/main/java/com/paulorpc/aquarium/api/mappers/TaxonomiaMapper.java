package com.paulorpc.aquarium.api.mappers;

import com.paulorpc.aquarium.api.dtos.TaxonomiaDto;
import com.paulorpc.aquarium.api.entities.Taxonomia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaxonomiaMapper {

  TaxonomiaMapper INSTANCE = Mappers.getMapper(TaxonomiaMapper.class);

  TaxonomiaDto toTaxonomiaDto(Taxonomia taxonomia);

  @Mapping(target = "id", ignore = true)
  Taxonomia toTaxonomia(TaxonomiaDto taxonomiaDto);
}
