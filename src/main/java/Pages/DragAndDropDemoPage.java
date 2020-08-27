package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class DragAndDropDemoPage {


    WebDriver driver = null;

    public DragAndDropDemoPage(WebDriver driver) {
        this.driver = driver;
    }


    public void openDragAndDropTestPage() {
        driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
    }


    public boolean checkIfDemoPageIsOpen() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.xpath("//a[@title='Drag and Drop']"))).isDisplayed();
    }

    public boolean checkIsBigCircleDoesntContainLittleCircleInside() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.xpath("//div[@id='droptarget' and contains (text(), 'Drag the small circle here.')]"))).isDisplayed();
    }

    public boolean checkIsSmallCircleExists() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.id("draggable"))).isDisplayed();
    }

    public void moveSmallCircleToTheBigCircle() {
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droptarget"));
        Actions action = new Actions(driver);
        action.dragAndDrop(source, target).build().perform();
    }

    public boolean checkIfSmallCircleInsideTheBIgCircle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        return wait.until(presenceOfElementLocated(By.xpath("//div[@id='droptarget' and contains (text(), 'You did great!')]"))).isDisplayed();
    }

}
