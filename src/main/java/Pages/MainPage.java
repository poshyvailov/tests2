package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class MainPage {
    WebDriver driver = null;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    private By createButton = By.id("create_link");
    private By searchTicketField = By.id("quickSearchInput");
    private By createTicketPopUp = By.xpath("//h2[@title='Create Issue' and contains (text(), 'Create Issue')]");



    public boolean checkIfMainPageIsOpen() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.xpath("//*[@id='dashboard-content']//h1"))).isDisplayed();
    }

    public boolean waitUntilOpenListOfAssignedTickets() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.xpath("//*[@class='date-header' and contains(text(), 'Today')]"))).isDisplayed();
    }

    public boolean isCreateTicketButtonIsActive() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(elementToBeClickable(createButton)).isEnabled();
    }

    public boolean checkIfJiraMainPageIsFullyOpened() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(elementToBeClickable(By.id("browse_link"))).isEnabled();
    }

    public void clickOnTheCreateButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        wait.until(elementToBeClickable(createButton)).isEnabled();
        driver.findElement(createButton).click();
    }

    public boolean checkIfCreateTicketWindowIsOpen() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.xpath("//h2[@title='Create Issue']"))).isDisplayed();
    }

    public boolean waitingWhenSearchFieldWillAppear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(searchTicketField)).isDisplayed();
    }

    public void typeTicketNameToSearch(String ticketName) {
        driver.findElement(searchTicketField).sendKeys(ticketName);
    }

    public void pressEnterForSearchField() {
        driver.findElement(searchTicketField).sendKeys(Keys.ENTER);
    }

    public boolean checkIfCreateTicketPopUpIsNotOpen(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(invisibilityOfElementLocated(createTicketPopUp));
    }


}
