import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginTest {

    @Test
    public void test_login_with_incorrect_credentials() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.navigate().to("https://the-internet.herokuapp.com/login");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

            driver.findElement(By.id("username")).sendKeys("wronguser");
            driver.findElement(By.id("password")).sendKeys("wrongpass");
            driver.findElement(By.cssSelector("button[type='submit']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".flash.error")
            ));

            String errorText = driver.findElement(By.cssSelector(".flash.error")).getText();
            assert(errorText.contains("Your username is invalid"));

        } finally {
            driver.quit();
        }
    }
}


