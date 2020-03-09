package ui.automation.bootstrap.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.automation.bootstrap.utils.constants.Constants;

public final class DriverContext {

    private static ThreadLocal<RemoteWebDriver> remoteWebDriverThreadLocal = new ThreadLocal<>();

    public static Browser browser;

    public static void setDriver(RemoteWebDriver driverThreadLocal) {
        remoteWebDriverThreadLocal.set(driverThreadLocal);
    }

    public static RemoteWebDriver getRemoteWebDriver() {
        return remoteWebDriverThreadLocal.get();
    }

    public static void waitPageIsLoaded() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getRemoteWebDriver();
        boolean isPageLoaded = jsExecutor.executeScript("return document.readyState").toString().equals("complete");

        if (!isPageLoaded) {
            WebDriverWait wait = new WebDriverWait(getRemoteWebDriver(), Constants.TIME_OUT_SECONDS);
            wait.until(webDriver -> jsExecutor.executeScript("return document.readyState").toString().equals("complete"));
        }
    }
}
