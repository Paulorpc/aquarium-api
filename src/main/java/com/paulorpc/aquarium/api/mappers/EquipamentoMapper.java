package com.paulorpc.aquarium.api.mappers;

import com.paulorpc.aquarium.api.dtos.EquipamentoDto;
import com.paulorpc.aquarium.api.entities.Equipamento;
import javax.transaction.Transactional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(
    componentModel = "spring",
    uses = {AquarioMapper.class})
@Transactional
public interface EquipamentoMapper {

  EquipamentoMapper INSTANCE = Mappers.getMapper(EquipamentoMapper.class);

  public EquipamentoDto toEquipamentoDto(Equipamento equipamento);

  @Mapping(target = "aquarios", ignore = true)
  public EquipamentoDto toEquipamentoDtoIgnoreAquarios(Equipamento equipamento);

  public Equipamento toEquipamento(EquipamentoDto equipamentoDto);
}
