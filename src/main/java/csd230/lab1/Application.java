package csd230.lab1;

import csd230.lab1.entities.*;
import csd230.lab1.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.github.javafaker.Faker;


//
//@SpringBootApplication
//public class Application implements CommandLineRunner {
//	@Autowired
//	private BookRepository bookRepository;
//
//	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);
//	}
//
//	@Override
//	public void run(String... args) {
//		// Insert test data
//		// Create a new Book object and save it to the database
//		Book book = new Book ();
//		book.setAuthor("Rodrigo Sanchez");
//		book.setISBN("978-3-16-148410-0");
//		book.setTitle("Spring Boot in Action");
//		book.setCopies(5);
//		book.setPrice(29.99);
//		book.setQuantity(10);
//		book.setDescription("A comprehensive guide to Spring Boot.");
//		Cart cart = new Cart();
//		bookRepository.save(book);
//
//		// Query test data
//		bookRepository.findAll().forEach(System.out::println);
//	}
//}
@SpringBootApplication
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	CartItemRepository cartItemRepository;

	@Autowired
	CartRepository cartRepository;
	@Autowired
	private PublicationRepository publicationRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	public CommandLineRunner demo(BookRepository bookRepository, MagazineRepository magazineRepository, TicketRepository ticketRepository, DiscMagRepository discMagRepository, CartItemRepository cartItemRepository, PublicationRepository publicationRepository) {
		return (args) -> {
			csd230.lab1.entities.Cart cart = new csd230.lab1.entities.Cart();
			cartRepository.save(cart);

			Faker faker = new Faker();

			createBooks(bookRepository, cart, faker);
			readBooks(bookRepository);
			updateBook(bookRepository, 1L);
			deleteBook(bookRepository, 2L);

			createMagazines(magazineRepository, faker); // Create
			readMagazines(magazineRepository);          // Read
			updateMagazine(magazineRepository, 7L);     // Update
			deleteMagazine(magazineRepository, 8L);

			createTickets(ticketRepository, faker);     // Create
			readTickets(ticketRepository);              // Read
			updateTicket(ticketRepository, 12L);         // Update
			deleteTicket(ticketRepository, 13L);

			createDiscMag(discMagRepository, faker);
			readDiscMag(discMagRepository);
			updateDiscMag(discMagRepository, 17L);
			deleteDiscMag(discMagRepository, 18L);
			findCartItemsByQuantity(cartItemRepository);
		};


	}
	private void createBooks(BookRepository bookRepository, Cart cart, Faker faker) {
		log.info("Creating books...");
		for (int i = 0; i < 5; i++) {
			Book book = new Book(
					faker.number().randomDouble(2, 10, 100), // price
					faker.number().numberBetween(1, 50),    // quantity
					"Book: " + faker.book().title(),         // description
					faker.book().title(),                    // title
					faker.number().numberBetween(5, 20),    // copies
					faker.book().author(),                  // author
					faker.code().isbn10()                   // ISBN
			);
			bookRepository.save(book);
			cart.addItem(book);
			log.info("Created book: " + book);
		}
		cartRepository.save(cart);
	}
	public void readBooks(BookRepository bookRepository) {
		// Fetch all books from the repository and log the results
		log.info("BookEntities found with findAll():");
		log.info("-------------------------------");
		bookRepository.findAll().forEach(book -> {
			log.info(book.toString());
		});
		log.info("");

		Book book = bookRepository.findById(1L);
		// Fetch books by ISBN dynamically from each book in the list
		String isbn = book.getISBN();
		log.info("BookEntities found with findByIsbn('" + isbn + "'):");  // Querying by ISBN
		log.info("--------------------------------------------");
		List<Book> booksByIsbn = bookRepository.findByISBN(isbn);
		booksByIsbn.forEach(b -> {
			log.info(b.toString());
		});
		log.info("");

		// Fetch books by price range
		double minPrice = 10.0; // Example minimum price
		double maxPrice = 50.0; // Example maximum price
		log.info("BookEntities found with findBooksByPriceRange('" + minPrice + ", " + maxPrice + "'):"); // Querying by price range
		log.info("--------------------------------------------");
		List<Book> booksByMultipleCriteria = bookRepository.findByPriceRange(minPrice, maxPrice);
		booksByMultipleCriteria.forEach(b -> {
			log.info(b.toString());
		});
		log.info("");

	}

	public void updateBook(BookRepository bookRepository, long bookId) {
		log.info("Updating book with ID: " + bookId);
		Book bookToUpdate = bookRepository.findById(bookId);
		if (bookToUpdate != null) {
			bookToUpdate.setPrice(19.99); // Update price
			bookToUpdate.setAuthor("Updated Author"); // Update author
			bookRepository.save(bookToUpdate);
			log.info("Updated book: " + bookToUpdate);
		} else {
			log.info("Book not found with ID: " + bookId);
		}
	}

	// --- Delete Operation ---
	private void deleteBook(BookRepository bookRepository, long bookId) {
		log.info("Deleting book with ID: " + bookId);
		if (bookRepository.existsById(bookId)) {
			bookRepository.deleteById(bookId);
			log.info("Book deleted with ID: " + bookId);
		} else {
			log.info("Book not found with ID: " + bookId);
		}
	}

	private void createMagazines(MagazineRepository magazineRepository ,Faker faker) {
		log.info("Creating magazines...");
		for (int i = 0; i < 5; i++) {
			Magazine magazine = new Magazine(
					faker.number().numberBetween(10, 50),   // price
					faker.number().numberBetween(1, 20),   // quantity
					"Magazine: " + faker.lorem().sentence(), // description
					faker.book().title(),                  // title
					faker.number().numberBetween(5, 20),   // copies
					faker.number().numberBetween(50, 100), // order quantity
					faker.date().past(30, java.util.concurrent.TimeUnit.DAYS) // current issue
			);
			magazineRepository.save(magazine);
//			cart.addItem(magazine);
			log.info("Created magazine: " + magazine);
		}
//		cartRepository.save(cart);
	}

	// --- Read Magazines ---
	private void readMagazines(MagazineRepository magazineRepository) {
		log.info("Fetching all magazines...");
		List<Magazine> allMagazines = magazineRepository.findAll();
		allMagazines.forEach(magazine -> {
			log.info(magazine.toString());
		});
		Optional<Magazine> optionalMagazine = magazineRepository.findById(7L);
		if (optionalMagazine.isPresent()) {
			Magazine magazine = optionalMagazine.get();
			int orderQty = magazine.getOrderQty();
			Date issue = magazine.getCurrIssue();

			log.info("Magazine found by Title and Issue date:");
			log.info("--------------------------------------------");
			magazineRepository.findByTitleAndCurrIssue(magazine.getTitle(), magazine.getCurrIssue()).forEach(m -> log.info(m.toString()));

		} else {
			log.warn("No magazine found with ID 7");
		}

	}

	// --- Update Magazine ---
	private void updateMagazine(MagazineRepository magazineRepository, long magazineId) {
		log.info("Updating magazine with ID: " + magazineId);
		Optional<Magazine> magazineToUpdate = magazineRepository.findById(magazineId);
		if (magazineToUpdate.isPresent()) {
			Magazine magazine = magazineToUpdate.get();
			magazine.setOrderQty(150); // Update order quantity
			magazine.setCurrIssue(new Date()); // Update current issue date
			magazineRepository.save(magazine);
			log.info("Updated magazine: " + magazine);
		} else {
			log.info("Magazine not found with ID: " + magazineId);
		}
	}

	// --- Delete Magazine ---
	private void deleteMagazine(MagazineRepository magazineRepository, long magazineId) {
		log.info("Deleting magazine with ID: " + magazineId);
		if (magazineRepository.existsById(magazineId)) {
			magazineRepository.deleteById(magazineId);
			log.info("Magazine deleted with ID: " + magazineId);
		} else {
			log.info("Magazine not found with ID: " + magazineId);
		}
	}

	private void createTickets(TicketRepository ticketRepository, Faker faker) {
		log.info("Creating tickets...");
		for (int i = 0; i < 5; i++) {
			Ticket ticket = new Ticket(
					faker.lorem().sentence(),               // text
					faker.number().randomDouble(2, 5, 20), // price
					faker.number().numberBetween(1, 10),   // quantity
					faker.lorem().sentence()               // description
			);
			ticketRepository.save(ticket);
			log.info("Created ticket: " + ticket);
		}
	}

	// --- Read Tickets ---
	private void readTickets(TicketRepository ticketRepository) {
		log.info("Fetching all tickets...");
		List<Ticket> allTickets = ticketRepository.findAll();
		allTickets.forEach(ticket -> log.info(ticket.toString()));


		log.info("all the tickets found between price between " + allTickets.get(0).getPrice() + " and " + allTickets.get(1).getPrice());
		log.info("--------------------------------------------");
		ticketRepository.findByPriceBetween(allTickets.get(0).getPrice(), allTickets.get(1).getPrice()).forEach(ticket -> {
			log.info(ticket.toString());
		});
		log.info("");

	}

	// --- Update Ticket ---
	private void updateTicket(TicketRepository ticketRepository, long ticketId) {
		log.info("Updating ticket with ID: " + ticketId);
		Optional<Ticket> ticketToUpdate = ticketRepository.findById(ticketId);
		if (ticketToUpdate.isPresent()) {
			Ticket ticket = ticketToUpdate.get();
			ticket.setText("Updated Ticket Text"); // Update text
			ticket.setPrice(15.99);               // Update price
			ticketRepository.save(ticket);
			log.info("Updated ticket: " + ticket);
		} else {
			log.info("Ticket not found with ID: " + ticketId);
		}
	}

	// --- Delete Ticket ---
	private void deleteTicket(TicketRepository ticketRepository, long ticketId) {
		log.info("Deleting ticket with ID: " + ticketId);
		if (ticketRepository.existsById(ticketId)) {
			ticketRepository.deleteById(ticketId);
			log.info("Ticket deleted with ID: " + ticketId);
		} else {
			log.info("Ticket not found with ID: " + ticketId);
		}
	}

	private void createDiscMag(DiscMagRepository discMagRepository, Faker faker) {
		log.info("Creating disc mag...");
		for (int i = 0; i < 5; i++) {
			DiscMag discMag = new DiscMag(
					faker.bool().bool(),
					faker.number().numberBetween(10, 50),   // price
					faker.number().numberBetween(1, 20),   // quantity
					"Magazine: " + faker.lorem().sentence(), // description
					faker.book().title(),                  // title
					faker.number().numberBetween(5, 20),   // copies
					faker.number().numberBetween(50, 100), // order quantity
					faker.date().past(30, java.util.concurrent.TimeUnit.DAYS)
			);
			discMagRepository.save(discMag);
			log.info("Created disc mag: " + discMag);
		}
	}

	private void readDiscMag(DiscMagRepository discMagRepository) {
		log.info("Fetching all disc mags...");
		List<DiscMag> allDiscMags = discMagRepository.findAll();
		allDiscMags.forEach(discMag -> log.info(discMag.toString()));

		// find the magazines with discs
		log.info("Magazines that have discs are: ");
		log.info("--------------------------------------------");
		discMagRepository.findByHasDisc(true).forEach(discMag -> log.info(discMag.toString()));
	}

	private void updateDiscMag(DiscMagRepository discMagRepository, Long discMagId) {
		log.info("Updating disc mag with id: " + discMagId);
		Optional<DiscMag> discMagToUpdate = discMagRepository.findById(discMagId);
		if (discMagToUpdate.isPresent()) {
			DiscMag discMag = discMagToUpdate.get();
			discMag.setHasDisc(true);
			discMag.setTitle("updated disc mag");
			discMagRepository.save(discMag);
			log.info("Updated disc mag: " + discMag);

		}
		else {
			log.info("Disc mag not found with ID: " + discMagId);
		}
	}

	private void deleteDiscMag(DiscMagRepository discMagRepository, Long discMagId) {
		log.info("Deleting disc mag with id: " + discMagId);
		if (discMagRepository.existsById(discMagId)) {
			discMagRepository.deleteById(discMagId);
			log.info("Disc mag deleted with ID: " + discMagId);
		}
		else {
			log.info("Disc mag not found with ID: " + discMagId);
		}
	}

	private void findCartItemsByQuantity(CartItemRepository cartItemRepository) {
		log.info("Finding cart items with quantity 5:");

		// Query the repository for CartItems where the quantity is greater than 5
		List<CartItem> cartItems = cartItemRepository.findByQuantity(5);

		// Log the results
		cartItems.forEach(cartItem -> log.info(cartItem.toString()));
	}

}
