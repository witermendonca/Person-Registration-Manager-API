package com.registrationManager.personapi.controller;

import com.registrationManager.personapi.dto.request.PersonDTO;
import com.registrationManager.personapi.dto.response.MessageResponseDTO;
import com.registrationManager.personapi.entity.Person;
import com.registrationManager.personapi.repository.PersonRepository;
import com.registrationManager.personapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private final PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
        return personService.createdPerson(personDTO);
    }

    @GetMapping
    public List<PersonDTO>  listAll(){
       return personService.listAll();
    }


}