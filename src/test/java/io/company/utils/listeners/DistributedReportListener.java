package io.company.utils.listeners;

import com.codeborne.selenide.Configuration;
import io.company.utils.config.CustomConfiguration;
import io.company.utils.logging.Loggable;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class DistributedReportListener implements ITestListener, Loggable {

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger().info("Pipeline: {} Browser: {} Status: {} Test: {} Suite: {}",
                CustomConfiguration.pipelineID, Configuration.browser, "passed", iTestResult.getName(), System.getProperty("suite"));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger().error("Pipeline: {} Browser: {} Status: {} Test: {} Suite: {} Trace: {}",
                CustomConfiguration.pipelineID, Configuration.browser, "failed", iTestResult.getName(), System.getProperty("suite"),
                iTestResult.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger().info("Pipeline: {} Browser: {} Status: {} Test: {} Suite: {}",
                CustomConfiguration.pipelineID, Configuration.browser, "skipped", iTestResult.getName(), System.getProperty("suite"));
    }
}