package common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class ExecutionListener implements ITestListener,IInvokedMethodListener,IClassListener,ISuiteListener{

    private TestStatus testStatus;
    protected WebDriver driver;
    private String browserName;

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutionListener.class);

    private void sendStatus(ITestResult iTestResult, String status){
        this.testStatus.setTestClass(iTestResult.getTestClass().getName());
        this.testStatus.setStatus(status);
        this.testStatus.setExecutionTime(LocalDateTime.now().toString());
        this.testStatus.setBrowser(browserName);
        ResultMapper.send(this.testStatus);
    }

    @Override
    public void onTestStart(ITestResult result) {
        this.testStatus = new TestStatus();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        this.sendStatus(result,"PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        this.sendStatus(result,"FAIL");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        this.sendStatus(result,"SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }

    @Override
    public void onBeforeClass(ITestClass testClass) {
        LOGGER.info("Started execution of test class :" + testClass.getRealClass().getSimpleName()
                + " with Thread Id: " + Thread.currentThread().getId());
        LOGGER.info("testClass.getTestName():"+testClass.getTestMethods().getClass().getSimpleName());
        LOGGER.info("testClass.getTestMethods():"+testClass.getTestMethods());
    }

    @Override
    public void onAfterClass(ITestClass testClass) {

    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

        browserName =method.getTestMethod().getXmlTest().getLocalParameters().get("browser");
        driver = DriverFactory.getBrowserInstance(browserName);
        DriverManager.setWebDriver(driver);

        try {
            DriverManager.getDriver().get(CommonHelper.getPropData("url"));
        } catch (Exception ex) {
            LOGGER.error(ex+"WRONG URL!!");
        }
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
    public void onStart(ISuite suite) {

    }

    @Override
    public void onFinish(ISuite suite) {

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
