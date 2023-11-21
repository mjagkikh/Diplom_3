import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import config.BrowserPath;

import java.time.Duration;

public class DriverFactory {
    WebDriver driver;

    public DriverFactory() {
        if ("yandex".equals(System.getProperty("browser")))
            setUpYandex();
        else
            WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public void setUpYandex() {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver116.exe");
        options.addArguments("--remote-allow-origins=*");
        options.setBinary(BrowserPath.Yandex);
        driver = new ChromeDriver(options);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void killDriver() {
        driver.quit();
    }
}