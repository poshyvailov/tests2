import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.AssertJUnit.assertEquals;

public class Lesson12AddCommentTest {
    WebDriver driver = null;

    @BeforeMethod
    public void setUp() {
        WebDriverFactory.createInstance("Chrome");
        driver = WebDriverFactory.getDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void addCommentPositiveTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());

        // Логинимся в систему
        driver.get("https://jira.hillel.it/secure/Dashboard.jspa");
        driver.findElement(By.id("login-form-username")).sendKeys("poshyvailov");
        driver.findElement(By.id("login-form-password")).sendKeys("poshyvailov");
        driver.findElement(By.id("login")).click();
        wait.until(presenceOfElementLocated(By.id("header-details-user-fullname"))).isDisplayed();

        //Без тред слипа тест не дожидается пока мы залогинимся и сразу вводит номер тикета в поиск


        // Ждем пока появится строка для поиска тикета, потом вводим номер тикета и нажимаем энтер чтобы открыть его
        wait.until(presenceOfElementLocated(By.id("quickSearchInput"))).isDisplayed();
        driver.findElement(By.id("quickSearchInput")).sendKeys("WEBINAR-11939");
        driver.findElement(By.id("quickSearchInput")).sendKeys(Keys.ENTER);


        // Проверячем что открылся именно наш тестовый тикет
        boolean checkIsTicketNameIsPresent = wait.until(presenceOfElementLocated(By.xpath("//a[@class='issue-link' and @data-issue-key='WEBINAR-11939']"))).isDisplayed();
        assertEquals(checkIsTicketNameIsPresent, true);

        //Нажимаем на конопку добавления коммента, ждем пока появится кнопка сохранения коммента и выбираем режим "Text" в поле для коммента
        driver.findElement(By.id("footer-comment-button")).click();
        wait.until(presenceOfElementLocated(By.id("issue-comment-add-submit"))).isDisplayed();
        driver.findElement(By.xpath("//div[@id='comment-wiki-edit']//a[contains (text(), 'Text')]")).click();

        // Находим поле для коммента, вводим коммент, проверяем что кнопка сохранения коммента стала активной
        driver.findElement(By.id("comment")).sendKeys("My random comment");
        boolean checkIfAddButtonIsActive = wait.until(presenceOfElementLocated(By.id("issue-comment-add-submit"))).isEnabled();
        assertEquals(checkIfAddButtonIsActive, true);

        // сохраняем коммент и проверяем,что он появился
        driver.findElement(By.id("checkIfAddButtonIsActive")).click();
        wait.until(presenceOfElementLocated(By.id("footer-comment-button"))).isDisplayed();
        boolean checkIsCommentExists = wait.until(presenceOfElementLocated(By.xpath("//div[@class='action-body flooded']//p[contains(text(), 'My random comment')]"))).isDisplayed();
        assertEquals(checkIsCommentExists, true);









    }


}
