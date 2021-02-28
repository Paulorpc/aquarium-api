package com.paulorpc.aquarium.api.mappers;

import com.paulorpc.aquarium.api.dtos.ProcedimentoTesteDto;
import com.paulorpc.aquarium.api.entities.ProcedimentoTeste;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProcedimentoTesteMapper {

  ProcedimentoTesteMapper INSTANCE = Mappers.getMapper(ProcedimentoTesteMapper.class);

  @Mapping(target = "parametro", ignore = true)
  public ProcedimentoTeste toProcedimentoTeste(ProcedimentoTesteDto dto);

  public ProcedimentoTesteDto toProcedimentoTesteDto(ProcedimentoTeste entidade);
}
