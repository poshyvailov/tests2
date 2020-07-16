package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class LoginPage {

    WebDriver driver = null;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


    private By userNameField = By.id("login-form-username");
    private By loginField = By.id("login-form-password");
    private By loginButton = By.id("login");


    public void openTestPage() {
        driver.get("https://jira.hillel.it/secure/Dashboard.jspa");
    }

    public void waitUntilLoginPageWillBeOpen(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        wait.until(presenceOfElementLocated(By.id("login-form-username"))).isDisplayed();

    }

    public void enterUserName(String name) {
        driver.findElement(userNameField).sendKeys(name);
    }

    public void enterUserPassword(String password) {
        driver.findElement(loginField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public boolean waitUntilDashboardWillBeOpen() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.xpath("//*[@id='dashboard-content']//h1"))).isDisplayed();
    }

    public boolean isErrorsMessageIsShown(String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.xpath("//*[contains(text(),'" + errorMessage + "')]"))).isDisplayed();
    }


}
