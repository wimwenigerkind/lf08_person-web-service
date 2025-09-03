package group.wimdev.lf08_personwebservice.controller;

import group.wimdev.lf08_personwebservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository personRepository;
}
