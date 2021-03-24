package com.registrationManager.personapi.mapper;

import com.registrationManager.personapi.dto.request.PersonDTO;
import com.registrationManager.personapi.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy") //mapeando birthData fomatada.
    Person toModel(PersonDTO personDTO);  //converter DTO para Model

    PersonDTO toDTO(Person person);       //conveter model para DTO
}
