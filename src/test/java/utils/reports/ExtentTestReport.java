package utils.reports;

import com.relevantcodes.extentreports.ExtentTest;
import utils.listeners.ExtentReportListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Extent reporter to be used by the {@link ExtentReportListener}.
 */
public class ExtentTestReport {

    /**
     * Holds the extent tests and corresponding threads in which they were executed.
     */
    private static Map<Long, ExtentTest> extentTestMap = new HashMap<>();

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get(Thread.currentThread().getId());
    }

    public static synchronized void endTest() {
        ExtentManager.getInstance().endTest(extentTestMap.get(Thread.currentThread().getId()));
    }

    public static synchronized void startTest(String testName, String desc) {
        ExtentTest test = ExtentManager.getInstance().startTest(testName, desc);
        extentTestMap.put(Thread.currentThread().getId(), test);
    }
}
