package group.wimdev.lf08_personwebservice.repository;

import group.wimdev.lf08_personwebservice.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
