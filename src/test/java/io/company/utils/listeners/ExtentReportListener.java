package io.company.utils.listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.company.utils.logging.Loggable;
import io.company.utils.reports.ExtentManager;
import io.company.utils.reports.ExtentTestReport;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * {@link ITestListener} implementation responsible for the test result reports.
 */
public class ExtentReportListener implements ITestListener, Loggable {

    @Override
    public void onFinish(ITestContext iTestContext) {
        logger().debug(iTestContext.getName() + " finished");
        ExtentManager.getInstance().flush();
        ExtentTestReport.removeTest();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        logger().debug("Test {} starting", iTestResult.getName());
        ExtentTestReport.createTest(iTestResult.getInstanceName(), iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger().debug("Test {} passed", iTestResult.getName());
        ExtentTestReport.getTest().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger().error("Test {} failed", iTestResult.getName());

        ExtentTestReport.getTest().fail(MarkupHelper.createLabel(iTestResult.getThrowable().getMessage(), ExtentColor.RED));

        try {
            ExtentTestReport
                    .getTest()
                    .fail("Screenshot - ", MediaEntityBuilder
                            .createScreenCaptureFromBase64String(getScreenshotBase64())
                            .build());
        } catch (IOException e) {
            logger().error(e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger().debug("Test {} skipped", iTestResult.getName());
        ExtentTestReport.getTest().skip("Test Skipped");
    }

    private String getScreenshotBase64() {
        return "data:image/png;base64," + ((TakesScreenshot) getWebDriver())
                .getScreenshotAs(OutputType.BASE64);
    }
}
