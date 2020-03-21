package utils.listeners;

import base.DriverContext;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.logging.Loggable;
import utils.reports.ExtentManager;
import utils.reports.ExtentTestReport;

public class ExtentReportListener implements ITestListener, Loggable {

    @Override
    public void onStart(ITestContext iTestContext) {
        logger().debug("<onStart> method: " + iTestContext.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        logger().debug("<onTestStart> method: " + getTestMethodName(iTestResult));
        ExtentTestReport.startTest(iTestResult.getInstanceName(), iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger().debug("<onTestSuccess> method: " + getTestMethodName(iTestResult));
        ExtentTestReport.getTest().log(LogStatus.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger().error("<onTestFailure> method: " + getTestMethodName(iTestResult));

        // Takes a base64Screenshot screenshot
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) DriverContext.getRemoteWebDriver())
                .getScreenshotAs(OutputType.BASE64);

        // ExtentReports log and screenshot for failed tests
        ExtentTestReport.getTest()
                .log(LogStatus.FAIL,
                        iTestResult.getMethod().getDescription(),
                        ExtentTestReport.getTest().addBase64ScreenShot(base64Screenshot));
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        logger().debug("<onFinish> method: " + iTestContext.getName());
        ExtentTestReport.endTest();
        ExtentManager.getInstance().flush();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger().debug("<onTestSkipped> method " + getTestMethodName(iTestResult) + " skipped");
        ExtentTestReport.getTest().log(LogStatus.SKIP, "Test Skipped");
    }

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

}
