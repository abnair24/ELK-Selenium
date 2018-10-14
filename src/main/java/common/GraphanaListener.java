package common;

import org.apache.commons.io.FileUtils;
import org.influxdb.dto.Point;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GraphanaListener implements ITestListener,IInvokedMethodListener,IClassListener,ISuiteListener {

    private TestStatus testStatus;
    protected WebDriver driver;
    private String browserName;

    private static final Logger LOGGER = LoggerFactory.getLogger(GraphanaListener.class);

    private void sendTestMethodStatus(ITestResult iTestResult, String status) {
        Point point = Point.measurement("testmethod")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("testclass", iTestResult.getTestClass().getName())
                .tag("name", iTestResult.getName())
                .tag("result", status)
                .addField("duration", (iTestResult.getEndMillis() - iTestResult.getStartMillis()))
                .build();
        GraphanaSender.send(point);
    }

    private void sendTestClassStatus(ITestContext iTestContext) {
        Point point = Point.measurement("testclass")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("name", iTestContext.getAllTestMethods()[0].getTestClass().getName())
                .addField("duration", (iTestContext.getEndDate().getTime() - iTestContext.getStartDate().getTime()))
                .build();
        GraphanaSender.send(point);
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

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        this.sendTestMethodStatus(result, "PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        this.sendTestMethodStatus(result, "FAIL");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        this.sendTestMethodStatus(result, "SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        this.sendTestClassStatus(context);
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
