import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginTest {

    @Test
    public void test_login_with_incorrect_credentials() {
        // System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        // In Docker (markhobson/maven-chrome), chromedriver is already set up
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        driver.navigate().to("http://103.139.122.250:4000/");

        driver.findElement(By.name("email")).sendKeys("qasim@malik.com");
        driver.findElement(By.name("password")).sendKeys("abcdefg");
        driver.findElement(By.id("m_login_signin_submit")).click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        String errorText = driver.findElement(
            By.xpath("/html/body/div/div/div[1]/div/div/div/div[2]/form/div[1]")
        ).getText();

        assert(errorText.contains("Incorrect email or password"));

        driver.quit();
    }
}