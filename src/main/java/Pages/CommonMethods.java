package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods {

    WebDriver driver = null;

    public CommonMethods(WebDriver driver) {
        this.driver = driver;
    }


    private void clickOnElementWithRetry(By elementToBeClicked, By successCriteria, int attempts, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        for (int i = 0; i < attempts; i++) {
            try{
                wait.until(ExpectedConditions.visibilityOfElementLocated(successCriteria)).isDisplayed();
                break;
            }   catch (TimeoutException e){
                wait.until(ExpectedConditions.elementToBeClickable(elementToBeClicked));
                driver.findElement(elementToBeClicked).click();
            }
        }

    }


}
