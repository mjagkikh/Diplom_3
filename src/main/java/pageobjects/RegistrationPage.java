package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage {
    WebDriver driver;
    private static final String REGISTRATION_URL = "https://stellarburgers.nomoreparties.site/register";
    private final By nameField = By.xpath(".//div[label[text()='Имя']]/input");
    private final By emailField = By.xpath(".//div[label[text()='Email']]/input");
    private final By passwordField = By.xpath(".//div[label[text()='Пароль']]/input");
    private final By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By errorPasswordMessage = By.xpath((".//p[text()='Некорректный пароль']"));
    private final By loginButton = By.xpath((".//a[@href='/login']"));

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step ("Open registration page")
    public void open() {
        driver.get(REGISTRATION_URL);
    }

    @Step ("Fill out registration form")
    public void fillRegistrationForm( String name, String email, String password) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step ("Wait and click on registration button")
    public void clickOnRegistrationButton() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(registrationButton));
        driver.findElement(registrationButton).click();
    }

    public WebElement getErrorMessage() {
        return driver.findElement(errorPasswordMessage);
    }

    @Step ("Wait and click on login button")
    public void clickOnLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }
}