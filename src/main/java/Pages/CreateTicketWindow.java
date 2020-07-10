package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class CreateTicketWindow {

    WebDriver driver = null;

    public CreateTicketWindow(WebDriver driver) {
        this.driver = driver;
    }

    private By ticketProjectField = By.id("project-field");
    private By ticketIssueTypeField = By.id("issuetype-field");
    private By ticketSummaryField = By.id("summary");
    private By saveTicketButton = By.id("create-issue-submit");


    public void clearTicketProjectField() {
        driver.findElement(ticketProjectField).clear();
    }

    public void ticketTypeProjectName(String name) {
        driver.findElement(ticketProjectField).sendKeys(name);
    }

    public void ticketProjectFieldClickTabButton() {
        driver.findElement(ticketProjectField).sendKeys(Keys.TAB);
    }


    public void ticketClearIssueTypeField() {
        driver.findElement(ticketIssueTypeField).clear();
    }

    public void ticketSelectIssueType(String issueType) {
        driver.findElement(ticketIssueTypeField).sendKeys(issueType);
    }

    public void ticketIssueTypeFiledPresTabButton() {
        driver.findElement(ticketIssueTypeField).sendKeys(Keys.TAB);
    }

    public boolean ticketWaitingForDescriptionWillBeActive(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(elementToBeClickable(By.id("description"))).isEnabled();
    }

    public void ticketTypeSummary(String summary) {
        driver.findElement(ticketSummaryField).sendKeys(summary);
    }

    public void ticketCLickSaveTicketButton() {
        driver.findElement(saveTicketButton).click();
    }

    public boolean checkIfSuccessPopUpIsPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.id("aui-flag-container"))).isDisplayed();
    }

    public boolean checkIfPopUpContainsWebinarName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.xpath("//*[@id='aui-flag-container']//a[contains (text(), 'WEBINAR')]"))).isDisplayed();


    }


}
