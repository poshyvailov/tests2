import Pages.LoginPage;
import Pages.MainPage;
import Pages.TicketDetails;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Lesson12AddCommentTest {
    WebDriver driver = null;
    MainPage mainPage;
    LoginPage loginPage;
    TicketDetails ticketDetails;

    @BeforeMethod
    public void setUp() {
        WebDriverFactory.createInstance("Chrome");
        driver = WebDriverFactory.getDriver();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        ticketDetails = new TicketDetails(driver);
    }

    @Test
    public void addCommentPositiveTest() {

        // Логинимся в систему
        loginPage.openTestPage();
        loginPage.enterUserName("poshyvailov");
        loginPage.enterUserPassword("poshyvailov");
        loginPage.clickLoginButton();
        assertTrue(mainPage.checkIfMainPageIsOpen());
        mainPage.checkIfJiraMainPageIsFullyOpened();

        // Ждем пока появится строка для поиска тикета, потом вводим номер тикета и нажимаем энтер чтобы открыть его
        assertTrue(mainPage.waitingWhenSearchFieldWillAppear());
        mainPage.typeTicketNameToSearch("WEBINAR-11939");
        mainPage.pressEnterForSearchField();

        // Проверяем, что открылся именно наш тестовый тикет
        assertTrue(ticketDetails.checkIsTicketNameIsPresent());

        //Нажимаем на конопку добавления коммента, ждем пока появится кнопка сохранения коммента и выбираем режим "Text" в поле для коммента
        ticketDetails.clickOnTheAddCommentButton();
        assertTrue(ticketDetails.waitingForSaveCommentButton());
        ticketDetails.goToTextModeForAddingComment();

        // Находим поле для коммента, вводим коммент, проверяем что кнопка сохранения коммента стала активной
        ticketDetails.typeNewCommentToTheCommentField("My random comment");
        assertTrue(ticketDetails.isSaveButtonIsActive());

        // сохраняем коммент и проверяем,что он появился
        ticketDetails.clickOnTheSaveCommentButton();
        assertTrue(ticketDetails.waitingWhenAddNewCommentFieldWillBeClosed());
        assertTrue(ticketDetails.checkIfTestCommentAppeared());

        //Находим иконку удаления коммента, нажимаем на нее и проверяем что поп ап для удаления коммента открылся
        ticketDetails.clickOnTheDeleteCommentIcon();
        assertTrue(ticketDetails.isDeleteConfirmationPopUpIsOpen());

        // Подтверждаем удаление коммента и ждем сообщение об успехе
        ticketDetails.confirmCommentDelete();
        assertTrue(ticketDetails.checkSuccessDeleteMessage());

        //Проверяем,что наш коммент пропал.
        assertEquals(ticketDetails.checkIfCommentIsNotExists() , true);


    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


}
