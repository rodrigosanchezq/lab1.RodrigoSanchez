package csd230.lab1;

import csd230.lab1.entities.Book;
import csd230.lab1.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testSaveBook() {
        Book book = new Book();
        book.setAuthor("J.K. Rowling");
        book.setISBN("978-3-16-148410-0");
        book.setTitle("Harry Potter and the Philosopher's Stone");
        book.setPrice(19.99);
        book.setDescription("A fantasy novel about a young wizard.");
        book.setCopies(45);
        bookRepository.save(book);
        assertNotNull(book.getId());
    }
}
