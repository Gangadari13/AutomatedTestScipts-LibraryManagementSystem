import org.junit.Test;
import static org.junit.Assert.*;

public class BookServiceTest1 {

    // Create an instance of the BookService class
    private final BookService bookService = new BookService();

    // Test for successful checkout (Passing test)
    @Test
    public void testCheckoutBookSuccess() {
        String username = "testUser";
        String bookId = "book123";

        boolean result = bookService.checkoutBook(username, bookId);

        // Assert that the checkoutBook method returns true
        assertTrue(result);
    }

    // Failing test case: Simulating failure (because method always returns true)
    @Test
    public void testCheckoutBookFail() {
        String username = "testUser";
        String bookId = "book123";

        // Simulate a condition that should cause failure (e.g., wrong bookId)
        boolean result = bookService.checkoutBook(username, "invalidBookId");

        // We expect the method to return false, but it returns true (this will fail)
        assertFalse("Expected the checkout to fail but it succeeded", result);
    }
}
