package utils.reports;


import com.relevantcodes.extentreports.ExtentReports;

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
            extentReports = new ExtentReports("reports/ExtentReportResults.html", true);
        }
        return extentReports;
    }
}
