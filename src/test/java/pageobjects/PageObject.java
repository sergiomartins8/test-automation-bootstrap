package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.DriverContext;
import utils.constants.Constants;
import utils.logging.Loggable;

/**
 * Abstraction that represents the definition of a page object
 * Contains methods that are reusable by any page object.
 *
 * @param <T> represents the type of the concrete page object.
 */
public abstract class PageObject<T extends PageObject<T>> implements Loggable {

    /**
     * a {@link FluentWait} for waiting util methods to be shared across page objects.
     */
    private static WebDriverWait wait;

    public PageObject() {
        wait = new WebDriverWait(DriverContext.getRemoteWebDriver(), Constants.TIME_OUT_SECONDS);
    }

    public T waitPageLoaded() {
        wait.until(webDriver -> ((JavascriptExecutor) DriverContext.getRemoteWebDriver())
                .executeScript("return document.readyState")
                .equals("complete"));
        return (T) this;
    }
}
