import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.AuthPage;
import pageobjects.MainPage;
import pageobjects.AccountPage;
import pageobjects.RegistrationPage;

import static org.junit.Assert.*;

public class AccountTest {

    DriverFactory factory = new DriverFactory();
    RegistrationPage registrationPage;
    AuthPage authPage;
    MainPage mainPage;
    AccountPage accountPage;

    private String name = RandomStringUtils.randomAlphanumeric(10);
    private String email = RandomStringUtils.randomAlphanumeric(10)+"@ya.ru";
    private String password = RandomStringUtils.randomAlphanumeric(10);


    @Before
    public void setUp() {
        registrationPage = new RegistrationPage(factory.getDriver());
        registrationPage.open();
        registrationPage.fillRegistrationForm(name, email, password);
        registrationPage.clickOnRegistrationButton();
        authPage = new AuthPage(factory.getDriver());
        mainPage = new MainPage(factory.getDriver());
    }

    @After
    public void killDriver() {
        factory.killDriver();
    }

    @Test
    @DisplayName("Check link to account")
    public void checkLinkToAccount() {
        authPage.auth(email, password);
        mainPage.clickOnAccountButton();
        accountPage = new AccountPage(factory.getDriver());
        assertEquals("Выход", accountPage.checkLogOutButtonInAccount());
    }

    @Test
    @DisplayName("Check exit from account")
    public void checkExitFromAccount() {
        authPage.auth(email, password);
        mainPage.clickOnAccountButton();
        accountPage = new AccountPage(factory.getDriver());
        accountPage.clickExitButtonInAccount();
        assertEquals("Войти", authPage.checkLogInButton());
    }

    @Test
    @DisplayName("Check click on logo from account and go to main page")
    public void checkClickOnLogoFromAccount() {
        authPage.auth(email, password);
        mainPage.clickOnAccountButton();
        accountPage = new AccountPage(factory.getDriver());
        accountPage.clickOnLogoFromAccount();
        assertEquals("Оформить заказ", mainPage.textOrderButton());
    }

    @Test
    @DisplayName("Check click on constructor from account and go to main page")
    public void checkClickOnConstructorFromAccount() {
        authPage.auth(email, password);
        mainPage.clickOnAccountButton();
        accountPage = new AccountPage(factory.getDriver());
        accountPage.clickOnConstructorFromAccount();
        assertEquals("Оформить заказ", mainPage.textOrderButton());
    }
}