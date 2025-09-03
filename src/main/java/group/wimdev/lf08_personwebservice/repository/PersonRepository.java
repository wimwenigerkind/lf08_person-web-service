package group.wimdev.lf08_personwebservice.repository;

import group.wimdev.lf08_personwebservice.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT p FROM Person p WHERE LOWER(p.firstname) = LOWER(:firstname) AND LOWER(p.lastname) = LOWER(:lastname)")
    List<Person> findByFirstnameAndLastname(@Param("firstname") String firstname, @Param("lastname") String lastname);
    
    @Query("SELECT p FROM Person p WHERE LOWER(p.firstname) = LOWER(:firstname)")
    List<Person> findByFirstname(@Param("firstname") String firstname);
    
    @Query("SELECT p FROM Person p WHERE LOWER(p.lastname) = LOWER(:lastname)")
    List<Person> findByLastname(@Param("lastname") String lastname);
}