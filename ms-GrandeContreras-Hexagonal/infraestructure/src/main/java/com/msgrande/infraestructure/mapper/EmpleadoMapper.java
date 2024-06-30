package com.msgrande.infraestructure.mapper;

import com.msgrande.domain.aggregates.dto.EmpleadoDto;
import com.msgrande.infraestructure.entity.EmpleadoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public EmpleadoDto mapToDto(EmpleadoEntity empleado){
        return modelMapper.map(empleado, EmpleadoDto.class);
    }

    public EmpleadoEntity mapToEntity(EmpleadoDto empleadoDto){
        return modelMapper.map(empleadoDto, EmpleadoEntity.class);
    }

    public List<EmpleadoDto> mapToDtoList(List<EmpleadoEntity> empleadoEntities){
        return empleadoEntities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

}
