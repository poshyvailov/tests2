import Pages.LoginPage;
import Pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


import static org.testng.AssertJUnit.assertTrue;

public class LoginTests {

    WebDriver driver = null;
    LoginPage loginPage;
    MainPage mainPage;


    @Parameters({"browserName"})
    @BeforeMethod
    public void setUp(String browserName) {
        WebDriverFactory.createInstance(browserName);
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

    @Parameters({"browserName"})
    @Test
    public void possitiveLoginTest(String browserName){
        System.out.println("Browser name from parameters is: " + browserName);
        loginPage.openTestPage();
        loginPage.waitUntilLoginPageWillBeOpen();
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
