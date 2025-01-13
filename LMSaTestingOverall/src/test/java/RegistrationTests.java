import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class RegistrationTests {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver-win64\\chromedriver.exe");

        // Initialize ChromeDriver
        driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // Navigate to the registration page
        driver.get("http://localhost/library/Student_/registration.php");
    }

    @Test
    public void testValidRegistration() {
        // Fill in the registration form with valid data
        driver.findElement(By.name("first")).sendKeys("Johan");
        driver.findElement(By.name("last")).sendKeys("Doeli");
        driver.findElement(By.name("username")).sendKeys("johanedoeli");
        driver.findElement(By.name("password")).sendKeys("password1275");
        driver.findElement(By.name("roll")).sendKeys("004");
        driver.findElement(By.name("email")).sendKeys("johane@example.com");
        driver.findElement(By.name("contact")).sendKeys("0705239739");

        // Submit the form
        WebElement signUpButton = driver.findElement(By.cssSelector("input[type='submit']"));
        signUpButton.click();

        // Verify if registration was successful via the alert
        String alertMessage = driver.switchTo().alert().getText();
        assertTrue("Registration failed", alertMessage.contains("Registration successful"));

        // Close the alert
        driver.switchTo().alert().accept();
    }

    @Test
    public void testDuplicateUsername() {
        // Fill in the registration form with duplicate username
        driver.findElement(By.name("first")).sendKeys("Jane");
        driver.findElement(By.name("last")).sendKeys("Doe");
        driver.findElement(By.name("username")).sendKeys("johnadoe");  // Existing username
        driver.findElement(By.name("password")).sendKeys("password123");
        driver.findElement(By.name("roll")).sendKeys("002");
        driver.findElement(By.name("email")).sendKeys("jane@example.com");
        driver.findElement(By.name("contact")).sendKeys("0705236799");

        // Submit the form
        WebElement signUpButton = driver.findElement(By.cssSelector("input[type='submit']"));
        signUpButton.click();

        // Verify if an alert for duplicate username is displayed
        String alertMessage = driver.switchTo().alert().getText();
        assertTrue("Expected error for duplicate username not found", alertMessage.contains("The username already exists"));

        // Close the alert
        driver.switchTo().alert().accept();
    }

    @Test
    public void testInvalidEmail() {
        // Fill in the registration form with invalid email
        driver.findElement(By.name("first")).sendKeys("James");
        driver.findElement(By.name("last")).sendKeys("Smith");
        driver.findElement(By.name("username")).sendKeys("jamessmith");
        driver.findElement(By.name("password")).sendKeys("password123");
        driver.findElement(By.name("roll")).sendKeys("003");
        driver.findElement(By.name("email")).sendKeys("invalidemail"); // Invalid email format
        driver.findElement(By.name("contact")).sendKeys("0705236788");

        // Submit the form
        WebElement signUpButton = driver.findElement(By.cssSelector("input[type='submit']"));
        signUpButton.click();

        // Verify if an alert for invalid email is displayed
        String alertMessage = driver.switchTo().alert().getText();
        assertTrue("Expected error for invalid email format not found", alertMessage.contains("Invalid email format"));

        // Close the alert
        driver.switchTo().alert().accept();
    }

    @Test
    public void testWeakPasswordRegistration() {
        // Fill in the registration form with a weak password
        driver.findElement(By.name("first")).sendKeys("John");
        driver.findElement(By.name("last")).sendKeys("Doe");
        driver.findElement(By.name("username")).sendKeys("johndoe");
        driver.findElement(By.name("password")).sendKeys("ab"); // Weak password
        driver.findElement(By.name("roll")).sendKeys("001");
        driver.findElement(By.name("email")).sendKeys("john@example.com");
        driver.findElement(By.name("contact")).sendKeys("0705236789");

        // Submit the form
        WebElement signUpButton = driver.findElement(By.cssSelector("input[type='submit']"));
        signUpButton.click();

        // Verify if an error message for weak password is displayed
        String alertMessage = driver.switchTo().alert().getText();
        assertTrue("Expected error for invalid password format not found", alertMessage.contains("Invalid password format"));
    }

    @Test
    public void testInvalidPhoneRegistration() {
        // Fill in the registration form with invalid phone number
        driver.findElement(By.name("first")).sendKeys("John");
        driver.findElement(By.name("last")).sendKeys("Doe");
        driver.findElement(By.name("username")).sendKeys("johndoe");
        driver.findElement(By.name("password")).sendKeys("StrongPassword123");
        driver.findElement(By.name("roll")).sendKeys("001");
        driver.findElement(By.name("email")).sendKeys("john@example.com");
        driver.findElement(By.name("contact")).sendKeys("070523"); // Invalid phone number

        // Submit the form
        WebElement signUpButton = driver.findElement(By.cssSelector("input[type='submit']"));
        signUpButton.click();

        // Verify if an error message for invalid phone number is displayed
        String alertMessage = driver.switchTo().alert().getText();
        assertTrue("Expected error for invalid phone format not found", alertMessage.contains("Invalid phone format"));
    }

    @After
    public void tearDown() {
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }
}
