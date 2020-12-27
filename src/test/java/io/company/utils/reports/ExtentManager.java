package io.company.utils.reports;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {

    /**
     * Extent report instance.
     */
    private static ExtentReports extentReports;

    /**
     * Private constructor to avoid instantiation.
     */
    private ExtentManager() {
    }

    /**
     * Call this method to get the extent report singleton instance.
     *
     * @return extent reports instance
     */
    public static synchronized ExtentReports getInstance() {
        if (extentReports == null) {
            extentReports = new ExtentReports();
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/reports/ExtentReport.html");
            htmlReporter.loadXMLConfig("reports/extent-config.xml");
            extentReports.attachReporter(htmlReporter);

            // General information related to application
            extentReports.setSystemInfo("Application Name", "test automation bootstrap");
            extentReports.setSystemInfo("User Name", "http://github.com/sergiomartins8");
            extentReports.setSystemInfo("Environment", "Production");
        }
        return extentReports;
    }
}
