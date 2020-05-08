package ${package}.base;

import com.codeborne.selenide.Configuration;
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
 * Holds methods that initialize and teardown the required driver; locally or remotely.
 * <br>
 * Also, an instance to the {@link Browser} in which browser actions can be performed
 */
public final class DriverContext {

    /**
     * Browser instance.
     */
    public static Browser browser;

    /**
     * Private constructor to avoid instantiation.
     */
    private DriverContext() {
    }

    /**
     * Initializes the webdriver accordingly to the requested browser.
     *
     * @throws UnsupportedOperationException in case of the required browser is not covered within the current switch case.
     */
    public static void initializeRemoteWebDriver() {
        RemoteWebDriver driver;
        switch (Configuration.browser) {
            case "chrome":
                driver = setupChromeDriver();
                break;
            case "firefox":
                driver = setupFirefoxDriver();
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

    private static RemoteWebDriver setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
        RemoteWebDriver driver = null;
        if (Configuration.remote == null) {
            driver = new ChromeDriver();
        } else {
            try {
                Capabilities capabilities = new ChromeOptions();
                driver = new RemoteWebDriver(new URL(Configuration.remote), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    private static RemoteWebDriver setupFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        RemoteWebDriver driver = null;
        if (Configuration.remote == null) {
            driver = new FirefoxDriver();
        } else {
            try {
                Capabilities capabilities = new FirefoxOptions();
                driver = new RemoteWebDriver(new URL(Configuration.remote), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }
}
