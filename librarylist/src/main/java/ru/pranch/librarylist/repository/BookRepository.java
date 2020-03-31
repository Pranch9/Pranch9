package ru.pranch.librarylist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pranch.librarylist.model.Book;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

}
