package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class MainPage {
    WebDriver driver = null;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }



    private By createButton = By.id("create_link");
    private By searchTicketField = By.id("quickSearchInput");



    public boolean checkIfMainPageIsOpen(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.xpath("//*[@id='dashboard-content']//h1"))).isDisplayed();
    }

    public boolean waitUntilOpenListOfAssignedTickets(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.xpath("//*[@class='date-header' and contains(text(), 'Today')]"))).isDisplayed();
    }

    public boolean isCreateTicketButtonIsActive(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(elementToBeClickable(createButton)).isEnabled();
    }

    public void clickOnTheCreateButton(){
        driver.findElement(createButton).click();
    }

    public boolean checkIfCreateTicketWindowIsOpen(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.xpath("//h2[@title='Create Issue']"))).isDisplayed();
    }

    public boolean waitingWhenSearchFieldWillAppear(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(searchTicketField)).isDisplayed();
    }

    public void typeTicketNameToSearch(String ticketName){
        driver.findElement(searchTicketField).sendKeys(ticketName);
    }

    public void pressEnterForSearchField(){
        driver.findElement(searchTicketField).sendKeys(Keys.ENTER);
    }



}
