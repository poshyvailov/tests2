package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class TicketDetails {
    WebDriver driver = null;

    public TicketDetails(WebDriver driver) {
        this.driver = driver;
    }


    private By addCommentButton = By.id("footer-comment-button");
    private By textModeButton = By.xpath("//div[@id='comment-wiki-edit']//a[contains (text(), 'Text')]");
    private By newCommentField = By.id("comment");
    private By saveCommentButton = By.id("issue-comment-add-submit");
    private By deleteCommentIcon = By.xpath("//span[@class='icon-default aui-icon aui-icon-small aui-iconfont-delete']");
    private By confirmDeletingCommentButton = By.id("comment-delete-submit");


    public boolean checkIsTicketNameIsPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.xpath("//a[@class='issue-link' and @data-issue-key='WEBINAR-11939']"))).isDisplayed();
    }

    public void clickOnTheAddCommentButton() {
        driver.findElement(addCommentButton).click();
    }

    public boolean waitingForSaveCommentButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.xpath("//div[@id='comment-wiki-edit']//a[contains (text(), 'Text')]"))).isDisplayed();
    }

    public void goToTextModeForAddingComment() {
        driver.findElement(textModeButton).click();
    }

    public void typeNewCommentToTheCommentField(String comment) {
        driver.findElement(newCommentField).sendKeys(comment);
    }

    public boolean isSaveButtonIsActive() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.id("issue-comment-add-submit"))).isEnabled();
    }

    public void clickOnTheSaveCommentButton() {
        driver.findElement(saveCommentButton).click();
    }

    public boolean checkIfNewCommentAppeared() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.id("issue-comment-add-submit"))).isEnabled();
    }

    public boolean waitingWhenAddNewCommentFieldWillBeClosed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.id("footer-comment-button"))).isEnabled();
    }

    public boolean checkIfTestCommentAppeared() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.xpath("//div[@class='action-body flooded']//p[contains(text(), 'My random comment')]"))).isDisplayed();
    }

    public void clickOnTheDeleteCommentIcon() {
        driver.findElement(deleteCommentIcon).click();
    }

    public boolean isDeleteConfirmationPopUpIsOpen() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.xpath("//h2['Delete Comment']"))).isDisplayed();
    }

    public void confirmCommentDelete() {
        driver.findElement(confirmDeletingCommentButton).click();
    }

    public boolean checkSuccessDeleteMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.xpath("//div[@class='aui-message closeable aui-message-success aui-will-close']"))).isDisplayed();
    }

    public boolean checkIfCommentIsNotExists() {
        return driver.findElements(By.xpath("//div[@class='action-body flooded']//p[contains(text(), 'My random comment')]")).isEmpty();
    }


}
