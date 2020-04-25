package ${package}.base;

import org.openqa.selenium.WebDriver;

/**
 * Holds actions that a user is able to perform on a browser.
 * <br>
 * Browser actions are performed through {@link DriverContext}
 * <br>
 * Example: {@code DriverContext.browser.maximize()}
 */
public class Browser {

    private final WebDriver driver;

    public Browser(WebDriver driver) {
        this.driver = driver;
    }

    public void maximize() {
        driver.manage().window().maximize();
    }
}
