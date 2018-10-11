package common;

import common.CommonHelper;
import common.DriverFactory;
import common.DriverManager;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;

import java.io.File;
import java.io.IOException;

public class MethodListener implements IInvokedMethodListener,IClassListener,ISuiteListener{

    protected WebDriver driver;

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodListener.class);

    @Override
    public void onStart(ISuite suite) {

    }

    @Override
    public void onFinish(ISuite suite) {
        LOGGER.info("Flushing... Extent Report");

    }

    @Override
    public void onBeforeClass(ITestClass testClass) {
        LOGGER.info("Started execution of test class :" + testClass.getRealClass().getSimpleName()
                + " with Thread Id: " + Thread.currentThread().getId());
        LOGGER.info("testClass.getTestName():"+testClass.getTestMethods().getClass().getSimpleName());
        LOGGER.info("testClass.getTestMethods():"+testClass.getTestMethods());


    }


    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browser");
        driver = DriverFactory.getBrowserInstance(browserName);
        DriverManager.setWebDriver(driver);

        try {
            DriverManager.getDriver().get(CommonHelper.getPropData("url"));
        } catch (Exception ex) {
            LOGGER.error(ex + "WRONG URL!!");
        }
        LOGGER.info("");
        LOGGER.info("");
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {

            try {
                String screenshot = captureScreenshot(CommonHelper.timeStamp(testResult.getName() + "_"),driver);

            } catch (IOException ex) {
                LOGGER.error(ex + "FAILED Capturing Screenshot");
            }
        }

        else if (testResult.getStatus() == ITestResult.SKIP) {
        }

        else {

        }

        if (DriverManager.getDriver() != null) {
            LOGGER.info("Finished execution of test :" + method.getTestMethod().getMethodName()
                    + ":with Thread Id: " + Thread.currentThread().getId());
            DriverManager.getDriver().quit();
        }
    }

    @Override
    public void onAfterClass(ITestClass testClass) {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
        }
        DriverManager.cleanup();
    }

    public String captureScreenshot(String screenshotName, WebDriver driver) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = "./ErrorScreenshot/" + screenshotName + ".png";
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);

        return dest;
    }
}
