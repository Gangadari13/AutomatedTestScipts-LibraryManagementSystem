import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {

    private List<Book> books = new ArrayList<>();  // In-memory book store for simplicity

    // Add a book to the system
    public void addBook(Book book) {
        books.add(book);
    }

    // Search for books by title
    public List<Book> searchBooks(String title) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    // Return all books in the system
    public List<Book> getAllBooks() {
        return books;
    }
}
