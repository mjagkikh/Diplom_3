package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AccountPage {
    WebDriver driver;
    private final By logOutButton = By.xpath(".//button[text()='Выход']");
    private final By logo = By.className("AppHeader_header__logo__2D0X2");
    private final By constructorInHeader = By.xpath(".//p[text()='Конструктор']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Find and get text from exit button in account")
    public String checkLogOutButtonInAccount() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(logOutButton));
        return driver.findElement(logOutButton).getText();
    }

    @Step("Click exit button in account")
    public void clickExitButtonInAccount() {
        driver.findElement(logOutButton).click();
    }

    @Step("Click on logo from account")
    public void clickOnLogoFromAccount() {
        driver.findElement(logo).click();
    }

    @Step("Click on constructor from account")
    public void clickOnConstructorFromAccount() {
        driver.findElement(constructorInHeader).click();
    }
}