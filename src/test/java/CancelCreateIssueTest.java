import Pages.CreateTicketWindow;
import Pages.LoginPage;
import Pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class CancelCreateIssueTest {

    WebDriver driver = null;
    LoginPage loginPage;
    MainPage mainPage;
    CreateTicketWindow createTicketWindow;

    @BeforeMethod
    public void setUp(){
        WebDriverFactory.createInstance("Chrome");
        driver = WebDriverFactory.getDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        createTicketWindow = new CreateTicketWindow(driver);

    }

    @Test
    public void cancelCreateIssueTest(){

        loginPage.openTestPage();
        loginPage.enterUserName("poshyvailov");
        loginPage.enterUserPassword("poshyvailov");
        loginPage.clickLoginButton();
        assertTrue(mainPage.checkIfMainPageIsOpen());
        mainPage.checkIfJiraMainPageIsFullyOpened();


        mainPage.clickOnTheCreateButton();
        assertTrue(mainPage.checkIfCreateTicketWindowIsOpen());
        //Заполняем данные тикета
        createTicketWindow.clearTicketProjectField();
        createTicketWindow.ticketTypeProjectName("Webinar (WEBINAR)");
        createTicketWindow.ticketProjectFieldClickTabButton();
        createTicketWindow.ticketWaitingForDescriptionWillBeActive();
        createTicketWindow.ticketClearIssueTypeField();
        createTicketWindow.ticketSelectIssueType("Task");
        createTicketWindow.ticketIssueTypeFiledPresTabButton();
        createTicketWindow.ticketWaitingForDescriptionWillBeActive();
        createTicketWindow.ticketTypeSummary("Some New Ticket For Test");
        //Сликаем на отмену и подтверждаем отмену в алерт сообщении
        createTicketWindow.clickOnTheCancelButton();
        createTicketWindow.clickOnTheOkInAlertMessage();
        //Проверяем,что create ticket pop-up уже не отобрадаетс яна странице
        assertTrue(mainPage.checkIfCreateTicketPopUpIsNotOpen());





    }

    @AfterMethod
    public void tearsDown(){
        driver.quit();
    }
}
