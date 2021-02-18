package com.paulorpc.aquarium.api.mappers;

import com.paulorpc.aquarium.api.dtos.TaxonomiaDto;
import com.paulorpc.aquarium.api.entities.Taxonomia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaxonomiaMapper {

  TaxonomiaDto toTaxonomiaDto(Taxonomia taxonomia);

  @Mapping(target = "biota", ignore = true)
  @Mapping(target = "id", ignore = true)
  Taxonomia toTaxonomia(TaxonomiaDto taxonomiaDto);
}
