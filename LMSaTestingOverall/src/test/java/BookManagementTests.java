import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class BookManagementTests {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver-win64\\chromedriver.exe");

        // Initialize ChromeDriver
        driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // Navigate to the login page
        driver.get("http://localhost/library/Student_/student_login.php");

        // Log in with valid credentials
        driver.findElement(By.name("username")).sendKeys("johndoe");
        driver.findElement(By.name("password")).sendKeys("password123");

        // Submit the login form
        WebElement loginButton = driver.findElement(By.cssSelector("input[type='submit']"));
        loginButton.click();

        // Verify successful login by checking if redirected to the profile page
        String currentUrl = driver.getCurrentUrl();
        assertTrue("Login failed, profile page not displayed", currentUrl.contains("profile.php"));

        // Navigate to the "Add Books" page
        driver.get("http://localhost/library/Admin_/add.php");
    }

    @Test
    public void testOpenAddBooksForm() {
        // Locate the form by its class name
        WebElement form = driver.findElement(By.xpath("//form[@class='book']"));
        assertTrue("Add Books form is not displayed", form.isDisplayed());
    }


    @Test
    public void testAddBookWithValidDetails() {
        // Enter valid book details
        driver.findElement(By.name("bid")).sendKeys("002");
        driver.findElement(By.name("name")).sendKeys("Data Structure");
        driver.findElement(By.name("authors")).sendKeys("John Doe");
        driver.findElement(By.name("edition")).sendKeys("4th");
        driver.findElement(By.name("status")).sendKeys("Active");
        driver.findElement(By.name("quantity")).sendKeys("5");
        driver.findElement(By.name("department")).sendKeys("Technology");

        // Submit the form
        WebElement addButton = driver.findElement(By.cssSelector("button[type='submit']"));
        addButton.click();

        // Verify success alert message
        assertTrue("Success message for book addition is not displayed", driver.switchTo().alert().getText().contains("Book Added Successfully."));
    }

    @Test
    public void testAddBookWithDuplicateId() {
        // Enter book details with a duplicate Book ID
        driver.findElement(By.name("bid")).sendKeys("002"); // Assuming "002" already exists
        driver.findElement(By.name("name")).sendKeys("New Book");
        driver.findElement(By.name("authors")).sendKeys("Jane Doe");
        driver.findElement(By.name("edition")).sendKeys("2nd");
        driver.findElement(By.name("status")).sendKeys("Active");
        driver.findElement(By.name("quantity")).sendKeys("3");
        driver.findElement(By.name("department")).sendKeys("Technology");

        // Submit the form
        WebElement addButton = driver.findElement(By.cssSelector("button[type='submit']"));
        addButton.click();

        // Verify failure alert message
        String alertMessage = driver.switchTo().alert().getText();
        assertTrue("Expected error for duplicate book  not found", alertMessage.contains("Already exist"));
    }

    @Test
    public void testAddBookWithNegativeQuantity() {
        // Enter book details with negative quantity
        driver.findElement(By.name("bid")).sendKeys("004");
        driver.findElement(By.name("name")).sendKeys("Data Structure");
        driver.findElement(By.name("authors")).sendKeys("John Doe");
        driver.findElement(By.name("edition")).sendKeys("4th");
        driver.findElement(By.name("status")).sendKeys("Active");
        driver.findElement(By.name("quantity")).sendKeys("-5");
        driver.findElement(By.name("department")).sendKeys("Technology");

        // Submit the form
        WebElement addButton = driver.findElement(By.cssSelector("button[type='submit']"));
        addButton.click();

        // Verify failure alert message
        String alertMessage = driver.switchTo().alert().getText();
        assertTrue("Expected error for negative quantity", alertMessage.contains("Can't exist"));
    }

    @Test
    public void testAddBookWithEmptyFields() {
        // Leave the "Book ID" field blank and try to submit
        driver.findElement(By.name("name")).sendKeys("Science");
        driver.findElement(By.name("authors")).sendKeys("John Lee");
        driver.findElement(By.name("edition")).sendKeys("4th");
        driver.findElement(By.name("status")).sendKeys("Active");
        driver.findElement(By.name("quantity")).sendKeys("4");
        driver.findElement(By.name("department")).sendKeys("Information");

        // Submit the form
        WebElement addButton = driver.findElement(By.cssSelector("button[type='submit']"));
        addButton.click();

        // Verify if required field errors are triggered
        WebElement bookIdField = driver.findElement(By.name("bid"));
        assertTrue("Book ID field should be required", bookIdField.getAttribute("required") != null);
    }

    @After
    public void tearDown() {
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }
}
