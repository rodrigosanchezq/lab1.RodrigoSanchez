package csd230.lab1;

import csd230.lab1.entities.Book;
import csd230.lab1.pojos.Cart;
import csd230.lab1.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) {
		// Insert test data
		// Create a new Book object and save it to the database
		Book book = new Book ();
		book.setAuthor("Rodrigo Sanchez");
		book.setISBN("978-3-16-148410-0");
		book.setTitle("Spring Boot in Action");
		book.setCopies(5);
		book.setPrice(29.99);
		book.setQuantity(10);
		book.setDescription("A comprehensive guide to Spring Boot.");
		Cart cart = new Cart();
		bookRepository.save(book);

		// Query test data
		bookRepository.findAll().forEach(System.out::println);
	}
}
