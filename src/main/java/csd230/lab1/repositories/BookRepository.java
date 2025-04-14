package csd230.lab1.repositories;

import csd230.lab1.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // This method will find books by their isbn number and return them as a list
    List<Book> findByIsbn(String isbn);

    // This method will find books by their id and return them as a single object
    Book findById(long id);

    // This method will find books by price range and return them as a list
    @Query("SELECT b FROM Book b WHERE b.price BETWEEN :min AND :max")
    List<Book> findByPriceRange(double min, double max);

    @Query("SELECT b FROM Book b WHERE b.author LIKE %:title%")
    List<Book> findByAuthorLike(String author);



}