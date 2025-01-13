import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class ReportHandling {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver-win64\\chromedriver.exe");

        // Initialize ChromeDriver
        driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // Navigate to the Books section
        driver.get("http://localhost/library/Admin_/books.php");
    }

    @Test
    public void testOpenBooksSection() {
        // Test if the Books section is accessible
        WebElement booksHeading = driver.findElement(By.xpath("//h2[text()='List Of Books']"));
        assertTrue("Books section is not displayed", booksHeading.isDisplayed());
    }

    @Test
    public void testDisplayBookDetails() {
        // Test if book details are displayed correctly
        WebElement bookTable = driver.findElement(By.className("table"));
        assertTrue("Book details table is not displayed", bookTable.isDisplayed());

        // Assuming the table has rows with book details
        // Verify the presence of specific book details
        assertTrue("Book Name is missing", bookTable.getText().contains("Black Hat Python")); // Replace with actual book names
        assertTrue("Authors Name is missing", bookTable.getText().contains("Justin Seitz")); // Replace with actual author names
        assertTrue("Edition is missing", bookTable.getText().contains("3rd")); // Replace with actual editions
        assertTrue("Status is missing", bookTable.getText().contains("Available")); // Replace with actual statuses
        assertTrue("Quantity is missing", bookTable.getText().contains("4")); // Replace with actual quantities
        assertTrue("Department is missing", bookTable.getText().contains("CSE")); // Replace with actual departments
    }

    @After
    public void tearDown() {
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }
}

