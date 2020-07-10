package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver = null;

   // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }



    private By userNameField = By.id("login-form-username");
    private By loginField = By.id("login-form-password");
    private By loginButton = By.id("login");


    public void openTestPage(){
        driver.get("https://jira.hillel.it/secure/Dashboard.jspa");
    }

    public void enterUserName(String name){
        driver.findElement(userNameField).sendKeys(name);
    }

    public void enterUserPassword(String password){
        driver.findElement(loginField).sendKeys(password);
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }





}
