package tests;

import org.testng.annotations.AfterMethod;
import ui.automation.bootstrap.base.DriverContext;
import ui.automation.bootstrap.base.FrameworkBootstrap;

public abstract class BaseTest extends FrameworkBootstrap {

    @AfterMethod
    public void teardown() {
        DriverContext.getRemoteWebDriver().quit();
    }
}
