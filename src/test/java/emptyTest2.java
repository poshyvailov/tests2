import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class emptyTest2 {

    @BeforeMethod
    public void setUpSteps(){
        System.out.println("BeforeMethod from emptyTest2 is working now");

    }

    @Test
    public void emptyTestSteps(){
        assert 1!=0;
        System.out.println("Test method from emptyTest2 is working now");


    }

    @AfterMethod
    public void tearDown(){
        System.out.println("AfterMethod from emptyTest2 is working now");

    }
}
