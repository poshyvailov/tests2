import Pages.LoginPage;
import Pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class LoginTests {

    WebDriver driver = null;
    LoginPage loginPage;
    MainPage mainPage;

    @BeforeMethod
    public void setUp() {
        WebDriverFactory.createInstance("Chrome");
        driver = WebDriverFactory.getDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
    }

    @DataProvider(name = "Logins")
    public Object[][] createData(){
        return new Object[][]{
                {"poshyvailov2", "poshyvailov33", "Sorry, your username and password are incorrect - please try again."},
                {"bla bla", " ", "Sorry, your username and password are incorrect - please try again."},
        };
    }


    @Test(dataProvider = "Logins")
    public void loginWithWrongCreds(String userName, String userPass, String expectedResult)throws InterruptedException {

        loginPage.openTestPage();
        loginPage.enterUserName(userName);
        loginPage.enterUserPassword(userPass);
        loginPage.clickLoginButton();
        assertTrue(loginPage.isErrorsMessageIsShown(expectedResult));
    }


    @Test
    public void possitiveLoginTest(){
        loginPage.openTestPage();
        loginPage.enterUserName("poshyvailov");
        loginPage.enterUserPassword("poshyvailov");
        loginPage.clickLoginButton();
        assertTrue(loginPage.waitUntilDashboardWillBeOpen());
    }



    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
