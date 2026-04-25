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

        WebDriver driver = new ChromeDriver(options);

        driver.navigate().to("http://103.139.122.250:4000/");

        // Wait up to 10 seconds for email field to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));

        driver.findElement(By.name("email")).sendKeys("qasim@malik.com");
        driver.findElement(By.name("password")).sendKeys("abcdefg");
        driver.findElement(By.id("m_login_signin_submit")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("/html/body/div/div/div[1]/div/div/div/div[2]/form/div[1]")
        ));

        String errorText = driver.findElement(
            By.xpath("/html/body/div/div/div[1]/div/div/div/div[2]/form/div[1]")
        ).getText();

        assert(errorText.contains("Incorrect email or password"));
        driver.quit();
    }
}
