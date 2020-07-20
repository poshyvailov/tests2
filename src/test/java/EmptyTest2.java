import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class EmptyTest2 {

    @BeforeMethod
    public void setUpSteps(){
        System.out.println("BeforeMethod from EmptyTest2 is working now");

    }

    @Test
    public void emptyTestSteps(){
        assert 1==0;
        System.out.println("Test method from EmptyTest2 is working now");


    }

    @AfterMethod
    public void tearDown(){
        System.out.println("AfterMethod from EmptyTest2 is working now");
    }
}
