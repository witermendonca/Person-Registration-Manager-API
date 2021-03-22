package com.registrationManager.personapi.service;

import com.registrationManager.personapi.dto.request.PersonDTO;
import com.registrationManager.personapi.dto.response.MessageResponseDTO;
import com.registrationManager.personapi.entity.Person;
import com.registrationManager.personapi.mapper.PersonMapper;
import com.registrationManager.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO createdPerson(PersonDTO personDTO){

        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);

        return MessageResponseDTO.builder().message("Created Person whith id " + savedPerson.getId()).build();
    }


    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }
}

