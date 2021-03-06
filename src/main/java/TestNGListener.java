import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class TestNGListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("onTestStart method from listener is working now");
        String browserName = result.getTestContext().getCurrentXmlTest().getParameter("browserName");
        //System.out.println("Browser name from listener is " + browserName);
        //WebDriverFactory.createInstance(browserName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("onTestSuccess method from listener is working now");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("onTestFailure method from listener is working now");
        File screenshotsFolder = new File(System.getProperty("user.dir") + "/screenshots");

        if (!screenshotsFolder.exists()) {
            screenshotsFolder.mkdir();
        }

        File screenshot = captureScreenshot();
        Path pathToScreenShot = Paths.get(screenshot.getPath());
        try {
            String screenshotName = screenshotsFolder + "/" + "Screenshot_" + java.time.LocalTime.now() + ".png";
            Files.copy(pathToScreenShot, Paths.get(screenshotName), StandardCopyOption.COPY_ATTRIBUTES);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("onTestSkipped method from listener is working now");

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("onTestFailedButWithinSuccessPercentage method from listener is working now");

    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("onStart method from listener is working now");
    }


    @Override
    public void onFinish(ITestContext context) {
        System.out.println("onFinish method from listener is working now");

    }

    private File captureScreenshot() {
        System.out.println("captureScreenshot method from listener is working now");
        return ((TakesScreenshot) WebDriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
    }
}
