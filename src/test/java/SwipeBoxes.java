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
        //Проверяем что изначально левый квадрат содержит в себе элемент А
        assertTrue(dragAndDropDemoPage.checkIsLeftBoxContainA());
        //Проверяем что изначально правй квадрат содержит в себе элемент B
        assertTrue(dragAndDropDemoPage.checkIsRightBoxContainB());
        //перемещаем левый квадрат в правый
        dragAndDropDemoPage.moveLeftBoxToTheRightBox();
        //Проверяем что теперь значение В хранится в левом квадрате
        assertTrue(dragAndDropDemoPage.checkIfRightBoxContainsASign());

    }

    @AfterMethod
    public void tearsDown(){
        driver.quit();
    }
}
