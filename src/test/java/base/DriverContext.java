package base;

import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Holds methods that initialize (and teardown) the required driver; locally or remotely.
 * Also, an instance to the {@link Browser} in which browser actions can be performed.
 * <p>
 * Browser actions are performed through {@link DriverContext}
 * e.g. {@link DriverContext#getBrowser().action()}
 * </p>
 */
public final class DriverContext {

    /**
     * Browser instance which is going to receive the webdriver.
     */
    private static Browser browser;

    private DriverContext() {
    }

    public static void initializeRemoteWebDriver(BrowserType browserType,
                                                 boolean runTestsLocal,
                                                 String remoteWebDriverUrl) {
        RemoteWebDriver driver;
        switch (browserType) {
            case chrome:
                driver = setupChromeDriver(runTestsLocal, remoteWebDriverUrl);
                break;
            case firefox:
                driver = setupFirefoxDriver(runTestsLocal, remoteWebDriverUrl);
                break;
            default:
                throw new UnsupportedOperationException("The required browser is not supported");
        }

        WebDriverRunner.setWebDriver(driver);
        browser = new Browser(DriverContext.getRemoteWebDriver());
    }

    public static void quitRemoteWebDriver() {
        getWebDriver().quit();
    }

    public static WebDriver getRemoteWebDriver() {
        return getWebDriver();
    }

    public static Browser getBrowser() {
        return browser;
    }

    private static RemoteWebDriver setupChromeDriver(boolean runTestsLocal, String remoteWebDriverUrl) {
        WebDriverManager.chromedriver().setup();
        RemoteWebDriver driver = null;
        if (runTestsLocal) {
            driver = new ChromeDriver();
        } else {
            try {
                Capabilities capabilities = new ChromeOptions();
                driver = new RemoteWebDriver(new URL(remoteWebDriverUrl), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    private static RemoteWebDriver setupFirefoxDriver(boolean runTestsLocal, String remoteWebDriverUrl) {
        WebDriverManager.firefoxdriver().setup();
        RemoteWebDriver driver = null;
        if (runTestsLocal) {
            driver = new FirefoxDriver();
        } else {
            try {
                Capabilities capabilities = new FirefoxOptions();
                driver = new RemoteWebDriver(new URL(remoteWebDriverUrl), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }
}
