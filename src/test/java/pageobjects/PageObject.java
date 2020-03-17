package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.DriverContext;
import utils.constants.Constants;
import utils.logging.Loggable;

public abstract class PageObject<T extends PageObject<T>> implements Loggable {

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
