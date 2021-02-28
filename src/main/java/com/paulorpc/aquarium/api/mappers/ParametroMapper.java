package com.paulorpc.aquarium.api.mappers;

import com.paulorpc.aquarium.api.dtos.ParametroDto;
import com.paulorpc.aquarium.api.entities.Parametro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    uses = {ProcedimentoTesteMapper.class})
public interface ParametroMapper {

  @Mapping(target = "historicoTestes", ignore = true)
  @Mapping(source = "aquarioId", target = "aquario.id")
  public Parametro toParametro(ParametroDto dto);

  @Mapping(source = "aquario.id", target = "aquarioId")
  public ParametroDto toParametroDto(Parametro entidade);
}
