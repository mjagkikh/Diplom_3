package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthPage {
    private final By emailField = By.xpath(".//input[@type='text']");
    private final By passwordField = By.xpath(".//input[@type='password']");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By forgotPasswordButton = By.xpath(".//a[@href='/forgot-password']");
    private final By logButtonFromForgotPassword = By.xpath(".//a[@href='/login']");
    WebDriver driver;

    public AuthPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Enter email & password for authentication and click login button")
    public void auth(String email, String password) {
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException ie){
        }
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();

    }

    @Step ("Wait and click on forgot password button")
    public void clickOnForgotPasswordButton() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(forgotPasswordButton));
        driver.findElement(forgotPasswordButton).click();
        driver.findElement(logButtonFromForgotPassword).click();
    }

    @Step ("Check login button on authentication page")
    public String checkLogInButton() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(loginButton));
        return driver.findElement(loginButton).getText();
    }
}