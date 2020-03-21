package utils.reports;

import com.relevantcodes.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;


public class ExtentTestReport {

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
