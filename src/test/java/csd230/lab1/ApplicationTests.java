package csd230.lab1;

import csd230.lab1.entities.*;
import csd230.lab1.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private MagazineRepository magazineRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private DiscMagRepository discMagRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartItemRepository cartItemRepository;

	private Book book;
	private Magazine magazine;
	private Ticket ticket;
	private DiscMag discMag;

	@BeforeEach
	void setUp() {
		// Initialize entities for testing
		book = new Book(19.99, 10, "Test Book", "Test Author", 15, "Test Title", "1234567890");
		magazine = new Magazine(15.99, 5, "Test Magazine", "Test Magazine Title", 10, 100, new java.util.Date());
		ticket = new Ticket("Test Ticket", 9.99, 100, "Test Ticket Description");
		discMag = new DiscMag(true, 20.99, 5, "Test DiscMag", "Test DiscMag Title", 10, 50, new java.util.Date());

		bookRepository.save(book);
		magazineRepository.save(magazine);
		ticketRepository.save(ticket);
		discMagRepository.save(discMag);
	}

	@Test
	void testCreateAndReadBook() {
		// Test Read
		Book fetchedBook = bookRepository.findById(book.getId()).orElse(null);
		assertNotNull(fetchedBook);
		assertEquals(book.getTitle(), fetchedBook.getTitle());
	}

	@Test
	void testCreateAndReadTicket() {
		// Test Read
		Ticket fetchedTicket = ticketRepository.findById(ticket.getId()).orElse(null);
		assertNotNull(fetchedTicket);
		assertEquals(ticket.getText(), fetchedTicket.getText());
	}

	@Test
	void testCreateAndReadMagazine() {
		// Test Read
		Magazine fetchedMagazine = magazineRepository.findById(magazine.getId()).orElse(null);
		assertNotNull(fetchedMagazine);
		assertEquals(magazine.getTitle(), fetchedMagazine.getTitle());
	}

	@Test
	void testCreateAndReadCart() {
		// Test Read
		Cart cart = new Cart();
		cartRepository.save(cart);
		Cart fetchedCart = cartRepository.findById(cart.getId()).orElse(null);
		assertNotNull(fetchedCart);
		assertEquals(cart.getId(), fetchedCart.getId());
	}

	@Test
	void testUpdateBook() {
		// Test Update
		book.setPrice(25.99);
		bookRepository.save(book);

		Book updatedBook = bookRepository.findById(book.getId()).orElse(null);
		assertNotNull(updatedBook);
		assertEquals(25.99, updatedBook.getPrice());
	}

	@Test
	void testUpdateMagazine() {
		// Test Update
		magazine.setPrice(25.99);
		magazineRepository.save(magazine);

		Magazine updatedMagazine = magazineRepository.findById(magazine.getId()).orElse(null);
		assertNotNull(updatedMagazine);
		assertEquals(25.99, updatedMagazine.getPrice());
	}

	@Test
	void testDeleteBook() {
		// Test Delete
		bookRepository.deleteById(book.getId());
		Book deletedBook = bookRepository.findById(book.getId()).orElse(null);
		assertNull(deletedBook);
	}

	@Test
	void testDeleteMagazine() {
		// Test Delete
		magazineRepository.deleteById(magazine.getId());
		Magazine deletedMagazine = magazineRepository.findById(magazine.getId()).orElse(null);
		assertNull(deletedMagazine);
	}
}
