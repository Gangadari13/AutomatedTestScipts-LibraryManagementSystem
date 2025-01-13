public class Book {
    private String title;
    private String author;
    private String edition;
    private int copiesAvailable;

    // Constructor
    public Book(String title, String author, String edition, int copiesAvailable) {
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.copiesAvailable = copiesAvailable;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getEdition() {
        return edition;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    // Setters (if necessary)
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }
}
