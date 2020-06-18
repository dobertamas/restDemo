package com.tamasdober.demo.api;

import com.tamasdober.demo.model.Person;
import com.tamasdober.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

// corresponding to the request URL localhost:8080/api/v1/person/
@RequestMapping("api/v1/person")
// To expose endpoints among others
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {

        this.personService = personService;
    }

    @PostMapping
    public int addPerson(
        @NonNull
        @RequestBody
            Person person) {

        return personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople() {

        return personService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    public Person findPersonById(
        @PathVariable("id")
            UUID id) {

        return personService.findPersonById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public int deletePersonById(
        @PathVariable("id")
            UUID id) {

        return personService.deletePersonById(id);

    }

    @PutMapping(path = "{id}")
    public int updatePersonById(
        @PathVariable("id")
            UUID id,
        @NonNull
        @RequestBody
            Person personToUpdate) {

        return personService.updatePersonById(id, personToUpdate);

    }
}
