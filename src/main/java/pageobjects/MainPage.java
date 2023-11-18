package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    WebDriver driver;
    private static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site";
    private final By makeOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By loginButtonOnMainPage = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By accountButtonOnMainPage = By.xpath(".//a[@href='/account']");
    private final By sauceButtonOnMainPage = By.xpath(".//span[text()='Соусы']");
    private final By fillingButtonOnMainPage = By.xpath(".//span[text()='Начинки']");
    private final By bunsButtonOnMainPage = By.xpath(".//span[text()='Булки']");
    private final By sauceSection = By.xpath(".//h2[text()='Соусы']");
    private final By fillingSection = By.xpath(".//h2[text()='Начинки']");
    private final By bunsSection = By.xpath(".//h2[text()='Булки']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step ("Open main page")
    public void open() {
        driver.get(MAIN_PAGE_URL);
    }

    @Step("Get text from order button on main page")
    public String textOrderButton () {
        WebElement textButton = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(makeOrderButton));
        return textButton.getText();
    }

    public void clickOnLoginButton() {
        driver.findElement(loginButtonOnMainPage).click();
    }

    @Step("Click on account button on main page")
    public void clickOnAccountButton() {
        driver.findElement(accountButtonOnMainPage).click();
    }

    @Step("Click sauce button on main page, check sauce")
    public boolean checkSauceIsDisplayed() {
        driver.findElement(sauceButtonOnMainPage).click();
        return driver.findElement(sauceSection).isDisplayed();
    }

    @Step("Click filling button on main page")
    public boolean checkFillingIsDisplayed() {
        driver.findElement(fillingButtonOnMainPage).click();
        return driver.findElement(fillingSection).isDisplayed();
    }

    @Step("Click buns button on main page")
    public boolean checkBunsIsDisplayed() {
        driver.findElement(sauceButtonOnMainPage).click();
        driver.findElement(bunsButtonOnMainPage).click();
        return driver.findElement(bunsSection).isDisplayed();
    }
}