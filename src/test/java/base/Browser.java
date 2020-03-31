package base;

import org.openqa.selenium.WebDriver;

/**
 * Holds actions that a user is able to perform on a browser.
 * <p>
 * Browser actions are performed through {@link DriverContext}
 * e.g. {@link DriverContext#getBrowser().action()}
 * </p>
 */
public final class Browser {

    /**
     * Webdriver instance which the browser is using.
     */
    private WebDriver driver;

    public Browser(WebDriver driver) {
        this.driver = driver;
    }

    public void maximize() {
        driver.manage().window().maximize();
    }
}
