package common;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.LocalDateTime;

public class ExecutionListener implements ITestListener {

    private TestStatus testStatus;

    private void sendStatus(ITestResult iTestResult, String status){
        this.testStatus.setTestClass(iTestResult.getTestClass().getName());
        this.testStatus.setStatus(status);
        this.testStatus.setExecutionTime(LocalDateTime.now().toString());
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

}
