import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.AssertJUnit.assertEquals;


public class Lesson12CreateIssue {

    WebDriver driver = null;


    @BeforeMethod
    public void setUp() {
        WebDriverFactory.createInstance("Chrome");
        driver = WebDriverFactory.getDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void createIssueSteps() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());

        driver.get("https://jira.hillel.it/secure/Dashboard.jspa");
        driver.findElement(By.id("login-form-username")).sendKeys("poshyvailov");
        driver.findElement(By.id("login-form-password")).sendKeys("poshyvailov");
        driver.findElement(By.id("login")).click();
        wait.until(presenceOfElementLocated(By.xpath("//*[@id='dashboard-content']//h1"))).isDisplayed();

        // без тред слип не нажимается кнопка Create
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Находим и нажимаем на кнопку Create
        driver.findElement(By.id("create_link")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h2[@title='Create Issue']"))).isDisplayed();


        //Ищем поле Project, чистим его, вводим наши данные, нажимаем табуляцию
        driver.findElement(By.id("project-field")).clear();
        driver.findElement(By.id("project-field")).sendKeys("Webinar (WEBINAR)");
        driver.findElement(By.id("project-field")).sendKeys(Keys.TAB);

        //Не работает без тред слип
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Ищем поле Issue Type, чистим его, вводим наши данные, нажимаем табуляцию
        driver.findElement(By.id("issuetype-field")).clear();
        driver.findElement(By.id("issuetype-field")).sendKeys("Task");
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.TAB);

        //Не работает без тред слип
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Заполняем поле с саммари и проверяем,что наш юзер нейм есть в поле Reporter
        driver.findElement(By.id("summary")).sendKeys("Some New Ticket For Test");
        wait.until(presenceOfElementLocated(By.xpath("//optgroup[@id='reporter-group-suggested']//option[@value='poshyvailov']"))).isDisplayed();

        //Нажимаем на кнопку для создания тикета, проверяем,что появился поп-ап с сообщением о создании ти кета
        driver.findElement(By.id("create-issue-submit")).click();
        boolean checkIfPopUpIsAppeared = wait.until(presenceOfElementLocated(By.id("aui-flag-container"))).isDisplayed();
        assertEquals(checkIfPopUpIsAppeared, true);

        //Проверяем что в поп апе есть названия проекта,в который был создан тикет ("WEBINAR")
        boolean checkIfPopUpContainsWebinar = wait.until(presenceOfElementLocated(By.xpath("//*[@id='aui-flag-container']//a[contains (text(), 'WEBINAR')]"))).isDisplayed();
        assertEquals(checkIfPopUpContainsWebinar, true);

    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}