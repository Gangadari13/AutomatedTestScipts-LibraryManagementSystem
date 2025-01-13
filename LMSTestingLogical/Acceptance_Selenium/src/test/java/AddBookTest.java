import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddBookTest {
    public static void main(String[] args) {
        // Set the path to the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        try {
            // Open the webpage where the form is hosted
            driver.get("http://localhost/library/Admin_/add.php");

            // Create an instance of WebDriverWait with a Duration type for the timeout
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));

            // Interact with form fields based on 'name' attributes
            WebElement bookId = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("bid")));
            bookId.sendKeys("12345");

            WebElement bookName = driver.findElement(By.name("name"));
            bookName.sendKeys("The Great Gatsby");

            WebElement authorsName = driver.findElement(By.name("authors"));
            authorsName.sendKeys("F. Scott Fitzgerald");

            WebElement edition = driver.findElement(By.name("edition"));
            edition.sendKeys("1st");

            WebElement status = driver.findElement(By.name("status"));
            status.sendKeys("Available");

            WebElement quantity = driver.findElement(By.name("quantity"));
            quantity.sendKeys("10");

            WebElement department = driver.findElement(By.name("department"));
            department.sendKeys("Literature");

            // Click the 'ADD' button
            WebElement addButton = driver.findElement(By.name("submit"));
            addButton.click();

            // Optionally, wait and verify if a confirmation message or some result occurs
            // Example: Verify an alert, a page element, or a success message

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            //driver.quit();
        }
    }
}
