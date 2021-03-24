package com.registrationManager.personapi.service;

import com.registrationManager.personapi.dto.request.PersonDTO;
import com.registrationManager.personapi.dto.response.MessageResponseDTO;
import com.registrationManager.personapi.entity.Person;
import com.registrationManager.personapi.exception.PersonNotFoundException;
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

        Person personToSave = personMapper.toModel(personDTO); //convertendo DTO para Model
        Person savedPerson = personRepository.save(personToSave);   //salva model no banco

        return MessageResponseDTO.builder().message("Created Person whith id " + savedPerson.getId()).build();
    }


    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)     //converte cada item para DTO
                .collect(Collectors.toList());     //retorna uma List
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {

       Person person = verifyIfExists(id);

       // Optional<Person> optionalPerson = personRepository.findById(id);
       // if(optionalPerson.isEmpty()){
       //     throw new PersonNotFoundException(id) ;
       // }

       // return personMapper.toDTO(optionalPerson.get());

        return personMapper.toDTO(person);
    }



    public void delete(Long id) throws PersonNotFoundException {
      verifyIfExists(id);

       personRepository.deleteById(id);

    }

    //Metodo para verificar se um id existe.
    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }
}

