package ru.spring.library.LibraryWithBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spring.library.LibraryWithBoot.models.Person;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {


    Optional<Person> findPersonByName(String name);
}
