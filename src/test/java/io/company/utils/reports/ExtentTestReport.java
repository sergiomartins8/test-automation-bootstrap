package io.company.utils.reports;

import com.aventstack.extentreports.ExtentTest;
import io.company.utils.listeners.ExtentReportListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Extent reporter to be used by the {@link ExtentReportListener}.
 */
public class ExtentTestReport {

    /**
     * Holds the extent tests and corresponding threads in which they were executed.
     */
    private static final Map<Long, ExtentTest> EXTENT_TEST_MAP = new HashMap<>();

    public static synchronized ExtentTest getTest() {
        return EXTENT_TEST_MAP.get(Thread.currentThread().getId());
    }

    public static synchronized void removeTest() {
        ExtentManager.getInstance().removeTest(EXTENT_TEST_MAP.get(Thread.currentThread().getId()));
    }

    public static synchronized void createTest(String testName, String desc) {
        ExtentTest test = ExtentManager.getInstance().createTest(testName, desc);
        EXTENT_TEST_MAP.put(Thread.currentThread().getId(), test);
    }
}
