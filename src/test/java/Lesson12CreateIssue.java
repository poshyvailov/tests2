import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Lesson12CreateIssue {

    WebDriver driver = null;

    @BeforeMethod
    public void setUp() {
        WebDriverFactory.createInstance("Chrome");
        driver = WebDriverFactory.getDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void createIssueSteps(){
        driver.get("https://www.deezer.com/ru/");
    }


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}