package ui.automation.bootstrap.base;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;
import ui.automation.bootstrap.utils.configs.Config;
import ui.automation.bootstrap.utils.configs.ConfigReader;
import ui.automation.bootstrap.utils.logging.Loggable;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class FrameworkBootstrap implements Loggable {

    private Config config;

    @BeforeSuite
    public void initializeFramework() {
        config = ConfigReader.populateConfigs();
        initializeBrowser();
        initializeSelenide();
    }

    private void initializeBrowser() {
        RemoteWebDriver driver;

        switch (config.getBrowserType()) {
            case chrome: {
                driver = setupChromeDriver(config.isRunTestsLocal());
                break;
            }
            case firefox: {
                driver = setupFirefoxDriver(config.isRunTestsLocal());
                break;
            }
            default:
                throw new UnsupportedOperationException("The current required browser implementation is not ready yet.");
        }

        DriverContext.setDriver(driver);
        DriverContext.browser = new Browser(driver);
    }

    private RemoteWebDriver setupChromeDriver(boolean isRunTestsLocal) {
        WebDriverManager.chromedriver().setup();
        RemoteWebDriver driver = null;
        if (isRunTestsLocal) {
            driver = new ChromeDriver();
        } else
            try {
                Capabilities capabilities = new ChromeOptions();
                driver = new RemoteWebDriver(new URL(config.getRemoteWebDriverUrl()), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        return driver;
    }

    private RemoteWebDriver setupFirefoxDriver(boolean isRunTestsLocal) {
        WebDriverManager.firefoxdriver().setup();
        RemoteWebDriver driver = null;
        if (isRunTestsLocal) {
            driver = new FirefoxDriver();
        } else
            try {
                Capabilities capabilities = new FirefoxOptions();
                driver = new RemoteWebDriver(new URL(config.getRemoteWebDriverUrl()), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        return driver;
    }

    private void initializeSelenide() {
        Configuration.baseUrl = config.getBaseUrl();
        Configuration.screenshots = config.takeScreenshots();
        Configuration.headless = config.isHeadless();
    }
}
