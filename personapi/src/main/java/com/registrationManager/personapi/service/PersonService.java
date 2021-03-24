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

        return createMessageResponse(savedPerson.getId(), "Created Person whith id ");
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

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO); //convertendo DTO para Model
        Person updatedPerson = personRepository.save(personToUpdate);   //salva model no banco

        return createMessageResponse(updatedPerson.getId(), "Updated Person whith id ");

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

    //Metodo para save person.
    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}

