import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class DriverFactory {
    WebDriver driver;

    public DriverFactory() {
       WebDriverManager.chromedriver().setup();
       driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void killDriver() {
        driver.quit();
    }
}