import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.AuthPage;
import pageobjects.MainPage;
import pageobjects.RegistrationPage;

import static org.junit.Assert.assertEquals;

public class AuthTest {
    DriverFactory factory = new DriverFactory();
    RegistrationPage registrationPage;
    AuthPage authPage;
    MainPage mainPage;

    private String name = RandomStringUtils.randomAlphanumeric(10);
    private String email = RandomStringUtils.randomAlphanumeric(10)+"@ya.ru";
    private String password = RandomStringUtils.randomAlphanumeric(10);

    @Before
    public void setUp() {
        registrationPage = new RegistrationPage(factory.getDriver());
        registrationPage.open();
        registrationPage.fillRegistrationForm(name, email, password);
        registrationPage.clickOnRegistrationButton();
    }

    @After
    public void killDriver() {
        factory.killDriver();
    }

    @Test
    @DisplayName("Authentication from main page")
    public void authFromMainPageTest() {
        mainPage = new MainPage(factory.getDriver());
        mainPage.open();
        mainPage.clickOnLoginButton();
        authPage = new AuthPage(factory.getDriver());
        authPage.auth(email, password);
        assertEquals("Оформить заказ", mainPage.textOrderButton());
    }

    @Test
    @DisplayName("Authentication from personal account page")
    public void authFromAccountTest() {
        mainPage = new MainPage(factory.getDriver());
        mainPage.open();
        mainPage.clickOnAccountButton();
        authPage = new AuthPage(factory.getDriver());
        authPage.auth(email, password);
        assertEquals("Оформить заказ", mainPage.textOrderButton());
    }

    @Test
    @DisplayName("Authentication from registration page")
    public void authFromRegistrationPageTest() {
        registrationPage.open();
        registrationPage.clickOnLoginButton();
        authPage = new AuthPage(factory.getDriver());
        authPage.auth(email,password);
        mainPage = new MainPage(factory.getDriver());
        assertEquals("Оформить заказ", mainPage.textOrderButton());
    }

    @Test
    @DisplayName("Authentication from reset password page")
    public void authFromResetPasswordPageTest() {
        authPage = new AuthPage(factory.getDriver());
        authPage.clickOnForgotPasswordButton();
        authPage.auth(email,password);
        mainPage = new MainPage(factory.getDriver());
        assertEquals("Оформить заказ", mainPage.textOrderButton());
    }
}