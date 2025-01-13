import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class BookServiceSystemTest {

    private BookService bookService;

    // Use TestNG's @BeforeMethod for setup
    @BeforeMethod
    public void setUp() {
        bookService = new BookService();  // Initialize the service before each test
    }

    // Use TestNG's @Test annotation for the test case
    @Test
    public void testBookSearchSystem() {
        // Given: A book is added to the system
        Book book = new Book("Data Structures", "John Doe", "3rd Edition", 5);
        bookService.addBook(book);

        // When: The user searches for the book by title
        List<Book> foundBooks = bookService.searchBooks("Data Structures");

        // Then: The book should be found
        assertFalse(foundBooks.isEmpty());
        assertEquals(foundBooks.get(0).getTitle(), "Data Structures");
    }



}

