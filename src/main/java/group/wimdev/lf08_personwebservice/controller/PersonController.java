package group.wimdev.lf08_personwebservice.controller;

import group.wimdev.lf08_personwebservice.model.Person;
import group.wimdev.lf08_personwebservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
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
            method = RequestMethod.PATCH,
            consumes = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    public Person patchPerson(@PathVariable long id, @RequestBody Person updatedPerson) {
        Optional<Person> person = this.personRepository.findById(id);
        if (person.isPresent()) {
            Person _person = person.get();
            if (updatedPerson.getFirstname() != null) {
                _person.setFirstname(updatedPerson.getFirstname());
            }
            if (updatedPerson.getLastname() != null) {
                _person.setLastname(updatedPerson.getLastname());
            }
            return personRepository.save(_person);
        }
        return updatedPerson;
    }

    @RequestMapping(value = "/SpringBootCrudService/person/{id}",
            method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    public void deletePersonById(@PathVariable long id) {
        this.personRepository.deleteById(id);
    }

    @RequestMapping(value = "/",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Object apiDocumentation(HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + 
                        (request.getServerPort() == 80 || request.getServerPort() == 443 ? "" : ":" + request.getServerPort());
        
        return Map.of(
                "service", "Person Web Service API",
                "version", "1.0.0",
                "description", "REST API for managing persons with CRUD operations and search functionality",
                "base_url", baseUrl,
                "endpoints", Map.of(
                        "GET /SpringBootCrudService/person/welcome", "Welcome message",
                        "GET /SpringBootCrudService/person", "Get all persons",
                        "GET /SpringBootCrudService/person/{id}", "Get person by ID",
                        "POST /SpringBootCrudService/person", "Create new person",
                        "PUT /SpringBootCrudService/person/{id}", "Update person (full)",
                        "PATCH /SpringBootCrudService/person/{id}", "Update person (partial)",
                        "DELETE /SpringBootCrudService/person/{id}", "Delete person",
                        "GET /SpringBootCrudService/person/search", "Search persons by firstname/lastname (case-insensitive)"
                ),
                "examples", Map.of(
                        "create_person", Map.of(
                                "method", "POST",
                                "url", "/SpringBootCrudService/person",
                                "body", Map.of("firstname", "John", "lastname", "Doe"),
                                "curl", "curl -X POST " + baseUrl + "/SpringBootCrudService/person -H 'Content-Type: application/json' -d '{\"firstname\":\"John\",\"lastname\":\"Doe\"}'"
                        ),
                        "search_person", Map.of(
                                "method", "GET",
                                "url", "/SpringBootCrudService/person/search?firstname=john",
                                "curl", "curl '" + baseUrl + "/SpringBootCrudService/person/search?firstname=john'"
                        ),
                        "update_person", Map.of(
                                "method", "PATCH",
                                "url", "/SpringBootCrudService/person/1",
                                "body", Map.of("firstname", "Jane"),
                                "curl", "curl -X PATCH " + baseUrl + "/SpringBootCrudService/person/1 -H 'Content-Type: application/json' -d '{\"firstname\":\"Jane\"}'"
                        )
                ),
                "features", List.of(
                        "Case-insensitive exact search",
                        "JSON and XML support",
                        "Full CRUD operations",
                        "In-memory H2 database",
                        "Spring Boot + JPA"
                ),
                "author", "Wim Wenigerkind",
                "repository", "lf08_person-web-service"
        );
    }

    @RequestMapping(value = "/SpringBootCrudService/person/welcome",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    public String welcome() {
        return "Welcome to the Person Web Service!";
    }

    @RequestMapping(value = "/SpringBootCrudService/person/search",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Iterable<Person> searchPersons(@RequestParam(required = false) String firstname,
                                          @RequestParam(required = false) String lastname) {
        if (firstname != null && lastname != null) {
            return personRepository.findByFirstnameAndLastname(firstname, lastname);
        } else if (firstname != null) {
            return personRepository.findByFirstname(firstname);
        } else if (lastname != null) {
            return personRepository.findByLastname(lastname);
        } else {
            return personRepository.findAll();
        }
    }
}
