import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.AuthPage;
import pageobjects.MainPage;
import pageobjects.AccountPage;
import pageobjects.RestClient;
import static org.junit.Assert.*;

public class AccountTest {
    DriverFactory factory = new DriverFactory();
    AuthPage authPage;
    MainPage mainPage;
    AccountPage accountPage;
    RestClient restClient = new RestClient();
    String accessToken;

    private String name = RandomStringUtils.randomAlphanumeric(10);
    private String email = RandomStringUtils.randomAlphanumeric(10)+"@ya.ru";
    private String password = RandomStringUtils.randomAlphanumeric(10);

    @Before
    public void setUp() {
        ValidatableResponse response = restClient.createUser(name, email, password);
        accessToken = restClient.getAccess(response);
        authPage = new AuthPage(factory.getDriver());
        mainPage = new MainPage(factory.getDriver());
    }

    @After
    public void killDriver() {
        factory.killDriver();
        restClient.delete(accessToken);
    }

    @Test
    @DisplayName("Check link to account")
    public void checkLinkToAccount() {
        authPage.open();
        authPage.auth(email, password);
        mainPage.clickOnAccountButton();
        accountPage = new AccountPage(factory.getDriver());
        assertEquals("Выход", accountPage.checkLogOutButtonInAccount());
    }

    @Test
    @DisplayName("Check exit from account")
    public void checkExitFromAccount() {
        authPage.open();
        authPage.auth(email, password);
        mainPage.clickOnAccountButton();
        accountPage = new AccountPage(factory.getDriver());
        accountPage.clickExitButtonInAccount();
        assertEquals("Войти", authPage.checkLogInButton());
    }

    @Test
    @DisplayName("Check click on logo from account and go to main page")
    public void checkClickOnLogoFromAccount() {
        authPage.open();
        authPage.auth(email, password);
        mainPage.clickOnAccountButton();
        accountPage = new AccountPage(factory.getDriver());
        accountPage.clickOnLogoFromAccount();
        assertEquals("Оформить заказ", mainPage.textOrderButton());
    }

    @Test
    @DisplayName("Check click on constructor from account and go to main page")
    public void checkClickOnConstructorFromAccount() {
        authPage.open();
        authPage.auth(email, password);
        mainPage.clickOnAccountButton();
        accountPage = new AccountPage(factory.getDriver());
        accountPage.clickOnConstructorFromAccount();
        assertEquals("Оформить заказ", mainPage.textOrderButton());
    }
}