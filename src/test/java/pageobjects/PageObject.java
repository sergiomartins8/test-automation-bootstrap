package pageobjects;

import base.DriverContext;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.constants.Constants;
import utils.logging.Loggable;

/**
 * Abstraction that represents the definition of a page object.
 * <br>
 * Contains methods that are reusable by any page object
 *
 * @param <T> represents the type of the concrete page object
 */
public abstract class PageObject<T extends PageObject<T>> implements Loggable {

    private static WebDriverWait wait;

    public PageObject() {
        wait = new WebDriverWait(DriverContext.getRemoteWebDriver(), Constants.TIME_OUT_SECONDS);
    }

    /**
     * Waits the page to be fully loaded.
     *
     * @return concrete page object of type <b>T</b>, after waiting for the page to be loaded
     */
    public T waitPageLoaded() {
        wait.until(webDriver -> ((JavascriptExecutor) DriverContext.getRemoteWebDriver())
                .executeScript("return document.readyState")
                .equals("complete"));
        return (T) this;
    }
}
