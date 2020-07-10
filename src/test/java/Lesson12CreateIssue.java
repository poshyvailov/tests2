import Pages.CreateTicketWindow;
import Pages.LoginPage;
import Pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import static org.testng.AssertJUnit.assertTrue;


public class Lesson12CreateIssue {

    WebDriver driver = null;
    LoginPage loginPage;
    MainPage mainPage;
    CreateTicketWindow createTicketWindow;


    @BeforeMethod
    public void setUp() {
        WebDriverFactory.createInstance("Chrome");
        driver = WebDriverFactory.getDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        createTicketWindow = new CreateTicketWindow(driver);

    }

    @Test
    public void createIssueSteps() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());

        loginPage.openTestPage();
        loginPage.enterUserName("poshyvailov");
        loginPage.enterUserPassword("poshyvailov");
        loginPage.clickLoginButton();
        assertTrue(mainPage.checkIfMainPageIsOpen());

        // без тред слип не нажимается кнопка Create
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Находим и нажимаем на кнопку Create
        mainPage.clickOnTheCreateButton();
        assertTrue(mainPage.checkIfCreateTicketWindowIsOpen());

        //Ищем поле Project, чистим его, вводим наши данные, нажимаем табуляцию
        createTicketWindow.clearTicketProjectField();
        createTicketWindow.ticketTypeProjectName("Webinar (WEBINAR)");
        createTicketWindow.ticketProjectFieldClickTabButton();

        //Не работает без тред слип
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Ищем поле Issue Type, чистим его, вводим наши данные, нажимаем табуляцию
        createTicketWindow.ticketClearIssueTypeField();
        createTicketWindow.ticketSelectIssueType("Task");
        createTicketWindow.ticketIssueTypeFiledPresTabButton();
        createTicketWindow.ticketWaitingForDescriptionWillBeActive();

        //Заполняем поле с саммари
        createTicketWindow.ticketTypeSummary("Some New Ticket For Test");

        //Нажимаем на кнопку для создания тикета, проверяем,что появился поп-ап с сообщением о создании ти кета
        createTicketWindow.ticketCLickSaveTicketButton();
        assertTrue(createTicketWindow.checkIfSuccessPopUpIsPresent());

        //Проверяем что в поп апе есть названия проекта,в который был создан тикет ("WEBINAR")
        createTicketWindow.checkIfPopUpContainsWebinarName();
        assertTrue(createTicketWindow.checkIfPopUpContainsWebinarName());

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}