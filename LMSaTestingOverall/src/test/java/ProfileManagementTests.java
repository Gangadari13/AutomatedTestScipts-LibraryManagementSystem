import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProfileManagementTests {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/library/Student_/student_login.php");


        driver.findElement(By.name("username")).sendKeys("johndoe");
        driver.findElement(By.name("password")).sendKeys("password123");
        WebElement loginButton = driver.findElement(By.cssSelector("input[type='submit']"));
        loginButton.click();

        driver.get("http://localhost/library/Student_/profile.php");
    }

    @Test
    public void testProfilePageElementsDisplayed() {
        WebElement firstName = driver.findElement(By.xpath("//td[text()='First Name:']/following-sibling::td"));
        WebElement lastName = driver.findElement(By.xpath("//td[text()='Last Name:']/following-sibling::td"));
        WebElement email = driver.findElement(By.xpath("//td[text()='Email:']/following-sibling::td"));
        WebElement contact = driver.findElement(By.xpath("//td[text()='Contact:']/following-sibling::td"));

        assertTrue("First name is not displayed", firstName.isDisplayed());
        assertTrue("Last name is not displayed", lastName.isDisplayed());
        assertTrue("Email is not displayed", email.isDisplayed());
        assertTrue("Contact number is not displayed", contact.isDisplayed());
    }

    @Test
    public void testEditButtonEnablesEditingFields() {
        WebElement editButton = driver.findElement(By.name("submit1"));
        editButton.click();

        WebElement firstNameField = driver.findElement(By.name("first"));
        WebElement emailField = driver.findElement(By.name("email"));

        assertTrue("First name field is not editable", firstNameField.isEnabled());
        assertTrue("Email field is not editable", emailField.isEnabled());
    }

    @Test
    public void testUpdateValidEmail() {
        WebElement editButton = driver.findElement(By.name("submit1"));
        editButton.click();

        WebElement emailField = driver.findElement(By.name("email"));
        emailField.clear();
        emailField.sendKeys("test@example.com");

        WebElement saveButton = driver.findElement(By.name("submit"));
        saveButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement updatedEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Email:']/following-sibling::td")));
        assertEquals("test@example.com", updatedEmail.getText().trim());
    }

    @Test
    public void testUpdateInvalidEmail() {
        WebElement editButton = driver.findElement(By.name("submit1"));
        editButton.click();

        WebElement emailField = driver.findElement(By.name("email"));
        emailField.clear();
        emailField.sendKeys("invalidemail");

        WebElement saveButton = driver.findElement(By.name("submit"));
        saveButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement alertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-danger")));
        assertTrue("Error message for invalid email is not displayed", alertMessage.getText().contains("Invalid email format"));
    }

    @Test
    public void testEditButtonRedirectsToEditPage() {
        WebElement editButton = driver.findElement(By.name("submit1"));
        editButton.click();




        String currentUrl = driver.getCurrentUrl();
        assertEquals("The user was not redirected to the edit.php page", "http://localhost/library/Student_/edit.php", currentUrl);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}