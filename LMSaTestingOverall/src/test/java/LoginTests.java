import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class LoginTests {

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
    }
    @Test
    public void testOpenLoginPage() {
        // Test if the login page is accessible by checking a unique element
        WebElement loginHeading = driver.findElement(By.xpath("//h1[text()='User Login Form']"));
        assertTrue("Login page is not displayed", loginHeading.isDisplayed());
    }




    @Test
    public void testValidLogin() {
        // Enter valid credentials
        driver.findElement(By.name("username")).sendKeys("johndoe");
        driver.findElement(By.name("password")).sendKeys("password123");

        // Submit the form
        WebElement loginButton = driver.findElement(By.cssSelector("input[type='submit']"));
        loginButton.click();

        // Verify if the user is redirected to the profile page
        String currentUrl = driver.getCurrentUrl();
        assertTrue("Redirection to profile page failed", currentUrl.contains("profile.php"));
    }

    @Test
    public void testInvalidUsername() {
        // Enter invalid username
        driver.findElement(By.name("username")).sendKeys("invaliduser");
        driver.findElement(By.name("password")).sendKeys("password123");

        // Submit the form
        WebElement loginButton = driver.findElement(By.cssSelector("input[type='submit']"));
        loginButton.click();

        // Verify if an error message is displayed for invalid username
        WebElement alertMessage = driver.findElement(By.className("alert-danger"));
        assertTrue("Error message for invalid username is not displayed", alertMessage.getText().contains("The username and password doesn't match"));
    }

    @Test
    public void testInvalidPassword() {
        // Enter valid username but invalid password
        driver.findElement(By.name("username")).sendKeys("johndoe");
        driver.findElement(By.name("password")).sendKeys("wrongpassword");

        // Submit the form
        WebElement loginButton = driver.findElement(By.cssSelector("input[type='submit']"));
        loginButton.click();

        // Verify if an error message is displayed for invalid password
        WebElement alertMessage = driver.findElement(By.className("alert-danger"));
        assertTrue("Error message for invalid password is not displayed", alertMessage.getText().contains("The username and password doesn't match"));
    }

    @Test
    public void testEmptyCredentials() {
        // Submit the form without entering any credentials
        WebElement loginButton = driver.findElement(By.cssSelector("input[type='submit']"));
        loginButton.click();

        // Verify if required field errors are triggered
        WebElement usernameField = driver.findElement(By.name("username"));
        assertTrue("Username field should be required", usernameField.getAttribute("required") != null);

        WebElement passwordField = driver.findElement(By.name("password"));
        assertTrue("Password field should be required", passwordField.getAttribute("required") != null);
    }

    @After
    public void tearDown() {
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }
}