package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class DragAndDropDemoPage {


    WebDriver driver = null;

    public DragAndDropDemoPage(WebDriver driver) {
        this.driver = driver;
    }


    private By leftBoxSource = By.id("column-a");
    private By rightBoxTarget = By.id("column-b");

//   private WebElement source = driver.findElement(By.id("column-a"));
//   private WebElement target = driver.findElement(By.id("column-b"));


    public void openDragAndDropTestPage() {
        driver.get("http://the-internet.herokuapp.com/drag_and_drop");
    }


    public boolean checkIfDemoPageIsOpen() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.xpath("//h3[contains (text(), 'Drag and Drop')]"))).isDisplayed();
    }

    public boolean checkIsLeftBoxContainA() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.xpath("//div[@id='column-a']/header[contains(text(),'A')]"))).isDisplayed();
    }

    public boolean checkIsRightBoxContainB() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.xpath("//div[@id='column-b']/header[contains(text(),'B')]"))).isDisplayed();
    }

    public void moveLeftBoxToTheRightBox() {
        Actions action = new Actions(driver);
        action.dragAndDrop(leftBoxSource, rightBoxTarget).build().perform();
    }

    public boolean checkIfRightBoxContainsASign() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(invisibilityOfElementLocated(By.xpath("//div[@id='column-a']/header[contains(text(),'B')]")));
    }

}
