import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.AuthPage;
import pageobjects.MainPage;
import pageobjects.RegistrationPage;
import pageobjects.RestClient;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationTest {
    DriverFactory factory = new DriverFactory();
    private String name = RandomStringUtils.randomAlphanumeric(10);
    private String email = RandomStringUtils.randomAlphanumeric(10) + "@ya.ru";
    RegistrationPage registrationPage;
    RestClient restClient = new RestClient();
    String accessToken;

    @Before
    public void setUp() {
        registrationPage = new RegistrationPage(factory.getDriver());
    }

    @After
    public void killDriver() {
        factory.killDriver();
    }

    @Test
    @DisplayName("Check success registration")
    public void registrationTest() {
        String password = RandomStringUtils.randomAlphanumeric(10);
        registrationPage.open();
        registrationPage.fillRegistrationForm(name, email, password);
        registrationPage.clickOnRegistrationButton();
        AuthPage authPage = new AuthPage(factory.getDriver());
        authPage.auth(email, password);
        MainPage mainPage = new MainPage(factory.getDriver());
        accessToken = mainPage.getAccessFromLocalStorage();
        assertEquals("Оформить заказ", mainPage.textOrderButton());
        restClient.delete(accessToken);
    }

    @Test
    @DisplayName("Check error message when password less then 6 characters")
    public void registrationWithErrorTest() {
        String password = "123";
        registrationPage.open();
        registrationPage.fillRegistrationForm(name, email,password);
        registrationPage.clickOnRegistrationButton();
        assertTrue(registrationPage.getErrorMessage().isDisplayed());
    }
}