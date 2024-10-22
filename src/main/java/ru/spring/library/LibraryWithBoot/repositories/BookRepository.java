package ru.spring.library.LibraryWithBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spring.library.LibraryWithBoot.models.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByTitleContainingIgnoreCase(String startingWith);




//    Optional<Book> sortByYear();findByTitleStartingWith


}
