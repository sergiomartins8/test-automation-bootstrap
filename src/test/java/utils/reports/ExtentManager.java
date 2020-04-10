package utils.reports;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

/**
 * Singleton ExtentManager.
 */
public class ExtentManager {

    /**
     * Extent report instance.
     */
    private static ExtentReports extentReports;

    public static synchronized ExtentReports getInstance() {
        if (extentReports == null) {
            extentReports = new ExtentReports();
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/reports/ExtentReport.html");
            htmlReporter.loadXMLConfig("extent-config.xml");
            extentReports.attachReporter(htmlReporter);

            // General information related to application
            extentReports.setSystemInfo("Application Name", "ui automation bootstrap");
            extentReports.setSystemInfo("User Name", "http://github.com/sergiomartins8");
            extentReports.setSystemInfo("Environment", "Production");
        }
        return extentReports;
    }
}
