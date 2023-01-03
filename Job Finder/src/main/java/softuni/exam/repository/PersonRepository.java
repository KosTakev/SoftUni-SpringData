package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Person;

import java.util.Optional;

// TODO:
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findPersonByFirstName(String firstName);
    Optional<Person> findPersonByEmail(String email);


}
