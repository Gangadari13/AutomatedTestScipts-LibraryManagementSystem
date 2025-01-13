import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        // Open the login page
        driver.get("http://localhost/library/Student_/student_login.php");
        Thread.sleep(2000); // Pause for 2 seconds

        // Enter valid credentials
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.name("submit"));

        usernameField.sendKeys("ganga");
        Thread.sleep(1000); // Pause for 1 second

        passwordField.sendKeys("123567");
        Thread.sleep(1000); // Pause for 1 second

        loginButton.click();
        Thread.sleep(2000); // Pause for 2 seconds to see the login

        // Assert the user is redirected to the profile page
        String expectedUrl = "http://localhost/library/Student_/profile.php";
        String actualUrl = driver.getCurrentUrl();

        if (expectedUrl.equals(actualUrl)) {
            System.out.println("Test Passed: Redirected to profile page.");
        } else {
            System.out.println("Test Failed: Not redirected to profile page.");
        }

        // Close the browser
        driver.quit();
    }
}


