import Pages.DragAndDropDemoPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertTrue;

public class SwipeBoxes {

    WebDriver driver;
    DragAndDropDemoPage dragAndDropDemoPage;


    @BeforeMethod
    public void setUp(){
        WebDriverFactory.createInstance("Chrome");
        driver = WebDriverFactory.getDriver();
        dragAndDropDemoPage = new DragAndDropDemoPage(WebDriverFactory.getDriver());
        driver.manage().window().maximize();
    }

    @Test
    public void DragAndDropTest(){
        dragAndDropDemoPage.openDragAndDropTestPage();
        assertTrue(dragAndDropDemoPage.checkIfDemoPageIsOpen());
        //Проверяем что наш большой круг ( зона куда будем бросать маленький круг ) не содержит, сейчас, в себе маленький круг
        assertTrue(dragAndDropDemoPage.checkIsBigCircleDoesntContainLittleCircleInside());
        //Проверяем что маленький круг ( элемент,который будем перетаскивать ) - существует
        assertTrue(dragAndDropDemoPage.checkIsSmallCircleExists());
        //Перетаскиваем маленький круг в большой круг
        dragAndDropDemoPage.moveSmallCircleToTheBigCircle();
        //Проверяем что наш большой круг содержит в себе маленький
        assertTrue(dragAndDropDemoPage.checkIfSmallCircleInsideTheBIgCircle());
    }

    @AfterMethod
    public void tearsDown(){
        driver.quit();
    }
}
