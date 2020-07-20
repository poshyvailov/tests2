import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EmptyTest1 {

    @BeforeMethod
    public void setUpSteps(){
        System.out.println("BeforeMethod from EmptyTest1 is working now");

    }

    @Test
    public void emptyTestSteps(){
        assert 1==1;
        System.out.println("Test method from EmptyTest1 is working now");

    }

    @AfterMethod
    public void tearDown(){
        System.out.println("AfterMethod from EmptyTest1 is working now");

    }
}
