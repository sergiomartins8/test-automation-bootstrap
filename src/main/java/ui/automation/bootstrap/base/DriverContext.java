package ui.automation.bootstrap.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public final class DriverContext {

    private static ThreadLocal<RemoteWebDriver> remoteWebDriverThreadLocal = new ThreadLocal<>();

    public static Browser browser;

    private DriverContext() {
    }

    public static void initializeRemoteWebDriver(BrowserType browserType, boolean runTestsLocal, String remoteWebDriverUrl) {
        RemoteWebDriver driver;

        switch (browserType) {
            case chrome: {
                driver = setupChromeDriver(runTestsLocal, remoteWebDriverUrl);
                break;
            }
            case firefox: {
                driver = setupFirefoxDriver(runTestsLocal, remoteWebDriverUrl);
                break;
            }
            default:
                throw new UnsupportedOperationException("The current required browser implementation is not ready yet.");
        }

        remoteWebDriverThreadLocal.set(driver);
        browser = new Browser(DriverContext.getRemoteWebDriver());
    }

    public static void quitRemoteWebDriver() {
        remoteWebDriverThreadLocal.get().quit();
    }

    public static RemoteWebDriver getRemoteWebDriver() {
        return remoteWebDriverThreadLocal.get();
    }

    private static RemoteWebDriver setupChromeDriver(boolean runTestsLocal, String remoteWebDriverUrl) {
        WebDriverManager.chromedriver().setup();
        RemoteWebDriver driver = null;
        if (runTestsLocal) {
            driver = new ChromeDriver();
        } else
            try {
                Capabilities capabilities = new ChromeOptions();
                driver = new RemoteWebDriver(new URL(remoteWebDriverUrl), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        return driver;
    }

    private static RemoteWebDriver setupFirefoxDriver(boolean runTestsLocal, String remoteWebDriverUrl) {
        WebDriverManager.firefoxdriver().setup();
        RemoteWebDriver driver = null;
        if (runTestsLocal) {
            driver = new FirefoxDriver();
        } else
            try {
                Capabilities capabilities = new FirefoxOptions();
                driver = new RemoteWebDriver(new URL(remoteWebDriverUrl), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        return driver;
    }
}
