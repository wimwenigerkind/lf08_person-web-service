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

    @RequestMapping(value = "/SpringBootCrudService/person",
        method = RequestMethod.GET,
        produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public Iterable<Person> getAllPersons() {
        return this.personRepository.findAll();
    }

    @RequestMapping(value = "/SpringBootCrudService/person/{id}",
        method = RequestMethod.PUT,
        consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public Person updatePerson(@PathVariable long id, @RequestBody Person updatedPerson) {
        Optional<Person> person = this.personRepository.findById(id);
        if (person.isPresent()) {
            Person _person = person.get();
            _person.setFirstname(updatedPerson.getFirstname());
            _person.setLastname(updatedPerson.getLastname());
            return personRepository.save(_person);
        } else {
            throw new RuntimeException("Could not find person " + id);
        }
    }

    @RequestMapping(value = "/SpringBootCrudService/person/{id}",
        method = RequestMethod.DELETE,
        produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public void deletePersonById(@PathVariable long id) {
        this.personRepository.deleteById(id);
    }

    @RequestMapping(value = "/SpringBootCrudService/person/welcome",
        method = RequestMethod.GET,
        produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public String welcome() {
        return "Welcome to the Person Web Service!";
        }
}
