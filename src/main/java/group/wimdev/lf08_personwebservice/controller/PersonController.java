package group.wimdev.lf08_personwebservice.controller;

import group.wimdev.lf08_personwebservice.model.Person;
import group.wimdev.lf08_personwebservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

        @RequestMapping(value = "/SpringBootCrudService/person",
        method = RequestMethod.POST,
        consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public Person createPerson(@RequestBody Person newPerson) {
        return personRepository.save(newPerson);
    }

    @RequestMapping(value = "/SpringBootCrudService/person/{id}",
        method = RequestMethod.GET,
        produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public Person getPersonById(@PathVariable long id) {
        Optional<Person> person = this.personRepository.findById(id);
        if (person.isPresent()) {
            return person.get();
        } else {
            throw new RuntimeException("Could not find person " + id);
        }
    }
}
