package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.automation.bootstrap.base.DriverContext;
import ui.automation.bootstrap.utils.constants.Constants;
import ui.automation.bootstrap.utils.logging.Loggable;

public abstract class BasePage implements Loggable {

    public void waitPageLoaded() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverContext.getRemoteWebDriver();
        boolean isPageLoaded = jsExecutor.executeScript("return document.readyState").toString().equals("complete");

        if (!isPageLoaded) {
            WebDriverWait wait = new WebDriverWait(DriverContext.getRemoteWebDriver(), Constants.TIME_OUT_SECONDS);
            wait.until(webDriver -> jsExecutor.executeScript("return document.readyState").toString().equals("complete"));
        }
    }
}
