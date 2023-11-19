import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.MainPage;
import static org.junit.Assert.assertTrue;

public class ConstructorTest {
    DriverFactory factory = new DriverFactory();
    MainPage mainPage;

    @Before
    public void setUp() {
        mainPage = new MainPage(factory.getDriver());
    }

    @After
    public void killDriver() {
        factory.killDriver();
    }
    @Test
    @DisplayName("Check constructor sauce at main page")
    public void checkSauceLink() {
        mainPage.open();
        assertTrue(mainPage.checkSauceIsDisplayed());
    }

    @Test
    @DisplayName("Check constructor filling at main page")
    public void checkFillingLink() {
        mainPage.open();
        assertTrue(mainPage.checkFillingIsDisplayed());
    }

    @Test
    @DisplayName("Check constructor buns at main page")
    public void checkBunsLink() {
        mainPage.open();
        assertTrue(mainPage.checkBunsIsDisplayed());
    }
}